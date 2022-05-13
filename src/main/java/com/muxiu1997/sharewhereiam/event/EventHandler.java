package com.muxiu1997.sharewhereiam.event;

import com.muxiu1997.sharewhereiam.client.key.KeyShare;
import com.muxiu1997.sharewhereiam.integration.journeymap.WaypointManager;
import com.muxiu1997.sharewhereiam.util.ShareWaypointUtil;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EventHandler {

    public static final EventHandler INSTANCE = new EventHandler();

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void handleEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.world.isRemote) {
            if (event.entity instanceof EntityPlayer) {
                WaypointManager.INSTANCE.clearActiveWaypoint();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void handleKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyShare.INSTANCE.isPressed()) {
            ShareWaypointUtil.shareLocation(Minecraft.getMinecraft().thePlayer);
        }
    }
}
