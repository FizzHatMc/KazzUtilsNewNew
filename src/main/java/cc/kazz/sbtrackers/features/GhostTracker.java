package cc.kazz.sbtrackers.features;


import cc.kazz.sbtrackers.config.Config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


import static cc.kazz.sbtrackers.config.Config.GhostTrackerSwitch;


public class GhostTracker {

    //Config config = new Config();
    public int sorrowCount;
    public boolean sorrowBool;
    public int voltaCount = 1;
    public boolean voltaBool;
    public boolean plasmaBool;
    public int plasmaCount;
    public int ghostlyCount;
    public boolean ghostlyBool;
    public boolean coinBool;
    public int coinCount;
    public int count;
    public int timer;
    public int timeMin;
    //private String test;
    // private int kills;
    public double totalCoins;
    public double coinsHour;
    //private String testx;
    //private int index;


/*
    @SubscribeEvent
    public void reset(){
        sorrowBool = false;
        sorrowCount = 0;
        voltaBool = false;
        voltaCount = 0;
        plasmaBool = false;
        plasmaCount = 0;
        ghostlyBool = false;
        ghostlyCount = 0;
        coinBool = false;
        coinCount = 0;
        timer = 0;
        totalCoins = 0;
    }

 */




    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {




        if ((voltaBool || sorrowBool || plasmaBool || ghostlyBool ||coinBool )&& GhostTrackerSwitch) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (player != null) {
                count++;
                if (count % 480 == 0) {
                    timer++;
                    timeMin = timer / 60;
                }
            }
        }

        coinsHour = (totalCoins / timeMin)*60;
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();

/*
        if(message.contains("test")) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            ItemStack itemInHand = player.inventory.getCurrentItem();
            test = itemInHand.getTagCompound().getCompoundTag("display").getTagList("Lore", 8).get(1).toString();
            if (test.contains("Kills:")) {


                testx= test.replaceAll("[^Kills:]","");
                kills = test.charAt(index+2);

            }
        }

 */
        if (GhostTrackerSwitch) {


            if (message.contains("RARE DROP! Sorrow")) {
                sorrowCount += 1;
                sorrowBool = true;
                double sorrowWorth = 0.4;
                totalCoins = totalCoins + sorrowWorth;
            }


            if (message.contains("RARE DROP! Volta")) {
                voltaCount += 1;
                voltaBool = true;
                double voltaWorth = 0.06;
                totalCoins = totalCoins + voltaWorth;
            }


            if (message.contains("RARE DROP! Plasma")) {
                plasmaCount += 1;
                plasmaBool = true;
                double plasmaWorth = 0.02;
                totalCoins = totalCoins + plasmaWorth;
            }


            if (message.contains("RARE DROP! Ghostly Boots")) {
                ghostlyCount += 1;
                ghostlyBool = true;
                double bootsWorth = 0.077;
                totalCoins = totalCoins + bootsWorth;
            }


            if (message.contains("The ghost")) {
                coinCount += 1;
                coinBool = true;
                totalCoins = totalCoins + 1;
            }







        }
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        double roundHour = Math.floor(coinsHour * 100)/100;
        double roundCoins = Math.floor(totalCoins * 100)/100;

        int y = 5;
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;

        if ((voltaBool || sorrowBool || plasmaBool || ghostlyBool ||coinBool) && GhostTrackerSwitch) {

            fRender.drawStringWithShadow(EnumChatFormatting.DARK_GRAY + "Ghost Tracker",5, y,0);
            y = y+10;
            fRender.drawStringWithShadow(EnumChatFormatting.DARK_GRAY + "Timer: "+timeMin +" Minuten",5, y,0);
            y = y+10;


            if (voltaBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.BLUE + "    Volta: " + EnumChatFormatting.WHITE + voltaCount, 5, y, 0);
                y = y + 10;
            }
            if (sorrowBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.AQUA + "    Sorrow: " + EnumChatFormatting.WHITE + sorrowCount, 5, y, 0);
                y = y + 10;
            }
            if (plasmaBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.DARK_AQUA + "    Plasma: " + EnumChatFormatting.WHITE + plasmaCount, 5, y, 0);
                y = y + 10;
            }
            if (ghostlyBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.GRAY + "    Ghostly Boots: " + EnumChatFormatting.WHITE + ghostlyCount, 5, y, 0);
                y = y + 10;
            }
            if (coinBool) {
                fRender.drawStringWithShadow(EnumChatFormatting.GOLD + "    Coin drops: " + EnumChatFormatting.WHITE + coinCount, 5, y, 0);
                y = y + 10;
            }
            if (totalCoins>0) {
                fRender.drawStringWithShadow(EnumChatFormatting.GOLD + "    Total Coins: " + EnumChatFormatting.GOLD + roundCoins + "M", 5, y, 0);
                y = y + 10;
            }
            if (coinsHour > 0 && coinsHour != Double.POSITIVE_INFINITY && Config.coinsPerHour) {
                fRender.drawStringWithShadow(EnumChatFormatting.GOLD + "    Coins/h: " + EnumChatFormatting.GOLD + roundHour + "M", 5, y, 0);

            }


        }
    }
}