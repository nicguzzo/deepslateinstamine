package net.nicguzzo.deepslateinstamine;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.state.BlockState;

import dev.architectury.event.events.common.LifecycleEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class DeepslateInstamineMod{
	public static final String MOD_ID = "deepslateinstamine";
	public static final Logger LOGGER = LogManager.getLogger("deepslateinstamine");

	public static Config config =null;
	public static void init() {
		LifecycleEvent.SERVER_STARTED.register((player)-> {
			LOGGER.info("SERVER_STARTED!");
			config = Config.get_instance();
		});

	}
	public static float instamine(BlockState blockState,Player player){
		ItemStack itemStack = player.getMainHandItem();
		if(config==null)
			config = Config.get_instance();


		Item item = itemStack.getItem();

		#if MC <"1206"
		if(item instanceof TieredItem){
		#else
		if(item instanceof DiggerItem){
		#endif
			int j=0;
			#if MC >="1206"
				RegistryAccess registryAccess = player.level().registryAccess();
				#if MC >="1211"
					ResourceKey<Enchantment> ef=Enchantments.EFFICIENCY;
					#if MC >="1214"
						Registry<Enchantment> efficiency = registryAccess.lookup(ef.registryKey()).orElse(null);
					#else
						Registry<Enchantment> efficiency = registryAccess.registry(ef.registryKey()).orElse(null);
					#endif
					if(efficiency!=null){
						#if MC >="1214"
							Optional<Holder.Reference<Enchantment>> efficiencyHolder=efficiency.get(ef.location());
						#else
							Optional<Holder.Reference<Enchantment>> efficiencyHolder=efficiency.getHolder(ef.location());
						#endif
						if(efficiencyHolder.isPresent()){
							j=EnchantmentHelper.getItemEnchantmentLevel(efficiencyHolder.get(),itemStack	);
						}
					}
				#else
					Enchantment ef=Enchantments.EFFICIENCY;
					Registry<Enchantment> efficiency = registryAccess.lookup(ef.registryKey()).orElse(null);
					if(efficiency!=null){
						Optional<Holder.Reference<Enchantment>> efficiencyHolder=efficiency.get(ef.location());
						if(efficiencyHolder.isPresent()){
							j=EnchantmentHelper.getItemEnchantmentLevel(efficiencyHolder.get(),itemStack	);
						}
					}
				#endif
			#else
				j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, itemStack);
			#endif

			ItemEnchantments enchantments=itemStack.getEnchantments();
			//RegistryAccess registryAccess = player.level().registryAccess();

			//#if MC >="1213"
			//	Registry<Enchantment> efficiency = registryAccess.lookup(ef.registryKey()).orElse(null);
			//	#if MC >="1211"
			//		Registry<Enchantment> efficiency = registryAccess.registry(ef.registryKey()).orElse(null);
			//	#endif
			//#endif

			//if(efficiency!=null){
			//	Optional<Holder.Reference<Enchantment>> efficiencyHolder=efficiency.get(ef.location());
			//	if(efficiencyHolder.isPresent()){
			//		j=EnchantmentHelper.getItemEnchantmentLevel(efficiencyHolder.get(),itemStack	);
			//	}
			//}
			//enchantments.getLevel(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE.getHolder(ef.location()));

			if(j>=5 && player.hasEffect(MobEffects.DIG_SPEED) && !player.hasEffect(MobEffects.DIG_SLOWDOWN)){

				//this.getAttributeValue(Attributes.BLOCK_BREAK_SPEED);

				//float speed = ((DiggerItem) item).getTier().getSpeed();
				float speed = item.getDestroySpeed(itemStack, blockState);
				MobEffectInstance eff= player.getEffect(MobEffects.DIG_SPEED);
				if(eff!=null && eff.getAmplifier()>=1){
					if(config.enable_logs_instamine && config.axes_item.contains(item)){
						if(blockState.is(BlockTags.LOGS) || config.axe_instamine_blk.contains(blockState.getBlock())){
							speed *= config.speed_factor;
							return speed;
						}
					}else if(config.pickaxes_item.contains(item)){
						if(config.pickaxe_instamine_blk.contains(blockState.getBlock())){
							speed *= config.speed_factor;
							return speed;
						}
					}
				}
			}
		}
		return -1.0f;
	}
}
