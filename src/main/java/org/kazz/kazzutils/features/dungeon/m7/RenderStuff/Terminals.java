package org.kazz.kazzutils.features.dungeon.m7.RenderStuff;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.data.dungeon.m7.coords.TerminalCoords;
import org.kazz.kazzutils.utils.CheckCatacombs;
import org.kazz.kazzutils.utils.RenderUtils;

import java.awt.*;


public class Terminals {
    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event){
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;


        if(player.posY < 145 && player.posY > 100 && player.posX < 115 && player.posX > 0 && player.posZ < 150 && player.posZ > 20 && (CheckCatacombs.floor.contains("F7") || CheckCatacombs.inM7)){


            Entity viewer = Minecraft.getMinecraft().getRenderViewEntity();
            TerminalCoords cClass = new TerminalCoords();

            double viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * event.partialTicks;
            double viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * event.partialTicks;
            double viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * event.partialTicks;

            switch (Config.dunClass){
                case 0:
                    for (int i = 0; i<4; i++){
                        BlockPos blockPos = cClass.getTankCoords(i);
                        double x = blockPos.getX() - viewerX;
                        double y = blockPos.getY() - viewerY;
                        double z = blockPos.getZ() - viewerZ;

                        Color color = new Color(Config.beamColor.getRGB());

                        if(Config.beamWa) RenderUtils.renderBeaconBeam(x,y,z,color.getRGB(),color.getAlpha(),event.partialTicks);
                        if(Config.beamTe) RenderUtils.renderWaypointText(Config.termT,blockPos,event.partialTicks);
                    }
                    break;

                case 1:
                    for (int i = 0; i<4; i++){
                        BlockPos blockPos = cClass.getMageCoords(i);
                        double x = blockPos.getX() - viewerX;
                        double y = blockPos.getY() - viewerY;
                        double z = blockPos.getZ() - viewerZ;

                        Color color = new Color(Config.beamColor.getRGB());

                        if(Config.beamWa) RenderUtils.renderBeaconBeam(x,y,z,color.getRGB(),color.getAlpha(),event.partialTicks);
                        if(Config.beamTe) RenderUtils.renderWaypointText(Config.termT,blockPos,event.partialTicks);
                    }
                    break;

                case 2:
                    for (int i = 0; i<4; i++){
                        BlockPos blockPos = cClass.getHealerCoords(i);
                        double x = blockPos.getX() - viewerX;
                        double y = blockPos.getY() - viewerY;
                        double z = blockPos.getZ() - viewerZ;

                        Color color = new Color(Config.beamColor.getRGB());

                        if(Config.beamWa) RenderUtils.renderBeaconBeam(x,y,z,color.getRGB(),color.getAlpha(),event.partialTicks);
                        if(Config.beamTe) RenderUtils.renderWaypointText(Config.termT,blockPos,event.partialTicks);
                    }
                    break;

                case 3:
                    for (int i = 0; i<4; i++){
                        BlockPos blockPos = cClass.getBersCoords(i);
                        double x = blockPos.getX() - viewerX;
                        double y = blockPos.getY() - viewerY;
                        double z = blockPos.getZ() - viewerZ;

                        Color color = new Color(Config.beamColor.getRGB());

                        if(Config.beamWa) RenderUtils.renderBeaconBeam(x,y,z,color.getRGB(),color.getAlpha(),event.partialTicks);
                        if(Config.beamTe) RenderUtils.renderWaypointText(Config.termT,blockPos,event.partialTicks);
                    }
                    break;

                case 4:
                    for (int i = 0; i<4; i++){
                        BlockPos blockPos = cClass.getArchCoords(i);
                        double x = blockPos.getX() - viewerX;
                        double y = blockPos.getY() - viewerY;
                        double z = blockPos.getZ() - viewerZ;

                        Color color = new Color(Config.beamColor.getRGB());

                        if(Config.beamWa) RenderUtils.renderBeaconBeam(x,y,z,color.getRGB(),color.getAlpha(),event.partialTicks);
                        if(Config.beamTe) RenderUtils.renderWaypointText(Config.termT,blockPos,event.partialTicks);
                    }
                    break;

                default:
                    break;
            }

        }
    }
}
