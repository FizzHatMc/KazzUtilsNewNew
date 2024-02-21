package org.kazz.kazzutils.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.TabUtilsKotlin;


@Command(value = "check", description = "c")
public class CheckPlayerBlock {
    @Main
    private void handle() {
        ChatUtils.messageToChat(TabUtilsKotlin.INSTANCE.getTrimmed());
        ChatUtils.messageToChat(TabUtilsKotlin.INSTANCE.getGardenMilestone());
        ChatUtils.messageToChat(String.valueOf(TabUtilsKotlin.INSTANCE.getNumVisitors()));
    }
}