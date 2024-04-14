package org.kazz.kazzutils.features.mining;

import cc.polyfrost.oneconfig.hud.TextHud;
import net.minecraft.util.EnumChatFormatting;
import org.kazz.kazzutils.utils.TabUtils;

import java.util.List;

public class Commisions extends TextHud {

    public Commisions() {
        super(false);
        super.background = false;

    }

    @Override
    protected void getLines(List<String> lines, boolean example) {
        if(example) lines.add(0,"Commisions");
        if(!TabUtils.area.replace(" ","").equals("Dwarven"))return;
        //if(!TabUtils.area.replace(" ","").equals("Crystal"))return;
        if(TabUtils.comms.isEmpty()) return;

        lines.add(0, EnumChatFormatting.BLUE+"Commisions:");
        lines.add(1,TabUtils.comms.get(0));
        if(TabUtils.comms.size()>=1) lines.add(2,TabUtils.comms.get(1));
        if(TabUtils.comms.size()>=2) lines.add(3,TabUtils.comms.get(2));
        if(!TabUtils.comms.get(3).equals(null) || !TabUtils.comms.get(3).equals("") || !TabUtils.comms.get(3).equals(" ")) lines.add(4,TabUtils.comms.get(3));

    }
}
