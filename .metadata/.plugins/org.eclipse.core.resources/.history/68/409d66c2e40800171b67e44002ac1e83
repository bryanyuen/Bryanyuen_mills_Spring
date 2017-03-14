package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
	private int row = 20;
	private int col = 12;
	private String command;
	private String text;
	private Cell [][] cells = new EmptyCell[20][12];
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
			loc = new SpreadsheetLocation(Command[1]);
			Cell input = new EmptyCell();
			cells[loc.getRow()][loc.getCol()] = input;
			return getGridText();
		}else if(Command.length == 3){						//assignment to string values (e.g., A1 = "Hello").
			loc = new SpreadsheetLocation(Command[0]); 
			TextCell input = new TextCell(Command[2].substring(1, Command[2].length()-1));
			cells[loc.getRow()][loc.getCol()] = input;
			return getGridText();	
		}else{
			if(Command.length==1&&Command[0].toLowerCase().equals("clear")){  //clearing the entire sheet (e.g., clear).
				clear();
			}else{     			//cell inspection (e.g., A1). This should return the value at that cell
				loc = new SpreadsheetLocation(Command[0]); 
				String text = "\"" + cells[loc.getRow()][loc.getCol()].fullCellText() + "\"";
			}
		}	
		return text;
	}
	public String clear(){
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				cells[i][j] = new EmptyCell();
			}
		}
		return this.getGridText();
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
		int row = loc.getRow();
		int column = loc.getCol();
		return cells[row][column];
	}

	@Override
	public String getGridText()
	{
		cells = new EmptyCell[20][12];
		String Grid = ""; 
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
		Grid = topLetter + numbers;
		return Grid;
	}
}
