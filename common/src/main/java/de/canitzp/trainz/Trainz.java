package de.canitzp.trainz;

import me.shedaniel.architectury.event.events.GuiEvent;
import me.shedaniel.architectury.event.events.PlayerEvent;
import me.shedaniel.architectury.networking.NetworkChannel;
import me.shedaniel.architectury.registry.DeferredRegister;
import me.shedaniel.architectury.registry.MenuRegistry;
import me.shedaniel.architectury.registry.entity.EntityRenderers;
import me.shedaniel.architectury.utils.Env;
import me.shedaniel.architectury.utils.EnvExecutor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;

public class Trainz {

    public static final String MOD_ID = "trainz";

    public static final NetworkChannel NET = NetworkChannel.create(new ResourceLocation(MOD_ID, "network"));

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(MOD_ID, Registry.ENTITY_TYPE_REGISTRY);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(MOD_ID, Registry.MENU_REGISTRY);

    public static void init() {
        ITEMS.register("fuelable_minecart", () -> ItemMinecartCustom.FUELABLE_MINECART_ITEM);
        ENTITIES.register("fuelable_minecart", () -> FuelableMinecart.TYPE);
        MENU_TYPES.register("fuelable_minecart", () -> FuelableMinecartMenu.TYPE);

        ITEMS.register();
        ENTITIES.register();
        MENU_TYPES.register();

        NET.register(OpenCustomMenuPacket.class, OpenCustomMenuPacket::encode, OpenCustomMenuPacket::decode, OpenCustomMenuPacket::receive);

        EnvExecutor.runInEnv(Env.CLIENT, () -> () -> {
            EntityRenderers.register(FuelableMinecart.TYPE, MinecartRenderer<FuelableMinecart>::new);
            MenuRegistry.registerScreenFactory(FuelableMinecartMenu.TYPE, FuelableMinecartScreen::new);

            GuiEvent.SET_SCREEN.register(screen -> {
                if(screen instanceof InventoryScreen){
                    LocalPlayer player = Minecraft.getInstance().player;
                    if(player != null){
                        if(player.getVehicle() instanceof FuelableMinecart){
                            NET.sendToServer(new OpenCustomMenuPacket());
                            return InteractionResultHolder.fail(null);
                        }
                    }
                }
                return InteractionResultHolder.pass(screen);
            });
        });

    }
}
