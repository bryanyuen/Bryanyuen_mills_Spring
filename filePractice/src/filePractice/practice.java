package filePractice;
import java.io.*;
import java.util.Scanner;
public class practice {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner input = new Scanner(new File("./name/111.txt"));
		double initialNum = input.nextDouble();
		for(int i = 0; i<7; i++){
			double nextNum = input.nextDouble();
			System.out.println(initialNum + " to" + nextNum + ", change = " + (double)Math.round((nextNum - initialNum)*10)/10);	
			initialNum = nextNum;
		}
	}
}