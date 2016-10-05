package Sudoku;

import java.util.Random;

class GenerateByLasVegas {
	public int GridNumber = 3;
	public int Number = 9;
	public int MaxCompareNum = 500000;
	public int Sudoku[][] = new int[Number][Number];
	public CandidateNumberOriginal Candinum = new CandidateNumberOriginal(Number);
	
	// public static void main(String args[]){
		// int times = 0;
		// while(times < 100){
			// if(Generate() == true){
				// times++;
			// }
		// }
		
	// }
	
	public int[][] Generate(){
		//Initialize
		for(int i = 0; i < Number; i++){
			for(int j = 0; j < Number; j++){
				Sudoku[i][j] = 0;
			}
		}
		//Generate
		int WrongTimes = 0;
		for(int i = 0; i < Number; i++){
			if(i == 0){
				Candinum.ChangeOrder();
				Sudoku[i] = Candinum.Candidate.clone();
			} else {
				do{
					Candinum.ChangeOrder();
					WrongTimes++;
					if(WrongTimes > MaxCompareNum){
						return null;
					}
				}while(CanFillInArray(Candinum.Candidate, Number, i) == false);
				Sudoku[i] = Candinum.Candidate.clone();
			}
		}
		
		//Print
		/*for(int i = 0; i < Number; i++){
			for(int j = 0; j < Number; j++){
				System.out.print(Sudoku[i][j] + " ");
			}
			System.out.println();
		}*/
		//System.out.println("-----------------");
		int result[][] = new int[Number+1][Number+1];
		for(int i = 1; i <= Number; i++){
			for(int j = 1; j <= Number; j++){
				result[i][j] = Sudoku[i - 1][j - 1];
			}
		}
		for(int i = 1; i <= Number; i++){
			for(int j= 1; j <= Number; j++){
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------------");
		return result;
	}
	
	public boolean CanFillIn(int num, int line, int column){
		//�������ͨ�����ٱȽϴ������Ż�
//		for(int i=0;i<Number;i++){
//			if(Sudoku[i][column]==num || Sudoku[line][i]==num){
//				return false;
//			}
//		}
		for(int i = 0; i < line; i++){
			if(Sudoku[i][column] == num){
				return false;
			}
		}
		for(int i = 0; i < column; i++){
			if(Sudoku[line][i] == num){
				return false;
			}
		}
		int linetmp = line - line % GridNumber;
		int columntmp = column - column % GridNumber;
		
		for(int i = 0; i <= line % GridNumber; i++){
			for(int j = 0; j < GridNumber; j++){
				if(Sudoku[linetmp + i][columntmp + j] == num){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean CanFillInArray(int a[],int top,int line){
		for(int i = 0; i < top; i++){
			if(CanFillIn(a[i], line, i) == false)
				return false;
		}
		return true;
	}

}

class CandidateNumberOriginal{
	int Candidate[] = null;
	int num = 0;
	public CandidateNumberOriginal(int Number){
		Candidate = new int[Number];
		for(int i = 0; i < Number; i++){
			Candidate[i] = i+1;
		}
		num = Number;
	}
	
	public boolean ChangeOrder(){
		int numtmp = 0;
		boolean Used[] = new boolean[num]; 
		int canditmp[] = Candidate.clone();
		Random random = new Random();
		for(int i = 0; i < num; i++){
			Used[i] = false;
		}
		for(int i = 0; i < num; i++){
			do{
				numtmp = random.nextInt(num);
			}while(Used[numtmp] == true);
			Candidate[i] = canditmp[numtmp];
			Used[numtmp] = true;
		}
		return true;
	}
}
