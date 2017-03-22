package textExcel;

public abstract class RealCell implements Cell {
	private String value;
	public RealCell(String command){
		this.value = command;
	}
	public void setValue(String input){
		value = input;
	}
	@Override
	public String abbreviatedCellText() {
		String realText = value;
		if(value.charAt(0) == '\"'){
			realText = value.substring(1, value.length() - 1);
		}
		if(realText.length() > 10){
			realText = value.substring(1, 11);
			return realText;
		}else{
			//fills in the spaces
			while(realText.length()<10){
				realText += " ";

			}
			return realText;
		}
	}
	@Override
	public String fullCellText() {
		return value;
	}
	public double getDoubleValue(){
		return Double.parseDouble(value);
	}

}