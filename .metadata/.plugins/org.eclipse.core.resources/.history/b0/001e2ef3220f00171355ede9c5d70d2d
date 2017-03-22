package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
		String stringSpecial1 = "A1 = ( avg A2-A3 )";
        String stringSpecial2 = "A1 = ( 1 * 2 / 1 + 3 - 5 )";
        Grid test = new Spreadsheet();
        String grid1 = test.processCommand("B7 = \"" + stringSpecial1 + "\"");
        System.out.println(grid1);
		Spreadsheet sheet = new Spreadsheet();
		Scanner userInput = new Scanner (System.in);
		String command = userInput.nextLine();
		while (command.equals("quit") == false){
			System.out.println(sheet.processCommand(command));
			command = userInput.nextLine();
		}
	    // Add your command loop here
	}
}
