package cc.kazz.sbtrackers.command;

import cc.kazz.sbtrackers.SbTracker;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see cc.kazz.sbtrackers.SbTracker
 */
@Command(value = cc.kazz.sbtrackers.SbTracker.MODID, description = "Access the " + cc.kazz.sbtrackers.SbTracker.NAME + " GUI.")
public class SbTrackerCommand {
    @Main
    private void handle() {
        SbTracker.config.openGui();
    }
}