package de.canitzp.trainz;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;

public class FuelableMinecart extends AbstractMinecart {

    public static final EntityType<FuelableMinecart> TYPE = EntityType.Builder.of(FuelableMinecart::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8).build("fueleable_minecart");

    public FuelableMinecart(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Type getMinecartType() {
        return Type.RIDEABLE;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return TrainzExpectedPlatform.getAddEntityPacket(this, super.getAddEntityPacket());
    }
}
