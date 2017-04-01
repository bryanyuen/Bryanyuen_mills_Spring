package textExcel;

public class ValueCell extends RealCell implements Cell {
	private String value;
	public ValueCell(String command) {
		super(command);
		this.value = command;
		}
	public String abbreviatedCellText() {
			String abrv;
			if(value.length()>10){
				abrv = value.substring(0,10);
			}else{
				abrv = GetDoubleValue () + "";
				abrv += "          ";
			}
			return abrv.substring(0,10);
		}
		public String fullCellText() {
			return value;
		}
		public double GetDoubleValue (){
			return Double.parseDouble(value);
		}

	}
