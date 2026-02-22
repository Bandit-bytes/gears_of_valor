package net.pixeldreamstudios.gearsofvalor.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.pixeldreamstudios.gearsofvalor.GearsOfValorMod;

public final class FabricWorldgen {

    private static final ResourceKey<PlacedFeature> FALLEN_ORE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(GearsOfValorMod.MOD_ID, "fallen_ore_placed"));

    private static final ResourceKey<PlacedFeature> SOULINITE_ORE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(GearsOfValorMod.MOD_ID, "soulinite_ore_placed"));

    private FabricWorldgen() {}

    public static void init() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                FALLEN_ORE_PLACED
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                SOULINITE_ORE_PLACED
        );
    }
}