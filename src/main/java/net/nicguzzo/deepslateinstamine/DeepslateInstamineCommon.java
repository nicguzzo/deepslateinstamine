package net.nicguzzo.deepslateinstamine;

import net.minecraft.server.MinecraftServer;

public class DeepslateInstamineCommon {

    public static void onServerStarted(MinecraftServer server) {
        // The server is fully initialized and the worlds are loaded.
        // Run your post-initialization code here!
        DeepslateInstamineMod.initialize();
    }

}