package com.terraformersmc.traverse.surfacebuilder;

import com.terraformersmc.terraform.surface.BeachSurfaceBuilder;
import com.terraformersmc.traverse.Traverse;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.HashMap;
import java.util.Map;

public class TraverseSurfaceBuilders {

	private static final Map<Identifier, SurfaceBuilder<? extends SurfaceConfig>> SURFACE_BUILDERS = new HashMap<>();

	public static final SurfaceBuilder<TernarySurfaceConfig> ARID_HIGHLANDS = add("arid_highlands", new PatchesSurfaceBuilder(TernarySurfaceConfig::deserialize, SurfaceBuilder.SAND_CONFIG, 0.9D));
	public static final SurfaceBuilder<TernarySurfaceConfig> ARID_SLOPES = add("arid_slopes", new PatchesSurfaceBuilder(TernarySurfaceConfig::deserialize, SurfaceBuilder.GRASS_CONFIG, 1.5D));
	public static final SurfaceBuilder<TernarySurfaceConfig> DESERT_SHRUBLAND = add("desert_shrubland", new PatchesSurfaceBuilder(TernarySurfaceConfig::deserialize, SurfaceBuilder.SAND_CONFIG, 1.5D));
	public static final SurfaceBuilder<TernarySurfaceConfig> PARCHED_PLATEAU = add("parched_plateau", new PatchesSurfaceBuilder(TernarySurfaceConfig::deserialize, SurfaceBuilder.STONE_CONFIG, 1.7D));
	public static final SurfaceBuilder<TernarySurfaceConfig> WOODED_ISLAND = add("wooded_island", new BeachSurfaceBuilder(TernarySurfaceConfig::deserialize, 62, v -> Blocks.SAND.getDefaultState()));

	public static <S extends SurfaceBuilder<? extends SurfaceConfig>> S add(String name, S feature) {
		SURFACE_BUILDERS.put(new Identifier(Traverse.MOD_ID, name), feature);
		return feature;
	}

	public static void register() {
		for (Identifier id : SURFACE_BUILDERS.keySet()) {
			Registry.register(Registry.SURFACE_BUILDER, id, SURFACE_BUILDERS.get(id));
		}
	}

}
