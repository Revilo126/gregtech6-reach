package multihelper.structure.elements;

import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ChunkCoordinates;

public class StructureElementAnyOf<T extends ITileEntityMultiBlockController> implements IStructureElement<T> {
    private final IStructureElement<T>[] allowed;

    public StructureElementAnyOf(final IStructureElement<T>[] allowed) {
        this.allowed = allowed;
    }

    @Override
    public boolean check(T t, ChunkCoordinates aCoordinates, Entity aPlayer, IInventory aInventory, int tX, int tY, int tZ) {
        for (IStructureElement<T> element : allowed) {
            if (element.check(t, aCoordinates, aPlayer, aInventory, tX, tY, tZ)) return true;
        }

        return false;
    }
}
