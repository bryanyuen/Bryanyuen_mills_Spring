package textExcel;

public class FormulaCell extends RealCell implements Cell {
	
private String [] equation;
	public FormulaCell (String input) {
		super(input);
		
	}
	public String[] getFormula(){
		String formula = this.fullCellText().substring(1, this.fullCellText().length()-1);
		equation = formula.split(" ");
		return equation;
	}
	public String abbreviatedCellText(){
		String answer = Double.toString(calculate(0,0));
if (answer.length() > 10){
	return answer.substring(0, 10);
} else {
	while (answer.length() != 10){
		answer += " ";
	}
}
return answer;
}
	public String fullCellText() {
		return super.fullCellText();
	}
	
	public double calculate (String [] equation) {
		int position = 0;
		double answer = 0.0;
		if (position == equation.length){
			return answer;
		}else {
			if (equation [position + 1].equals("+")){
				answer = getDoubleValue(equation[position]) + getDoubleValue(equation[position + 2]);//add
			}else if (equation [position + 1].equals("-")){
				answer = getDoubleValue(equation[position]) - getDoubleValue(equation[position + 2]);//subtract
			}else if (equation [position + 1].equals("*")){
				answer = getDoubleValue(equation[position]) * getDoubleValue(equation[position + 2]);//multiply
			}else if (equation [position + 1].equals("/")){
				answer = getDoubleValue(equation[position]) / getDoubleValue(equation[position + 2]);//divide
			}
			calculate (position+3, answer); //recursion 
			return answer;
		}
	}
	
	public double getDoubleValue (String number) {
		return Double.parseDouble(number);
	}
	
	public String getType(){
		return "FormulaCell";
	}
}