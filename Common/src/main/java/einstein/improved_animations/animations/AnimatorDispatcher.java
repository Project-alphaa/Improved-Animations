package einstein.improved_animations.animations;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import einstein.improved_animations.ImprovedAnimations;
import einstein.improved_animations.animations.entity.LivingEntityPartAnimator;
import einstein.improved_animations.api.ImprovedAnimationsApi;
import einstein.improved_animations.util.animation.BakedPose;

import java.util.HashMap;
import java.util.UUID;

import einstein.improved_animations.util.data.EntityAnimationData;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class AnimatorDispatcher {
    public static final AnimatorDispatcher INSTANCE = new AnimatorDispatcher();

    private final HashMap<UUID, EntityAnimationData> entityAnimationDataMap = Maps.newHashMap();
    private final HashMap<UUID, BakedPose> bakedPoseMap = Maps.newHashMap();
    public void tickEntity(LivingEntity livingEntity, LivingEntityPartAnimator<?, ?> livingEntityPartAnimator){
        if(!entityAnimationDataMap.containsKey(livingEntity.getUUID())){
            entityAnimationDataMap.put(livingEntity.getUUID(), new EntityAnimationData());
        }
        livingEntityPartAnimator.tickMethods(livingEntity);
    }

    @SuppressWarnings("unchecked")
    public <T extends LivingEntity, M extends EntityModel<T>> boolean animateEntity(T livingEntity, M entityModel, PoseStack poseStack, float partialTicks){
        if(ImprovedAnimationsApi.moddedAnimationRunning(livingEntity)) {
            if(entityModel instanceof PlayerModel<?> playerModel) {
                // reset offsets, as vanilla doesn't touch them
                playerModel.head.setPos(0, 0, 0);
                playerModel.body.setPos(0, 0, 0);
                playerModel.leftArm.setPos(0, 0, 0);
                playerModel.rightArm.setPos(0, 0, 0);
                playerModel.leftLeg.setPos(0, 0, 0);
                playerModel.rightLeg.setPos(0, 0, 0);
            }
            return false;
        }
        if(entityAnimationDataMap.containsKey(livingEntity.getUUID())){
            if(ImprovedAnimations.ENTITY_ANIMATORS.contains(livingEntity.getType())){
                LivingEntityPartAnimator<T, M> livingEntityPartAnimator = (LivingEntityPartAnimator<T, M>) ImprovedAnimations.ENTITY_ANIMATORS.get(livingEntity.getType());
                livingEntityPartAnimator.animate(livingEntity, entityModel, poseStack, entityAnimationDataMap.get(livingEntity.getUUID()), partialTicks);
                return true;
            }
        }
        return false;
    }

    public void saveBakedPose(UUID uuid, BakedPose bakedPose){
        this.bakedPoseMap.put(uuid, bakedPose);
    }

    public BakedPose getBakedPose(UUID uuid){
        if(this.bakedPoseMap.containsKey(uuid)){
            return this.bakedPoseMap.get(uuid);
        }
        return new BakedPose();
    }

    public boolean hasAnimationData(UUID uuid){
        return this.entityAnimationDataMap.containsKey(uuid);
    }

    public EntityAnimationData getEntityAnimationData(UUID uuid){
        if(entityAnimationDataMap.containsKey(uuid)){
            return entityAnimationDataMap.get(uuid);
        }
        return new EntityAnimationData();
    }

    public <T extends Entity> EntityAnimationData getEntityAnimationData(T entity){
        return getEntityAnimationData(entity.getUUID());
    }
}
