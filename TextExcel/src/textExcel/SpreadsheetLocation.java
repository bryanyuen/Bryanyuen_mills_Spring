package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private int row = 20;
	private int col = 12;
	private String location;
    @Override
    public int getRow()
    {
        row = Integer.parseInt(location.substring(1));
        return row - 1;
    }

    @Override
    public int getCol()
    {
    	this.col = location.charAt(0);
        return col-65;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        location = cellName;
    }

}
