package com.muxiu1997.sharewhereiam.event

import com.muxiu1997.sharewhereiam.client.KeyShare
import com.muxiu1997.sharewhereiam.integration.journeymap.WaypointManager
import com.muxiu1997.sharewhereiam.util.ShareWaypointUtil
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.InputEvent
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.event.entity.EntityJoinWorldEvent

object EventHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun handleEntityJoinWorld(event: EntityJoinWorldEvent) {
        if (event.world.isRemote) {
            if (event.entity is EntityPlayer) {
                WaypointManager.clearActiveWaypoint()
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    fun handleKeyInput(event: InputEvent.KeyInputEvent?) {
        if (KeyShare.isPressed) {
            ShareWaypointUtil.shareLocation(Minecraft.getMinecraft().thePlayer)
        }
    }
}
