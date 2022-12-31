package net.nicguzzo.deepslateinstamine.fabric;

import net.nicguzzo.deepslateinstamine.DeepslateInstamineExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class DeepslateInstamineExpectPlatformImpl {    
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
