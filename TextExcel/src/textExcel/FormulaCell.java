package textExcel;

public class FormulaCell extends RealCell implements Cell {
	private Spreadsheet spreadSheet;
	public FormulaCell(String text, Spreadsheet spreadSheet) { //constructor
		this.spreadSheet = spreadSheet;
		setValue(text);
	}

	public String abbreviatedCellText() {	//method that returns the 10 indexes text 
		String value = getDoubleValue() + "";
		if (value.length() > 10) {		//check if it is longer than 10 indexes
			return value.substring(0, 10);	//if so just take the first 10 indexes
		} else {
			for (int i = value.length(); i < 10; i++) {		//if not longer than 10 indexes and shorter than 10
				value += " ";		//pad to ten with filling spaces
			}
			return value;
		}
	}

	public String fullCellText() {		//getter
		return getValue();
	}

	public double sum(String locFirst, String locSecond, String locThird) { //used to find the sum of several cells
		if (locSecond.equals(locThird)) {	//check if lcoSecond and locThird are the same
			SpreadsheetLocation location = spreadSheet.getLocation(locThird);	//getting the location of third
			Cell cell = spreadSheet.getSheet()[location.getRow()][location.getCol()];	//getting the instance of locThird
			if (cell instanceof RealCell) {		//check if the cell is real cell 
				return ((RealCell) cell).getDoubleValue();	//if so return the value
			} else {
				return 0.0;	//if not, return 0.0
			}
		} else if (locSecond.substring(1).equals(locThird.substring(1))) {	//check if second location's column is same as the third';s
			SpreadsheetLocation loc = spreadSheet.getLocation(locSecond);		//getting the location of second
			locSecond = ((char) (locSecond.charAt(0) + 1)) + locFirst.substring(1);	  
			Cell cell = spreadSheet.getSheet()[loc.getRow()][loc.getCol()];		//getting the instance of locSecond
			if (cell instanceof RealCell) {			//checking if the cell is real cell
				return ((RealCell) cell).getDoubleValue() + sum(locFirst, locSecond, locThird);	//if so, return the the double value of locSecond and the sum 
			} else {
				return sum(locFirst, locSecond, locThird);
			}  //recursion
		} else {
			SpreadsheetLocation loc = spreadSheet.getLocation(locSecond);	//getting the location of locSceond
			locSecond = locSecond.substring(0, 1) + (Integer.parseInt(locSecond.substring(1)) + 1);		
			Cell cell = spreadSheet.getSheet()[loc.getRow()][loc.getCol()];		//getting the instance of locSecond
			if (cell instanceof RealCell) {		//checking if it is real cell
				return ((RealCell) cell).getDoubleValue() + sum(locFirst, locSecond, locThird);
			} else {
				return sum(locFirst, locSecond, locThird);
			}
		}

	}

	public double countCell(String locFirst, String locSecond, String locThird) { //used to count the number of cells that are involved
		SpreadsheetLocation loc = spreadSheet.getLocation(locSecond);	//get the location of locSecond
		if (locSecond.equals(locThird)) {		//check if locSecond and locThird are the same
			return 1;  //if so return one 
		} else if (locSecond.substring(1).equals(locThird.substring(1))) {	//check if the second index of locSecond and locThird are the same 
			locSecond = (char) (locSecond.charAt(0) + 1) + locFirst.substring(1);	
			if (spreadSheet.getSheet()[loc.getRow()][loc.getCol()] instanceof RealCell) {	//checking if the instance of locSecond is a real cell
				return 1 + countCell(locFirst,locSecond, locThird);		//if so return 1 plus the output of countCell //which means recursion is used
			} else {
				return countCell(locFirst,locSecond, locThird);		//if not just call the countCell again
			}
		} else {	//if locSecond is not same as locThird
			locSecond = locSecond.substring(0, 1) + (Integer.parseInt(locSecond.substring(1)) + 1);	
			if (spreadSheet.getSheet()[loc.getRow()][loc.getCol()] instanceof RealCell) {	//check if the instance of locSecond is a real cell
				return 1 + countCell(locFirst,locSecond, locThird);		//if so return 1 plus the output of countCell //which means recursion is used
			} else {
				return countCell(locFirst, locSecond, locThird);	//if not just call the countCell again
			}

		}
	}

	public double getDoubleValue() { //used to get the value in double form

		String formula = this.getValue().substring(2, getValue().length() - 2);
			String[] equation = formula.trim().split(" ");	//splitting the equation by space
			if (equation[0].toLowerCase().equals("sum")) {		//check if the user want the sum
				String[] range = equation[1].toUpperCase().split("-");	// split using "-"
				return sum(range[0], range[0], range[1]);
			} else if (equation[0].toLowerCase().equals("avg")) {	// check if the user want the average
				String[] range = equation[1].toUpperCase().split("-");		//split using "-"
				return sum(range[0], range[0], range[1]) / countCell(range[0], range[0], range[1]);		//average = the sum divide by the number of cells, so sum and countCell method should be used
			} else {
				for (int i = 0; i < equation.length; i++) {		
					if (equation[i].toUpperCase().charAt(0) >= 'A' && equation[i].toUpperCase().charAt(0) <= 'L') {		//check if each location is within the range
						SpreadsheetLocation loc = spreadSheet.getLocation(equation[i].toUpperCase());
						Cell cell = spreadSheet.getSheet()[loc.getRow()][loc.getCol()]; 	//getting the instance
						if (cell instanceof RealCell) {		//check if it is a real cell
							equation[i] = ((RealCell) cell).getDoubleValue() + "";	//casting //using the getDoubleValue in real cell
						} else {	
							equation[i] = "";	//if it is not real cell, return ""
						}
					}
				}
				double standingValue = Double.parseDouble(equation[0]);	
				for (int i = 1; i < equation.length; i++) {
					if (equation[i].equals("*"))	//checking if it is multiplication
						standingValue *= Double.parseDouble(equation[i + 1]);
					else if (equation[i].equals("/"))	//checking if it is a division 
						standingValue /= Double.parseDouble(equation[i + 1]);
					else if (equation[i].equals("+"))	//checking if it is a sum 
						standingValue += Double.parseDouble(equation[i + 1]);
					else if (equation[i].equals("-"))	//checking if it is a subtract
						standingValue -= Double.parseDouble(equation[i + 1]);
				}
				return standingValue;
			}
	}

}