package textExcel;

public class FormulaCell extends RealCell implements Cell {
	
	private Spreadsheet sheet;

	public FormulaCell(String text, Spreadsheet sheet) {
		this.sheet = sheet;
		setValue(text);
	}

	public double getDoubleValue() {

		String value = this.getValue().substring(2, getValue().length() - 2);
			String[] expression = value.trim().split(" ");
			if (expression[0].toUpperCase().equals("SUM")) {
				String[] range = expression[1].toUpperCase().split("-");
				return sum(range[0], range[0], range[1]);
			} else if (expression[0].toUpperCase().equals("AVG")) {
				String[] range = expression[1].toUpperCase().split("-");
				return sum(range[0], range[0], range[1]) / countRange(range[0], range[0], range[1]);
			} else {
				for (int i = 0; i < expression.length; i++) {
					if (expression[i].toUpperCase().charAt(0) >= 'A' && expression[i].toUpperCase().charAt(0) <= 'L') {
						SpreadsheetLocation loc = sheet.getLocation(expression[i].toUpperCase());
						Cell cell = sheet.getSheet()[loc.getRow()][loc.getCol()];
						if (cell instanceof RealCell) {
							expression[i] = ((RealCell) cell).getDoubleValue() + "";
						} else {
							expression[i] = "";
						}
					}
				}
				double standingValue = Double.parseDouble(expression[0]);
				for (int i = 1; i < expression.length; i++) {
					if (expression[i].equals("*"))
						standingValue *= Double.parseDouble(expression[i + 1]);
					else if (expression[i].equals("/"))
						standingValue /= Double.parseDouble(expression[i + 1]);
					else if (expression[i].equals("+"))
						standingValue += Double.parseDouble(expression[i + 1]);
					else if (expression[i].equals("-"))
						standingValue -= Double.parseDouble(expression[i + 1]);
				}
				return standingValue;
			}
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

	public double sum(String locStart, String loc1, String loc2) {
		if (loc1.equals(loc2)) {
			SpreadsheetLocation loc = sheet.getLocation(loc2);
			Cell cell = sheet.getSheet()[loc.getRow()][loc.getCol()];
			if (cell instanceof RealCell) {
				return ((RealCell) cell).getDoubleValue();
			} else {
				return 0.0;
			}
		} else if (loc1.substring(1).equals(loc2.substring(1))) {
			SpreadsheetLocation loc = sheet.getLocation(loc1);
			loc1 = ((char) (loc1.charAt(0) + 1)) + locStart.substring(1);
			Cell cell = sheet.getSheet()[loc.getRow()][loc.getCol()];
			if (cell instanceof RealCell) {
				return ((RealCell) cell).getDoubleValue() + sum(locStart, loc1, loc2);
			} else {
				return sum(locStart, loc1, loc2);
			}
		} else {
			SpreadsheetLocation loc = sheet.getLocation(loc1);
			loc1 = loc1.substring(0, 1) + (Integer.parseInt(loc1.substring(1)) + 1);
			Cell cell = sheet.getSheet()[loc.getRow()][loc.getCol()];
			if (cell instanceof RealCell) {
				return ((RealCell) cell).getDoubleValue() + sum(locStart, loc1, loc2);
			} else {
				return sum(locStart, loc1, loc2);
			}
		}

	}

	public double countRange(String locStart, String loc1, String loc2) {
		SpreadsheetLocation loc = sheet.getLocation(loc1);
		if (loc1.equals(loc2)) {
			return 1;
		} else if (loc1.substring(1).equals(loc2.substring(1))) {
			loc1 = (char) (loc1.charAt(0) + 1) + locStart.substring(1);
			if (sheet.getSheet()[loc.getRow()][loc.getCol()] instanceof RealCell) {
				return 1 + countRange(locStart,loc1, loc2);
			} else {
				return countRange(locStart,loc1, loc2);
			}
		} else {
			loc1 = loc1.substring(0, 1) + (Integer.parseInt(loc1.substring(1)) + 1);
			if (sheet.getSheet()[loc.getRow()][loc.getCol()] instanceof RealCell) {
				return 1 + countRange(locStart,loc1, loc2);
			} else {
				return countRange(locStart, loc1, loc2);
			}

		}
	}

}