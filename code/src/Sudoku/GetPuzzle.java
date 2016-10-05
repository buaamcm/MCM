package Sudoku;

import java.util.Random;
import java.util.Stack;

public class GetPuzzle {
	public static final int NUMBER = 40;
	public static final int LIMIT = 100;
	public static final int TIMES = 1000;
	public int[][] input;
	public Sudoku sudoku;
	public Random random;
	public int count;
	public Stack<Array> stack;
	public Resolve resolve;
	public int times;
	
	public GetPuzzle(int[][] input){
		//sudoku = new Sudoku(input);
		this.input = input;
		random = new Random();
		count = 0;
		stack = new Stack<Array>();
		/*for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= 9; j++){
				System.out.print(input[i][j] + " ");
			}
			System.out.println();
		}*/
	}
	
	public void get(){
		boolean[][] sign = new boolean[10][10];
		for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= 9; j++){
				sign[i][j] = true;
			}
		}
		//stack.push(new Array(0, 0, 0));
		while(true){
			if(tryToRemoveACell(sign)){
				break;
			}
		}
		while(count < NUMBER){
			/*int x = 0;
			int y = 0;
			do{
				x = random.nextInt(9) + 1;
				y = random.nextInt(9) + 1;
			}while(!sign[x][y]);
			stack.push(new Array(x, y, input[x][y]));
			input[x][y] = 0;
			sudoku = new Sudoku(input);
			resolve = new Resolve(sudoku);
			resolve.resolve(0);
			count = count + 1;
			//ArrayList<LinkedList<SudokuNode>> list = resolve.allAnswers;
			if(resolve.allAnswers.size() > 1){
				Array array = stack.pop();
				input[array.x][array.y] = array.value;
				count = count - 1;
			}*/
			int i = 0 ;
			for(; i < LIMIT; i++){
				//System.out.println("!!!!!!!!!!!");
				if(tryToRemoveACell(sign)){
					break;
				}
			}
			if(i == LIMIT){
				Array array = stack.pop();
				//sign[array.x][array.y] = true;
				input[array.x][array.y] = array.value;
				count = count - 1;
			}
		}
		
		return;
	}
	
	public boolean get(int number){
		boolean[][] sign = new boolean[10][10];
		GenerateFullSudoku generateFullSudoku = new GenerateFullSudoku();
		input = generateFullSudoku.Generate();
		for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= 9; j++){
				sign[i][j] = true;
			}
		}
		//stack.push(new Array(0, 0, 0));
		times = 0;
		while(true){
			if(tryToRemoveACell(sign)){
				times = times + 1;
				break;
			}
		}
		while(times < TIMES){
			if(count == number){
				return true;
			}
			int i = 0 ;
			for(; i < LIMIT; i++){
				//System.out.println("!!!!!!!!!!!");
				if(tryToRemoveACell(sign)){
					break;
				}
			}
			if(i == LIMIT){
				Array array = stack.pop();
				sign[array.x][array.y] = true;
				input[array.x][array.y] = array.value;
				count = count - 1;
			}
			times = times + 1;
			System.out.println(times);
		}
		
		return false;
	}
	
	public boolean tryToGet(){
		boolean[][] sign = new boolean[10][10];
		for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= 9; j++){
				sign[i][j] = true;
			}
		}
		//stack.push(new Array(0, 0, 0));
		times = 0;
		while(true){
			if(tryToRemoveACell(sign)){
				times = times + 1;
				break;
			}
		}
		while(times < TIMES){
			int i = 0 ;
			for(; i < LIMIT; i++){
				//System.out.println("!!!!!!!!!!!");
				if(tryToRemoveACell(sign)){
					break;
				}
			}
			if(i == LIMIT){
				Array array = stack.pop();
				sign[array.x][array.y] = true;
				input[array.x][array.y] = array.value;
				count = count - 1;
			}
			times = times + 1;
			if(count == NUMBER){
				return true;
			}
		}
		
		return false;
	}
	
	public void getHard(){
		while(!tryToGet()){}
	}
	
	public void getHard(int number){
		while(!get(number)){}
	}
	
	public boolean tryToRemoveACell(boolean[][] sign){
		int x = 0;
		int y = 0;
		do{
			x = random.nextInt(9) + 1;
			y = random.nextInt(9) + 1;
		}while(!sign[x][y]);
		sign[x][y] = false;
		/*System.out.print(x + " " + y + " ");
		System.out.println(input[x][y]);*/
		stack.push(new Array(x, y, input[x][y]));
		input[x][y] = 0;
		sudoku = new Sudoku(input);
		resolve = new Resolve(sudoku);
		resolve.resolve(0);
		count = count + 1;
		//ArrayList<LinkedList<SudokuNode>> list = resolve.allAnswers;
		if(resolve.allAnswers.size() > 1){
			Array array = stack.pop();
			input[array.x][array.y] = array.value;
			sign[array.x][array.y] = true;
			count = count - 1;
			return false;
		}
		return true;
	}
}
