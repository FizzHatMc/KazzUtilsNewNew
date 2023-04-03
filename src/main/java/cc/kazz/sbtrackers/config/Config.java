package cc.kazz.sbtrackers.config;

import cc.kazz.sbtrackers.SbTracker;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;


/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class Config extends cc.polyfrost.oneconfig.config.Config {





    @Switch(
            name = "Ghost Tracker OFF/ON",
            size = OptionSize.SINGLE // optional
    )
    public static boolean GhostTrackerSwitch = false;

    @Checkbox(
            name = "Show Coins per Hour",
            size = OptionSize.DUAL // optional
    )
    public static boolean coinsPerHour = false;

    /*
    @Info(
            text = "Sorry aber du bist nen Hurensohn",
            type = InfoType.ERROR // Types are: INFO, WARNING, ERROR, SUCCESS
    )
    public static boolean ignored; // Useless. Java limitations with @annotation.

     */


    public Config() {
        super(new Mod(SbTracker.NAME, ModType.HYPIXEL, "src/main/resources/Main.png"), SbTracker.MODID + ".json");
        initialize();
    }
}

