package einstein.improved_animations.util.data;

import com.google.common.collect.Maps;
import einstein.improved_animations.animations.entity.LivingEntityPartAnimator;
import java.util.HashMap;

import net.minecraft.world.entity.EntityType;

public class LivingEntityAnimatorRegistry {

    private final HashMap<EntityType<?>, LivingEntityPartAnimator<?, ?>> livingEntityPartAnimatorHashMap = Maps.newHashMap();

    public LivingEntityAnimatorRegistry(){
    }

    public void register(EntityType<?> entityType, LivingEntityPartAnimator<?, ?> livingEntityPartAnimator){
        livingEntityPartAnimatorHashMap.put(entityType, livingEntityPartAnimator);
    }

    public boolean contains(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.containsKey(entityType);
    }

    public LivingEntityPartAnimator<?, ?> get(EntityType<?> entityType){
        return livingEntityPartAnimatorHashMap.get(entityType);
    }
}
