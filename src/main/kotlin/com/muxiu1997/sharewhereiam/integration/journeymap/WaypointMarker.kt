package com.muxiu1997.sharewhereiam.integration.journeymap

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import journeymap.client.model.Waypoint
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.RenderWorldLastEvent
import org.lwjgl.opengl.GL11

@SideOnly(Side.CLIENT)
object WaypointMarker {
    fun render(event: RenderWorldLastEvent) {
        val transientBeacons = WaypointManager.getTransientBeacons()
        if (transientBeacons.isEmpty()) return

        val partialTicks: Float = event.partialTicks
        val player = Minecraft.getMinecraft().thePlayer
        val posX: Double =
            player.prevPosX + (player.posX - player.prevPosX) * partialTicks
        val posY: Double =
            player.prevPosY + (player.posY - player.prevPosY) * partialTicks
        val posZ: Double =
            player.prevPosZ + (player.posZ - player.prevPosZ) * partialTicks

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS)
        GL11.glPushMatrix()
        GL11.glTranslated(-posX, -posY, -posZ)
        GL11.glDisable(GL11.GL_LIGHTING)
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDisable(GL11.GL_DEPTH_TEST)
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST)

        GL11.glBegin(GL11.GL_QUADS)
        transientBeacons.forEach { markWaypoint(it) }
        GL11.glEnd()

        GL11.glEnable(GL11.GL_LIGHTING)
        GL11.glEnable(GL11.GL_DEPTH_TEST)
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glPopMatrix()
        GL11.glPopAttrib()
    }

    private fun markWaypoint(waypoint: Waypoint) {
        val x = waypoint.x.toDouble()
        val y = waypoint.y.toDouble()
        val z = waypoint.z.toDouble()

        GL11.glColor4d(255.0, 255.0, 255.0, 0.8)

        // NORTH
        GL11.glVertex3d(x, y + 1, z)
        GL11.glVertex3d(x + 1, y + 1, z)
        GL11.glVertex3d(x + 1, y, z)
        GL11.glVertex3d(x, y, z)

        // EAST
        GL11.glVertex3d(x + 1, y, z + 1)
        GL11.glVertex3d(x + 1, y, z)
        GL11.glVertex3d(x + 1, y + 1, z)
        GL11.glVertex3d(x + 1, y + 1, z + 1)

        // SOUTH
        GL11.glVertex3d(x + 1, y + 1, z + 1)
        GL11.glVertex3d(x, y + 1, z + 1)
        GL11.glVertex3d(x, y, z + 1)
        GL11.glVertex3d(x + 1, y, z + 1)

        // WEST
        GL11.glVertex3d(x, y + 1, z + 1)
        GL11.glVertex3d(x, y + 1, z)
        GL11.glVertex3d(x, y, z)
        GL11.glVertex3d(x, y, z + 1)

        // TOP
        GL11.glVertex3d(x, y + 1, z)
        GL11.glVertex3d(x, y + 1, z + 1)
        GL11.glVertex3d(x + 1, y + 1, z + 1)
        GL11.glVertex3d(x + 1, y + 1, z)

        // BOTTOM
        GL11.glVertex3d(x + 1, y, z)
        GL11.glVertex3d(x + 1, y, z + 1)
        GL11.glVertex3d(x, y, z + 1)
        GL11.glVertex3d(x, y, z)
    }
}
