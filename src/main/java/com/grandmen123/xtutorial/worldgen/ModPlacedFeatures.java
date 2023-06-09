package com.grandmen123.xtutorial.worldgen;

import com.grandmen123.xtutorial.XTutorial;
import com.grandmen123.xtutorial.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> EBONY_PLACED_KEY = createKey("ebony_placed");
    public static final ResourceKey<PlacedFeature> BLACK_OPAL_PLACED_KEY = createKey("black_opal_placed");
    public static final ResourceKey<PlacedFeature> NETHER_BLACK_OPAL_PLACED_KEY = createKey("nether_black_opal_placed");
    public static final ResourceKey<PlacedFeature> END_BLACK_OPAL_PLACED_KEY = createKey("end_black_opal_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, EBONY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EBONY_KEY),
                 VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                                                    ModBlocks.EBONY_SAPLING.get()));

        register(context, BLACK_OPAL_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_BLACK_OPAL_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(16,
                                                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                                                                                 VerticalAnchor.absolute(64))
                 ));
        register(context, NETHER_BLACK_OPAL_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_BLACK_OPAL_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(16,
                                                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                                                                                 VerticalAnchor.absolute(100))
                 ));
        register(context, END_BLACK_OPAL_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.END_BLACK_OPAL_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(16,
                                                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),
                                                                                 VerticalAnchor.absolute(64))
                 ));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(XTutorial.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configs, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configs, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configs, PlacementModifier... modifiers) {
        register(context, key, configs, List.of(modifiers));
    }
}
