package multihelper.structure.elements;

import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ChunkCoordinates;

public class StructureElementAir<T extends ITileEntityMultiBlockController> implements IStructureElement<T> {
    @Override
    public boolean check(T t, ChunkCoordinates aCoordinates, Entity aPlayer, IInventory aInventory, int tX, int tY, int tZ) {
        return t.getAir(tX, tY, tZ);
    }
}
