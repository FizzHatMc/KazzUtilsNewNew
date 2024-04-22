package org.kazz.kazzutils.features.farming.hud;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.TEST.ContainerUtils;

import java.util.ArrayList;
import java.util.List;

import static org.kazz.kazzutils.KazzUtils.mc;

public class Pests {
    List<ItemStack> plots = new ArrayList<ItemStack>();
    List<Integer> plotNumber = new ArrayList<Integer>();


    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(mc.theWorld == null || mc.thePlayer == null) return;
        String screenName = ContainerUtils.openInventoryName();
        if(screenName.contains("Configure Plots")){
            scanPlots();
        }
    }


    private void scanPlots() {
        List<Slot> slots = ContainerUtils.getItemsInOpenChest();
        for(Slot slot : slots) {
            ItemStack stack = slot.getStack();
            String displayName = stack.getDisplayName();
            ChatUtils.messageToChat(stack.getDisplayName() + EnumChatFormatting.RESET + EnumChatFormatting.RED + displayName.contains("Plot -"));
            //ChatUtils.messageToChat(EnumChatFormatting.BLUE + "39, " + stack.getDisplayName() + stack.getDisplayName().contains(EnumChatFormatting.GREEN + "Plot -"));
            if(stack.getDisplayName().contains("Plot -")){
                ChatUtils.messageToChat("True");
                plots.add(stack);
                plotNumber.add(slot.slotNumber);

                ChatUtils.messageToChat(EnumChatFormatting.GREEN + "Plot " + stack.getDisplayName() + " found! Slotnumber: " + slot.slotNumber);
            }
        }
    }
}
