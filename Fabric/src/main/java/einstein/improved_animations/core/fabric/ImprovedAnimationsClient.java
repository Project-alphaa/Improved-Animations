package einstein.improved_animations.core.fabric;

import einstein.improved_animations.ImprovedAnimations;
import einstein.improved_animations.api.ImprovedAnimationsApi;
import einstein.improved_animations.util.data.TimelineGroupDataLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ImprovedAnimationsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ImprovedAnimations.onClientInit();
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new TimelineGroupDataReloadWrapper());
        if(FabricLoader.getInstance().isModLoaded("notenoughanimations")) {
            ImprovedAnimationsApi.registerAnimationHandler(new NotEnoughAnimationsHandler());
        }
    }

    private static class TimelineGroupDataReloadWrapper implements IdentifiableResourceReloadListener {

        private final TimelineGroupDataLoader loader;

        public TimelineGroupDataReloadWrapper() {
            loader = new TimelineGroupDataLoader();
        }

        @Override
        public ResourceLocation getFabricId() {
            return new ResourceLocation(ImprovedAnimations.MOD_ID, "timeline_group_loader");
        }

        @Override
        public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller2, Executor executor, Executor executor2) {
            return loader.reload(preparationBarrier, resourceManager, profilerFiller, profilerFiller2, executor, executor2);
        }
    }
}
