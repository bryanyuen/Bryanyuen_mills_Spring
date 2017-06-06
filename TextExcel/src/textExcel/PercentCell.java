package textExcel;

public class PercentCell extends RealCell implements Cell {
	private String value;
	public PercentCell(String command) {
		this.value = command;
		// TODO Auto-generated constructor stub
	}
	public String abbreviatedCellText() {	//method that return the percent with 10 indexes
		String text;
		int dot = value.indexOf(".");	//check the number of index of the dot
		return (value.substring(0, dot) + "%" + "          ").substring(0, 10);		//ensure it is 10 indexes
}
	public double getDoubleValue(){		//the method that transfer into double
		return Double.parseDouble(this.fullCellText().substring(0,this.fullCellText().length()-1));
	}
	public String fullCellText(){	//method that transfer into a string
			double number = Double.parseDouble(value.substring(0, value.indexOf("%")))/100.0;
			return Double.toString(number);
	}
}
	
