package mcm;

public class Sudoku {
	public SudokuPoint[][] matrix;
	
	public Sudoku(){
		matrix = new SudokuPoint[10][10];
	}
	
	public boolean testRow(){
		boolean b = true;
		boolean[] test = new boolean[10];
		boolean[] vaild = new boolean[10]; 
		for(int i = 1; i <= 9 ; i++){
			for(int j = 1; j <= 9; j++){
				if(test[matrix[i][j].value] == false){
					test[matrix[i][j].value] = true;
				}
				else {
					break;
				}
			}
			boolean s = true;
			for(int k = 1; k <= 9; k++){
				s = s && test[k];
			}
			if(!s){
				break;
			}
			else {
				vaild[i] = true;
				test = new boolean[10];
			}
		}
		for(int i = 1; i <= 9; i++){
			b = b && vaild[i];
		}
		return b;
	}
	
	public boolean testColumn(){
		boolean b = true;
		boolean[] test = new boolean[10];
		boolean[] vaild = new boolean[10]; 
		for(int j = 1; j <= 9 ; j++){
			for(int i = 1; i <= 9; i++){
				if(test[matrix[i][j].value] == false){
					test[matrix[i][j].value] = true;
				}
				else {
					break;
				}
			}
			boolean s = true;
			for(int k = 1; k <= 9; k++){
				s = s && test[k];
			}
			if(!s){
				break;
			}
			else {
				vaild[j] = true;
				test = new boolean[10];
			}
		}
		for(int i = 1; i <= 9; i++){
			b = b && vaild[i];
		}
		return b;
	}
	
	public boolean testGrid(){
		boolean b = true;
		boolean[] test = new boolean[10];
		boolean[] vaild = new boolean[10]; 
		for(int i = 1; i <= 3; i++){
			for(int j = 1; j <= 3; j++){
				for(int k = 1; k <= 9; k++){
					int offset = k <= 3 ? 1 : k <= 6 ? 2 : k <= 9 ? 3 : -1;
					if(test[matrix[3 * (i - 1) + offset][(3 * (j - 1) + k - (offset - 1) * 3)].value] == false){
						test[matrix[3 * (i - 1) + offset][(3 * (j - 1) + k - (offset - 1) * 3)].value] = true;
					}
					else {
						break;
					}
				}
				boolean s = true;
				for(int l = 1; l <= 9; l++){
					s = s && test[l];
				}
				if(!s){
					break;
				}
				else{
					vaild[3 * (i - 1) + j] = true;
					test = new boolean[10];
				}
			}
		}
		for(int i = 1; i <=9; i++){
			b = b && vaild[i];
		}
		return b;
	}
	
	public boolean test(){
		return testColumn() && testRow() && testGrid();
	}
}
