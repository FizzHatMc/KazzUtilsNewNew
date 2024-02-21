package org.kazz.kazzutils.utils;

import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.kazz.kazzutils.handler.ScoreboardHandler;

import java.awt.*;
import java.util.List;

public class CheckCatacombs {
    private static String lastmsg;
    public static boolean inM7 = false;
    public static String floor = "n";
    public static final double a = 13.5;
    public static final BlockPos[] red = {new BlockPos(27,14,58), new BlockPos(40,20,45)};
    public static final BlockPos[] orange = {new BlockPos(84,14,55), new BlockPos(77,18,59), new BlockPos(81,19,68)};
    public static final BlockPos[] green = {new BlockPos(27,15,94), new BlockPos(24,19,82)};
    public static final BlockPos[] blue = {new BlockPos(84,14,94), new BlockPos(80,18,104)};
    public static final BlockPos[] purp = {new BlockPos(56, 14, 125), new BlockPos(56, 14, 125)};


    /*public static void checkCata() {
        List<String> scoreboard = ScoreboardHandler.getSidebarLines();
        for (String s : scoreboard) {
            String sCleaned = ScoreboardHandler.cleanSB(s);
            if (sCleaned.contains("Keys:")) {
                List<String> sBoard = ScoreboardHandler.getSidebarLines();
                for (String t : sBoard) {
                    String tCleaned = ScoreboardHandler.cleanSB(t);
                    if (tCleaned.contains("The Catacombs")) {
                        floor = sCleaned.substring(sCleaned.indexOf("(") + 1, sCleaned.indexOf(")"));
                        if (floor.equals("M7")) inM7 = true;
                    }
                }
            } else if (sCleaned.contains("Time Elapsed:")) {
                List<String> sBoard = ScoreboardHandler.getSidebarLines();
                for (String t : sBoard) {
                    String tCleaned = ScoreboardHandler.cleanSB(t);
                    if (!tCleaned.contains("The Catacombs")) {
                        inM7 = true;
                    }
                }
            }
        }
    }

     */

    public static void checkCata2() {
        List<String> scoreboard = ScoreboardHandler.getSidebarLines();
        for (String s : scoreboard) {
            String sCleaned = ScoreboardHandler.cleanSB(s);
            if (sCleaned.contains("The Catacombs")) {
                floor = sCleaned.substring(sCleaned.indexOf("(") + 1, sCleaned.indexOf(")"));
                if (floor.equals("M7")) inM7 = true;
            }
        }
    }

    //

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().theWorld != null) {
            for (CheckCatacombs.WitherKingDragons drag : CheckCatacombs.WitherKingDragons.values()) {
                Block block = Minecraft.getMinecraft().theWorld.getBlockState(drag.getDetectBlock()).getBlock();
                //RenderUtils.renderBoxOutline(drag.getBlockPos(),1,1,1,event.partialTicks,1,Color.RED,1F);
                if (block.equals(Blocks.cobblestone)) {
                    drag.setDestroyed(false);
                }else drag.setDestroyed(true);
            }

            GuiPlayerTabOverlay tabOverlay = Minecraft.getMinecraft().ingameGUI.getTabList();
        }
    }





    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent event) {
        lastmsg = event.message.getFormattedText();
    }


    public enum WitherKingDragons {

        POWER("Red", new BlockPos(27, 14, 59), Color.RED, ChatColor.RED, red,new BlockPos(20,6,59), EnumChatFormatting.RED),
        APEX("Green", new BlockPos(27, 14, 94), Color.GREEN, ChatColor.GREEN, green, new BlockPos(20,6,94), EnumChatFormatting.GREEN),
        SOUL("Purple", new BlockPos(56, 14, 125), Color.MAGENTA, ChatColor.DARK_PURPLE, purp, new BlockPos(56,8,132), EnumChatFormatting.LIGHT_PURPLE),
        ICE("Blue", new BlockPos(84, 14, 94), Color.CYAN, ChatColor.AQUA, blue, new BlockPos(91,6,94), EnumChatFormatting.BLUE),
        FLAME("Orange", new BlockPos(85, 14, 56), Color.ORANGE, ChatColor.GOLD, orange, new BlockPos(92,6,56), EnumChatFormatting.GOLD);



        private final String textColor;
        private final BlockPos blockPos;
        private final Color color;
        private final ChatColor chatColor;
        private final BlockPos detectBlock;
        private boolean isDestroyed;
        private final String itemName;
        private final String itemId;
        // private final ResourceLocation texture;
        private final AxisAlignedBB bb;
        private final BlockPos particleLocation;
        private BlockPos[] lineCoords;
        private final BlockPos relic;
        private final BlockPos dragonText;
        private final EnumChatFormatting enumChatFormatting;


        public String getTextColor() {
            return textColor;
        }

        // Getter and setter for blockPos
        public BlockPos getBlockPos() {
            return blockPos;
        }

        // Getter and setter for color
        public Color getColor() {
            return color;
        }

        // Getter and setter for detectBlock
        public BlockPos getDetectBlock() {
            return detectBlock;
        }

        // Getter and setter for isDestroyed
        public boolean isDestroyed() {
            return isDestroyed;
        }

        public void setDestroyed(boolean destroyed) {
            isDestroyed = destroyed;
        }

        // Getter and setter for itemName
        public String getItemName() {
            return itemName;
        }

        // Getter and setter for itemId
        public String getItemId() {
            return itemId;
        }

        // Getter and setter for bb
        public AxisAlignedBB getBb() {
            return bb;
        }

        // Getter and setter for particleLocation
        public BlockPos getParticleLocation() {
            return particleLocation;
        }

        public BlockPos[] getLineCoords(){
            return lineCoords;
        }

        public BlockPos getRelic(){
            return relic;
        }

        public BlockPos getDragonText(){
            return dragonText;
        }

        public EnumChatFormatting getEnumChatFormatting(){
            return enumChatFormatting;
        }

        WitherKingDragons(String textColor, BlockPos blockPos, Color color, ChatColor chatColor, BlockPos[] lineCoords, BlockPos relic, EnumChatFormatting enumChatFormatting) {
            this.textColor = textColor;
            this.blockPos = blockPos;
            this.color = color;
            this.chatColor = chatColor;
            this.detectBlock = blockPos.up(13);
            this.isDestroyed = false;
            this.itemName = "§cCorrupted " + this.textColor + " Relic";
            this.itemId = this.textColor.toUpperCase() + "_KING_RELIC";
            // this.texture = new ResourceLocation("skytils", "textures/dungeons/m7/dragon_" + this.name().toLowerCase() + ".png");
            this.bb = new AxisAlignedBB(
                    blockPos.getX() - a,
                    blockPos.getY() - 8.0,
                    blockPos.getZ() - a,
                    blockPos.getX() + a,
                    blockPos.getY() + a + 2,
                    blockPos.getZ() + a
            );
            this.particleLocation = blockPos.up(5);
            this.lineCoords = lineCoords;
            this.relic = relic;
            this.dragonText = relic.up(4);
            this.enumChatFormatting = enumChatFormatting;
        }


    }
}