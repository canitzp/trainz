package de.canitzp.trainz;

import me.shedaniel.architectury.utils.NbtType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class FuelableMinecart extends AbstractMinecart {

    public static final EntityType<FuelableMinecart> TYPE = EntityType.Builder.of(FuelableMinecart::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8).build("fueleable_minecart");

    private SimpleContainer inventory = new SimpleContainer(1);
    private int burnTimeLeft, maxBurnTime = 200;
    private double accelerationFactor = 0;

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

    @Override
    public InteractionResult interact(Player player, InteractionHand interactionHand) {
        if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        } else if (this.isVehicle()) {
            return InteractionResult.PASS;
        } else if (!this.level.isClientSide) {
            return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            return InteractionResult.SUCCESS;
        }
    }

    public SimpleContainer getInventory() {
        return this.inventory;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.put("Inventory", this.inventory.createTag());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if(compoundTag.contains("Inventory", NbtType.LIST)){
            this.inventory.fromTag(compoundTag.getList("Inventory", NbtType.COMPOUND));
        }
    }

    @Override
    public void tick() {
        this.accelerationFactor = 1.05D;
        super.tick();
    }

    @Override
    protected void moveAlongTrack(BlockPos blockPos, BlockState blockState) {
        double maxAccelerationPerTick = 0.5D;
        Vec3 vec3 = this.getDeltaMovement();
        double acceleration = this.accelerationFactor;//Math.min(this.accelerationFactor, maxAccelerationPerTick);
        this.setDeltaMovement(vec3.multiply(acceleration, 0, acceleration));
        super.moveAlongTrack(blockPos, blockState);
    }

    @Override
    protected double getMaxSpeed() {
        return 0.5D;
    }


}
