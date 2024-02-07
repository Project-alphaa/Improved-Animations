package einstein.improved_animations.api;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.world.entity.LivingEntity;

public class ImprovedAnimationsApi {

    private static final Set<AnimationHandler> animationHandlers = new HashSet<>();
    
    public static boolean moddedAnimationRunning(LivingEntity entity) {
        for(AnimationHandler handler : animationHandlers) {
            if(handler.moddedAnimationRunning(entity)) {
                return true;
            }
        }
        return false;
    }
    
    public static void registerAnimationHandler(AnimationHandler handler) {
        animationHandlers.add(handler);
    }
    
    public interface AnimationHandler {
        
        boolean moddedAnimationRunning(LivingEntity entity);
        
    }
    
}
