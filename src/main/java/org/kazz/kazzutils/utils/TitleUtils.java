package org.kazz.kazzutils.utils;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static org.kazz.kazzutils.KazzUtils.mc;

public class TitleUtils {
    public static String curString = "";
    public static long endTime = 0;
    public static int color = 0;


    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event) {
        if (System.currentTimeMillis() > endTime) return;
        int width = mc.fontRendererObj.getStringWidth(curString);
        double screenWidth = new ScaledResolution(mc).getScaledWidth_double();
        double screenHeight = new ScaledResolution(mc).getScaledHeight_double();
        double scale = ((screenWidth - 100) * 0.7F) / width;
        scale = Math.min(scale, 10.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate((screenWidth / 2 - width * scale / 2), screenHeight / 2 - (4.5 * scale), 0.0);
        GlStateManager.scale(scale, scale, scale);
        mc.fontRendererObj.drawString(curString, 0f, 0f, color, true);
        GlStateManager.popMatrix();
    }

    public static void drawStringForTime(String string, int time, int Color) {
        curString = string;
        endTime = (long) time + System.currentTimeMillis();
        color = Color;
    }


}
