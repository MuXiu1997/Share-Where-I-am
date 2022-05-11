package com.muxiu1997.sharewhereiam.proxy;

import com.muxiu1997.sharewhereiam.command.CommandSaveWaypoint;
import com.muxiu1997.sharewhereiam.command.CommandShareWhereIAm;
import com.muxiu1997.sharewhereiam.command.CommandToggleTempBeacon;
import com.muxiu1997.sharewhereiam.journeymap.WaypointManager;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ClientCommandHandler.instance.registerCommand(new CommandSaveWaypoint());
        ClientCommandHandler.instance.registerCommand(new CommandShareWhereIAm());
        ClientCommandHandler.instance.registerCommand(new CommandToggleTempBeacon());
        MinecraftForge.EVENT_BUS.register(WaypointManager.instance);
    }
}
