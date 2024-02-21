package org.kazz.kazzutils.hud;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.TabUtils;

/**
 * An example OneConfig HUD that is started in the config and displays text.
 *
 * @see Config#hud
 */
public class DebugHud extends SingleTextHud {
    public DebugHud() {
        super("Garden Level", false);
    }

    @Override
    public String getText(boolean debug) {
        if(debug) return "Debug Test";
        return TabUtils.gardenLevel;

    }
}
