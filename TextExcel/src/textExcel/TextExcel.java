package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args)
	{
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
