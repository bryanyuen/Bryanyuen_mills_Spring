package textExcel;

public class ValueCell extends RealCell implements Cell {
	private String value;
	public ValueCell(String command) {
		super(command);
		this.value = command;
		}
	public String abbreviatedCellText() {
		String abrv = getDoubleValue() + "";
		abrv += "          ";
		return abrv.substring(0,10);
	}
	public String getCellText(){
		return getDoubleValue() + "";
	}
	public double getDoubleValue(){
		return Double.parseDouble(value);
	}
	}
