package net.nicguzzo.deepslateinstamine.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.nicguzzo.deepslateinstamine.Config;
import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlock.class)
public abstract class LiquidBlockMixin {
    @Shadow
    FlowingFluid fluid;
    @Shadow
    @Final
    public static ImmutableList<Direction> POSSIBLE_FLOW_DIRECTIONS;

    @Shadow
    protected abstract void fizz(LevelAccessor levelAccessor, BlockPos blockPos);

    @Inject(at = @At("HEAD"), cancellable = true, method = "shouldSpreadLiquid")
    public void shouldSpreadLiquid(Level level, BlockPos blockPos, BlockState blockState, CallbackInfoReturnable ci) {
        if (DeepslateInstamineMod.config != null) {
            DeepslateInstamineMod.config = Config.get_instance();
        }
        if (DeepslateInstamineMod.config != null && DeepslateInstamineMod.config.enable_renewable_deepslate &&
                blockPos.getY() < DeepslateInstamineMod.config.renewable_deepslate_below_level) {
            if (this.fluid.is(FluidTags.LAVA)) {
                boolean bl = level.getBlockState(blockPos.below()).is(Blocks.SOUL_SOIL);
                for (Direction direction : POSSIBLE_FLOW_DIRECTIONS) {
                    BlockPos blockPos2 = blockPos.relative(direction.getOpposite());
                    if (level.getFluidState(blockPos2).is(FluidTags.WATER)) {
                        Block block = level.getFluidState(blockPos).isSource() ? Blocks.OBSIDIAN : Blocks.COBBLED_DEEPSLATE;
                        level.setBlockAndUpdate(blockPos, block.defaultBlockState());
                        this.fizz(level, blockPos);
                        ci.setReturnValue(false);
                    }
                    if (!bl || !level.getBlockState(blockPos2).is(Blocks.BLUE_ICE)) continue;
                    level.setBlockAndUpdate(blockPos, Blocks.CALCITE.defaultBlockState());
                    this.fizz(level, blockPos);
                    ci.setReturnValue(false);
                }
            }
        }
    }
}
