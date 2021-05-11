package de.canitzp.trainz.fabric;

import de.canitzp.trainz.Trainz;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.impl.networking.ServerSidePacketRegistryImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.UUID;

public class SpawnEntityPacket {

    public static final ResourceLocation NAME = new ResourceLocation(Trainz.MOD_ID, "spawn_entity_packet");

    @Environment(EnvType.CLIENT)
    public static void register(){
        ClientPlayNetworking.registerGlobalReceiver(NAME, SpawnEntityPacket::receive);
    }

    public static Packet<?> create(Entity entity){
        if (entity.level.isClientSide()) {
            throw new IllegalStateException("SpawnPacketUtil.create called on the logical client!");
        }
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(Registry.ENTITY_TYPE.getId(entity.getType()));
        buf.writeUUID(entity.getUUID());
        buf.writeVarInt(entity.getId());
        buf.writeDouble(entity.position().x);
        buf.writeDouble(entity.position().y);
        buf.writeDouble(entity.position().z);
        return ServerSidePacketRegistryImpl.INSTANCE.toPacket(NAME, buf);
    }

    @Environment(EnvType.CLIENT)
    public static void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        EntityType<?> entityType = Registry.ENTITY_TYPE.byId(buf.readVarInt());
        UUID uuid = buf.readUUID();
        int entityId = buf.readVarInt();
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        client.execute(() -> {
            Entity entity = entityType.create(client.level);
            entity.setUUID(uuid);
            entity.setId(entityId);
            entity.setPos(x, y, z);
            client.level.addFreshEntity(entity);
        });
    }

}
