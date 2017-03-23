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
		if (value.contains(".")){
			text = value;
		}else{
			text = value.substring(0,value.indexOf("."));
		}
		text += "%          ";
		return text.substring(0,11);
}
	public String getCellText(){
		return getDoubleValue() + "";
	}
	public double getDoubleValue(){
			return Double.parseDouble(value.substring(0, value.length()-1))/100;
	}
}
	
