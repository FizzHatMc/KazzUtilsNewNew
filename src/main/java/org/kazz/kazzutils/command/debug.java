package org.kazz.kazzutils.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import org.kazz.kazzutils.utils.CheckCatacombs;

@Command(value = "debug", description = "Opens Kazz MOD Gui")
public class debug {
    @Main
    private void handle() {
        CheckCatacombs.checkCata2();
    }
}