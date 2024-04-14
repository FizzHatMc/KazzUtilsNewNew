package org.kazz.kazzutils.events.impl;


import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.kazz.kazzutils.events.KazzEvent;

@Cancelable
public class MainReceivePacketEvent<T extends Packet<U>, U extends INetHandler> extends KazzEvent {
    private final U handler;
    private final T packet;

    public MainReceivePacketEvent(U handler, T packet) {
        this.handler = handler;
        this.packet = packet;
    }

    public U getHandler() {
        return handler;
    }

    public T getPacket() {
        return packet;
    }
}
