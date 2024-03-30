package org.kazz.kazzutils.features.events.mytho.hud;

import cc.polyfrost.oneconfig.hud.TextHud;
import org.kazz.kazzutils.features.events.mytho.mobTracker;
import org.kazz.kazzutils.utils.TabUtils;

import java.util.List;
import java.util.Objects;

public class mobTrackerHud extends TextHud {

    public mobTrackerHud() {
            super(false);
        }

    @Override
    protected void getLines(List<String> lines, boolean example) {
        if(example) lines.add(0,"Mytho Tracker");
        if(!TabUtils.area.replace(" ","").equals("Hud"))return;


        if(mobTracker.totalMobs>0) lines.add("Total Burrows: " + mobTracker.burrows);
        if(mobTracker.hunter>0) lines.add("Minos Hunter: " + mobTracker.hunter2 + " | " + mobTracker.hunterChance + "%");
        if(mobTracker.lynx>0) lines.add("Siamese Lynxes: " + mobTracker.lynx2+ " | " + mobTracker.lynxChance + "%");
        if(mobTracker.mino>0) lines.add("Minotaur: " + mobTracker.mino2+ " | " + mobTracker.minoChance + "%");
        if(mobTracker.gaia>0) lines.add("Gaia Construct:: " + mobTracker.gaia2+ " | " + mobTracker.gaiaChance + "%");
        if(mobTracker.champ>0) lines.add("Minos Champion: " + mobTracker.champ2+ " | " + mobTracker.champChance + "%");
        if(mobTracker.inq>0) lines.add("Inquisitor: " + mobTracker.inq2+ " | " + mobTracker.inqChance + "%");
        if(mobTracker.coins>0) lines.add("Coins: " + mobTracker.coins + "M");
        if(mobTracker.feathers>0) lines.add("Feathers: " + mobTracker.feathers);
        if(mobTracker.totalMobs>0) lines.add("Total Mobs: " + mobTracker.totalMobs);
    }
}


