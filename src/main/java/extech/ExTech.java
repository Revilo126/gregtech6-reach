package extech;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import extech.loaders.b.MultiTileEntityLoader;
import gregapi.api.Abstract_Proxy;
import gregapi.block.MaterialMachines;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.MD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import static gregapi.data.CS.*;

/**
 * @author Revilo12
 *
 * Addon mod for GT6 providing custom Multiblocks, TileEntities and Materials.
 *
 * Designed to expand GT6's late-game without making anything 'too' overpowered.
 *
 * Extended Technology
 */
@Mod(modid=ExTech.MOD_ID, name=ExTech.MOD_NAME, version=ExTech.VERSION, dependencies="required-after:gregapi_post")
public final class ExTech extends gregapi.api.Abstract_Mod {
    public static final String MOD_ID = "extech";
    public static final String MOD_NAME = "ExTech";
    public static final String VERSION = "0.0.1";

    @SidedProxy(modId = MOD_ID, clientSide = "extech.ExTechClient", serverSide = "extech.ExTechServer")
    public static Abstract_Proxy PROXY;

    @Override public String getModID() {return MOD_ID;}
    @Override public String getModName() {return MOD_NAME;}
    @Override public String getModNameForLog() {return "ExTech";}
    @Override public Abstract_Proxy getProxy() {return PROXY;}

    @Override
    public void onModPreInit2(FMLPreInitializationEvent aEvent) {

        // Init MTE registry and blocks
        new MultiTileEntityRegistry("extech.multitileentity");
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "iron"         , Material.iron             , Block.soundTypeMetal, TOOL_pickaxe, 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "iron"         , Material.iron             , Block.soundTypeMetal, TOOL_shovel , 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "machine"      , MaterialMachines.instance , Block.soundTypeMetal, TOOL_cutter , 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "machine"      , MaterialMachines.instance , Block.soundTypeMetal, TOOL_wrench , 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "wood"         , Material.wood             , Block.soundTypeWood , TOOL_axe    , 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "leaves"       , Material.leaves           , Block.soundTypeGrass, TOOL_axe    , 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "rock"         , Material.rock             , Block.soundTypeStone, TOOL_pickaxe, 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "cloth"        , Material.cloth            , Block.soundTypeCloth, TOOL_shears , 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "tnt"          , Material.tnt              , Block.soundTypeGrass, TOOL_pickaxe, 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "redstoneLight", Material.redstoneLight    , Block.soundTypeMetal, TOOL_pickaxe, 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "redstoneLight", Material.redstoneLight    , Block.soundTypeStone, TOOL_pickaxe, 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "redstoneLight", Material.redstoneLight    , Block.soundTypeWood , TOOL_axe    , 0, 0, 15, F, F);
        MultiTileEntityBlock.getOrCreate(MD.XT.mID, "redstoneLight", Material.redstoneLight    , Block.soundTypeCloth, TOOL_shears , 0, 0, 15, F, F);
        // MultiTileEntityBlock.getOrCreate(MD.XT.mID, "rock"         , MaterialScoopable.instance, Block.soundTypeWood , TOOL_scoop  , 0, 0, 15, F, F); // Hive not used
    }

    @Override
    public void onModInit2(FMLInitializationEvent aEvent) {
        ArrayListNoNulls<Runnable> tList = new ArrayListNoNulls<>(F,
            new MultiTileEntityLoader()
        );

        for (Runnable tRunnable : tList) try {tRunnable.run();} catch(Throwable e) {e.printStackTrace(ERR);}
    }

    @Override
    public void onModPostInit2(FMLPostInitializationEvent aEvent) {

    }

    @Override
    public void onModServerStarting2(FMLServerStartingEvent aEvent) {

    }

    @Override
    public void onModServerStarted2(FMLServerStartedEvent aEvent) {

    }

    @Override
    public void onModServerStopping2(FMLServerStoppingEvent aEvent) {

    }

    @Override
    public void onModServerStopped2(FMLServerStoppedEvent aEvent) {

    }

    // Do not change these 7 Functions. Just keep them this way.
    @Mod.EventHandler public final void onPreLoad           (FMLPreInitializationEvent    aEvent) {onModPreInit(aEvent);}
    @Mod.EventHandler public final void onLoad              (FMLInitializationEvent       aEvent) {onModInit(aEvent);}
    @Mod.EventHandler public final void onPostLoad          (FMLPostInitializationEvent   aEvent) {onModPostInit(aEvent);}
    @Mod.EventHandler public final void onServerStarting    (FMLServerStartingEvent       aEvent) {onModServerStarting(aEvent);}
    @Mod.EventHandler public final void onServerStarted     (FMLServerStartedEvent        aEvent) {onModServerStarted(aEvent);}
    @Mod.EventHandler public final void onServerStopping    (FMLServerStoppingEvent       aEvent) {onModServerStopping(aEvent);}
    @Mod.EventHandler public final void onServerStopped     (FMLServerStoppedEvent        aEvent) {onModServerStopped(aEvent);}
}
