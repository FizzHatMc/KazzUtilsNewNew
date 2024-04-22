package org.kazz.kazzutils.features.dungeon.Hud;

import cc.polyfrost.oneconfig.hud.TextHud;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.TEST.ContainerUtils;
import org.kazz.kazzutils.utils.TitleUtils;

import java.awt.*;
import java.util.List;

import static org.kazz.kazzutils.KazzUtils.mc;

public class ArrowSoulflow extends TextHud {
    private long ticks;
    private String arrowType;
    private int amount;

    public ArrowSoulflow() {
        super(false);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatReceive(ClientChatReceivedEvent event){
        if (event.type != 2) return;
        if(mc.thePlayer == null) return;

        String message = event.message.getUnformattedTextForChat();
        if(message.contains("Not enough soulflow!")){
            TitleUtils.drawStringForTime("SOULFLOW",3000, Color.RED.getRGB());
        }
    }

    @Override
    protected void getLines(List<String> lines, boolean example) {
        if(example) lines.add(0,"Arrow Overlay");
        if(mc.thePlayer == null) return;
        if(mc.theWorld == null) return;

        ItemStack quiver = ContainerUtils.checkInventoryForName("Quiver", mc.thePlayer.inventory);
        List<String> quiverLore = ContainerUtils.getLore(quiver);
        if(quiver == null) return;
        for(String tag : quiverLore) {
            if(tag.contains("Active Arrow:")){
                arrowType = tag.substring(tag.indexOf("Active Arrow: ")+"Active Arrow: ".length(), tag.indexOf('('));
                amount = Integer.parseInt(tag.substring(tag.indexOf('(')+3,tag.indexOf(')')-2));
            }
        }

        lines.add(0, arrowType + " / " + amount + "x");
    }
}
