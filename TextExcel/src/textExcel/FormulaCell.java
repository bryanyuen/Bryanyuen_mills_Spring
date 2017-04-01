package textExcel;

public class FormulaCell extends RealCell implements Cell {
	private Spreadsheet spreadSheet;
	public FormulaCell(String text, Spreadsheet spreadSheet) {
		this.spreadSheet = spreadSheet;
		setValue(text);
	}

	public String abbreviatedCellText() {
		String value = getDoubleValue() + "";
		if (value.length() > 10) {
			return value.substring(0, 10);
		} else {
			for (int i = value.length(); i < 10; i++) {
				value += " ";
			}
			return value;
		}
	}

	public String fullCellText() {
		return getValue();
	}

	public double sum(String locFirst, String locSecond, String locThird) { //used to find the sum of several cells
		if (locSecond.equals(locThird)) {
			SpreadsheetLocation location = spreadSheet.getLocation(locThird);
			Cell cell = spreadSheet.getSheet()[location.getRow()][location.getCol()];
			if (cell instanceof RealCell) {
				return ((RealCell) cell).getDoubleValue();
			} else {
				return 0.0;
			}
		} else if (locSecond.substring(1).equals(locThird.substring(1))) {
			SpreadsheetLocation loc = spreadSheet.getLocation(locSecond);
			locSecond = ((char) (locSecond.charAt(0) + 1)) + locFirst.substring(1);
			Cell cell = spreadSheet.getSheet()[loc.getRow()][loc.getCol()];
			if (cell instanceof RealCell) {
				return ((RealCell) cell).getDoubleValue() + sum(locFirst, locSecond, locThird);
			} else {
				return sum(locFirst, locSecond, locThird);
			}
		} else {
			SpreadsheetLocation loc = spreadSheet.getLocation(locSecond);
			locSecond = locSecond.substring(0, 1) + (Integer.parseInt(locSecond.substring(1)) + 1);
			Cell cell = spreadSheet.getSheet()[loc.getRow()][loc.getCol()];
			if (cell instanceof RealCell) {
				return ((RealCell) cell).getDoubleValue() + sum(locFirst, locSecond, locThird);
			} else {
				return sum(locFirst, locSecond, locThird);
			}
		}

	}

	public double countCell(String locFirst, String locSecond, String locThird) { //used to get the Value from cells
		SpreadsheetLocation loc = spreadSheet.getLocation(locSecond);
		if (locSecond.equals(locThird)) {
			return 1;
		} else if (locSecond.substring(1).equals(locThird.substring(1))) {
			locSecond = (char) (locSecond.charAt(0) + 1) + locFirst.substring(1);
			if (spreadSheet.getSheet()[loc.getRow()][loc.getCol()] instanceof RealCell) {
				return 1 + countCell(locFirst,locSecond, locThird);
			} else {
				return countCell(locFirst,locSecond, locThird);
			}
		} else {
			locSecond = locSecond.substring(0, 1) + (Integer.parseInt(locSecond.substring(1)) + 1);
			if (spreadSheet.getSheet()[loc.getRow()][loc.getCol()] instanceof RealCell) {
				return 1 + countCell(locFirst,locSecond, locThird);
			} else {
				return countCell(locFirst, locSecond, locThird);
			}

		}
	}

	public double getDoubleValue() { //used to get the value in double form

		String formula = this.getValue().substring(2, getValue().length() - 2);
			String[] equation = formula.trim().split(" ");
			if (equation[0].toLowerCase().equals("sum")) {
				String[] range = equation[1].toUpperCase().split("-");
				return sum(range[0], range[0], range[1]);
			} else if (equation[0].toLowerCase().equals("avg")) {
				String[] range = equation[1].toUpperCase().split("-");
				return sum(range[0], range[0], range[1]) / countCell(range[0], range[0], range[1]);
			} else {
				for (int i = 0; i < equation.length; i++) {
					if (equation[i].toUpperCase().charAt(0) >= 'A' && equation[i].toUpperCase().charAt(0) <= 'L') {
						SpreadsheetLocation loc = spreadSheet.getLocation(equation[i].toUpperCase());
						Cell cell = spreadSheet.getSheet()[loc.getRow()][loc.getCol()];
						if (cell instanceof RealCell) {
							equation[i] = ((RealCell) cell).getDoubleValue() + "";
						} else {
							equation[i] = "";
						}
					}
				}
				double standingValue = Double.parseDouble(equation[0]);
				for (int i = 1; i < equation.length; i++) {
					if (equation[i].equals("*"))
						standingValue *= Double.parseDouble(equation[i + 1]);
					else if (equation[i].equals("/"))
						standingValue /= Double.parseDouble(equation[i + 1]);
					else if (equation[i].equals("+"))
						standingValue += Double.parseDouble(equation[i + 1]);
					else if (equation[i].equals("-"))
						standingValue -= Double.parseDouble(equation[i + 1]);
				}
				return standingValue;
			}
	}

}