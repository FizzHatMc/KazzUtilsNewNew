package org.kazz.kazzutils.features.dungeon.Hud;



import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.RenderUtils;


public class BonzoSpirit {
    public BonzoSpirit(){
        EventManager.INSTANCE.register(this);
    }

    private boolean bonzoPopped;
    private boolean spiritPopped;
    private long bonzoTime;

    @Subscribe
    public void onChatReceive(ChatReceiveEvent event) {
        String unformat = event.message.getUnformattedText();

        if(unformat.contains("Your Bonzo's Mask saved your life!") || unformat.contains("Your ⚚ Bonzo's Mask saved your life!")){
            bonzoPopped = true;
            bonzoTime = System.currentTimeMillis();
            renderText();

        }
    }

    private void renderText(){
        long test = (System.currentTimeMillis()-this.bonzoTime)-3; // String.valueOf(test)
        RenderUtils.showTitle(EnumChatFormatting.RED + "Bonzo Mask","",0,1,0);
        RenderUtils.showTitle(EnumChatFormatting.BOLD + String.valueOf(test),"",0,5,0);

    }



}

