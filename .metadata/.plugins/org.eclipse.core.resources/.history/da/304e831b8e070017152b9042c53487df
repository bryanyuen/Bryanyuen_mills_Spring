package textExcel;

public class TextCell implements Cell {
	private String text;
	public TextCell(String text){
		this.text = text;
	}
	@Override
	public String abbreviatedCellText(){
		return this.text;
	}

	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		return this.text;
	}
	public String padToTen(){
		int numOfSpace = 10 - text.length();
		for(int i = 0; i < numOfSpace; i++){
			this.text += " ";
		}
		return this.text;
		}
	public String truncate(){
		this.text = text.substring(0, 11);
		return this.text;
	}
}
