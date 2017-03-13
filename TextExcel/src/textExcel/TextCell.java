package textExcel;

public class TextCell implements Cell {
	private String text;
	public TextCell(String text){
		this.text = text;
	}
	@Override
	public String abbreviatedCellText(){
		if(text.length() < 10){
			int numOfSpace = 10 - text.length();
			for(int i = 0; i < numOfSpace; i++){
				this.text += " ";
			}
		}else if(text.length() > 10){
			this.text = text.substring(0, 11);
		}else{ 
			this.text = text;
		}
		return this.text;
		}
	public String fullCellText() {
		return text;
	}
}
