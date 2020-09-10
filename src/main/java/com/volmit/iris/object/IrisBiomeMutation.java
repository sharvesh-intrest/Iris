package com.volmit.iris.object;

import com.volmit.iris.gen.ContextualTerrainProvider;
import com.volmit.iris.gen.atomics.AtomicCache;
import com.volmit.iris.util.ArrayType;
import com.volmit.iris.util.Desc;
import com.volmit.iris.util.DontObfuscate;
import com.volmit.iris.util.KList;
import com.volmit.iris.util.KSet;
import com.volmit.iris.util.MaxNumber;
import com.volmit.iris.util.MinNumber;
import com.volmit.iris.util.RegistryListBiome;
import com.volmit.iris.util.RegistryListObject;
import com.volmit.iris.util.Required;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@Desc("A biome mutation if a condition is met")
@Data
public class IrisBiomeMutation
{
	@Builder.Default
	@RegistryListBiome
	@Required
	@ArrayType(min = 1, type = String.class)
	@DontObfuscate
	@Desc("One of The following biomes or regions must show up")
	private KList<String> sideA = new KList<>();

	@Builder.Default
	@RegistryListBiome
	@Required
	@ArrayType(min = 1, type = String.class)
	@DontObfuscate
	@Desc("One of The following biomes or regions must show up")
	private KList<String> sideB = new KList<>();

	@Builder.Default
	@Required
	@MinNumber(1)
	@MaxNumber(1024)
	@DontObfuscate
	@Desc("The scan radius for placing this mutator")
	private int radius = 16;

	@Builder.Default
	@Required
	@MinNumber(1)
	@MaxNumber(32)
	@DontObfuscate
	@Desc("How many tries per chunk to check for this mutation")
	private int checks = 2;

	@Builder.Default
	@RegistryListObject
	@ArrayType(min = 1, type = IrisObjectPlacement.class)
	@DontObfuscate
	@Desc("Objects define what schematics (iob files) iris will place in this biome mutation")
	private KList<IrisObjectPlacement> objects = new KList<IrisObjectPlacement>();

	private final transient AtomicCache<KList<String>> sideACache = new AtomicCache<>();
	private final transient AtomicCache<KList<String>> sideBCache = new AtomicCache<>();

	public KList<String> getRealSideA(ContextualTerrainProvider xg)
	{
		return sideACache.aquire(() -> processList(xg, getSideA()));
	}

	public KList<String> getRealSideB(ContextualTerrainProvider xg)
	{
		return sideBCache.aquire(() -> processList(xg, getSideB()));
	}

	public KList<String> processList(ContextualTerrainProvider xg, KList<String> s)
	{
		KSet<String> r = new KSet<>();

		for(String i : s)
		{
			String q = i;

			if(q.startsWith("^"))
			{
				r.addAll(xg.loadRegion(q.substring(1)).getLandBiomes());
				continue;
			}

			else if(q.startsWith("*"))
			{
				String name = q.substring(1);
				r.addAll(xg.loadBiome(name).getAllChildren(xg, 7));
			}

			else if(q.startsWith("!"))
			{
				r.remove(q.substring(1));
			}

			else if(q.startsWith("!*"))
			{
				String name = q.substring(2);
				r.removeAll(xg.loadBiome(name).getAllChildren(xg, 7));
			}

			else
			{
				r.add(q);
			}
		}

		return new KList<String>(r);
	}
}
