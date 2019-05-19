package maphandler;
import processing.core.*;

public class Tile
{
	public PVector pos;
	public TerrainType terrainType;
	public boolean occupied;
	public float maxFood, food, foodRegen;

	Tile(int x, int y, TerrainType terrainType)
	{    
		this.pos = new PVector(x,y);
		this.terrainType = terrainType;
		this.occupied = false;
		switch(terrainType)
		{
		case GRASS:
			this.maxFood = 15;
			this.foodRegen = .35f;
			break;
		case STONE:
			this.maxFood = 6;
			this.foodRegen = .09f;
			break;
		case WATER:
			this.maxFood = 0;
			this.foodRegen = 0;
			break;
		case SAND:
			this.maxFood = 1;
			this.foodRegen = 0;
			break;
		default:
			this.maxFood = 0;
			this.foodRegen = 0;
			break;
		}
		this.food = this.maxFood;
	}

	void update(Map map)
	{
		this.food = (this.food + this.foodRegen >= this.maxFood) ? this.maxFood : this.food + this.foodRegen;

		map.app.rectMode(PConstants.CENTER);
		switch(this.terrainType)
		{
		case GRASS:
			map.app.fill(0,255,0);
			break;
		case STONE:
			map.app.fill(210);
			break;
		case WATER:
			map.app.fill(0, 180, 255);
			break;
		case SAND:
			map.app.fill(240, 230, 90);
			break;
		case TEMP:
			map.app.fill(255, 255, 0);
			break;
		}
		map.app.rect(this.pos.x*map.gridScale, this.pos.y*map.gridScale,map.gridScale,map.gridScale);
		map.app.fill(0);
		//text(this.food, this.pos.x*gridScale-gridScale/2.2, this.pos.y*gridScale+gridScale/2.2);
	}
}