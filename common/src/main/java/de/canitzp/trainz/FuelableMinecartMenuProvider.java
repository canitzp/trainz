package de.canitzp.trainz;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Nullable;

public class FuelableMinecartMenuProvider implements MenuProvider {

    public static final FuelableMinecartMenuProvider INSTANCE = new FuelableMinecartMenuProvider();

    @Override
    public Component getDisplayName() {
        return new TextComponent("Fuelable Minecart");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player) {
        return new FuelableMinecartMenu(windowId, inventory);
    }
}
