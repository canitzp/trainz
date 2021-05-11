package de.canitzp.trainz;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;

public class TrainzExpectedPlatform {

    @ExpectPlatform
    public static Packet<?> getAddEntityPacket(Entity entity, Packet<?> parentPacket){
        throw new AssertionError();
    }

}
