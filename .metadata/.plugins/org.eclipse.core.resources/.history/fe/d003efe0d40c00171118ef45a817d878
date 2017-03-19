package textExcel;

public class TextCell implements Cell {
	private String text;
	public TextCell(String text){
		this.text = text;
	}
	public void setText(String input){
		text = input;
	}
	@Override
	public String abbreviatedCellText(){
		String realText = text;
		if(text.charAt(0) == '\"'){
			realText = text.substring(1, text.length() - 1);
		}
		if(text.length() > 10){
			realText = text.substring(1, 11);
			return realText;
		}else{
			//fills in the spaces
			while(text.length()<10){
				realText += " ";

			}
			return realText;
		}
	}
	public String fullCellText() {
		return text;
	}
}
