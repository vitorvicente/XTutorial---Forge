package com.grandmen123.xtutorial.datagen;

import com.grandmen123.xtutorial.XTutorial;
import com.grandmen123.xtutorial.block.ModBlocks;
import com.grandmen123.xtutorial.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> consumer,
                                                  RecipeCategory materialRecipeCategory,
                                                  ItemLike material, RecipeCategory blockRecipeCategory, ItemLike block,
                                                  String blockResourceLocation,
                                                  @Nullable String blockGroup, String materialResourceLocation,
                                                  @Nullable String materialGroup) {
        ShapelessRecipeBuilder.shapeless(materialRecipeCategory, material, 9)
                              .requires(block).group(materialGroup)
                              .unlockedBy(getHasName(block), has(block))
                              .save(consumer, new ResourceLocation(XTutorial.MOD_ID, materialResourceLocation));
        ShapedRecipeBuilder.shaped(blockRecipeCategory, block).define('#', material)
                           .pattern("###")
                           .pattern("###")
                           .pattern("###")
                           .group(blockGroup)
                           .unlockedBy(getHasName(material), has(material))
                           .save(consumer, new ResourceLocation(XTutorial.MOD_ID, blockResourceLocation));
    }

    protected static void oreSmeltingAndBlasting(Consumer<FinishedRecipe> consumer, List<ItemLike> itemToSmelt,
                                                 RecipeCategory recipeCategory, ItemLike result, float exp,
                                                 int duration,
                                                 String group) {
        oreCooking(consumer, RecipeSerializer.SMELTING_RECIPE, itemToSmelt, recipeCategory, result, exp, duration,
                   group, "_from_smelting");
        oreCooking(consumer, RecipeSerializer.BLASTING_RECIPE, itemToSmelt, recipeCategory, result, exp, duration,
                   group, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> consumer,
                                     RecipeSerializer<? extends AbstractCookingRecipe> recipeSerializer,
                                     List<ItemLike> itemToSmelt, RecipeCategory recipeCategory, ItemLike result,
                                     float exp, int duration, String group, String recipePathModifier) {
        for (ItemLike itemlike : itemToSmelt) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), recipeCategory, result, exp, duration,
                                               recipeSerializer)
                                      .group(group)
                                      .unlockedBy(getHasName(itemlike), has(itemlike))
                                      .save(consumer, new ResourceLocation(XTutorial.MOD_ID,
                                                                           getItemName(result)
                                                                           + recipePathModifier + "_"
                                                                           + getItemName(itemlike)));
        }

    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, ModItems.BLACK_OPAL.get(),
                                RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_OPAL_BLOCK.get(),
                                getSimpleRecipeName(ModBlocks.BLACK_OPAL_BLOCK.get()),
                                null,
                                getSimpleRecipeName(ModItems.BLACK_OPAL.get()),
                                null);


        oreSmeltingAndBlasting(consumer, List.of(ModItems.RAW_BLACK_OPAL.get()),
                    RecipeCategory.MISC, ModItems.BLACK_OPAL.get(),
                    0.7f, 200, "black_opal");

        oreSmeltingAndBlasting(consumer, List.of(ModBlocks.BLACK_OPAL_ORE.get()),
                    RecipeCategory.MISC, ModItems.RAW_BLACK_OPAL.get(),
                    0.7f, 200, "black_opal");
        oreSmeltingAndBlasting(consumer, List.of(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE.get()),
                    RecipeCategory.MISC, ModItems.RAW_BLACK_OPAL.get(),
                    0.7f, 200, "black_opal");
        oreSmeltingAndBlasting(consumer, List.of(ModBlocks.NETHERRACK_BLACK_OPAL_ORE.get()),
                    RecipeCategory.MISC, ModItems.RAW_BLACK_OPAL.get(),
                    0.7f, 200, "black_opal");
        oreSmeltingAndBlasting(consumer, List.of(ModBlocks.ENDSTONE_BLACK_OPAL_ORE.get()),
                    RecipeCategory.MISC, ModItems.RAW_BLACK_OPAL.get(),
                    0.7f, 200, "black_opal");
    }
}
