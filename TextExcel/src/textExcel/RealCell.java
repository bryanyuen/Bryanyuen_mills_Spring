package textExcel;

public abstract class RealCell implements Cell {
private String value;
	
	abstract double getDoubleValue();  //abstract method
	public String getValue() {		//method that return modified value
		return this.value;
	}
	public void setValue(String text) {		//method that is known as a setter
		this.value = text;
	}
	private boolean isDecimalEqualToZero(String doubleValue) {	//it is used to check condition later
		if (doubleValue.contains("%")) {
			return false;
		}
		String[] n = doubleValue.split("\\.");
		if(n.length > 1) {
			// get the value to right of decimal point
			int d = Integer.parseInt(n[1]);
			return d == 0;
		}
		else {
			return true;
		}
		
	}

	public String abbreviatedCellText()		//method that return the content in the cell with 10 indexes
	{
		String fauxValue = value;
		if (value.contains("%") && value.contains(".")) {	//check if value contains dot or percent sign
			fauxValue =	value.replace(value.substring(value.indexOf(".")), "%");	//replace the dot or the percentage  sign
		}
		else if (!value.contains("%")) { 	//if it doesn't contain percent sign
			fauxValue = Double.parseDouble(value) + "";			//it should be a double
		}
	
	
		if (fauxValue.length() > 10) {		//if there are more than 10 num or char, it would just get the first 10
			return fauxValue.substring(0, 10);
		}
		else { //This is padToTen
			
			for (int i = fauxValue.length(); i < 10; i++) {	//if there are less than 10, it adds more space
				fauxValue += " ";
			}
			return fauxValue;
		}
	}
	public String fullCellText() {		//method that transfer value into full cell text
		String value = getDoubleValue() + "";
			if(getValue().equals("0")) {	// it equals to 0, we can just return 
				return getValue();
			}
			else if (!value.contains("%") && value.contains(".0") 	//check if value contains no percent sign or ".0"
					&& isDecimalEqualToZero(value)					//make sure it is a whole number
					&& value.replace("-", "").length() > 3) {	
				return value.substring(0, value.indexOf("."));
			}
			else if (!value.contains("%") && !value.contains(".")) {	//if value contains percent sign and no contain dot
				value += ".0";											//just add ".0" at the end
			}
		return value;
	}

}
