package org.kazz.kazzutils.features.dungeon.m7.RenderStuff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.CheckCatacombs;
import org.kazz.kazzutils.utils.RenderUtils;

public class Colorblind {
    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event){
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player.posY < 50  && (CheckCatacombs.floor.contains("F7") || CheckCatacombs.inM7) && Config.colorBlind) {
            for(CheckCatacombs.WitherKingDragons drag : CheckCatacombs.WitherKingDragons.values()){
                RenderUtils.renderWaypointText(drag.getEnumChatFormatting() + drag.getTextColor(), drag.getDragonText(), event.partialTicks,Config.cbScale);
            }
        }
    }
}
