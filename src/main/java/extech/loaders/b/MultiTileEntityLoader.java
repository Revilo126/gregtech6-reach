package extech.loaders.b;

import extech.tileentity.multiblock.MultiTileEntityIntronicUltraCapacitor;
import gregapi.block.MaterialMachines;
import gregapi.block.MaterialScoopable;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

import static gregapi.data.CS.*;
import static gregapi.data.CS.F;
import static gregapi.data.CS.TOOL_axe;
import static gregapi.data.CS.TOOL_cutter;
import static gregapi.data.CS.TOOL_pickaxe;
import static gregapi.data.CS.TOOL_scoop;
import static gregapi.data.CS.TOOL_shears;
import static gregapi.data.CS.TOOL_wrench;

public class MultiTileEntityLoader implements Runnable{
    @Override
    public void run() {
        MultiTileEntityRegistry aRegistry = MultiTileEntityRegistry.getRegistry("extech.multitileentity");

        MultiTileEntityBlock
            aMetal      = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "iron"         , Material.iron             , Block.soundTypeMetal, TOOL_pickaxe, 0, 0, 15, F, F)
            , aMetalChips = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "iron"         , Material.iron             , Block.soundTypeMetal, TOOL_shovel , 0, 0, 15, F, F)
            , aMetalWires = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "machine"      , MaterialMachines.instance , Block.soundTypeMetal, TOOL_cutter , 0, 0, 15, F, F)
            , aMachine    = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "machine"      , MaterialMachines.instance , Block.soundTypeMetal, TOOL_wrench , 0, 0, 15, F, F)
            , aWooden     = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "wood"         , Material.wood             , Block.soundTypeWood , TOOL_axe    , 0, 0, 15, F, F)
            , aBush       = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "leaves"       , Material.leaves           , Block.soundTypeGrass, TOOL_axe    , 0, 0, 15, F, F)
            , aStone      = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "rock"         , Material.rock             , Block.soundTypeStone, TOOL_pickaxe, 0, 0, 15, F, F)
            , aWool       = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "cloth"        , Material.cloth            , Block.soundTypeCloth, TOOL_shears , 0, 0, 15, F, F)
            , aTNT        = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "tnt"          , Material.tnt              , Block.soundTypeGrass, TOOL_pickaxe, 0, 0, 15, F, F)
            , aUtilMetal  = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "redstoneLight", Material.redstoneLight    , Block.soundTypeMetal, TOOL_pickaxe, 0, 0, 15, F, F)
            , aUtilStone  = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "redstoneLight", Material.redstoneLight    , Block.soundTypeStone, TOOL_pickaxe, 0, 0, 15, F, F)
            , aUtilWood   = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "redstoneLight", Material.redstoneLight    , Block.soundTypeWood , TOOL_axe    , 0, 0, 15, F, F)
            , aUtilWool   = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "redstoneLight", Material.redstoneLight    , Block.soundTypeCloth, TOOL_shears , 0, 0, 15, F, F)
            // , aHive       = MultiTileEntityBlock.getOrCreate(MD.XT.mID, "rock"         , MaterialScoopable.instance, Block.soundTypeWood , TOOL_scoop  , 0, 0, 15, F, F) // Hive not used
            ;

        OreDictMaterial aMat = MT.NULL;
        Class<? extends TileEntity> aClass = null;


        basic_machines(aRegistry, aMetal, aMetalChips, aMetalWires, aMachine, aWooden, aBush, aStone, aWool, aTNT, aUtilMetal, aUtilStone, aUtilWood, aUtilWool, aMat, aClass);
        multiblocks   (aRegistry, aMetal, aMetalChips, aMetalWires, aMachine, aWooden, aBush, aStone, aWool, aTNT, aUtilMetal, aUtilStone, aUtilWood, aUtilWool, aMat, aClass);
        connectors    (aRegistry, aMetal, aMetalChips, aMetalWires, aMachine, aWooden, aBush, aStone, aWool, aTNT, aUtilMetal, aUtilStone, aUtilWood, aUtilWool, aMat, aClass);
    }

    /** Id range 0 - 1000 */
    public static void basic_machines(MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aMetal, MultiTileEntityBlock aMetalChips, MultiTileEntityBlock aMetalWires, MultiTileEntityBlock aMachine, MultiTileEntityBlock aWooden, MultiTileEntityBlock aBush, MultiTileEntityBlock aStone, MultiTileEntityBlock aWool, MultiTileEntityBlock aTNT, MultiTileEntityBlock aUtilMetal, MultiTileEntityBlock aUtilStone, MultiTileEntityBlock aUtilWood, MultiTileEntityBlock aUtilWool, OreDictMaterial aMat, Class<? extends TileEntity> aClass) {

    }

    /** Id range 1001 - 2000 */
    public static void multiblocks(MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aMetal, MultiTileEntityBlock aMetalChips, MultiTileEntityBlock aMetalWires, MultiTileEntityBlock aMachine, MultiTileEntityBlock aWooden, MultiTileEntityBlock aBush, MultiTileEntityBlock aStone, MultiTileEntityBlock aWool, MultiTileEntityBlock aTNT, MultiTileEntityBlock aUtilMetal, MultiTileEntityBlock aUtilStone, MultiTileEntityBlock aUtilWood, MultiTileEntityBlock aUtilWool, OreDictMaterial aMat, Class<? extends TileEntity> aClass) {
        aClass = MultiTileEntityMultiBlockPart.class;
        aMat = MT.Cr; aRegistry.add("Intronic Capacitor (IV)", "Multiblock Machines", 1001, 17101, aClass, aMat.mToolQuality, 64, aMachine, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 4.0F, NBT_RESISTANCE, 4.0F, NBT_TEXTURE, "introniccapacitor", NBT_DESIGNS, 0));

        // aMat = MT.Cr; aRegistry.add("Intronic Ultra Capacitor", "Multiblock Machines", 1002, 17101, MultiTileEntityIntronicUltraCapacitor.class, aMat.mToolQuality, 64, UT.NBT.make(NBT_MATERIAL, aMat, NBT_HARDNESS, 4.0F, NBT_RESISTANCE, 4.0F, NBT_TEXTURE));
    }

    /** Id range 2001 - 4000 */
    public static void connectors(MultiTileEntityRegistry aRegistry, MultiTileEntityBlock aMetal, MultiTileEntityBlock aMetalChips, MultiTileEntityBlock aMetalWires, MultiTileEntityBlock aMachine, MultiTileEntityBlock aWooden, MultiTileEntityBlock aBush, MultiTileEntityBlock aStone, MultiTileEntityBlock aWool, MultiTileEntityBlock aTNT, MultiTileEntityBlock aUtilMetal, MultiTileEntityBlock aUtilStone, MultiTileEntityBlock aUtilWood, MultiTileEntityBlock aUtilWool, OreDictMaterial aMat, Class<? extends TileEntity> aClass) {

    }
}
