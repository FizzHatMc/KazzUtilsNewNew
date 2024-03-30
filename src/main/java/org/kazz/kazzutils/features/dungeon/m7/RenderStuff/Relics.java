package org.kazz.kazzutils.features.dungeon.m7.RenderStuff;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.data.dungeon.m7.coords.CauldronCoords;
import org.kazz.kazzutils.data.dungeon.m7.coords.RelicCoords;
import org.kazz.kazzutils.utils.CheckCatacombs;
import org.kazz.kazzutils.utils.RenderUtils;

import java.awt.*;

import static org.kazz.kazzutils.KazzUtils.mc;

public class Relics {




    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event){
        EntityPlayerSP player = mc.thePlayer;
        Entity viewer = mc.getRenderViewEntity();
        //Relic
        if (player.posY < 50  && (CheckCatacombs.floor.contains("F7") || CheckCatacombs.inM7())) {
            double viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * event.partialTicks;
            double viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * event.partialTicks;
            double viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * event.partialTicks;

            BlockPos blockPos = RelicCoords.getRelic(Config.dunClass);
            double x = blockPos.getX() - viewerX;
            double y = blockPos.getY() - viewerY;
            double z = blockPos.getZ() - viewerZ;
            CheckCatacombs.WitherKingDragons drag = null;
            switch(Config.dunClass){
                case 0:
                    drag = CheckCatacombs.WitherKingDragons.APEX;
                    break;

                case 1:
                    drag = CheckCatacombs.WitherKingDragons.ICE;
                    break;

                case 2:
                    drag = CheckCatacombs.WitherKingDragons.SOUL;
                    break;

                case 3:
                    drag = CheckCatacombs.WitherKingDragons.FLAME;
                    break;

                case 4:
                    drag = CheckCatacombs.WitherKingDragons.POWER;
                    break;
            }

            Color color = new Color(Config.beamColor.getRGB());

            if (Config.beamWa) {
                assert drag != null;
                if (!drag.isDestroyed()) {
                    RenderUtils.renderBeaconBeam(x, y, z, color.getRGB(), color.getAlpha(), event.partialTicks);
                }
            }
            if (Config.beamTe) {
                assert drag != null;
                if (!drag.isDestroyed()) {
                    RenderUtils.renderWaypointText(Config.relicT, blockPos, event.partialTicks);
                }
            }
        }
        //Cauldron
        if (player.posY < 50  && (CheckCatacombs.floor.contains("F7") || CheckCatacombs.inM7())) {
            double viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * event.partialTicks;
            double viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * event.partialTicks;
            double viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * event.partialTicks;

            BlockPos blockPos = RelicCoords.getRelic(Config.dunClass);
            double x = blockPos.getX() - viewerX;
            double y = blockPos.getY() - viewerY;
            double z = blockPos.getZ() - viewerZ;
            CheckCatacombs.WitherKingDragons drag = null;
            switch(Config.dunClass){
                case 0:
                    drag = CheckCatacombs.WitherKingDragons.APEX;
                    break;

                case 1:
                    drag = CheckCatacombs.WitherKingDragons.ICE;
                    break;

                case 2:
                    drag = CheckCatacombs.WitherKingDragons.SOUL;
                    break;

                case 3:
                    drag = CheckCatacombs.WitherKingDragons.FLAME;
                    break;

                case 4:
                    drag = CheckCatacombs.WitherKingDragons.POWER;
                    break;
            }

            BlockPos pos = CauldronCoords.getRelic(Config.dunClass);

            if (Config.cauldron) {
                assert drag != null;
                if (!drag.getPicked()) {

                    RenderUtils.drawCustomBox(pos.getX(),1.0,pos.getY()+1,1.0,pos.getZ(), 1.0, drag.getColor(),3f,true);
                }
            }

        }
    }
}
