package org.kazz.kazzutils.features.dungeon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.RenderUtils;
import org.kazz.kazzutils.utils.TabUtils;

import java.awt.*;
import java.util.Objects;

import static org.kazz.kazzutils.KazzUtils.mc;
import static org.kazz.kazzutils.utils.TabUtils.getPlayerByName;


public class ClassHighlight {
    @SubscribeEvent(receiveCanceled = true)
    public void onWorldRender(RenderWorldLastEvent event){

        if(Objects.equals(TabUtils.area, "Catacombs")) {
            String className = null;
            Color color = Config.highlightClassColor.toJavaColor();
            switch (Config.highlightClass) {
                case 0:
                    className = TabUtils.tankName;
                    break;
                case 1:
                    className = TabUtils.mageName;
                    break;
                case 2:
                    className = TabUtils.healerName;
                    break;
                case 3:
                    className = TabUtils.berserkerName;
                    break;
                case 4:
                    className = TabUtils.archerName;
                    break;
            }


            if (className == null) return;
            EntityPlayer player = getPlayerByName(className);
            if (player == null) return;
            if (player == mc.thePlayer) return;

            AxisAlignedBB boundingBox = player.getEntityBoundingBox();
            RenderUtils.drawOutlinedBoundingBox(boundingBox, color, 3.69F, event.partialTicks);

        }

    }
}
