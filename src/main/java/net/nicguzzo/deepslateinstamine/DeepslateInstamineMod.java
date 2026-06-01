package net.nicguzzo.deepslateinstamine;


//? if >= 1.21.5 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.enchantment.ItemEnchantments;
//?}

//? if >= 1.21.1 && < 1.21.5 {
/*import net.minecraft.world.item.DiggerItem;
*///?}

//? if < 1.21.1 {
/*import net.minecraft.world.item.TieredItem;
*///?}

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;


import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;

//import dev.architectury.event.events.common.LifecycleEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Optional;


public class DeepslateInstamineMod{
	public static final String MOD_ID = "deepslateinstamine";
	public static final Logger LOGGER = LogManager.getLogger("deepslateinstamine");

	public static Config config =null;

	public static void initialize() {
		if(config==null)
			config = Config.get_instance();
	}
	public static float instamine(BlockState blockState,Player player){
		ItemStack itemStack = player.getMainHandItem();

		Item item = itemStack.getItem();

		if(is_tool(item))
		{
			int j=get_eff_level(player,itemStack);

			//ItemEnchantments enchantments=itemStack.getEnchantments();
			//? if >=1.21.5 {
		     Holder<MobEffect> haste=MobEffects.HASTE;
			 Holder<MobEffect> fatigue=MobEffects.MINING_FATIGUE;
			
			//?} else {
    			/*//? if >=1.21.1 {
    			Holder<MobEffect> haste= MobEffects.DIG_SPEED;
    			Holder<MobEffect> fatigue=MobEffects.DIG_SLOWDOWN;
    			
    			//?} else {
    			/^MobEffect haste= MobEffects.DIG_SPEED;
    			MobEffect fatigue=MobEffects.DIG_SLOWDOWN;
                ^///?}
			*///?}

			if(j>=5 && player.hasEffect(haste) && !player.hasEffect(fatigue))
			{
				float speed = item.getDestroySpeed(itemStack, blockState);
				MobEffectInstance eff= player.getEffect(haste);

				if(eff!=null && eff.getAmplifier()>=1){
					//LOGGER.info("Eff 5 haste 2");
					if(config.enable_logs_instamine && config.axes_item.contains(item)){
						if(blockState.is(BlockTags.LOGS) || config.axe_instamine_blk.contains(blockState.getBlock())){
							speed *= config.speed_factor;
							return speed;
						}
					}else if(config.pickaxes_item.contains(item)){
						if(config.pickaxe_instamine_blk.contains(blockState.getBlock())){
							speed *= config.speed_factor;
							//LOGGER.info("pick speed "+speed);
							return speed;
						}
					}
				}
			}
		}
		return -1.0f;
	}
	public static boolean is_tool(Item item){
		//? if >= 1.21.5 {
		 return item.getDefaultInstance().getComponents().has(DataComponents.TOOL); 
		//?} else {
			/*//? if >=1.20.6 {
					return (item instanceof DiggerItem);
			//?} else {
					/^return (item instanceof TieredItem);
			^///?}
		*///?}
	}

	public static int get_eff_level(Player player,ItemStack itemStack) {
		int j=0;
		//? if >= 1.21.4 {
		    RegistryAccess registryAccess = player.level().registryAccess();
			ResourceKey<Enchantment> ef=Enchantments.EFFICIENCY;
			Registry<Enchantment> efficiency = registryAccess.lookup(ef.registryKey()).orElse(null);
			if(efficiency!=null){
				Optional<Holder.Reference<Enchantment>> efficiencyHolder=efficiency.get(ef.identifier());
				if(efficiencyHolder.isPresent()){
					j=EnchantmentHelper.getItemEnchantmentLevel(efficiencyHolder.get(),itemStack	);
				}
			}
		
		//?} else {
			/*//? if >= 1.21.1 {
			  RegistryAccess registryAccess = player.level().registryAccess();
				ResourceKey<Enchantment> ef=Enchantments.EFFICIENCY;
				Registry<Enchantment> efficiency = registryAccess.registry(ef.registryKey()).orElse(null);
				if(efficiency!=null){
					Optional<Holder.Reference<Enchantment>> efficiencyHolder=efficiency.getHolder(ef.identifier());
					if(efficiencyHolder.isPresent()){
						j=EnchantmentHelper.getItemEnchantmentLevel(efficiencyHolder.get(),itemStack	);
					}
				}
			//?} else {
				/^//? if >= 1.20.6 {
					RegistryAccess registryAccess = player.level().registryAccess();
					Enchantment ef=Enchantments.EFFICIENCY;
					Registry<Enchantment> efficiency = registryAccess.lookup(ef.registryKey()).orElse(null);
					if(efficiency!=null){
						Optional<Holder.Reference<Enchantment>> efficiencyHolder=efficiency.get(ef.identifier());
						if(efficiencyHolder.isPresent()){
							j=EnchantmentHelper.getItemEnchantmentLevel(efficiencyHolder.get(),itemStack	);
						}
					}

				//?} else {
					 /^¹j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, itemStack);
				¹^///?}
			^///?}
		*///?}
		return j;
	}
}
