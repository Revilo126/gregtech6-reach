package extech.tileentity.multiblock;

import com.cleanroommc.modularui.factory.GuiFactories;
import cpw.mods.fml.common.network.IGuiHandler;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import multihelper.structure.Definition;
import multihelper.structure.StructureUtil;
import multihelper.tile.multiblock.TileEntityBase10MultiBlockBaseMH;
import net.minecraft.entity.player.EntityPlayer;

import static gregapi.data.CS.*;

public class MultiTileEntityGeologicalSurveyer extends TileEntityBase10MultiBlockBaseMH<MultiTileEntityGeologicalSurveyer> {
    static String[][] STRUCTURE = {
        { "   ", " - " },
        { " S ", "GLG" },
        { "   ", " G " },
    };

    @Override
    public Definition<MultiTileEntityGeologicalSurveyer> getStructure() {
        return Definition.<MultiTileEntityGeologicalSurveyer>builder(STRUCTURE)
            .where('S', StructureUtil.part(18002, StructureUtil.getGTRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN))
            .where('G', StructureUtil.part(18008, StructureUtil.getGTRegistryID()))
            .where('L', StructureUtil.part(18011, StructureUtil.getGTRegistryID()))
            .build();
    }

    @Override
    public void onTick2(long aTimer, boolean aIsServerSide) {

    }

    @Override
    public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
        if (super.onBlockActivated3(aPlayer, aSide, aHitX, aHitY, aHitZ)) return T;

        if (isServerSide()) {
            //GuiFactories.tileEntity().open(aPlayer, this);
        }

        return true;
    }

    @Override public String getTileEntityName() { return "extech.tileentity.multiblock.geosurveyer"; }

    private static class Util {
        public static boolean prospect() {
            return T;
        }
    }
}
