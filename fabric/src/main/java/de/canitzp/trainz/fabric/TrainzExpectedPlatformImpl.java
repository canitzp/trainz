package de.canitzp.trainz.fabric;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;

public class TrainzExpectedPlatformImpl {

    public static Packet<?> getAddEntityPacket(Entity entity, Packet<?> parentPacket) {
        return SpawnEntityPacket.create(entity);
    }

}
