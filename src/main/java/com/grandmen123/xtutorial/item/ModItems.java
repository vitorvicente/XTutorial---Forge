package com.grandmen123.xtutorial.item;

import com.grandmen123.xtutorial.XTutorial;
import com.grandmen123.xtutorial.block.ModBlocks;
import com.grandmen123.xtutorial.entity.ModEntities;
import com.grandmen123.xtutorial.item.custom.AmethystArmorItem;
import com.grandmen123.xtutorial.item.custom.AnimatedBlockItem;
import com.grandmen123.xtutorial.item.custom.AnimatedItem;
import com.grandmen123.xtutorial.sound.ModSounds;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
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

    public static final RegistryObject<Item> ANIMATED_ITEM = ITEMS.register("animated_item", () ->
            new AnimatedItem(new Item.Properties()));

    public static final RegistryObject<Item> ANIMATED_BLOCK_ITEM = ITEMS.register("animated_block", () ->
            new AnimatedBlockItem(ModBlocks.ANIMATED_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> AMETHYST_HELMET = ITEMS.register("amethyst_helmet", () ->
            new AmethystArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_CHESTPLATE = ITEMS.register("amethyst_chestplate", () ->
            new AmethystArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_LEGGINGS = ITEMS.register("amethyst_leggings", () ->
            new AmethystArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_BOOTS = ITEMS.register("amethyst_boots", () ->
            new AmethystArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> MUSIC_BOX_MUSIC_DISC = ITEMS.register("music_box_music_disc", () ->
            new RecordItem(8, ModSounds.MUSIC_BOX, new Item.Properties(), 320));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
