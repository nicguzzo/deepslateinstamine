//? if neoforge {
package net.nicguzzo.deepslateinstamine.loaders.neoforge;


import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.nicguzzo.deepslateinstamine.Config;
import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;
import org.slf4j.Logger;
import net.neoforged.fml.loading.FMLPaths;
import java.nio.file.Path;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.nicguzzo.deepslateinstamine.DeepslateInstamineCommon;

@Mod("deepslateinstamine")
public class NeoforgeEntrypoint {
    private static final Logger LOGGER = LogUtils.getLogger();

    public NeoforgeEntrypoint() {
        NeoForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        Path configDir = FMLPaths.CONFIGDIR.get();
        Config.configDir=configDir.toString();
        DeepslateInstamineCommon.onServerStarted(event.getServer());
    }
}
//?}
