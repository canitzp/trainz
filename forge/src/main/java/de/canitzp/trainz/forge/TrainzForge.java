package de.canitzp.trainz.forge;

import de.canitzp.trainz.Trainz;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Trainz.MOD_ID)
public class TrainzForge {

    public TrainzForge() {
        EventBuses.registerModEventBus(Trainz.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        Trainz.init();
    }

}
