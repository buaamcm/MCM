package Sudoku;

import java.util.Random;

class GenerateFullSudoku {
    public int GridNumber = 3;
    public int Number = 9;
    public int CycleTimes = 20;
    public int MaxChangesTimes = 220;
    public int Sudoku[][] = new int[Number][Number];
    public CandidateNumber Candinum = new CandidateNumber(Number,CycleTimes);
    
    public void Initialize(){
        for(int i=0;i<Number;i++){
            for(int j=0;j<Number;j++){
                Sudoku[i][j] = 0;
            }
        }
    }
    
    public int[][] Generate(){
        int ChangeTimes = 0;
        
        //Initialize
        Initialize();
        
        //Generate
        for(int i=0;i<Number;i++){
            if(i==0){
                Candinum.ChangeOrder();
                Sudoku[i] = Candinum.Candidate.clone();
            } else {
                Candinum.ChangeOrder();
                ChangeTimes++;
                if(ChangeTimes > MaxChangesTimes){
                    i = -1; //Restart the generate process
                    ChangeTimes = 0;
                    Initialize();
                } else {
                	if(CanFillInArray(Candinum.Candidate, Candinum.Used, i) == false){
	                    for(int k=0;k<Number;k++){
	                        Sudoku[i][k] = 0;
	                    }
	                    i = i - 1; // Calculate again
                	}
                }
            }
        }
        
        //Print
        for(int i=0;i<Number;i++){
            for(int j=0;j<Number;j++){
                System.out.print(Sudoku[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
        int[][] result = new int[10][10];
        for(int i = 1; i <= Number; i++){
        	for(int j = 1; j <= Number; j++){
        		result[i][j] = Sudoku[i - 1][j - 1];
        	}
        }
        return result;
    }
    
    public boolean CanFillIn(int num, int line, int column){
        for(int i=0;i<line;i++){
            if(Sudoku[i][column] == num){
                return false;
            }
        }
        for(int i=0;i<column;i++){
            if(Sudoku[line][i] == num){
                return false;
            }
        }
        int linetmp = line - line % GridNumber;
        int columntmp = column - column % GridNumber;
        
        for(int i=0;i<=line%GridNumber;i++){
            for(int j=0;j<GridNumber;j++){
                if(Sudoku[linetmp+i][columntmp+j] == num){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean CanFillInArray(int array[],boolean Used[],int line){
        boolean Flag = false;
        for(int i=0;i<Number;i++){
            Flag = false;
            for(int j=0;j<array.length;j++){
                if(Used[j] == false && CanFillIn(array[j], line, i)){
                    Sudoku[line][i] = array[j];
                    Used[j] = true;
                    Flag = true;
                    break;
                }
            }
            if(Flag == false){
                return false;
            }
        }
        return true;
    }
}

class CandidateNumber{
    public int Candidate[] = null;
    public int CycleTimes = 15;
    public boolean Used[] = null;
    public CandidateNumber(int Number,int CycleTimes){
        Candidate = new int[Number];
        Used = new boolean[Number];
        for(int i=0;i<Number;i++){
            Candidate[i] = i+1;
            Used[i] = false;
        }
        this.CycleTimes = CycleTimes;
    }
    
    public void ChangeOrder(){
        int tmp = 0;
        int numtmp = 0;
        Random random = new Random();
        for(int i=0;i<CycleTimes;i++){
            tmp = random.nextInt(9);
            numtmp = Candidate[tmp];
            Candidate[tmp] = Candidate[0];
            Candidate[0] = numtmp;
        }
        for(int i=0;i<Used.length;i++){
            Used[i] = false;
        }
    }
}
