package de.canitzp.trainz.fabric;

import de.canitzp.trainz.Trainz;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class TrainzFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Trainz.init();

        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
            SpawnEntityPacket.register();
        }
    }

}
