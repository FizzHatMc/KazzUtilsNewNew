package org.kazz.kazzutils.features.farming.hud;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import org.kazz.kazzutils.utils.TabUtilsKotlin;

public class NextContest extends SingleTextHud {

    public NextContest(){super("Next: ", false);}

    @Override
    protected String getText(boolean example) {

        //return TabUtilsKotlin.INSTANCE.getCropList().get(0) + " " + TabUtilsKotlin.INSTANCE.getCropList().get(1) + " " + TabUtilsKotlin.INSTANCE.getCropList().get(2);
        return TabUtilsKotlin.INSTANCE.getFirst() + " " + TabUtilsKotlin.INSTANCE.getSecond() + " " + TabUtilsKotlin.INSTANCE.getThird() + " (" + TabUtilsKotlin.INSTANCE.getTime() + ")";
    }
}
