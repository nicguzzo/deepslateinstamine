//? if fabric {
/*package net.nicguzzo.deepslateinstamine.loaders.fabric;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.Path;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.nicguzzo.deepslateinstamine.Config;
import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;
import org.slf4j.Logger;

public class FabricEntrypoint implements ModInitializer {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            Path configDir = FabricLoader.getInstance().getConfigDir();
            Config.configDir = configDir.toString();
            DeepslateInstamineMod.onServerStarted(server);
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            DeepslateInstamineMod.onJoin();
        });
        ServerPlayConnectionEvents.DISCONNECT.register((handler, sender) -> {
            DeepslateInstamineMod.onDisconnect();
        });
    }
}
*///?}
