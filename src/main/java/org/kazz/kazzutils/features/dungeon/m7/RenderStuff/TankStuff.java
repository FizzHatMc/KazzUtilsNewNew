package org.kazz.kazzutils.features.dungeon.m7.RenderStuff;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.RenderUtils;
import org.kazz.kazzutils.utils.TabUtils;

import java.awt.*;
import java.util.Objects;

import static org.kazz.kazzutils.utils.TabUtils.getPlayerByName;

public class TankStuff {



    @SubscribeEvent
    public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        String tankName = TabUtils.tankName;
        EntityPlayer tank = getPlayerByName(tankName);
        Color color = Config.tankRangeColor.toJavaColor();


        float radius = 30F;
        if(Config.boneNecklace) radius += 15F;
        if(Config.tankRange && tank!= null && Objects.equals(TabUtils.area, "Catacombs")) {
            BlockPos pos = new BlockPos(tank.posX, tank.posY, tank.posZ);
            RenderUtils.drawCylinder(new Vec3(pos).addVector(0.5, 1.0, 0.5), radius, radius,
                    60F, 40, 1, 0F, 0F, 0F,
                    color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255F, color.getAlpha() / 255F,
                    false, false
            );

        }


    }
}
