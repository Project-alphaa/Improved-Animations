package einstein.improved_animations.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import einstein.improved_animations.util.animation.BakedPose;
import einstein.improved_animations.animations.AnimatorDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandLayer.class)
public abstract class MixinItemInHandLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    public MixinItemInHandLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Inject(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ArmedModel;translateToHand(Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    protected void transformItemInHandLayer(LivingEntity livingEntity, ItemStack itemStack, ItemTransforms.TransformType transformType, HumanoidArm humanoidArm, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci){
        if(shouldTransformItemInHand(livingEntity, itemStack)){
            // +
            poseStack.translate((humanoidArm == HumanoidArm.LEFT ? 1 : -1) /16F, 0, 0);
            String locatorIdentifier = humanoidArm == HumanoidArm.LEFT ? "leftHand" : "rightHand";
            AnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID()).getLocator(locatorIdentifier, Minecraft.getInstance().getFrameTime()).translateAndRotatePoseStack(poseStack);
            // +
        }
    }
    private boolean shouldTransformItemInHand(LivingEntity livingEntity, ItemStack itemStack){
        if(itemStack.isEmpty()){
            return false;
        }
        BakedPose bakedPose = AnimatorDispatcher.INSTANCE.getBakedPose(livingEntity.getUUID());
        if(bakedPose != null){
            return bakedPose.containsLocator("leftHand") && bakedPose.containsLocator("rightHand");
        }
        return false;
    }
}
