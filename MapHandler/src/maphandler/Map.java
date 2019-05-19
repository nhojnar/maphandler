package maphandler;
import java.util.ArrayList;

import processing.core.*;

public class Map 
{
	public ArrayList<ArrayList<Tile>> grid;
	public int gridScale;
	public int x, y, mapWidth, mapHeight, windowWidth, windowHeight;
	PApplet app;
	
	public Map(int x, int y, int windowWidth, int windowHeight, int gridScale, PApplet app)
	{
		this.x = x;
		this.y = y;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		mapWidth = windowWidth / gridScale;
		mapHeight = windowHeight / gridScale;
		this.gridScale = gridScale;
		this.app = app;
	}
	public Map(int mapWidth, int mapHeight, int gridScale, PApplet app)
	{
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.gridScale = gridScale;
		windowWidth = mapWidth * gridScale;
		windowHeight = mapHeight * gridScale;
		this.app = app;
	}
	
	void setup(int[][] mapData)
	{
		grid = new ArrayList<ArrayList<Tile>>();
		for(int i = 0; i < mapData.length; i++)
		{
			grid.add(new ArrayList<Tile>());
			for(int j = 0; j < mapData[0].length; j++)
			{
				TerrainType thisType;
				switch(mapData[i][j])
				{
				case 0:
					thisType = TerrainType.WATER;
					break;
				case 1:
					thisType = TerrainType.GRASS;
					break;
				case 2:
					thisType = TerrainType.STONE;
					break;
				case 3:
					thisType = TerrainType.SAND;
					break;
				default:
					thisType = TerrainType.TEMP;
				}
				grid.get(i).add(new Tile(i, j, thisType));
			}
		}
	}
	
	public void refresh()
	{
		for(ArrayList<Tile> row : grid)
		{
			for(Tile t : row)
			{
				t = new Tile((int)t.pos.x, (int)t.pos.y, t.terrainType);
			}
		}
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void initiateBlankMap()
	{
		grid = new ArrayList<ArrayList<Tile>>();
		for(int i = 0; i < mapWidth; i++)
		{
			grid.add(new ArrayList<Tile>());
			for(int j = 0; j < mapHeight; j++)
			{
				grid.get(i).add(new Tile(i,j,TerrainType.GRASS));
			}
		}
	}
	
	public void update()
	{
		app.stroke(0);
		app.strokeWeight(1);
		for(ArrayList<Tile> gridRow : grid)
			for(Tile tile : gridRow)
				tile.update(this);
	}
	
	public void clear()
	{
		grid.clear();
	}
	
	public Tile tileAt(PVector pos)
	{
		return tileAt((int)pos.x, (int)pos.y);
	}
	public Tile tileAt(int x, int y)
	{
		return grid.get(x).get(y);
	}
	
	public boolean mouseWithin(PApplet app)
	{
		return (app.mouseX >= x && app.mouseX <= x + windowWidth && app.mouseY >= y && app.mouseY <= y + windowHeight);
	}
	public Tile tileAt(float x2, float f) 
	{
		return tileAt((int)x2, (int)f);
	}
}
