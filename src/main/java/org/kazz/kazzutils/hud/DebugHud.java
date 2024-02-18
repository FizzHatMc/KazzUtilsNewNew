package org.kazz.kazzutils.hud;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.features.mining.StarCult;
import org.kazz.kazzutils.utils.CheckCatacombs;

/**
 * An example OneConfig HUD that is started in the config and displays text.
 *
 * @see Config#hud
 */
public class DebugHud extends SingleTextHud {
    public DebugHud() {
        super("Debug", true);
    }

    @Override
    public String getText(boolean example) {
        for(CheckCatacombs.WitherKingDragons drag : CheckCatacombs.WitherKingDragons.values()){
            return drag.isDestroyed() + "";
        }
     return "";
    }
}
