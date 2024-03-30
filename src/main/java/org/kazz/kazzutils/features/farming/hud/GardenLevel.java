package org.kazz.kazzutils.features.farming.hud;

import cc.polyfrost.oneconfig.hud.TextHud;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.data.farming.GardenXp;
import org.kazz.kazzutils.utils.TabUtils;

import java.util.List;

public class GardenLevel extends TextHud {


    public GardenLevel() {
        super(false);
    }

    @Override
    public void getLines(List<String> lines, boolean example) {
        if(!TabUtils.area.replace(" ","").equals("Garden"))return;
        if(example) lines.add(0,"Garden Level");

        if (TabUtils.gardenLevel == 15) {
            lines.add(0, "15");
        } else if (Config.gardenPercentage) {
            //re = TabUtils.getGardenLevel() + " " + TabUtils.getGardenPercent() + "%";
            lines.add(0, TabUtils.gardenLevel + " §e" + TabUtils.gardenPercent + "§7%");
        } else {
            int xp = GardenXp.getGardenXp(TabUtils.gardenLevel, TabUtils.gardenPercent);
            //re = TabUtils.getGardenLevel() + " (" + xp + "/" + GardenXp.getMaxXp(TabUtils.getGardenLevel()) + ")";
            lines.add(0, TabUtils.gardenLevel + " §7(§6" + xp + "§7/§6" + GardenXp.getMaxXp(TabUtils.gardenLevel) + "§7)§r");
        }
    }
}
