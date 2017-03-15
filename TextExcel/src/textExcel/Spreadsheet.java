package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private Cell [][] cells = new Cell [this.getRows()][this.getCols()];
	public Spreadsheet(){
		for (int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[i].length; j++){
			cells[i][j] = new EmptyCell();
		}
		}
	}
	@Override
	public String processCommand(String command)
	{
		Location loc;
		String [] Command = command.split(" ");
		if(Command.length == 2&&Command[0].toLowerCase().equals("clear")){  		//clearing a particular cell (e.g., clear A1).
			clearCell(Command[1]);
			return getGridText();
		}else if(Command.length == 3){						//assignment to string values (e.g., A1 = "Hello").
			assignValue(Command);
			return getGridText();	
		}else{
			if(Command.length==1&&Command[0].toLowerCase().equals("clear")){  //clearing the entire sheet (e.g., clear).
				clear();
				return getGridText();
			}else{     			//cell inspection (e.g., A1). This should return the value at that cell
				return inspectCell(Command[0]);
			}
		}	
	}
	public void clear(){
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				cells[i][j] = new EmptyCell();
			}
		}
	}
	public void clearCell(String location){
		SpreadsheetLocation loc = new SpreadsheetLocation(location.toUpperCase());
		cells[loc.getRow()][loc.getCol()] = new EmptyCell();
	}
	@Override
	public int getRows()
	{
		return 20;
	}

	@Override
	public int getCols()
	{
		return 12;
	}

	@Override
	public Cell getCell(Location loc)
	{
		int row = loc.getRow();
		int column = loc.getCol();
		return cells[row][column];
	}

	@Override
	public String getGridText()
	{
		String Grid = ""; 
		String topLetter = "   |";
		String rows = "";
		char start = 'A';
		for(int col = 0; col < this.getCols(); col++){
			topLetter += (char)(start + col) + "         |";
		}
		topLetter += "\n";
		Grid += topLetter;
		String rowHeader = "";
		for(int i = 0;i < this.getRows();i++){
			if(i<9){
				rowHeader = ((i + 1) + "  |");
			}else{ 
				rowHeader = ((i + 1) + " |");
			}
				Grid += rowHeader;
				for(int j = 0; j< this.getCols();j++){
					rows = cells[i][j].abbreviatedCellText() + "|";
				}
				rows +="\n";
				Grid += rows;
				}
		return Grid;
	}
	public void assignValue(String[] cell){
		SpreadsheetLocation loc = new SpreadsheetLocation(cell[0].toUpperCase());
		cells[loc.getRow()][loc.getCol()] = new TextCell(cell[2]);
	}
	
	public String inspectCell(String cell){
		SpreadsheetLocation loc = new SpreadsheetLocation(cell.toUpperCase()); 
		return getCell(loc).fullCellText();
}
}
