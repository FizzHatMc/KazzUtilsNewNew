package org.kazz.kazzutils.features.dungeon.m7.dragons;

import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.kazz.kazzutils.config.Config;
import org.kazz.kazzutils.events.impl.MainReceivePacketEvent;
import org.kazz.kazzutils.utils.ChatUtils;
import org.kazz.kazzutils.utils.CheckCatacombs;
import org.kazz.kazzutils.utils.TabUtils;

import java.util.*;


public class dragPrio {
    private Set<CheckCatacombs.WitherKingDragons> spawningDragons = new HashSet<>();
    private Map<CheckCatacombs.WitherKingDragons, Long> dragonSpawnTimes = new HashMap<>();

//org.kazz.kazzutils.utils.CheckCatacombs.WitherKingDragons.particleYConstant

    @SubscribeEvent
    public void onPacket(MainReceivePacketEvent<?, ?> event) {
        if (!Objects.equals(TabUtils.area, "Dungeon")) return;
        if (event.getPacket() instanceof S2CPacketSpawnGlobalEntity && ((S2CPacketSpawnGlobalEntity) event.getPacket()).func_149053_g() == 1) {

            double x = ((S2CPacketSpawnGlobalEntity) event.getPacket()).func_149051_d() / 32.0;
            double y = ((S2CPacketSpawnGlobalEntity) event.getPacket()).func_149050_e() / 32.0;
            double z = ((S2CPacketSpawnGlobalEntity) event.getPacket()).func_149049_f() / 32.0;
            if (x % 1 != 0.0 || y % 1 != 0.0 || z % 1 != 0.0) return;
            ChatUtils.messageToChat(" xyz " + x + " " + y + " " + z);
            CheckCatacombs.WitherKingDragons drag = Arrays.stream(CheckCatacombs.WitherKingDragons.values())
                    .filter(entry -> entry.getBlockPos().getX() == (int) x && entry.getBlockPos().getZ() == (int) z)
                    .findFirst()
                    .orElse(null);


            if (drag != null && spawningDragons.add(drag)) {
                ChatUtils.messageToChat(drag.name() + " spawning " + x + " " + y + " " + z + "witherkingdrags KAZZ MOD");
            }
        } else if (event.getPacket() instanceof S2APacketParticles) {
            S2APacketParticles packet = (S2APacketParticles) event.getPacket();
            if (packet.getParticleCount() != 20 || packet.getYCoordinate() != CheckCatacombs.WitherKingDragons.particleYConstant
                    || packet.getParticleType() != EnumParticleTypes.FLAME || packet.getXOffset() != 2f
                    || packet.getYOffset() != 3f || packet.getZOffset() != 2f || packet.getParticleSpeed() != 0f
                    || !packet.isLongDistance() || packet.getXCoordinate() % 1 != 0.0 || packet.getZCoordinate() % 1 != 0.0) {
                return;
            }
            ChatUtils.userError("IS CORRECT PARTICLE KAZZ MOD");

            CheckCatacombs.WitherKingDragons owner = Arrays.stream(CheckCatacombs.WitherKingDragons.values())
                    .filter(entry -> entry.getParticleLocation().getX() == (int) packet.getXCoordinate() && entry.getParticleLocation().getZ() == (int) packet.getZCoordinate())
                    .findFirst()
                    .orElse(null);

            if (owner != null && !dragonSpawnTimes.containsKey(owner)) {
                dragonSpawnTimes.put(owner, System.currentTimeMillis() + 5000);
                if (Config.dragNotif) {
                    ChatUtils.messageToChat("§c§lThe " + owner.getEnumChatFormatting() + "§l" + owner.name() + " §c§ldragon is spawning! KAZZ MOD");
                }
            }
        }
    }

}
