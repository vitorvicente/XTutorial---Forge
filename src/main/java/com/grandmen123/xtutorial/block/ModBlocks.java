package com.grandmen123.xtutorial.block;

import com.grandmen123.xtutorial.XTutorial;
import com.grandmen123.xtutorial.block.custom.AnimatedBlock;
import com.grandmen123.xtutorial.block.custom.ModFlammableRotatedPillarBlock;
import com.grandmen123.xtutorial.item.ModItems;
import com.grandmen123.xtutorial.worldgen.tree.EbonyTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, XTutorial.MOD_ID);

    public static final RegistryObject<Block> BLACK_OPAL_BLOCK = registerBlock("black_opal_block", () -> new Block(
            BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLACK_OPAL_ORE =
            registerBlock("black_opal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(
                    Material.STONE).strength(3.0F, 3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(),
                                                                          UniformInt.of(2, 7)));

    public static final RegistryObject<Block> DEEPSLATE_BLACK_OPAL_ORE =
            registerBlock("deepslate_black_opal_ore",
                          () -> new DropExperienceBlock(BlockBehaviour.Properties
                                                                .copy(BLACK_OPAL_ORE.get())
                                                                .color(MaterialColor.DEEPSLATE)
                                                                .strength(4.5F, 3.0F)
                                                                .sound(SoundType.DEEPSLATE),
                                                        UniformInt.of(2
                                                                , 6)));


    public static final RegistryObject<Block> NETHERRACK_BLACK_OPAL_ORE =
            registerBlock("netherrack_black_opal_ore",
                          () -> new DropExperienceBlock(BlockBehaviour.Properties
                                                                .copy(BLACK_OPAL_ORE.get())
                                                                .color(MaterialColor.NETHER)
                                                                .strength(3.0F, 3.0F)
                                                                .sound(SoundType.NETHER_ORE),
                                                        UniformInt.of(2
                                                                , 6)));

    public static final RegistryObject<Block> ENDSTONE_BLACK_OPAL_ORE =
            registerBlock("endstone_black_opal_ore",
                          () -> new DropExperienceBlock(BlockBehaviour.Properties
                                                                .copy(BLACK_OPAL_ORE.get())
                                                                .color(MaterialColor.SAND)
                                                                .strength(3.0F, 9.0F),
                                                        UniformInt.of(2, 6)));

    public static final RegistryObject<Block> EBONY_LOG =
            registerBlock("ebony_log", () -> new ModFlammableRotatedPillarBlock(
                    BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(5f)));
    public static final RegistryObject<Block> EBONY_WOOD =
            registerBlock("ebony_wood", () -> new ModFlammableRotatedPillarBlock(
                    BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(5f)));
    public static final RegistryObject<Block> STRIPPED_EBONY_LOG =
            registerBlock("stripped_ebony_log", () -> new ModFlammableRotatedPillarBlock(
                    BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(5f)));
    public static final RegistryObject<Block> STRIPPED_EBONY_WOOD =
            registerBlock("stripped_ebony_wood", () -> new ModFlammableRotatedPillarBlock(
                    BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(5f)));

    public static final RegistryObject<Block> EBONY_PLANKS =
            registerBlock("ebony_planks",
                          () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                              @Override
                              public boolean isFlammable(BlockState state, BlockGetter level,
                                                         BlockPos pos, Direction direction) {
                                  return true;
                              }

                              @Override
                              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos,
                                                            Direction direction) {
                                  return 5;
                              }

                              @Override
                              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos,
                                                         Direction direction) {
                                  return 5;
                              }
                          });

    public static final RegistryObject<Block> EBONY_LEAVES =
            registerBlock("ebony_leaves",
                          () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                              @Override
                              public boolean isFlammable(BlockState state, BlockGetter level,
                                                         BlockPos pos, Direction direction) {
                                  return true;
                              }

                              @Override
                              public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos,
                                                            Direction direction) {
                                  return 30;
                              }

                              @Override
                              public int getFlammability(BlockState state, BlockGetter level, BlockPos pos,
                                                         Direction direction) {
                                  return 60;
                              }
                          });

    public static final RegistryObject<Block> EBONY_SAPLING = registerBlock("ebony_sapling", () ->
            new SaplingBlock(new EbonyTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> ANIMATED_BLOCK = BLOCKS.register("animated_block", () ->
            new AnimatedBlock(BlockBehaviour.Properties.of(Material.STONE).noOcclusion()));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
}
