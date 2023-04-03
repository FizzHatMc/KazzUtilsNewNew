package cc.kazz.sbtrackers;

import cc.kazz.sbtrackers.command.SbTrackerCommand;
import cc.kazz.sbtrackers.config.Config;
import cc.kazz.sbtrackers.features.GhostTracker;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.IOException;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = SbTracker.MODID, name = SbTracker.NAME, version = SbTracker.VERSION)
public class SbTracker {
    public static final String MODID = "sbtracker";
    public static final String NAME = "SbTracker";
    public static final String VERSION = "0.1";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static SbTracker INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static Config config;


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(new GhostTracker());

    }

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new Config();
        CommandManager.INSTANCE.registerCommand(new SbTrackerCommand());

    }
}
