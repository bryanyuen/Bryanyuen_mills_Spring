package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private Cell [][] cells = new Cell[20][12];
	public Spreadsheet(){
		for (int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[i].length; j++){
			cells[i][j] = new EmptyCell();
		}
		}
	}
	private int row = 20;
	private int col = 12;
	private String command;
	@Override
	public String processCommand(String command)
	{
		if (command.equals("")){
			return command;
		}
		if(command.toUpperCase().equals("CLEAR")){
			for(int i =0; i < cells.length; i++){
				for(int j = 0; j < cells[i].length; j++){
					cells[i][j] = new EmptyCell();
				}
			}
		}
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
		return null;
}
}
