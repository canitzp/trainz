package de.canitzp.trainz;

import me.shedaniel.architectury.event.events.PlayerEvent;
import me.shedaniel.architectury.registry.DeferredRegister;
import me.shedaniel.architectury.registry.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class Trainz {

    public static final String MOD_ID = "trainz";

    public static final @NotNull DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(MOD_ID, Registry.ENTITY_TYPE_REGISTRY);
    public static final @NotNull DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);

    public static void init() {
        ITEMS.register("fuelable_minecart", () -> ItemMinecartCustom.FUELABLE_MINECART_ITEM);
        ENTITIES.register("fuelable_minecart", () -> FuelableMinecart.TYPE);

        ITEMS.register();
        ENTITIES.register();

        EntityRenderers.register(FuelableMinecart.TYPE, MinecartRenderer<FuelableMinecart>::new);

        PlayerEvent.OPEN_MENU.register((player, menu) -> {
            System.out.println(player);
            System.out.println(menu);
            System.out.println("==================================================================================");
        });
    }
}
