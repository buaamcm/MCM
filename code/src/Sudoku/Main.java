package Sudoku;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args){
		int[][] s = new int[10][10];
		int x = 1;
		for(int i = 1; i <= 3; i++){
			for(int j = 1; j <= 3; j++){
				for(int k = 1; k <= 9; k++){
					s[3 * (i - 1) + j][k] = x % 9 + 1;
					x = x + 1;
				}
				x = x + 3;
			}
			x = x + 1;
		}
		
		s[1][1] = 0;
		s[1][2] = 0;
		s[9][9] = 0;
		s[6][7] = 0;
		s[2][1] = 0;
		s[2][2] = 0;
		s[1][3] = 0;
		for(int i = 1; i <= 9; i++){
			s[1][i] = 0;
			s[2][i] = 0;
			//s[3][i] = 0;
		}
		
		/*for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= 9; j++){
				System.out.print(s[i][j] + " ");
			}
			System.out.println();
		}*/
		
		GenerateByLasVegas generateByLasVegas = new GenerateByLasVegas();
		while(true){
			s = generateByLasVegas.Generate();
			if(s != null){
				break;
			}
		}
		
		for(int i = 0; i <=9; i++){
			s[1][i] = 0;
		}
		
		Sudoku sudoku = new Sudoku(s);
		ColumnHead columnHead = sudoku.columnLists[1];
		SudokuNode pHead = columnHead.downNode;
		SudokuNode pNode = pHead.rightNode;
		/*do{
			System.out.println(pNode.columnHead.name);
			pNode = pNode.rightNode;
		}while(pNode != pHead.rightNode);*/
		
		/*for(int i = 1; i <= Sudoku.MAX_COLUMN; i++){
			System.out.print(sudoku.columnLists[i].sum);
		}*/
		
		System.out.println();
		
		Resolve resolve = new Resolve(sudoku);
		resolve.resolve(0);
		ArrayList<LinkedList<SudokuNode>> answers = resolve.allAnswers;
		/*for (LinkedList<SudokuNode> linkedList : answers) {
			for (SudokuNode sudokuNode : linkedList) {
				SudokuNode sNode = sudokuNode;
				do{
					System.out.print(sNode.columnHead.name + " ");
					sNode = sNode.rightNode;
				}while(sNode != sudokuNode);
				System.out.println();
			}
			System.out.println();
		}*/
		
		TranslateSolution translateSolution = new TranslateSolution(answers);
		translateSolution.translate();
	}
}
