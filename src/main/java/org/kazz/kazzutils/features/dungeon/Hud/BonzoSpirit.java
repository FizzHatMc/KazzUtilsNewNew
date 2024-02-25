package org.kazz.kazzutils.features.dungeon.Hud;



import cc.polyfrost.oneconfig.events.EventManager;
import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent;
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
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
/*
    @Subscribe
    public void onChatReceive(ChatReceiveEvent event) {
        String unformat = event.message.getUnformattedText();
        if(unformat.contains("Your Bonzo's Mask saved your life!") || unformat.contains("Your ⚚ Bonzo's Mask saved your life!")){
            bonzoPopped = true;
            bonzoTime = System.currentTimeMillis();
            renderText();

        }
    }

 */

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void onChatReceive(ClientChatReceivedEvent event){
        String unformatted = event.message.getUnformattedText();
        if(unformatted.contains("Your Bonzo's Mask saved your life!") || unformatted.contains("Your ⚚ Bonzo's Mask saved your life!")){
            renderText();
        }
    }

    private void renderText(){
        World world = Minecraft.getMinecraft().theWorld;
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        long test = (System.currentTimeMillis()-this.bonzoTime)-3; // String.valueOf(test)
        RenderUtils.showTitle(EnumChatFormatting.RED + "Bonzo Mask");
        world.playSound(player.posX, player.posY, player.posZ, "random.orb",1F,1F,false);
    }





}

