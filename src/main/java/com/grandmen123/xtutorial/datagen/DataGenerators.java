package com.grandmen123.xtutorial.datagen;

import com.grandmen123.xtutorial.XTutorial;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = XTutorial.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        gen.addProvider(true, new ModRecipeProvider(output));
        gen.addProvider(true, ModLootTableProvider.create(output));
        gen.addProvider(true, new ModBlockStateProvider(output, fileHelper));
        gen.addProvider(true, new ModItemModelProvider(output, fileHelper));
    }
}
