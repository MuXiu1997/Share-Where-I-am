package com.muxiu1997.sharewhereiam.proxy;

import com.muxiu1997.sharewhereiam.command.CommandWaypointSave;
import com.muxiu1997.sharewhereiam.command.CommandWaypointShareLocation;
import com.muxiu1997.sharewhereiam.command.CommandWaypointToggleTempBeacon;
import com.muxiu1997.sharewhereiam.journeymap.WaypointManager;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ClientCommandHandler.instance.registerCommand(CommandWaypointSave.INSTANCE);
        ClientCommandHandler.instance.registerCommand(CommandWaypointShareLocation.INSTANCE);
        ClientCommandHandler.instance.registerCommand(CommandWaypointToggleTempBeacon.INSTANCE);
    }
}
