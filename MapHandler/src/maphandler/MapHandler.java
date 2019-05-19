package maphandler;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;

public class MapHandler
{
	public static Map importMap(String mapName, PApplet app) throws NullPointerException
	{
		Table table = app.loadTable("maps\\"+ mapName + ".map", "csv");
		int gridScale = table.getInt(0,0);
		table.removeRow(0);
		Map map = new Map(table.getRowCount(), table.getColumnCount(), gridScale, app);
		int mapData[][] = new int[table.getRowCount()][table.getColumnCount()];
		for(int i = 0; i < table.getRowCount(); i++)
		{
			for(int j = 0; j < table.getColumnCount(); j++)
			{
				mapData[j][i] = table.getInt(i, j);
				//gridN.get(i).add(new Tile(i, j, Integer.parseInt(table.getString(j, i)),app));
			}
		}
		map.setup(mapData);
		System.out.printf("%s.map imported.\n",  mapName);
		return map;
	}


	public static void exportMap(Map map, String mapName, PApplet app)
	{
		Table mapData = new Table();
		String metaData[] = new String[1];
		metaData[0] = PApplet.str(map.gridScale);
		mapData.addRow(metaData);
		for(int j = 0; j < map.grid.get(0).size(); j++)
		{
			String row[] = new String[map.grid.size()];
			for(int i = 0; i < map.grid.size(); i++)
			{
				row[i] = PApplet.str(map.tileAt(i,j).terrainType.terrainInt());
			}
			mapData.addRow(row);
		}
		app.saveTable(mapData, "maps\\" + mapName + ".map", "csv");
		System.out.printf("Map exported as %s.map\n",mapName);
	}

	public static Map basicMap(int width, int height, int gridScale, PApplet app)
	{
		Map map = new Map(width/gridScale, height/gridScale, gridScale, app); 
		map.grid = new ArrayList<ArrayList<Tile>>();
		for(int i = 0; i < width/map.gridScale - 3; i++)
		{
			map.grid.add(new ArrayList<Tile>());
			for(int j = 0; j < height/map.gridScale; j++)
			{
				float thirdWidth = width/map.gridScale/3;
				float thirdHeight = height/map.gridScale/3;
				if(i > thirdWidth && i < 2*thirdWidth && j > thirdHeight && j < 2*thirdHeight) map.grid.get(i).add(new Tile(i,j,TerrainType.STONE));
				else map.grid.get(i).add(new Tile(i,j,TerrainType.GRASS));
			}
		}
		for(int i = width/map.gridScale - 3; i < width/map.gridScale; i++)
		{
			map.grid.add(new ArrayList<Tile>());
			for(int j = 0; j < height/map.gridScale; j++)
			{
				map.grid.get(i).add(new Tile(i,j,TerrainType.WATER));
			}
		}
		return map;
	}
}