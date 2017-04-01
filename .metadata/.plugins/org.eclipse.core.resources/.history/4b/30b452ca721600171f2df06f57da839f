package textExcel;

public class PercentCell extends RealCell implements Cell {
	private String value;
	public PercentCell(String command) {
		super(command);
		this.value = command;
		// TODO Auto-generated constructor stub
	}
	public String abbreviatedCellText() {
		String text;
		int dot = value.indexOf(".");
		return (value.substring(0, dot) + "%" + "          ").substring(0, 10);
}
	public double getDoubleValue(){
		return Double.parseDouble(this.fullCellText().substring(0,this.fullCellText().length()-1));
	}
	public String fullCellText(){
			double number = Double.parseDouble(value.substring(0, value.indexOf("%")))/100.0;
			return Double.toString(number);
	}
}
	
