package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private int row = 20;			
	private int col = 12;
	private String location;
    @Override
    public int getRow()			//method that get row 
    {
        row = Integer.parseInt(location.substring(1));
        return row - 1;		//array is 0 based, so we need to minus 1
    }

    @Override
    public int getCol()		//method that get column 
    {
    	this.col = location.charAt(0);
        return col-65;		//65  equals to 'A'
    }
    
    public SpreadsheetLocation(String cellName)
    {
        location = cellName.toUpperCase();
    }

}
