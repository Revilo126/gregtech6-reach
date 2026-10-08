package extech.tileentity.multiblock;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.data.TD;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.util.UT;
import multihelper.structure.Definition;
import multihelper.structure.elements.IStructureElement;
import multihelper.tile.multiblock.TileEntityBase10MultiBlockBaseMH;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static gregapi.data.CS.*;
import static multihelper.structure.StructureUtil.*;

public class MultiTileEntityIntronicUltraCapacitor extends TileEntityBase10MultiBlockBaseMH<MultiTileEntityIntronicUltraCapacitor> implements ITileEntityEnergy, ITileEntityEnergyDataCapacitor, ITileEntityProgress, ITileEntityRunningActively {
    public boolean mEmitsEnergy = F, mStopped = F, mActive = F;
    public long mEnergy = 0, mInput = 32, mOutput = 32;
    public byte mActiveState = 0, mMode = 0;
    public TagData mEnergyType = TD.Energy.QU;
    public TagData mEnergyTypeOut = TD.Energy.QU;

    public long mCapacity = 0, mScratch = 0;
    public long mBatteryCount = 0;
    long maxPackets = Math.max(1, mBatteryCount / 3);

    private static final int MIN_BATTERIES = 6;

    private static final int[] BATTERY_IDS = { 0, 0, 0 };
    private static final long[] CAPACITY_BY_TIER = { 0, 1_024_000L, 4_096_000L, 16_384_000L };

    static String[][] STRUCTURE = {
        { "CCCCC", "C   C", "C   C", "C   C", "CC-CC" },
        { "CIIIC", " BBB ", " BBB ", " BBB ", "CIIIC" },
        { "CIOIC", " BBB ", " BBB ", " BBB ", "CIPIC" },
        { "CIIIC", " BBB ", " BBB ", " BBB ", "CIIIC" },
        { "CCCCC", "C   C", "C   C", "C   C", "CCCCC" },
    };

    public Definition<MultiTileEntityIntronicUltraCapacitor> getStructure() {
        Map<IStructureElement<MultiTileEntityIntronicUltraCapacitor>, Integer> batteries = new LinkedHashMap<>();
        batteries.put(part(1001, getMultiTileEntityRegistryID()), 1);
        //batteries.put(air(), 2);
        //batteries.put(air(), 3);

        return Definition.<MultiTileEntityIntronicUltraCapacitor>builder(STRUCTURE)
            .where('C', part(18015, getGTRegistryID()))
            .where('I', part(18007, getGTRegistryID()))
            .where('O', part(18015, getGTRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_OUT))
            .where('P', part(18015, getGTRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN))
            .where('B', any_of(
                tiered(batteries, (c, tier) -> c.addBattery(tier)),
                part(18015, getGTRegistryID())
            ))
            .build();
    }

    private void addBattery(int tier) {
        mBatteryCount++;

        if (tier < 0 || tier >= CAPACITY_BY_TIER.length)
            throw new IllegalStateException("No capacity defined for battery tier " + tier);
        mScratch += CAPACITY_BY_TIER[tier];

        maxPackets = Math.max(1, mBatteryCount / 3);
    }

    @Override
    protected void resetState() {
        mBatteryCount = 0;
        mScratch = 0;
    }

    @Override
    public void readFromNBT2(NBTTagCompound aNBT) {
        super.readFromNBT2(aNBT);
        mEnergy = aNBT.getLong(NBT_ENERGY);
        if (aNBT.hasKey(NBT_MODE)) mMode = aNBT.getByte(NBT_MODE);
        if (aNBT.hasKey(NBT_ACTIVE_ENERGY)) mEmitsEnergy = aNBT.getBoolean(NBT_ACTIVE_ENERGY);
        if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
        if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
        if (aNBT.hasKey(NBT_INPUT)) mInput = aNBT.getLong(NBT_INPUT);
        if (aNBT.hasKey(NBT_OUTPUT)) mOutput = aNBT.getLong(NBT_OUTPUT);
        if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyType = mEnergyTypeOut = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
        if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyType = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));

    }

    @Override
    public void writeToNBT2(NBTTagCompound aNBT) {
        super.writeToNBT2(aNBT);
        if (mMode != 0) aNBT.setByte(NBT_MODE, mMode);
        UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
        UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
        UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
        UT.NBT.setBoolean(aNBT, NBT_ACTIVE_ENERGY, mEmitsEnergy);
    }

    @Override
    public void onTick2(long aTimer, boolean aIsServerSide) {
        super.onTick2(aTimer, aIsServerSide);
        if (aIsServerSide) {
            mActive = (mEnergy >= mOutput);
            if (mActive && !mStopped) {
                long tPackets = Math.min(mMode == 0 ? maxPackets : Math.min(mMode, maxPackets), mEnergy / mOutput);
                if (tPackets > 0) {
                    long tEmitted = ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyTypeOut, mOutput, tPackets, this);
                    mEmitsEnergy = tEmitted > 0;
                    mEnergy -= mOutput * tEmitted;
                }
            }
        }
    }

    @Override
    public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
        if (mCapacity <= 0) return 0;
        aSize = Math.abs(aSize);
        if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {
            if (aDoInject) overcharge(aSize, aEnergyType);
            return aAmount;
        }
        if (mEnergy >= mCapacity) return 0;
        long tInput = Math.min(mCapacity - mEnergy, aSize * aAmount);
        long tConsumed = Math.min(aAmount, (tInput / aSize) + (tInput % aSize != 0 ? 1 : 0));
        if (aDoInject) mEnergy += tConsumed * aSize;
        return tConsumed;
    }

    @Override
    public String getTileEntityName() {
        return "extech.tileentity.multiblock.intronicultracapacitor";
    }

    @Override public long getEnergySizeInputRecommended(TagData t, byte side) { return mInput; }
    @Override public long getEnergySizeOutputMin(TagData t, byte side)        { return mOutput; }
    @Override public long getEnergySizeOutputRecommended(TagData t, byte side){ return mOutput; }
    @Override public long getEnergySizeOutputMax(TagData t, byte side)        { return mOutput; }
    @Override public long getEnergyStored(TagData t, byte side)   { return mEnergy; }
    @Override public long getEnergyCapacity(TagData t, byte side) { return mCapacity; }
    @Override public boolean isEnergyType(TagData t, byte side, boolean emitting) { return t == (emitting ? mEnergyTypeOut : mEnergyType); }
    @Override public boolean isEnergyCapacitorType(TagData t, byte side) { return t == mEnergyType; }
    @Override public Collection<TagData> getEnergyTypes(byte side) { return new ArrayListNoNulls<>(F, mEnergyType, mEnergyTypeOut); }
    @Override public Collection<TagData> getEnergyCapacitorTypes(byte side) { return mEnergyType.AS_LIST; }

    @Override public long getProgressValue(byte side) { return mEnergy; }
    @Override public long getProgressMax(byte side)   { return mCapacity; }
    @Override public boolean getStateRunningPossible()  { return mEnergy > mOutput; }
    @Override public boolean getStateRunningPassively() { return mActive; }
    @Override public boolean getStateRunningActively()  { return mEmitsEnergy; }

    @Override protected boolean validateExtra() { return mBatteryCount >= MIN_BATTERIES; }
    @Override protected void onStructureValid() {
        mCapacity = mScratch;
        maxPackets = Math.max(1, mBatteryCount / 3);
        if (mEnergy > mCapacity) mEnergy = mCapacity;
    }
    @Override protected void onStructureInvalid() { mCapacity = 0; maxPackets = 1; }
}
