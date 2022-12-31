package net.nicguzzo.deepslateinstamine.forge;

import net.nicguzzo.deepslateinstamine.DeepslateInstamineExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class DeepslateInstamineExpectPlatformImpl {    
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
