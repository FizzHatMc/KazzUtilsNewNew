package org.kazz.kazzutils.features.hud;

import cc.polyfrost.oneconfig.hud.TextHud;
import org.kazz.kazzutils.utils.TabUtils;

import java.util.List;

public class PetOverlay extends TextHud {

    public PetOverlay() {
        super(false);
    }

    @Override
    protected void getLines(List<String> lines, boolean example) {
        if(example) lines.add(0,"Debug");

        lines.add(0, TabUtils.petName);
        lines.add(1, TabUtils.petXp);
    }
}

/*


public PetOverlay() {
    super("Pet Overlay",false);
}

@Override
protected String getText(boolean example) {
    if(example) return "Pet Overlay";
    String text = TabUtils.petName + TabUtils.petXp;
    return text;
}

@Override
public void getLines(List<String> lines, boolean example) {
    if(Objects.equals(TabUtils.petName, "") || Objects.equals(TabUtils.petXp, "")) return;
    if(example) lines.add(0,"Pet Overlay"); lines.add(1,"XP");

    lines.add(0, TabUtils.petName);
    lines.add(0, TabUtils.petXp);
    lines.add(1, TabUtils.petXp);
}



 */

