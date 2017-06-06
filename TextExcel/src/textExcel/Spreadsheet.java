package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private Cell [][] cells = new Cell [this.getRows()][this.getCols()]; //creating the grid
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
	public void clear(){		//method that use to make a certain cell into empty cell
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				cells[i][j] = new EmptyCell();
			}
		}
	}
	public void clearCell(String location){     	//method that make the whole list into empty cell
		SpreadsheetLocation loc = new SpreadsheetLocation(location.toUpperCase());
		cells[loc.getRow()][loc.getCol()] = new EmptyCell();
	}
	@Override
	public int getRows()		//row equals to 20
	{
		return 20;
	}

	@Override
	public int getCols()		//column equals to 12
	{
		return 12;
	}

	@Override
	public Cell getCell(Location loc)		//get the type of the cell
	{
		int row = loc.getRow();
		int column = loc.getCol();
		return cells[row][column];
	}

	@Override
	public String getGridText()		//Method that return the text in the cell
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
	public void assignValue(String cell, String input){		//method that use to determine which cell it is
		SpreadsheetLocation loc = new SpreadsheetLocation(cell.toUpperCase());
		if(input.contains("\"")){
			cells[loc.getRow()][loc.getCol()] = new TextCell(input.trim());
		}else if(input.contains("%")){
			cells[loc.getRow()][loc.getCol()] = new PercentCell(input);
		}else if(input.charAt(0) == '('){
			cells[loc.getRow()][loc.getCol()] = new FormulaCell(input, this);
		}else{ 
			cells[loc.getRow()][loc.getCol()] = new ValueCell(input);
		}
	}
	
	public String inspectCell(String cell){			//method that return the context in the cell
		SpreadsheetLocation loc = new SpreadsheetLocation(cell.toUpperCase()); 
		return getCell(loc).fullCellText();
}
	public Cell [][] getSheet() {	
		return this.cells;
	}
public SpreadsheetLocation getLocation(String command) {	//method that return the location of the cell
	SpreadsheetLocation location = new SpreadsheetLocation(command);
	return location;
}

}
