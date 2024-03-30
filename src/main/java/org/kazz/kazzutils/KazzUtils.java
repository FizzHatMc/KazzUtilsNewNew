package org.kazz.kazzutils;

import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
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
import org.kazz.kazzutils.features.dungeon.Hud.BonzoSpirit;
import org.kazz.kazzutils.features.dungeon.m7.RenderStuff.*;
import org.kazz.kazzutils.features.events.mytho.hud.mobTrackerHud;
import org.kazz.kazzutils.features.events.mytho.mobTracker;
import org.kazz.kazzutils.features.farming.GardenPlots;
import org.kazz.kazzutils.features.mining.StarCult;
import org.kazz.kazzutils.features.render.GyroRange;
import org.kazz.kazzutils.utils.CheckCatacombs;
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
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    public long tickammount;
    @Mod.Instance(MODID)
    public static KazzUtils INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static Config config;
    public static Minecraft mc = Minecraft.getMinecraft();

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new Config();
        CommandManager.INSTANCE.registerCommand(new openGUI());
        CommandManager.INSTANCE.registerCommand(new debug());
        CommandManager.INSTANCE.registerCommand(new CheckPlayerBlock());
        MinecraftForge.EVENT_BUS.register(new TitleUtils());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        EventBus mfe = MinecraftForge.EVENT_BUS;
        // mfe.register(new );

        mfe.register(this);
        mfe.register(new Terminals());
        mfe.register(new Crystals());
        mfe.register(new Relics());
        mfe.register(new DragonsBoxesLines());
        mfe.register(new CheckCatacombs());
        mfe.register(new Colorblind());
        mfe.register(new BonzoSpirit());

        mfe.register(new GardenPlots());
        mfe.register(new GyroRange());
        mfe.register(new TankStuff());
        mfe.register(new ClassHighlight());
        mfe.register(new PartyCommands());
        mfe.register(new mobTracker());

    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        //if (event.phase != TickEvent.Phase.START);
        tickammount++;
        if (mc.theWorld != null && mc.thePlayer != null) {
            if (tickammount % 20 == 0) {
                if (!StarCult.firstTick && Config.starCult) StarCult.checkCult();
                CheckCatacombs.checkCata();
                TabUtils.parseTabEntries();
            } //each second
            if (tickammount % 300 == 0) {
                StarCult.firstTick = false;

                //TabUtilsKotlin.INSTANCE.parseTabEntries();


            } // 30 Sec
            if (tickammount % 1200 == 0) {

            } //each minute
            if (tickammount % 6000 == 0) {
            } //each 5 minutes
        }
    }
}