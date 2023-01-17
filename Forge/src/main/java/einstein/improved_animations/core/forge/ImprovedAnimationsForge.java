package einstein.improved_animations.core.forge;

import einstein.improved_animations.ImprovedAnimations;
import einstein.improved_animations.util.data.TimelineGroupDataLoader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ImprovedAnimations.MOD_ID)
public class ImprovedAnimationsForge {

    public ImprovedAnimationsForge() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::onRegisterClientReloadListeners);
    }

    @OnlyIn(Dist.CLIENT)
    void clientSetup(FMLClientSetupEvent event) {
        ImprovedAnimations.onClientInit();
    }

    @OnlyIn(Dist.CLIENT)
    void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new TimelineGroupDataLoader());
    }
}
