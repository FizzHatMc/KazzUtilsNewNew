package org.kazz.kazzutils.features.farming;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.kazz.kazzutils.utils.ChatUtils;

import java.util.Objects;

public class GardenPlots {
    public int[][] gardenPlots = new int[5][5];

    public GardenPlots(){
        gardenPlots[2][3] = 1;
    }



    private Minecraft mc = Minecraft.getMinecraft();
    private boolean isChestOpened = false;

    /*
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent.ClientTickEvent event) {
        GuiScreen screen = mc.currentScreen;
        if(screen instanceof GuiContainer){
            GuiChest guiContainer = (GuiChest) screen;
            Container container = guiContainer.inventorySlots;
            if(container != null && container.inventorySlots != null && container.getInventory() instanceof IInventory && ((IInventory) container.getInventory()).hasCustomName()){
                if(Objects.equals(((IInventory) container.getInventory()).getName(), "Configure Plots")){
                    ChatUtils.messageToChat("You have opened the Configure Plots GUI!");
                }
            }
        }
    }
    */


    private void saveButtonSlots(Container container) {
        for (int i = 0; i < container.inventorySlots.size(); i++) {
            Slot slot = container.getSlot(i);
            ItemStack itemStack = slot.getStack();

            // Check if the item in the slot is a button (You'll need to define criteria for identifying buttons)
            if (isButton(itemStack)) {
                // Save the slot information or handle it as needed
                int slotIndex = slot.slotNumber;
                ChatUtils.messageToChat("Slot " + slotIndex + " is a button!");
                // You can do something with the slot index here
            }
        }
    }

    private boolean isButton(ItemStack itemStack) {
        // Implement your criteria to identify buttons here
        // For example, you might check the item's ID, metadata, or NBT data
        // Return true if the itemStack represents a button, false otherwise
        if (itemStack != null) {
            return Item.getIdFromItem(itemStack.getItem()) == 77;
        }
        return false;
    }
}
