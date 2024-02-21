package org.kazz.kazzutils.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.world.WorldSettings;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TabUtils {

    private static Minecraft mc = Minecraft.getMinecraft();
    private static final Pattern visitorPattern = Pattern.compile("Visitors: \\((.+)\\)");
    private static final Pattern areaPattern = Pattern.compile("Area: (.+)");
    public static String area = "";
    public static boolean explosivity = false;
    public static boolean maxVisitors = false;
    public static boolean emptyComposter = false;
    public static String gardenMilestone = "";
    public static String timeTillNextVisitor = "";
    public static int numVisitors = 0;
    public static String archerName = "";
    public static String gardenLevel;

    private static boolean isSpectator(WorldSettings.GameType gameType) {
        return WorldSettings.GameType.SPECTATOR == gameType;
    }

    private static Comparator<NetworkPlayerInfo> playerInfoOrdering = (info1, info2) -> {
        if (info1 == null) return -1;
        if (info2 == null) return 1;
        int spectatorComparison = Boolean.compare(!isSpectator(info2.getGameType()), !isSpectator(info1.getGameType()));
        if (spectatorComparison != 0) return spectatorComparison;
        //int teamComparison = info1.getPlayerTeam().getRegisteredName().compareTo(info2.getPlayerTeam().getRegisteredName());
        //if (teamComparison != 0) return teamComparison;
        return info1.getGameProfile().getName().compareTo(info2.getGameProfile().getName());
    };

    public static List<NetworkPlayerInfo> fetchTabEntries() {
        if (mc.thePlayer == null) return Collections.emptyList();
        return mc.thePlayer.sendQueue.getPlayerInfoMap().stream()
                .sorted(playerInfoOrdering)
                .collect(Collectors.toList());
    }

    public static void parseTabEntries() {
        boolean exploFlag = false;
        boolean numVisitorsFlag = false;
        List<String> scoreboardList = fetchTabEntries().stream()
                .map(info -> info.getDisplayName() != null ? info.getDisplayName().getUnformattedText() : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        for (String line : scoreboardList) {
            String trimmed = line.trim();
            Matcher areaMatcher = areaPattern.matcher(trimmed);
            Matcher visitorMatcher = visitorPattern.matcher(trimmed);

            switch (trimmed) {
                case "Volcano Explosivity:":
                    exploFlag = true;
                    break;
                case "Dungeon Stats":
                    area = "Dungeon";
                    break;
                case "Time Left: ":
                    emptyComposter = "INACTIVE".equals(trimmed.split(": ")[1]);
                    break;
                case "Milestone":
                    gardenMilestone = trimmed;
                    break;
                case "(Archer":
                    archerName = line.split(" ")[1];
                    break;
                case "Garden Level:":
                    gardenLevel = line.split(" ")[1];
                    break;

                default:
                    if (areaMatcher.matches()) {
                        area = areaMatcher.group(1);
                    } else if (exploFlag) {
                        exploFlag = false;
                        explosivity = !"INACTIVE".equals(trimmed);
                    } else if (visitorMatcher.find()) {
                        timeTillNextVisitor = visitorMatcher.group(1);
                        maxVisitors = "Queue Full!".equals(timeTillNextVisitor);
                        numVisitorsFlag = true;
                        int index = scoreboardList.indexOf(line) + 1;
                        int visitors = 0;
                        while (index < scoreboardList.size() && !"".equals(scoreboardList.get(index)) && visitors < 5) {
                            visitors++;
                            index++;
                        }
                        numVisitors = visitors;
                    }
                    break;
            }



        }

        if (!"Dungeon".equals(area)) {
            archerName = "";
        }
        if (!"Crimson Isle".equals(area)) {
            explosivity = false;
        }
        if (!"Garden".equals(area)) {
            maxVisitors = false;
        }
        if (!numVisitorsFlag) {
            numVisitors = 0;
        }
    }



    // Assuming NetworkPlayerInfo and GameType are classes you have in your environment.
    // You would need to implement or import these classes as appropriate for your application.


}


/*
if (areaMatcher.matches()) {
                area = areaMatcher.group(1);
            } else if ("Volcano Explosivity:".equals(trimmed)) {
                exploFlag = true;
            } else if (exploFlag) {
                exploFlag = false;
                explosivity = !"INACTIVE".equals(trimmed);
            } else if ("Dungeon Stats".equals(trimmed)) {
                area = "Dungeon";
            } else if (trimmed.startsWith("Time Left: ")) {
                emptyComposter = "INACTIVE".equals(trimmed.split(": ")[1]);
            } else if (trimmed.startsWith("Milestone")) {
                gardenMilestone = trimmed;
            } else if (visitorMatcher.find()) {
                timeTillNextVisitor = visitorMatcher.group(1);
                maxVisitors = "Queue Full!".equals(timeTillNextVisitor);
                numVisitorsFlag = true;
                int index = scoreboardList.indexOf(line) + 1;
                int visitors = 0;
                while (index < scoreboardList.size() && !"".equals(scoreboardList.get(index)) && visitors < 5) {
                    visitors++;
                    index++;
                }
                numVisitors = visitors;
            } else if (line.contains("(Archer")) {
                archerName = line.split(" ")[1];
            }
 */