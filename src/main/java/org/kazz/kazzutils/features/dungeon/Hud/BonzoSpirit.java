package org.kazz.kazzutils.features.dungeon.Hud;

import cc.polyfrost.oneconfig.events.EventManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.TitleUtils;

import static org.kazz.kazzutils.KazzUtils.mc;

public class BonzoSpirit {
    public BonzoSpirit(){
        EventManager.INSTANCE.register(this);
    }

    private boolean bonzoPopped;
    private boolean spiritPopped;
    private long bonzoTime;
    private World world = mc.theWorld;
    private EntityPlayer player = mc.thePlayer;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatReceive(ClientChatReceivedEvent event){
        String unformatted = event.message.getUnformattedText();
        if(unformatted.contains("Your Bonzo's Mask saved your life!") || unformatted.contains("Your ⚚ Bonzo's Mask saved your life!")){
            bonzoTime = System.currentTimeMillis();
            if(System.currentTimeMillis() >= bonzoTime+3000) return;
            ChatUtils.messageToChat(EnumChatFormatting.RED+ "BONZO MASK POPPED");
            TitleUtils.drawStringForTime("BONZO POPPED",3000, Config.bonzoColor.getRGB());
            world.playSound(player.posX, player.posY, player.posZ, "random.orb",1F,1F,false);
            //RenderUtils.renderTitle("BONZO POPPED"," ",0,20,5);

        }else if (unformatted.contains("Second Wind Activated! Your Spirit Mask saved your life!") || unformatted.contains("Second Wind Activated! Your ⚚Spirit Mask saved your life!")) {
            bonzoTime = System.currentTimeMillis();
            if(System.currentTimeMillis() >= bonzoTime+3000) return;
            ChatUtils.messageToChat(EnumChatFormatting.RED+ "SPIRIT MASK POPPED");
            TitleUtils.drawStringForTime("SPIRIT MASK POPPED",3000,Config.bonzoColor.getRGB());
            world.playSound(player.posX, player.posY, player.posZ, "random.orb",1F,1F,false);
        }
    }







}

