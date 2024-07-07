package net.nicguzzo.deepslateinstamine.neoforge;

import net.nicguzzo.deepslateinstamine.DeepslateInstamineExpectPlatform;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class DeepslateInstamineExpectPlatformImpl {    
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
