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
		if(command.length() > 1){
		String [] Command = command.split(" ",3);
		if(Command.length == 2&&Command[0].toLowerCase().equals("clear")){  		//clearing a particular cell (e.g., clear A1).
			clearCell(Command[1]);
			return getGridText();
		}else if(Command.length == 3){						//assignment to string values (e.g., A1 = "Hello").
			assignValue(Command[0],Command[2]);
			return getGridText();	
		}else{
			if(Command.length==1&&Command[0].toLowerCase().equals("clear")){  //clearing the entire sheet (e.g., clear).
				clear();
				return getGridText();
			}else{     			//cell inspection (e.g., A1). This should return the value at that cell
				return inspectCell(Command[0]);
			}
		}
		}else {
			return command;
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
		String topLetter = "   |";
		for(char i = 'A'; i<='L'; i++){
			topLetter += i + "         |";
		}
		String numbers = "\n";
		for(int i = 0;i < 20;i++){
			if(i<9){
				numbers += (i+1);
				numbers += "  |";
				for(int j = 0; j<12;j++){
					numbers += cells[i][j].abbreviatedCellText() + "|";
				}
				numbers +="\n";
			}else{
				numbers += (i+1);
				numbers += " |";
				for(int j = 0; j<12;j++){
					numbers += cells[i][j].abbreviatedCellText() + "|";
				}
				numbers +="\n";
			}
		}
		return topLetter + numbers;
	}
	public void assignValue(String cell, String input){
		SpreadsheetLocation loc = new SpreadsheetLocation(cell.toUpperCase());
		if (Double.parseDouble(input) > 1.0){
			cells[loc.getRow()][loc.getCol()] = new ValueCell(input);
		}else if(input.contains("%")){
			cells[loc.getRow()][loc.getCol()] = new PercentCell(input);
		}else if(input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")){
			cells[loc.getRow()][loc.getCol()] = new FormulaCell(input);
		}else{
		cells[loc.getRow()][loc.getCol()] = new TextCell(input.trim());
		}
	}
	
	public String inspectCell(String cell){
		SpreadsheetLocation loc = new SpreadsheetLocation(cell.toUpperCase()); 
		return getCell(loc).fullCellText();
}
}
