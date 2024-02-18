package org.kazz.kazzutils.features.mining;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.kazz.kazzutils.handler.ScoreboardHandler;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.RenderUtils;

import java.util.List;


public class StarCult {
public static boolean firstTick = false;
    public static void checkCult() {
        List<String> scoreboard = ScoreboardHandler.getSidebarLines();
        for (String s : scoreboard) {
            String sCleaned = ScoreboardHandler.cleanSB(s);
            if(sCleaned.contains("11:50pm")){
                checkDate();
            }
        }
    }

    public static void checkDate(){
        World world = Minecraft.getMinecraft().theWorld;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        List<String> scoreboard = ScoreboardHandler.getSidebarLines();
        for(String t : scoreboard){
            String sCleaned = ScoreboardHandler.cleanSB(t);
            if(sCleaned.contains("6th") || sCleaned.contains("13th") || sCleaned.contains("20th") || sCleaned.contains("27th")){
                firstTick = true;

                ChatUtils.messageToChat(EnumChatFormatting.BLUE + "---------------------------------");
                ChatUtils.messageToChat(EnumChatFormatting.RED +  "------------STAR CULT------------");
                ChatUtils.messageToChat(EnumChatFormatting.BLUE + "--------------------------------");

                RenderUtils.showTitle(EnumChatFormatting.RED + "STAR CULT");

                world.playSound(player.posX, player.posY, player.posZ, "random.orb",1F,1F,false);
                break;
            }else{
                firstTick = false;
            }
        }
    }
}