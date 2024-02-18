package org.kazz.kazzutils.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import org.kazz.kazzutils.KazzUtils;


@Command(value = "Kazz", description = "Opens Kazz MOD Gui")
public class openGUI {
    @Main
    private void handle() {
        KazzUtils.config.openGui();
    }
}