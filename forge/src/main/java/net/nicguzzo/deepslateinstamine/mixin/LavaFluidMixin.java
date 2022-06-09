package net.nicguzzo.deepslateinstamine.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {
    @Inject(at = @At("HEAD"), cancellable = true, method = "spreadTo")
    private void spreadTo(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Direction direction,
            FluidState fluidState, CallbackInfo ci) {

        if (DeepslateInstamineMod.config.enable_renewable_deepslate &&
            blockPos.getY() < DeepslateInstamineMod.config.renewable_deepslate_below_level) {
            if (direction == Direction.DOWN) {
                FluidState fluidState2 = levelAccessor.getFluidState(blockPos);
                if (fluidState2.is(FluidTags.WATER)) {
                    if (blockState.getBlock() instanceof LiquidBlock) {
                        levelAccessor.setBlock(blockPos, Blocks.DEEPSLATE.defaultBlockState(), 3);
                    }
                    levelAccessor.levelEvent(1501, blockPos, 0);
                    ci.cancel();
                }
            }
        }
    }
}
