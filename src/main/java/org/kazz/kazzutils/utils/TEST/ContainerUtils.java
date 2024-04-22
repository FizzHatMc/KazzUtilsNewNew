package org.kazz.kazzutils.utils.TEST;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.kazz.kazzutils.utils.ChatUtils;

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
}


// Quiver