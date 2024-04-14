package org.kazz.kazzutils.events;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.kazz.kazzutils.KazzUtils;
import org.kazz.kazzutils.utils.ChatUtils;

public abstract class KazzEvent extends Event {
    private final String eventName;

    public KazzEvent() {
        this.eventName = this.getClass().getSimpleName();
    }

    public boolean postAndCatch() {
        try {
            MinecraftForge.EVENT_BUS.post(this);
        } catch (Throwable t) {
            t.printStackTrace();
            ChatUtils.userError("$failPrefix §cKazzUtils " + KazzUtils.VERSION + " caught and logged an " + (t.getClass().getSimpleName() != null ? t.getClass().getSimpleName() : "error") + " at " + eventName + ". Please report this on the Discord server at discord.gg/skytils.");
        }
        return false;
    }
}
