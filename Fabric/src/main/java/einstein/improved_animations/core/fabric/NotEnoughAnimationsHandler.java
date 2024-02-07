package einstein.improved_animations.core.fabric;

import java.lang.reflect.Field;
import java.util.Set;

import dev.tr7zw.notenoughanimations.NEAnimationsMod;
import dev.tr7zw.notenoughanimations.access.PlayerData;
import dev.tr7zw.notenoughanimations.animations.BasicAnimation;
import dev.tr7zw.notenoughanimations.logic.AnimationProvider;
import einstein.improved_animations.api.ImprovedAnimationsApi.AnimationHandler;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;

public class NotEnoughAnimationsHandler implements AnimationHandler {

    private Set<BasicAnimation> enabledBasicAnimations = null;

    @SuppressWarnings("unchecked")
    private boolean setup() {
        if (enabledBasicAnimations != null) {
            return true;
        }
        try {
            Field animations = AnimationProvider.class.getDeclaredField("enabledBasicAnimations");
            animations.setAccessible(true);
            enabledBasicAnimations = (Set<BasicAnimation>) animations.get(NEAnimationsMod.INSTANCE.animationProvider);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean moddedAnimationRunning(LivingEntity entity) {
        if (!setup())
            return false;
        if (entity instanceof AbstractClientPlayer player) {
            PlayerData playerData = (PlayerData) entity;
            for (BasicAnimation basicAnimation : enabledBasicAnimations) {
                if (basicAnimation.isValid(player, playerData)) {
                    int prio = basicAnimation.getPriority(player, playerData);
                    if (prio > 0 && basicAnimation.getBodyParts(player, playerData).length > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
