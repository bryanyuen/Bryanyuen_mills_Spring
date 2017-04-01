package textExcel;

public class ValueCell extends RealCell implements Cell {
	private String value;
	public ValueCell(String command) {
			this.value = command;
			setValue(value);
	}
	public String abbreviatedCellText() {
			String abrv;
			if(value.length()>10){
				abrv = value.substring(0,10);
			}else{
				abrv = getDoubleValue () + "";
				abrv += "          ";
			}
			return abrv.substring(0,10);
		}
		public String fullCellText() {
			return value;
		}
		@Override
		double getDoubleValue() {
			double holder = Double.parseDouble(this.getValue());
			return holder;
		}
	}
