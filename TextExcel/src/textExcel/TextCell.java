package textExcel;

public class TextCell implements Cell {
	private String text;
	public TextCell(String str){	//constructor
		this.text = str;
	}
	public void setText(String input){		//setter
		text = input;
	}
	@Override
	public String abbreviatedCellText(){	//method that returns the 10 indexes text 
		String realText = text;
		if(text.charAt(0) == '\"'){
			realText = text.substring(1, text.length() - 1);
		}
		if(realText.length() > 10){		//if it contains more than 10 characters
			realText = text.substring(1, 11);	//using substring to take the first 10
			return realText;
		}else{
			//fills in the spaces
			while(realText.length()<10){	//if it contains less than 10 characters	
				realText += " ";		//pad to ten

			}
			return realText;
		}
	}
	public String fullCellText() {		//getter
		return text;
	}
}
