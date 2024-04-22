package org.kazz.kazzutils.utils;


import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.kazz.kazzutils.KazzUtils.mc;

public class TabUtils {
    private static Pattern areaPattern = Pattern.compile("Area: (.+)");
    private static List<String> tablist = null;
    public static String area = "";
    public static boolean explosivity = false;
    public static boolean maxVisitors = false;
    public static boolean emptyComposter = false;
    public static String gardenMilestone = "";
    public static String timeTillNextVisitor = "";
    public static int numVisitors = 0;
    public static String archerName = "";
    public static String tankName = "";
    public static String healerName = "";
    public static String mageName = "";
    public static String berserkerName = "";
    public static int gardenLevel = 0;
    public static double gardenPercent = 0.0;
    public static String trim = "";
    public static String first = "";
    public static String second = "";
    public static String third = "";
    public static String time = "";
    static playerInfoOrdering p = new playerInfoOrdering();
    private static boolean petFlag = false;
    private static int petFlagCount = 0;
    public static String petName = "";
    public static String petXp = "";
    public static String petNameUnform = "";
    //public static String petXp = "";
    public static Boolean gardenLevelBool = false;
    public static String gardenLevelString = "";
    public static ArrayList<String> comms = new ArrayList<String>();

    public static EntityPlayer getPlayerByName(String playerName) {
        List<EntityPlayer> players = mc.theWorld.playerEntities; // Assuming mc is your Minecraft instance

        for (EntityPlayer player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }

        return null; // Player not found
    }

    @SideOnly(Side.CLIENT)
    public static class playerInfoOrdering extends Ordering<NetworkPlayerInfo> {


        @Override
        public int compare(NetworkPlayerInfo info1, NetworkPlayerInfo info2) {
            if (info1 == null) return -1;
            if (info2 == null) return 0;
            return ComparisonChain.start()
                    .compareTrueFirst(!isSpectator(info1.getGameType()), !isSpectator(info2.getGameType()))
                    .compare(info1.getPlayerTeam() != null ? info1.getPlayerTeam().getRegisteredName() : "",
                            info2.getPlayerTeam() != null ? info2.getPlayerTeam().getRegisteredName() : "")
                    .compare(info1.getGameProfile().getName(), info2.getGameProfile().getName())
                    .result();
        }
    }


    private static List<NetworkPlayerInfo> fetchTabEntries(){
        List<NetworkPlayerInfo> entries = emptyList();
        if(mc.thePlayer != null){

            entries = p.sortedCopy(mc.thePlayer.sendQueue.getPlayerInfoMap());
        }
        return entries;
    }


    public static void parseTabEntries(){
        comms.clear();
        if(mc.thePlayer == null) return;
        if(mc.theWorld == null) return;

        List<String> scoreboardList = mapNotNull(fetchTabEntries());

        for(String line : scoreboardList){
            trim = line.trim();

            Matcher matcher = areaPattern.matcher(trim);
            if (matcher.find()) {
                area = matcher.group(1);
            }

            if(petFlag && petFlagCount <= 1){
                if(petFlagCount == 0) petName = line.substring(trim.indexOf(']')+2);
                if(petFlagCount == 1) petXp = line;

                petFlagCount++;
            }else{
                petFlagCount = 0;
                petFlag = false;
            }


            if (trim.contains("Contasdasdest:")) {
                //System.out.println(trim);
                if (scoreboardList == null) {
                    return;
                }
                int index = scoreboardList.indexOf(line) + 1;
                if (index == -1) {
                    return;
                }
                time = scoreboardList.get(index).substring(scoreboardList.get(index).indexOf(": ") + 2);
                index++;
                first = scoreboardList.get(index);
                index++;
                second = scoreboardList.get(index);
                index++;
                third = scoreboardList.get(index);
            } else if (trim.contains("Garden Level:")) {
                String split = trim.substring(trim.indexOf(":")+1);
                String lvl = split.split(" ")[1];
                lvl = lvl.replace(" ", "");
                Integer test = NumberUtils.getNumber(lvl);

                if(test != null) {
                    //gardenLevel = Integer.parseInt(trim.substring(trim.indexOf(":") + 2 /*trim.indexOf(":") + 4*/).replace(" ", ""));
                    gardenLevel = test.intValue();
                    if (gardenLevel != 15) {
                        gardenPercent = Double.parseDouble(trim.substring(trim.indexOf("(") + 1, trim.indexOf(")") - 7));
                    }
                }else{
                    if(!Objects.equals(lvl, "XV")){
                        //String percent = split.split(" ")[1];
                        gardenLevel = NumberUtils.toInteger(lvl);
                        gardenPercent = Double.parseDouble(trim.substring(trim.indexOf("(") + 1, trim.indexOf(")") - 1));
                    }else gardenLevel= NumberUtils.toInteger("XV");
                }

            }else if(trim.contains("Pet:")){
                 petFlag = true;
            }else if(trim.contains("Dungeon: Catacombs")){
                area = "Catacombs";
            }else if(trim.contains("Commissions:")){
                area = "Dwarven";
                int index2 = scoreboardList.indexOf(line) +1 ;
                comms.add(scoreboardList.get(index2));
                index2++;
                comms.add(scoreboardList.get(index2));
                index2++;
                comms.add(scoreboardList.get(index2));
                index2++;
                comms.add(scoreboardList.get(index2));
            }

            if (line.contains("(Archer")) {
                archerName = line.split(" ")[1];
            } else if (line.contains("(Tank")) {
                tankName = line.split(" ")[1];
            } else if (line.contains("(Mage")) {
                mageName = line.split(" ")[1];
            } else if (line.contains("(Healer")) {
                healerName = line.split(" ")[1];
            } else if (line.contains("(Berserker")) {
                berserkerName = line.split(" ")[1];
            }

        }
    }

    private static boolean isSpectator(WorldSettings.GameType gameType) {
        return gameType == WorldSettings.GameType.SPECTATOR;
    }

    private static List<String> mapNotNull(List<NetworkPlayerInfo> tabEntries) {
        return tabEntries.stream()
                .map(info -> info.getDisplayName() != null ? info.getDisplayName().getUnformattedText() : null)
                .filter(displayName -> displayName != null)
                .collect(Collectors.toList());
    }

    Collection<NetworkPlayerInfo> playerOrdering = null;

    public List<String> readTabList(){
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
        playerOrdering  = thePlayer.sendQueue.getPlayerInfoMap();
        List<String> result = new ArrayList<>();
        for(NetworkPlayerInfo info : playerOrdering){
            String name = mc.ingameGUI.getTabList().getPlayerName(info);
            result.add(stripVanillaMessage(name));
        }
        if (!result.isEmpty()) {
            result.remove(result.size() - 1);
        }
        return result;
    }

    private String stripVanillaMessage(String orignalMessage){
        String message = orignalMessage;
        while (message.startsWith("§r")) {
            message = message.substring(2);
        }
        while (message.endsWith("§r")) {
            message = message.substring(0, message.length() - 2);
        }
        return message;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        tablist = readTabList();
        for(String t : tablist){
            //System.out.println(t);
        }
    }


}
