//? if forge {
/*package net.nicguzzo.deepslateinstamine.loaders.forge;


import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;
import net.nicguzzo.deepslateinstamine.Config;
import org.slf4j.Logger;
import net.minecraftforge.fml.loading.FMLPaths;
import java.nio.file.Path;

@Mod("deepslateinstamine")
public class ForgeEntrypoint {
    private static final Logger LOGGER = LogUtils.getLogger();

    public ForgeEntrypoint() {

        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        Path configDir = FMLPaths.CONFIGDIR.get();
        Config.configDir=configDir.toString();
        DeepslateInstamineMod.onServerStarted(event.getServer());
    }
}
*///?}
