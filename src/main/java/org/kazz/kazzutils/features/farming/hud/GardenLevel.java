package org.kazz.kazzutils.features.farming.hud;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.data.farming.GardenXp;
import org.kazz.kazzutils.utils.TabUtilsKotlin;

public class GardenLevel extends SingleTextHud {

    public GardenLevel() {
        super("Garden Level", false);
    }




    @Override
    public String getText(boolean debug) {
        String re ="";
        if(debug) return "Debug Test";
        if(TabUtilsKotlin.INSTANCE.getGardenLevel() == 15){
           re = "15";
        }else if(Config.gardenPercentage){
            re = TabUtilsKotlin.INSTANCE.getGardenLevel() + " " + TabUtilsKotlin.INSTANCE.getGardenPercent() + "%";
        } else {
            int xp = GardenXp.getGardenXp(TabUtilsKotlin.INSTANCE.getGardenLevel(), TabUtilsKotlin.INSTANCE.getGardenPercent());
            re = TabUtilsKotlin.INSTANCE.getGardenLevel() + " (" + xp + "/" + GardenXp.getMaxXp(TabUtilsKotlin.INSTANCE.getGardenLevel()) + ")";
        }

        return re;
    }
}
