package net.nicguzzo.deepslateinstamine;

#if MC=="1165"
import me.shedaniel.architectury.ExpectPlatform;
import me.shedaniel.architectury.platform.Platform;
#else
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
#endif

import java.nio.file.Path;

public class DeepslateInstamineExpectPlatform {
    @ExpectPlatform
    public static Path getConfigDirectory() {
        // Just throw an error, the content should get replaced at runtime.
        throw new AssertionError();
    }
}
