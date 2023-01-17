package einstein.improved_animations;

import einstein.improved_animations.animations.entity.CreeperPartAnimator;
import einstein.improved_animations.animations.entity.PlayerPartAnimator;
import einstein.improved_animations.util.data.LivingEntityAnimatorRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImprovedAnimations {


	public static final String MOD_ID = "improved_animations";
	public static Logger LOGGER = LogManager.getLogger();

	//public static final PollinatedRegistry<LivingEntityAnimator> ENTITY_ANIMATORS = PollinatedRegistry.createSimple(LivingEntityAnimator.class, new ResourceLocation(MOD_ID, "entity_animators"));
	public static final LivingEntityAnimatorRegistry ENTITY_ANIMATORS = new LivingEntityAnimatorRegistry();
	public static Entity debugEntity;

	public static void onClientInit() {
		registerEntityAnimators();
	}

	private static void registerEntityAnimators(){
		ENTITY_ANIMATORS.register(EntityType.PLAYER, new PlayerPartAnimator());
		ENTITY_ANIMATORS.register(EntityType.CREEPER, new CreeperPartAnimator());
	}
}
