package net.nicguzzo.deepslateinstamine.fabric;

import net.nicguzzo.deepslateinstamine.DeepslateInstamineMod;
import net.fabricmc.api.ModInitializer;

public class DeepslateInstamineModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DeepslateInstamineMod.init();
    }
}
