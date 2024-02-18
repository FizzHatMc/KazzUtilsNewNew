package org.kazz.kazzutils.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import org.kazz.kazzutils.utils.ChatUtils;


@Command(value = "check", description = "c")
public class CheckPlayerBlock {
    @Main
    private void handle() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        BlockPos blockPos = player.playerLocation.down(2);
        Block block = mc.theWorld.getBlockState(blockPos).getBlock();
        ChatUtils.messageToChat(block.toString() + "To String");
        ChatUtils.messageToChat(block.getRegistryName() + "Reg Name");
        if (block.equals(Blocks.air)) ChatUtils.messageToChat("Air at: " + blockPos.getY() + "/" + blockPos.getZ() + "/" + blockPos.getZ()  );
    }
}