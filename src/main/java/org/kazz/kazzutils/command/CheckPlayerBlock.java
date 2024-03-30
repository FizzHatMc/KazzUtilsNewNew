package org.kazz.kazzutils.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import org.kazz.kazzutils.utils.TitleUtils;


@Command(value = "check", description = "c")
public class CheckPlayerBlock {
    @Main
    private void handle() {
        TitleUtils.drawStringForTime("Test",1500,0xFF00000);

    }





}




