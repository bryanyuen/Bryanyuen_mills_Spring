package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private int row = 20;
	private int col = 12;
	private String command;

	@Override
	public String processCommand(String command)
	{
		this.command = command;
		return this.command;
	}

	@Override
	public int getRows()
	{
		return this.row;
	}

	@Override
	public int getCols()
	{
		return this.col;
	}

	@Override
	public Cell getCell(Location loc)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGridText()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
