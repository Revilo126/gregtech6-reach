package gregapi.tileentity;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ITileEntityWaila {
    /** Modify the Waila stack */
    public default ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    /** Modify the Waila head */
    public default List<String> getWailaHead(List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        getWailaInfos(new ArrayList<>()).forEach(info-> info.getWailaHead(currenttip,accessor,config));
        return currenttip;
    }

    /** Modify the Waila body */
    public default List<String> getWailaBody(List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        getWailaInfos(new ArrayList<>()).forEach(info-> info.getWailaBody(currenttip,accessor,config));
        return currenttip;
    }

    /** Modify the Waila tail */
    public default List<String> getWailaTail(List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        getWailaInfos(new ArrayList<>()).forEach(info-> info.getWailaTail(currentTip,accessor,config));
        return currentTip;
    }

    /** Modify the Waila NBT */
    public default NBTTagCompound getWailaNBT(TileEntity te, NBTTagCompound aNBT) {
        getWailaInfos(new ArrayList<>()).forEach(info-> info.getWailaNBT(te,aNBT));
        return aNBT;
    }

    /**
     * the simplified infos, return an instance of IWailaInfoProvider below and show that infos.
     **/
    @Deprecated
    public default IWailaInfoProvider[] getWailaInfos(){
        return new IWailaInfoProvider[0];
    }

    /**
     * the simplified infos, return an instance of IWailaInfoProvider below and show that infos.
     **/
    public default List<IWailaInfoProvider> getWailaInfos(List<IWailaInfoProvider> current){
        return Arrays.asList(getWailaInfos());
    }

    public interface IWailaInfoProvider extends ITileEntityWaila {
        public default IWailaInfoProvider[] asArray(){
            return new IWailaInfoProvider[]{this};
        }
    }
}
