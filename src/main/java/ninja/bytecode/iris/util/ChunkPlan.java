package ninja.bytecode.iris.util;

import ninja.bytecode.iris.pack.IrisBiome;
import ninja.bytecode.shuriken.collections.GMap;

public class ChunkPlan
{
	private final GMap<SChunkVector, Integer> realHeightCache;
	private final GMap<SChunkVector, Integer> realWaterHeightCache;
	private final GMap<SChunkVector, Double> heightCache;
	private final GMap<SChunkVector, IrisBiome> biomeCache;

	public ChunkPlan()
	{
		this.realHeightCache = new GMap<>();
		this.realWaterHeightCache = new GMap<>();
		this.heightCache = new GMap<>();
		this.biomeCache = new GMap<>();
	}

	public IrisBiome getBiome(int x, int z)
	{
		return biomeCache.get(new SChunkVector(x, z));
	}

	public void setBiome(int x, int z, IrisBiome cng)
	{
		biomeCache.put(new SChunkVector(x, z), cng);
	}

	public double getHeight(int x, int z)
	{
		SChunkVector c = new SChunkVector(x, z);
		if(hasHeight(c))
		{
			return heightCache.get(c);
		}

		return -1;
	}

	public int getRealHeight(int x, int z)
	{
		SChunkVector c = new SChunkVector(x, z);
		if(realHeightCache.containsKey(c))
		{
			return realHeightCache.get(c);
		}

		return 0;
	}

	public int getRealWaterHeight(int x, int z)
	{
		SChunkVector c = new SChunkVector(x, z);

		if(realWaterHeightCache.containsKey(c))
		{
			return realWaterHeightCache.get(c);
		}

		return 0;
	}

	public boolean hasHeight(SChunkVector c)
	{
		return heightCache.containsKey(c);
	}

	public boolean hasHeight(int x, int z)
	{
		return hasHeight(new SChunkVector(x, z));
	}

	public void setHeight(SChunkVector c, double h)
	{
		heightCache.put(c, h);
	}

	public void setRealHeight(SChunkVector c, int h)
	{
		realHeightCache.put(c, h);
	}

	public void setRealHeight(int x, int z, int h)
	{
		setRealHeight(new SChunkVector(x, z), h);
	}

	public void setRealWaterHeight(SChunkVector c, int h)
	{
		realWaterHeightCache.put(c, h);
	}

	public void setRealWaterHeight(int x, int z, int h)
	{
		setRealWaterHeight(new SChunkVector(x, z), h);
	}

	public void setHeight(int x, int z, double h)
	{
		setHeight(new SChunkVector(x, z), h);
	}
}
