package maphandler;

public enum TerrainType 
{
	GRASS, WATER, STONE, SAND, TEMP;
	
	public int terrainInt()
	{
		if (this == GRASS) return 1;
		if (this == STONE) return 2;
		if (this == WATER) return 0;
		if (this == SAND) return 3;
		return -1;
	}
}
