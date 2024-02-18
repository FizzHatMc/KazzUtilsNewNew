package org.kazz.kazzutils.features.dungeon.m7.RenderStuff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.data.dungeon.m7.coords.CrystalCoords;
import org.kazz.kazzutils.utils.CheckCatacombs;
import org.kazz.kazzutils.utils.RenderUtils;

import java.awt.*;

public class Crystals {

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event){
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player.posY > 220 && (Config.dunClass == 1 || Config.dunClass == 3) && (CheckCatacombs.floor.contains("F7") || CheckCatacombs.inM7)) {
            Entity viewer = Minecraft.getMinecraft().getRenderViewEntity();
            double viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * event.partialTicks;
            double viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * event.partialTicks;
            double viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * event.partialTicks;

            BlockPos blockPos = CrystalCoords.getCrystal(Config.dunClass);
            double x = blockPos.getX() - viewerX;
            double y = blockPos.getY() - viewerY;
            double z = blockPos.getZ() - viewerZ;

            Color color = new Color(Config.beamColor.getRGB());

            if (Config.beamWa) RenderUtils.renderBeaconBeam(x, y, z, color.getRGB(), color.getAlpha(), event.partialTicks);
            if (Config.beamTe) RenderUtils.renderWaypointText(Config.crystalT, blockPos, event.partialTicks);
        }
    }
}
