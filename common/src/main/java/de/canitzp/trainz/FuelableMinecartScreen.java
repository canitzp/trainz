package de.canitzp.trainz;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FuelableMinecartScreen extends AbstractContainerScreen<FuelableMinecartMenu> {

    public static final ResourceLocation GUI_LOCATION = new ResourceLocation(Trainz.MOD_ID, "textures/menu/fuelable_minecart.png");

    protected FuelableMinecartScreen(FuelableMinecartMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);

        if(!(inventory.player.getVehicle() instanceof FuelableMinecart)){
            throw new RuntimeException("Can't open FuelableMinecart Screen without the Entity!");
        }
        FuelableMinecart vehicle = (FuelableMinecart) inventory.player.getVehicle();
    }

    @Override
    protected void init() {
        super.init();
        this.imageWidth = 176;
        this.imageHeight = 112;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float f, int i, int j) {
        this.renderBackground(poseStack);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI_LOCATION);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

}
