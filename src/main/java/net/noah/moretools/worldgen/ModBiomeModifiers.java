package net.noah.moretools.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.noah.moretools.MoreTools;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_ENDERITE_ORE = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
            new ResourceLocation(MoreTools.MOD_ID, "add_enderite_ore"));

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_ENDERITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_HIGHLANDS)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ENDERITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }
}
