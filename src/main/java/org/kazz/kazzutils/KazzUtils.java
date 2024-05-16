package org.kazz.kazzutils;

import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.hypixel.modapi.HypixelModAPI;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.kazz.kazzutils.command.CheckPlayerBlock;
import org.kazz.kazzutils.command.debug;
import org.kazz.kazzutils.command.openGUI;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.features.chatCommands.PartyCommands;
import org.kazz.kazzutils.features.dungeon.ClassHighlight;
import org.kazz.kazzutils.features.dungeon.Hud.ArrowSoulflow;
import org.kazz.kazzutils.features.dungeon.f7.Crystals;
import org.kazz.kazzutils.features.dungeon.Hud.BonzoSpirit;
import org.kazz.kazzutils.features.dungeon.TankStuff;
import org.kazz.kazzutils.features.dungeon.f7.Terminals;
import org.kazz.kazzutils.features.dungeon.m7.RenderStuff.*;
import org.kazz.kazzutils.features.dungeon.m7.dragons.DragonsBoxesLines;
import org.kazz.kazzutils.features.dungeon.m7.dragons.dragPrio;
import org.kazz.kazzutils.features.events.mytho.mobTracker;
import org.kazz.kazzutils.features.farming.GardenPlots;
import org.kazz.kazzutils.features.farming.hud.Pests;
import org.kazz.kazzutils.features.mining.Commisions;
import org.kazz.kazzutils.features.mining.StarCult;
import org.kazz.kazzutils.features.render.GyroRange;
import org.kazz.kazzutils.utils.CheckCatacombs;
import org.kazz.kazzutils.utils.TEST.ContainerUtils;
import org.kazz.kazzutils.utils.TabUtils;
import org.kazz.kazzutils.utils.TitleUtils;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = KazzUtils.MODID, name = KazzUtils.NAME, version = KazzUtils.VERSION)
public class KazzUtils {

    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    public static final String modDir = Minecraft.getMinecraft().mcDataDir.getPath();
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)

    public static KazzUtils INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static Config config;
    public static Minecraft mc = Minecraft.getMinecraft();
    //public static HypixelModAPI API = HypixelModAPI.getInstance();


    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new Config();
        CommandManager.INSTANCE.registerCommand(new openGUI());
        CommandManager.INSTANCE.registerCommand(new debug());
        CommandManager.INSTANCE.registerCommand(new CheckPlayerBlock());
        MinecraftForge.EVENT_BUS.register(new TitleUtils());

    }

    private void reg(Object object){
        MinecraftForge.EVENT_BUS.register(object);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        reg(this);
        reg(new Terminals());
        reg(new Crystals());
        reg(new Relics());
        reg(new DragonsBoxesLines());
        reg(new CheckCatacombs());
        reg(new Colorblind());
        reg(new BonzoSpirit());
        reg(new dragPrio());
        reg(new GardenPlots());
        reg(new GyroRange());
        reg(new TankStuff());
        reg(new ClassHighlight());
        reg(new PartyCommands());
        reg(new mobTracker());
        reg(new Commisions());
        reg(new ArrowSoulflow());
        reg(new Pests());
    }

    public long tickammount = 0;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {

        if (mc.thePlayer == null) return;
        if (mc.theWorld == null) return;
        if (event.phase != TickEvent.Phase.START) return;

        tickammount++;

        if(tickammount % 2 == 0) {
            TabUtils.parseTabEntries();
        } //each 1/10th Second
        if (tickammount % 20 == 0) {
            if (!StarCult.firstTick && Config.starCult) StarCult.checkCult();
            CheckCatacombs.checkCata();

        } //each second
        if (tickammount % 300 == 0) {
            StarCult.firstTick = false;

        } // 30 Sec
        if (tickammount % 1200 == 0) {

        } //each minute
        if (tickammount % 6000 == 0) {
        } //each 5 minutes

    }
}