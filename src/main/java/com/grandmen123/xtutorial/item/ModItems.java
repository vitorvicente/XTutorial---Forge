package com.grandmen123.xtutorial.item;

import com.grandmen123.xtutorial.XTutorial;
import com.grandmen123.xtutorial.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, XTutorial.MOD_ID);

    public static final RegistryObject<Item> BLACK_OPAL =
            ITEMS.register("black_opal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_BLACK_OPAL =
            ITEMS.register("raw_black_opal", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TIGER_SPAWN_EGG =
            ITEMS.register("tiger_spawn_egg",
                           () -> new ForgeSpawnEggItem(ModEntities.TIGER, 0xD57e36, 0x100000,
                                                       new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
