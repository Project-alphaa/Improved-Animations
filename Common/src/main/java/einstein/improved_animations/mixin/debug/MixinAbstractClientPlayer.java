package einstein.improved_animations.mixin.debug;

import net.minecraft.client.player.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractClientPlayer.class)
public class MixinAbstractClientPlayer {
//    private static final Identifier debugCapeLocation = new Identifier("textures/testcape.png");
//
//    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
//    private void useDebugCapeTexture(CallbackInfoReturnable<Identifier> cir) {
//        if (!Platform.isProduction()) {
//            cir.setReturnValue(debugCapeLocation);
//        }
//    }
}
