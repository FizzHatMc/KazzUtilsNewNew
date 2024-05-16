package org.kazz.kazzutils.utils.TEST;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.kazz.kazzutils.KazzUtils;
import org.kazz.kazzutils.utils.ChatUtils;
import sun.swing.FilePane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.kazz.kazzutils.KazzUtils.mc;

public class ContainerUtils {


    public static List<String> getLore(ItemStack stack) {
        List<String> lore = new ArrayList<>();
        if(stack==null)return lore;
        // Überprüfen, ob der ItemStack eine Lore hat
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("display", 10)) {
            NBTTagCompound display = stack.getTagCompound().getCompoundTag("display");
            if (display.hasKey("Lore", 9)) {
                NBTTagList loreList = display.getTagList("Lore", 8);
                for (int i = 0; i < loreList.tagCount(); ++i) {
                    lore.add(loreList.getStringTagAt(i));
                }
            }
        }

        return lore;
    }



    public static ItemStack checkInventoryForName(String itemName, IInventory inventory2) {
        for (int i = 0; i < inventory2.getSizeInventory(); i++) {
            if(inventory2.getStackInSlot(i) == null) continue;
            if(inventory2.getStackInSlot(i).getDisplayName().contains(itemName)) return inventory2.getStackInSlot(i);
        }
        return null;
    }

    public static List<Slot> getItemsInOpenChest() {
        List<Slot> slots = new ArrayList<>();
        Object screen = mc.currentScreen;
        if (screen instanceof GuiChest) {
            GuiChest guiChest = (GuiChest) screen;
            Container container = guiChest.inventorySlots;
            if (container instanceof ContainerChest) {
                ContainerChest chest = (ContainerChest) container;
                for (Object slotObj : chest.inventorySlots) {
                    if (slotObj instanceof Slot) {
                        Slot slot = (Slot) slotObj;
                        if (slot.inventory instanceof InventoryPlayer) {
                            break;
                        }
                        if (slot.getStack() != null) {
                            slots.add(slot);
                        }
                    }
                }
            }
        }
        return slots;
    }



    public static String openInventoryName() {
        Object screen = mc.currentScreen;
        if (screen instanceof GuiChest) {
            GuiChest guiChest = (GuiChest) screen;
            Container container = guiChest.inventorySlots;
            if (container instanceof ContainerChest) {
                ContainerChest chest = (ContainerChest) container;
                return chest.getLowerChestInventory().getDisplayName().getFormattedText().trim();
            }
        }
        return "";
    }


    public static GuiContainer getCurrentScreen(){
        if (mc.currentScreen instanceof GuiContainer) {
            return (GuiContainer) mc.currentScreen;
        }
        return null;
    }
}


// Quiver