package org.kazz.kazzutils.features.hud;

import cc.polyfrost.oneconfig.hud.BasicHud;
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack;
import cc.polyfrost.oneconfig.renderer.NanoVGHelper;
import net.minecraft.util.ResourceLocation;
import org.kazz.kazzutils.config.Config;

/**
 * An example OneConfig HUD that is started in the config and displays text.
 *
 * @see Config#hud
 */
public class DebugHud extends BasicHud {
    @Override
    protected void draw(UMatrixStack matrices, float x, float y, float scale, boolean example) {
        String filepath = "assets/KazzUtils/Main.png"; //../resources/
        ResourceLocation png = new ResourceLocation("C:\\Users\\Dieen\\Desktop\\coding\\KazzUtils\\src\\main\\resources\\assets\\KazzUtils\\Main.png"); //../resources/
        //NanoVGHelper.INSTANCE.setupAndDraw(true, vg -> NanoVGHelper.INSTANCE.drawImage(vg,filepath, x, y, 50 * scale, 50f * scale));
        NanoVGHelper.INSTANCE.setupAndDraw(true, vg -> {NanoVGHelper.INSTANCE.drawImage(vg,png.getResourcePath(),x,y,50 * scale, 50f * scale);});
    }

    @Override
    protected float getWidth(float scale, boolean example) {
        return 50 * scale;
    }

    @Override
    protected float getHeight(float scale, boolean example) {
        return 50 * scale;
    }
}
