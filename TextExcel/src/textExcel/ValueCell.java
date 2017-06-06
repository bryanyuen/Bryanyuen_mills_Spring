package textExcel;

public class ValueCell extends RealCell implements Cell {
	private String value;
	public ValueCell(String command) {	//constructor
			this.value = command;
			setValue(value);
	}
	public String abbreviatedCellText() {	//method that returns 10 indexes value
			String abrv;
			if(value.length()>10){		//check if value is longer than 10 numbers
				abrv = value.substring(0,10);	//if so just return the first 10
			}else{
				abrv = getDoubleValue () + "";		//if not just fill in spaces 
				abrv += "          ";
			}
			return abrv.substring(0,10);	//get the first 10 indexes 
		}
		public String fullCellText() {	//getter
			return value;
		}
		@Override
		public double getDoubleValue() {	//method that transfer into double value
			double holder = Double.parseDouble(this.getValue());
			return holder;
		}
	}
