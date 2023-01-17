package einstein.improved_animations.mixin.pestomodels;

import einstein.improved_animations.access.CubeDeformationAccess;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CubeDeformation.class)
public class MixinCubeDeformation implements CubeDeformationAccess {

    @Shadow @Final private float growX;

    @Override
    public float getGrow() {
        return this.growX;
    }
}
