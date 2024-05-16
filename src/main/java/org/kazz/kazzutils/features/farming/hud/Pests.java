package org.kazz.kazzutils.features.farming.hud;

import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.data.farming.Plots;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.RenderUtils;
import org.kazz.kazzutils.utils.TEST.ContainerUtils;
import org.kazz.kazzutils.utils.TabUtils;
import org.kazz.kazzutils.utils.TitleUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.kazz.kazzutils.KazzUtils.mc;

public class Pests {
    List<Slot> plotSlot = new ArrayList<>();
    List<Plots> plots = new ArrayList<>();
    final double a = (double) 48 ;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(mc.theWorld == null || mc.thePlayer == null) return;
        String screenName = ContainerUtils.openInventoryName();
        if(screenName.contains("Configure Plots")){
            plots.clear();
            scanPlots();
        }
    }


    private void scanPlots() {

        List<Slot> slots = ContainerUtils.getItemsInOpenChest();
        for(Slot slot : slots) {
            boolean bought = true;
            int infect = 0;
            ItemStack stack = slot.getStack();
            String displayName = stack.getDisplayName();

            if(displayName.isEmpty() || displayName.contains("Go Back") || displayName.contains("Close") || displayName.contains("The Barn") || displayName.length() < 6) continue;
            String name = displayName.substring(displayName.indexOf("Pest -")+"Pest -".length()+1);

            List<String> lore = ContainerUtils.getLore(stack);
            for(String Lore : lore){
                if(Lore.contains("Requirement")) {
                    bought = false;
                    break;
                }else if(Lore.contains("This plot has")){
                    infect = Integer.parseInt(Lore.substring(Lore.indexOf("has ")+"has ".length()+2,Lore.indexOf("Pest")-1));;
                }
            }

            if(bought && plots.size() <= 24){
                plots.add(new Plots(infect,slot.slotNumber,name));
            }
        }
    }

    @SubscribeEvent()
    public void onChatRecieved(ClientChatReceivedEvent event) {
        if(event.type == 2) return;
        String message = event.message.getFormattedText().trim();
        int pestAmount = 0;
        String name= null;

        if(message.contains("has appeared")){
            // Define regular expressions to extract variables
            Pattern namePattern = Pattern.compile("\\[(.*?)\\]"); // Matches anything inside square brackets
            // Search for matches in the input string
            Matcher nameMatcher = namePattern.matcher(message);
            // Extract the variables if matches are found
            if (nameMatcher.find()) {
                name = nameMatcher.group(1);
            }
            pestAmount = 1;
        } else if (message.contains("have spawned")) {
            // Define regular expressions to extract variables
            Pattern xPattern = Pattern.compile("\\b(\\d+)\\b"); // Matches one or more digits
            Pattern namePattern = Pattern.compile("\\[(.*?)\\]"); // Matches anything inside square brackets

            // Search for matches in the input string
            Matcher xMatcher = xPattern.matcher(message);
            Matcher nameMatcher = namePattern.matcher(message);

            // Extract the variables if matches are found
            if (xMatcher.find()) {
                pestAmount = Integer.parseInt(xMatcher.group(1));
            }
            if (nameMatcher.find()) {
                name = nameMatcher.group(1);
            }
        }

        if(message.contains("has appeared") || message.contains("have spawned")){
            for (Plots plot : plots) {
                if (plot.plotName.equals(name)) {
                    plot.infectedAmount = pestAmount;
                } else
                    ChatUtils.messageToChat(EnumChatFormatting.RED + "Unkown Plot + " + name + ". Please Open Plot Configuration Menu.");
            }
        }

    }

    @SubscribeEvent(receiveCanceled = true)
    public void onWorldRender(RenderWorldLastEvent event){
        if(!Objects.equals(TabUtils.area, "garden")) return;
        for(Plots plot : plots){

            if(plot.infectedAmount>0) {
                BlockPos blockPos = Plots.getBlockPosFromPlotNumber(Plots.slotNumberToPlotNumber(plot.plotNumber));

                RenderUtils.drawOutlinedBoundingBox(new AxisAlignedBB(
                        blockPos.getX() - a,
                        blockPos.getY() - 8.0,
                        blockPos.getZ() - a,
                        blockPos.getX() + a,
                        blockPos.getY() + 10,
                        blockPos.getZ() + a
                ), Color.RED,3.69F,event.partialTicks);
            }
        }
    }
}

/*
for(Plots pl : plots){
            BlockPos blockPos = pl.plotPos;
            final double a = (double) 48 ;
            RenderUtils.drawOutlinedBoundingBox(new AxisAlignedBB(
                    blockPos.getX() - a,
                    blockPos.getY() - 8.0,
                    blockPos.getZ() - a,
                    blockPos.getX() + a,
                    blockPos.getY() + 10,
                    blockPos.getZ() + a
            ), Color.RED,3.69F,event.partialTicks);
        }
 */
