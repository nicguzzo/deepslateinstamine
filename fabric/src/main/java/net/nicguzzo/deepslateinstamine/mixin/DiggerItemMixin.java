package net.nicguzzo.deepslateinstamine.mixin;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DiggerItem.class)
public class DiggerItemMixin {
	@Inject(at = @At("HEAD"), cancellable = true, method = "getDestroySpeed(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;)F")
	private void getDestroySpeed(ItemStack itemStack, BlockState blockState, CallbackInfoReturnable<Float> cir) {
		if(itemStack!=null && blockState !=null){
			Item item = itemStack.getItem();
			float speed = ((TieredItem) item).getTier().getSpeed();
			if (item.getDescriptionId().equals("item.minecraft.netherite_pickaxe")
					&& blockState.getBlock().getDescriptionId().equals("block.minecraft.deepslate")) {

				speed *= 4.3f;
				cir.setReturnValue(speed);
			}
		}
	}
}
