package cc.kazz.sbtrackers.hud;

import cc.kazz.sbtrackers.config.Config;
import cc.polyfrost.oneconfig.hud.SingleTextHud;
import cc.kazz.sbtrackers.features.GhostTracker;

import javax.swing.*;

/**
 * An example OneConfig HUD that is started in the config and displays text.
 *
 * @see Config#hud
 */
public class TestHud extends SingleTextHud {

    GhostTracker ghostTracker = new GhostTracker();
    public TestHud() {
        super("Coins", true);



    }

    @Override
    protected String getText(boolean example) {
        return null;
    }





}
