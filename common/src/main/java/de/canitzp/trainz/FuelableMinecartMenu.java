package de.canitzp.trainz;

import me.shedaniel.architectury.registry.MenuRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;

public class FuelableMinecartMenu extends AbstractContainerMenu {

    public static final MenuType<FuelableMinecartMenu> TYPE = MenuRegistry.of(FuelableMinecartMenu::new);

    protected FuelableMinecartMenu(int windowId, Inventory inventory) {
        super(TYPE, windowId);

        if(!(inventory.player.getVehicle() instanceof FuelableMinecart)){
            throw new RuntimeException("Can't open FuelableMinecart Menu without the Entity!");
        }
        FuelableMinecart vehicle = (FuelableMinecart) inventory.player.getVehicle();

        this.addSlot(new Slot(vehicle.getInventory(), 0, 8, 8));

        // player inventory
        for(int row = 0; row < 3; ++row) {
            for(int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 30 + row * 18));
            }
        }

        for(int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 88));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
