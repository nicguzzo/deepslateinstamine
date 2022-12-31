package net.nicguzzo.deepslateinstamine.fabric.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixinFabric {
	@Inject(at = @At("HEAD"), cancellable = true, method = "getDestroySpeed(Lnet/minecraft/world/level/block/state/BlockState;)F")
	private void getDestroySpeed(BlockState blockState, CallbackInfoReturnable<Float> cir) {
		if(blockState !=null){
			float s=DeepslateInstamineMod.instamine(blockState,(Player)(Object)this);
			if(s!=-1.0f)
				cir.setReturnValue(s);
		}
	}
}