package com.muxiu1997.sharewhereiam.mixins.journeymap;


import com.muxiu1997.sharewhereiam.client.key.KeyShare;
import com.muxiu1997.sharewhereiam.integration.Mods;
import com.muxiu1997.sharewhereiam.network.MessageStartShareWaypoint;
import com.muxiu1997.sharewhereiam.network.NetworkHandler;
import com.muxiu1997.sharewhereiam.util.VPWaypointUtil;
import journeymap.client.Constants;
import journeymap.client.model.Waypoint;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.fullscreen.MapChat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.StatCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@SuppressWarnings("UnusedMixin")
@Mixin(Fullscreen.class)
public abstract class MixinFullscreen extends JmUI {

    @SuppressWarnings("ShadowModifiers")
    @Shadow(remap = false)
    private MapChat chat;

    public MixinFullscreen() {
        super("");
    }

    @Inject(method = "func_73869_a",
        at = @At(value = "HEAD"),
        remap = false,
        require = 1,
        cancellable = true
    )
    private void inject_func_73869_a(CallbackInfo callbackInfo) {
        if (!Mods.VISUAL_PROSPECTING.isLoaded()) return;
        if ((chat == null || chat.isHidden()) && Constants.isPressed(KeyShare.INSTANCE)) {
            @Nullable final Waypoint waypoint = VPWaypointUtil.getHoveredWaypoint();
            if (waypoint == null) return;
            final EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
            NetworkHandler.INSTANCE.sendToServer(
                new MessageStartShareWaypoint(
                    player.getDisplayName(),
                    waypoint,
                    StatCollector.translateToLocal("sharewhereiam.chat.sharewaypoint.additionalInformation.VP")
                )
            );
            callbackInfo.cancel();
        }
    }
}
