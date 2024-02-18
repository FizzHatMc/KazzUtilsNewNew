package org.kazz.kazzutils.features.dungeon.m7.RenderStuff;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.CheckCatacombs;
import org.kazz.kazzutils.utils.RenderUtils;
import net.minecraft.util.Vec3;

public class DragonsBoxesLines {

    @SubscribeEvent(receiveCanceled = true)
    public void onWorldRender(RenderWorldLastEvent event){
        /*
        if(Minecraft.getMinecraft().thePlayer.posY<50 && Config.dragLines){
            drawLines(Lines.getOrangeLinePos(), Color.orange,event);
            drawLines(Lines.getGreenLinePos(), Color.GREEN,event);
            drawLines(Lines.getRedLinePos(), Color.RED,event);
            drawLines(Lines.getBlueLinePos(), Color.CYAN,event);
        }
         */

        for(CheckCatacombs.WitherKingDragons drag : CheckCatacombs.WitherKingDragons.values()) {
            if(drag.isDestroyed()) continue;
            if(!Config.dragLines) break;
            if(!(Minecraft.getMinecraft().thePlayer.posY<50)) break;
            BlockPos[] blockPos = drag.getLineCoords();
            for (int i = 1; i < blockPos.length; i++) {

                RenderUtils.drawLine(
                        new Vec3(blockPos[i-1].getX(), blockPos[i-1].getY(), blockPos[i-1].getZ()).addVector(
                                1.0,
                                1.0,
                                1.0
                        ),
                        new Vec3(blockPos[i].getX(), blockPos[i].getY(), blockPos[i].getZ()).addVector(
                                1.0,
                                1.0,
                                1.0
                        ),
                        drag.getColor(),
                        event.partialTicks
                );
            }
        }

        for(CheckCatacombs.WitherKingDragons drag : CheckCatacombs.WitherKingDragons.values()){
            if(drag.isDestroyed()) continue;
            if(!Config.dragBox) break;
            if(!(Minecraft.getMinecraft().thePlayer.posY<50)) break;
            RenderUtils.drawOutlinedBoundingBox(drag.getBb(),drag.getColor(),3.69F,event.partialTicks);
        }
    }

}
