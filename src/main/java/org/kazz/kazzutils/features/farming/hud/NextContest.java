package org.kazz.kazzutils.features.farming.hud;

import cc.polyfrost.oneconfig.hud.TextHud;
import net.minecraft.util.ResourceLocation;
import org.kazz.kazzutils.utils.TabUtils;

import java.util.List;

public class NextContest extends TextHud {

    public NextContest(){super(false);}

    @Override
    public void getLines(List<String> lines, boolean example) {
        if(!TabUtils.area.equals("Garden"))return;
        if(example) lines.add(0,"Next Contest");

        final ResourceLocation texture = new ResourceLocation("minecraft", "textures/items/carrot.png");

        lines.add(0,TabUtils.first + " " + TabUtils.second + " " + TabUtils.third + " §7(§3" + TabUtils.time + "§7)");


    }
}
