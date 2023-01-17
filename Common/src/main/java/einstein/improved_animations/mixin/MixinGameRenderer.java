package einstein.improved_animations.mixin;

import einstein.improved_animations.ImprovedAnimations;
import einstein.improved_animations.animations.AnimatorDispatcher;
import einstein.improved_animations.animations.entity.LivingEntityPartAnimator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Shadow @Final Minecraft minecraft;



    /*
    @Inject(method = "renderLevel", at = @At("HEAD"))
    private void adjustTimersForAllEntities(float f, long l, PoseStack poseStack, CallbackInfo ci){
        for(Entity entity : this.minecraft.level.entitiesForRendering()){
            if(entity instanceof LivingEntity){

                EntityType<?> entityType = entity.getType();
                if(ImprovedAnimations.ENTITY_ANIMATORS.contains(entityType)){
                    LivingEntityAnimator livingEntityAnimator = ImprovedAnimations.ENTITY_ANIMATORS.get(entityType);
                    livingEntityAnimator.setPartialTicks(f);
                    livingEntityAnimator.tick((LivingEntity) entity);
                }
            }
        }
    }

     */


    @Inject(method = "tick", at = @At("TAIL"))
    private void tickEntityInformation(CallbackInfo ci){
        if(this.minecraft.level != null){
            for(Entity entity : this.minecraft.level.entitiesForRendering()){
                if(entity instanceof LivingEntity){
                    EntityType<?> entityType = entity.getType();
                    if(ImprovedAnimations.ENTITY_ANIMATORS.contains(entityType)){
                        LivingEntityPartAnimator<?, ?> livingEntityAnimator = ImprovedAnimations.ENTITY_ANIMATORS.get(entityType);
                        AnimatorDispatcher.INSTANCE.tickEntity((LivingEntity) entity, livingEntityAnimator);
                    }
                }
            }
        }
    }
}
