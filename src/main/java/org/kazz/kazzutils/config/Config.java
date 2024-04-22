package org.kazz.kazzutils.config;

import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.data.InfoType;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import org.kazz.kazzutils.KazzUtils;
import org.kazz.kazzutils.features.dungeon.Hud.ArrowSoulflow;
import org.kazz.kazzutils.features.events.mytho.hud.mobTrackerHud;
import org.kazz.kazzutils.features.farming.hud.GardenLevel;
import org.kazz.kazzutils.features.farming.hud.NextContest;
import org.kazz.kazzutils.features.hud.PetOverlay;
import org.kazz.kazzutils.features.mining.Commisions;


/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class Config extends cc.polyfrost.oneconfig.config.Config {

    @Switch(
            name = "Star Cult notifier",
            description = "Notifies when Star cult happens",
            category = "Mining"
    )public static boolean starCult = false;

    
    
    @Dropdown(
            name = "Class",
            description = "What Class are you?",
            category = "Dungeon",
            subcategory = "Class",
            options = {"Tank", //0
                    "Mage",    //1
                    "Healer",  //2
                    "Bers",    //3
                    "Archer",  //4
                    "NON"      //5
            }
    )public static int dunClass = 5;
    @Switch(
            name = "Waypoint Beam",
            description = "Enables the beam of the Waypoints",
            category = "Dungeon"
    )public static boolean beamWa = false;

    @Switch(
            name = "Waypoint Text",
            description = "Enables the beam of the Waypoints",
            category = "Dungeon"
    )public static boolean beamTe = false;

    @Color(
            name = "Waypoint Beam Color",
            description = "Changes the Color of the Waypoints (Crystal, Terminals, Relic waypoints)",
            category = "Dungeon"
    )public static OneColor beamColor = new OneColor(0, 255, 255, 255);

    @Text(
            name = "Crystal Waypoint Text",
            description = "Changes the Displayed Text of the Crystal Waypoints",
            category = "Dungeon"
    )public static String crystalT = "Crystal";

    @Text(
            name = "Terminal Waypoint Text",
            description = "Changes the Displayed Text of the Terminal Waypoints",
            category = "Dungeon"
    )public static String termT = "Terminal";

    @Switch(
            name = "Tank Range",
            description = "Displays the Range of the Tank",
            category = "Dungeon",
            subcategory = "Tank"
    )public static boolean tankRange = false;

    @Color(
            name = "Tank Range Color",
            description = "Changes the Color of the Tank Range",
            category = "Dungeon",
            subcategory = "Tank"
    )public static OneColor tankRangeColor = new OneColor(0, 255, 0,100);

    @Switch(
            name = "Bone Necklace",
            description = "Increases the Tank Range due to the Ability of the Bone Necklace",
            category = "Dungeon",
            subcategory = "Tank"
    )public static boolean boneNecklace = false;

    @Text(
            name = "Relic Waypoint Text",
            description = "Changes the Displayed Text of the Relic Waypoints",
            category = "Dungeon",
            subcategory = "M7"
    )public static String relicT = "Relic";


    @Switch(
            name = "Dragon Boxes",
            description = "Enables the Kill Boxes of M7 Dragons",
            category = "Dungeon",
            subcategory = "M7"
    )public static boolean dragBox = false;

    @Dropdown(
            name = "Highlight Class",
            description = "What Other Dungeon Class do u want to Highlight  ",
            category = "Dungeon",
            subcategory = "Class",
            options = {"Tank", //0
                    "Mage",    //1
                    "Healer",  //2
                    "Bers",    //3
                    "Archer",  //4
                    "NON"      //5
            }
    )public static int highlightClass = 5;

    @Color(
            name = "Highlight Class Box Color",
            description = "Changes the Color of the highlighted classes Box",
            category = "Dungeon",
            subcategory = "Class"
    )public static OneColor highlightClassColor = new OneColor(0, 255, 0,100);

    @Switch(
            name = "Dragon Lines",
            description = "Enables Lines within the Kill Boxes of M7 Dragons (Paths)",
            category = "Dungeon",
            subcategory = "M7"
    )public static boolean dragLines = false;

    @Switch(
            name = "Dragon Spawn Notif",
            description = "Notifies when a Dragon Spawns in M7",
            category = "Dungeon",
            subcategory = "M7"
    )public static boolean dragNotif = true;

    @Color(
            name = "Bonzo/Spirit Color",
            description = "Changes the Color of the Bonzo/Spirit Notifaction",
            category = "Dungeon"
    )public static OneColor bonzoColor = new OneColor(0, 255, 0,100);








    @Switch(
            name = "Gyro Range",
            description = "Displays the Range of the Gyrowand",
            category = "Items"
    )public static boolean gyroRange = false;

    @Color(
            name = "Gyro Range Color",
            description = "Changes the Color of the Gyro Range",
            category = "Items"
    )public static OneColor gyroRangeColor = new OneColor(0, 255, 0,100);

    @Slider(
            name = "Gyro Range Scale",
            description = "Changes the Size of the Displayed Text",
            category = "Items",
            min = 0F,
            max = 10F,
            step = 1
    )public static float gyroRangeScale = 1F;






    @Switch(
            name = "Colorblind",
            description = "Displays Text for Statues in P5 for People with colorblindness",
            category = "Colorblind"
    )public static boolean colorBlind = false;

    @Slider(
            name = "Colorblind Scale",
            description = "Changes the Size of the Displayed Text",
            category = "Colorblind",
            min = 1F,
            max = 15F
    ) public static float cbScale = 1F;

    @Switch(
            name = "Highlight Cauldron",
            description = "Highlights the Cauldron for the M7 Dragons",
            category = "Colorblind"
    )public static boolean cauldron = false;





    @HUD(
            name = "Display Garden Level",
            category = "Farming"
    )public static GardenLevel gardenLevel = new GardenLevel();

    @Switch(
            name = "Display Garden Level Progress in %",
            description = "Changes the Progress from XP missing to % to next LvL",
            category = "Farming"
    )public static boolean gardenPercentage = false;

    @HUD(
            name = "Display next Contest",




            category = "Farming",
            subcategory = "Contest"
    )public static NextContest contest = new NextContest();



    /*
    @Switch(
            name = "Toggle Hotkey",
            description = "",
            category = "Hotkeys"
    )public static boolean hotkeys = false;

    @Text(
            name = "First Command",
            category = "Hotkeys"
    )public static String firstCommand = "";
     */






    @Info(
            text = "All of the Below Features need their Respecive Tab Widget to be turned on.",
            size = 2,
            type = InfoType.WARNING,
            category = "Tab Widgets"
    )public static boolean ignored;

    @HUD(
            name = "Pet Overlay",
            category = "Tab Widgets"
    )public static PetOverlay petOverlay = new PetOverlay();

    @HUD(
            name = "Commissions Overlay",
            category = "Tab Widgets"
    )public static Commisions commisions = new Commisions();





    @Switch(
            name = "Party Commands",
            category = "Miscellaneous",
            subcategory = "Party"
    )public static boolean partyCommands = true;

    @Dropdown(
            name = "Prefix",
            category = "Miscellaneous",
            subcategory = "Party",
            description = "What Prefix do you want to use for the Chat Commands",
            options = {".","?","!"}
    )public static int prefix = 2;





    @Info(
            text = "DOES NOT SAVE BETWEEN RESTARTS AT THE MOMENT",
            type = InfoType.INFO,
            category = "Events"
    )
    public static boolean ignored2;

    @HUD(
            name = "Mythological Tracker",
            category = "Events"
    )public static mobTrackerHud mobtrackerHud = new mobTrackerHud();



    @HUD(
            name = "Notify Arrows and Soulflow low",
            category = "Hud"
    )public static ArrowSoulflow arrowSoulflow = new ArrowSoulflow();



/*

    @HUD(
            name = "Dungeon Overlay",
            category = "Debug"
    )public static DebugHud debugHud = new DebugHud();

*/

    public Config() {
        super(new Mod(KazzUtils.NAME, ModType.UTIL_QOL), KazzUtils.MODID + ".json");
        //addDependency("gardenPercentage", () -> gardenLevel.isEnabled());
        initialize();
    }
}

