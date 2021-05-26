package de.canitzp.trainz;

import me.shedaniel.architectury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public class OpenCustomMenuPacket {

    public static void encode(OpenCustomMenuPacket packet, FriendlyByteBuf buf){}

    public static OpenCustomMenuPacket decode(FriendlyByteBuf buf){
        return new OpenCustomMenuPacket();
    }

    public void receive(Supplier<NetworkManager.PacketContext> ctx){
        ctx.get().queue(() -> {
            ctx.get().getPlayer().openMenu(FuelableMinecartMenuProvider.INSTANCE);
        });
    }

}
