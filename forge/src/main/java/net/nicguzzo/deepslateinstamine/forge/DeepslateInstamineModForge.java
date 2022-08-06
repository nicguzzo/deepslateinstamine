package net.nicguzzo.deepslateinstamine.forge;

#if MC=="1165"
import me.shedaniel.architectury.platform.forge.EventBuses;
import me.shedaniel.architectury.utils.Env;
import me.shedaniel.architectury.utils.EnvExecutor;
#else
import dev.architectury.platform.forge.EventBuses;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
#endif

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;


@Mod(DeepslateInstamineMod.MOD_ID)

public class DeepslateInstamineModForge {

    public DeepslateInstamineModForge() {
        
        EventBuses.registerModEventBus(DeepslateInstamineMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        DeepslateInstamineMod.init();        
        /*EnvExecutor.runInEnv(Env.CLIENT, () -> 
            ()-> {
                DeepslateInstamineModClient.initialize();
            }
        );*/
    }
}
