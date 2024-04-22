package org.kazz.kazzutils.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import static org.kazz.kazzutils.KazzUtils.mc;

public class ChatUtils {
    public static void messageToChat(String message){
        mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }

    public static void userMessage(String message){
        mc.thePlayer.sendChatMessage(message);
    }

    public static void messageToChat(EnumChatFormatting e, String message){
        mc.thePlayer.addChatMessage(new ChatComponentText(e+message));
    }

    public static void userError(String message){
        mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+message));
    }

}
