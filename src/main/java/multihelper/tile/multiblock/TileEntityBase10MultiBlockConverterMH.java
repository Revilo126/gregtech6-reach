package multihelper.tile.multiblock;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

import multihelper.structure.logic.ICountedStructure;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ChunkCoordinates;

import gregapi.tileentity.multiblocks.TileEntityBase11MultiBlockConverter;
import multihelper.structure.IStructured;

import java.util.HashMap;
import java.util.Map;

public abstract class TileEntityBase10MultiBlockConverterMH<T extends TileEntityBase10MultiBlockConverterMH<T>>
    extends TileEntityBase11MultiBlockConverter implements IStructured<T>, ICountedStructure {

    protected boolean tSuccess = F;

    @SuppressWarnings("unchecked")
    @Override
    public boolean checkStructure2(ChunkCoordinates aCoordinates, Entity aPlayer, IInventory aInventory) {
        clearCounts();
        resetState();
        seedCounts();

        boolean tSuccess = T;

        if (!check((T) this, aCoordinates, aPlayer, aInventory, getX(), getY(), getZ(), mFacing)) tSuccess = false;
        if (!checkCounts()) tSuccess = false;
        if (!validateExtra()) tSuccess = false;
        if (!checkStructure3(aCoordinates, aPlayer, aInventory)) tSuccess = false;

        if (tSuccess) onStructureValid(); else onStructureInvalid();
        return tSuccess;
    }

    /** Allows custom checks to be done in  */
    public boolean checkStructure3(ChunkCoordinates aCoordinates, Entity aPlayer, IInventory aInventory) { return T; }

    @Override
    public boolean isInsideStructure(int aX, int aY, int aZ) {
        int baseX = getOffsetXN(mFacing);
        int baseY = yCoord;
        int baseZ = getOffsetZN(mFacing);

        return getStructure().isInside(aX, aY, aZ, baseX, baseY, baseZ);
    }

    protected final Map<String, Integer> counts = new HashMap<String, Integer>();

    @Override
    public int getCount(String key) {
        Integer v = counts.get(key);
        return v == null ? 0 : v.intValue();
    }

    @Override
    public void setCount(String key, int value) {
        counts.put(key, value);
    }

    @Override
    public void decCount(String key) {
        setCount(key, getCount(key) - 1);
    }

    @Override
    public void clearCounts() {
        counts.clear();
    }

    public boolean checkCounts() {
        boolean tSuccess = T;
        for (String i : counts.keySet()) {
            if (getCount(i) != 0) tSuccess = F;
        }
        return tSuccess;
    }

    protected void resetState() {}
    protected void seedCounts() {}
    protected boolean validateExtra() { return T; }
    protected void onStructureValid() {}
    protected void onStructureInvalid() {}
}
