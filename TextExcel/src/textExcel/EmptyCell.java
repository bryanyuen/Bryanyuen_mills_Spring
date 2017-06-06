package textExcel;

public class EmptyCell implements Cell {
	public EmptyCell(){
	}
	@Override
	public String abbreviatedCellText() {	//method that returns the content in empty cell 10 indexes
		// TODO Auto-generated method stub
		return "          ";
	}

	@Override
	public String fullCellText() {		//method that returns the empty cell
		// TODO Auto-generated method stub
		return "";
	}

}
