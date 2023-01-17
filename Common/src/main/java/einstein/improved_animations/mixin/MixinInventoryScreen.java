package einstein.improved_animations.mixin;

import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InventoryScreen.class)
public class MixinInventoryScreen {
//    @Inject(method = "drawEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;setRenderShadows(Z)V"))
//    private static void setRendererToEntity(int i, int j, int k, float f, float g, LivingEntity livingEntity, CallbackInfo ci){
//        ((LivingEntityAccess)livingEntity).setUseInventoryRenderer(true);
//    }
}
