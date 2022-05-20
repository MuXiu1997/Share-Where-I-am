package com.muxiu1997.sharewhereiam.mixins.journeymap;

import com.muxiu1997.sharewhereiam.localization.Lang;
import com.muxiu1997.sharewhereiam.network.MessageStartShareWaypoint;
import journeymap.client.model.Waypoint;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.waypoint.WaypointManagerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.muxiu1997.sharewhereiam.network.NetworkHandler.network;


@SuppressWarnings("UnusedMixin")
@Mixin(WaypointManagerItem.class)
public abstract class MixinWaypointManagerItem {

    @SuppressWarnings("ShadowModifiers")
    @Shadow(remap = false)
    private Waypoint waypoint;
    private Button buttonShare;

    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyArg(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Ljourneymap/client/ui/component/ButtonList;<init>([Ljourneymap/client/ui/component/Button;)V",
            ordinal = 1,
            remap = false
        ),
        require = 1,
        remap = false
    )
    private Button[] redirect_init(Button[] buttons) {
        this.buttonShare = new Button(Lang.TEXT_JM_WAYPOINT_BUTTON.invoke());
        Button[] newButtons = new Button[buttons.length + 1];
        System.arraycopy(buttons, 0, newButtons, 1, buttons.length);
        newButtons[0] = this.buttonShare;
        return newButtons;
    }

    @Inject(
        method = "clickScrollable",
        at = @At(
            value = "RETURN",
            ordinal = 1,
            remap = false
        ),
        require = 1,
        remap = false,
        cancellable = true
    )
    private void inject_clickScrollable(int mouseX, int mouseY, CallbackInfoReturnable<Boolean> mouseOver) {
        if (!mouseOver.getReturnValue() && this.buttonShare.mouseOver(mouseX, mouseY)) {
            final EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
            network.sendToServer(new MessageStartShareWaypoint(player.getDisplayName(), this.waypoint));
            mouseOver.setReturnValue(true);
        }
    }
}


