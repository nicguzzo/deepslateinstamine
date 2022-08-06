package net.nicguzzo.deepslateinstamine;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DeepslateInstamineMod{
	public static final String MOD_ID = "deepslateinstamine";
	public static final Logger LOGGER = LogManager.getLogger("deepslateinstamine");

	public static Config config =null;
	public static void init() {
		config = Config.get_instance();
	}
	public static float instamine(BlockState blockState,Player player){
		ItemStack itemStack = player.getMainHandItem();
		Item item = itemStack.getItem();
		int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, itemStack);

		if(config!=null && j>=5 && player.hasEffect(MobEffects.DIG_SPEED) && !player.hasEffect(MobEffects.DIG_SLOWDOWN)){
			float speed = ((TieredItem) item).getTier().getSpeed();
			MobEffectInstance eff= player.getEffect(MobEffects.DIG_SPEED);
			if(eff!=null && eff.getAmplifier()>=1){
				if(config.enable_logs_instamine && Items.NETHERITE_AXE.equals(item)){
					if(blockState.is(BlockTags.LOGS)) {
						speed *= config.speed_factor;
						return speed;
					}
				}else if(Items.NETHERITE_PICKAXE.equals(item)){
					if(config.pickaxe_instamine_blk.contains(blockState.getBlock())){
						speed *= config.speed_factor;
						return speed;
					}
				}
			}
		}
		return -1.0f;
	}
}
