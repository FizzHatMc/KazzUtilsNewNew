package org.kazz.kazzutils.features.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.ItemUtils;
import org.kazz.kazzutils.utils.RenderUtils;

import java.awt.*;
import java.util.Objects;

public class GyroRange {

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        ItemStack heldItem = ItemUtils.INSTANCE.getHeldItem();
        String itemId = heldItem != null ? ItemUtils.INSTANCE.getItemID(heldItem) : null;
        Color color = Config.gyroRangeColor.toJavaColor();

        if(!Config.gyroRange)return;
        if(!Objects.equals(itemId, "GYROKINETIC_WAND"))return;
        BlockPos pos = mc.thePlayer.rayTrace(25.0,event.partialTicks).getBlockPos();
        Block block = mc.theWorld.getBlockState(pos).getBlock();
        if(block.isAir(mc.theWorld,pos))return;

        RenderUtils.drawCylinder(new Vec3(pos).addVector(0.5,1.0,0.5),
    10f,10f-Config.gyroRangeScale,0.2f,40,1,0f,0f,0f,
                color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255F, color.getAlpha() / 255f,
                false,false);

    }

}
