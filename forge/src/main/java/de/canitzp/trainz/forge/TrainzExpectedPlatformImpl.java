package de.canitzp.trainz.forge;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.network.NetworkHooks;

public class TrainzExpectedPlatformImpl {

    public static Packet<?> getAddEntityPacket(Entity entity, Packet<?> parentPacket) {
        return NetworkHooks.getEntitySpawningPacket(entity);
    }
}
