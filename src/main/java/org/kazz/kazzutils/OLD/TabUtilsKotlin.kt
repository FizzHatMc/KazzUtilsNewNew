package org.kazz.kazzutils.OLD

import com.google.common.collect.ComparisonChain
import com.google.common.collect.Ordering
import net.minecraft.client.Minecraft
import net.minecraft.client.network.NetworkPlayerInfo
import net.minecraft.world.WorldSettings

object TabUtilsKotlin {
    private val visitorPattern = "Visitors: \\((.+)\\)".toRegex()
    private val areaPattern = "Area: (.+)".toRegex()
    private val mc = Minecraft.getMinecraft()

    var area: String = ""
    var explosivity: Boolean = false
    var maxVisitors: Boolean = false
    var emptyComposter: Boolean = false
    var gardenMilestone: String = ""
    var timeTillNextVisitor: String = ""
    var numVisitors: Int = 0
    var archerName: String = ""
    var tankName: String = ""
    var healerName: String = ""
    var mageName: String = ""
    var berserkerName: String = ""
    var gardenLevel : Int = 0
    var gardenLevelString : String = ""
    var gardenLevelBool : Boolean = false
    var gardenPercent : Double = 0.0
    var trimmed : String = ""
    var first : String = ""
    var second : String = ""
    var third : String = ""
    var time : String = ""

    private val playerInfoOrdering = object : Ordering<NetworkPlayerInfo>() {
        override fun compare(info1: NetworkPlayerInfo?, info2: NetworkPlayerInfo?): Int {
            if (info1 == null) return -1
            if (info2 == null) return 0
            return ComparisonChain.start()
                    .compareTrueFirst(!info1.gameType.isSpectator(), !info2.gameType.isSpectator())
                    .compare(info1.playerTeam?.registeredName ?: "", info2.playerTeam?.registeredName ?: "")
                    .compare(info1.gameProfile.name, info2.gameProfile.name).result()
        }
    }

    fun fetchTabEntries(): List<NetworkPlayerInfo> {
        return if (mc.thePlayer == null) emptyList()
        else playerInfoOrdering.sortedCopy(mc.thePlayer.sendQueue.playerInfoMap)
    }

    /**
     * Sets a bunch of useful values based on the state of the scoreboard. Functionality is collected all into
     * this one method in order to avoid more transversal of the list than is necessary, as these checks need
     * to happen somewhat frequently.
     */
    fun parseTabEntries() {
        if(mc.thePlayer == null) return
        if(mc.theWorld == null) return

        // exploFlag is just telling the loop that the next line is the relevant tab entry
        var exploFlag = false
        var numVisitorsFlag = false

        val scoreboardList = fetchTabEntries().mapNotNull {
            it.displayName?.unformattedText
        }


        for (line in scoreboardList) {
            trimmed = line.trim()
            when {
                trimmed.contains(areaPattern) -> area = areaPattern.find(trimmed)!!.groupValues[1]
                trimmed == "Volcano Explosivity:" -> exploFlag = true
                exploFlag -> {
                    exploFlag = false
                    explosivity = trimmed != "INACTIVE"
                }

                trimmed == "Dungeon Stats" -> {
                    area = "Dungeon"
                }

                trimmed.contains("Garden Level:") ->{
                    //gardenLevel = trimmed.split(":")[1]
                    if(!trimmed.contains("[a-zA-Z]+") && trimmed.length > 2) {
                        gardenLevel = trimmed.subSequence(trimmed.indexOf(":") + 2, trimmed.indexOf(":") + 4).toString().replace(" ", "").toInt()
                        if (gardenLevel != 15) gardenPercent = trimmed.subSequence(trimmed.indexOf("(") + 1, trimmed.indexOf(")") - 7).toString().toDouble()
                        gardenLevelBool = true
                    }else{
                       gardenLevelString  = trimmed.subSequence(trimmed.indexOf(":") + 2, trimmed.indexOf(":") + 4).toString().replace(" ", "")
                        gardenLevelBool = false
                    }
                    area = "Garden"
                }

                trimmed.startsWith("Time Left: ") -> {
                    emptyComposter = trimmed.split(": ")[1] == "INACTIVE"
                }

                trimmed.startsWith("Milestone") -> gardenMilestone = trimmed


                trimmed.contains("Contest:") ->{
                    if(scoreboardList == null)return
                    var index = scoreboardList.indexOf(line)+1
                    if(index == -1)return
                    //time = scoreboardList[index].subSequence(scoreboardList[index].indexOf(": ")+2,scoreboardList[index].indexOf("m")+1).toString()
                    //time = scoreboardList[index].split(": ")[1]
                    time = scoreboardList[index].subSequence(scoreboardList[index].indexOf(": ")+2,scoreboardList[index].length).toString()
                    index++
                    first = scoreboardList[index]
                    index++
                    second = scoreboardList[index]
                    index++
                    third = scoreboardList[index]

                }

                trimmed.contains(visitorPattern) -> {
                    timeTillNextVisitor = visitorPattern.find(trimmed)!!.groupValues[1]
                    maxVisitors = timeTillNextVisitor == "Queue Full!"
                    numVisitorsFlag = true

                    // figure out how many visitors
                    var index = scoreboardList.indexOf(line) + 1
                    var visitors = 0
                    while (index < scoreboardList.size && scoreboardList.get(index) != "" && visitors < 5) {
                        visitors++
                        index++
                    }
                    numVisitors = visitors
                }

                line.contains("(Archer") -> {
                    archerName = line.split(" ")[1]
                }

                line.contains("(Tank") -> {
                    tankName = line.split(" ")[1]
                }

                line.contains("(Mage") -> {
                    mageName = line.split(" ")[1]
                }

                line.contains("(Healer") -> {
                    healerName = line.split(" ")[1]
                }

                line.contains("(Berserker") -> {
                    berserkerName = line.split(" ")[1]
                }
            }
        }

        if (area != "Dungeon") {
            archerName = ""
        }
        if (area != "Crimson Isle") {
            explosivity = false
        }
        if (area != "Garden") {
            maxVisitors = false
        }
        if (!numVisitorsFlag) {
            numVisitors = 0
        }
    }

    private fun WorldSettings.GameType.isSpectator() = this == WorldSettings.GameType.SPECTATOR
}


/*


 */