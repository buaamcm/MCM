package Sudoku;

import java.util.ArrayList;
import java.util.LinkedList;

public class TranslateSolution {
	public ArrayList<LinkedList<SudokuNode>> answers;
	
	public TranslateSolution(ArrayList<LinkedList<SudokuNode>> answers){
		this.answers = answers;
	}
	
	public void translate(){
		for (LinkedList<SudokuNode> linkedList : answers) {
			int[][] result = new int[10][10];
			for (SudokuNode sudokuNode : linkedList) {
				int[] a = new int[4];
				int i = 0;
				SudokuNode sNode = sudokuNode;
				do {
					a[i] = sNode.columnHead.name;
					//System.out.println(a[i]);
					sNode = sNode.rightNode;
					i = i + 1;
				} while (sNode != sudokuNode);
				int x = 0;
				int y = 0;
				int value = 0;
				for(int k = 0; k < 4; k++){
					//System.out.println(a[k]);
					if(a[k] <= 81){
						//x = a[k] / 9 + 1;
						y = a[k] % 9;
						y = y == 0 ? 9 : y;
						x = (a[k] - y) / 9 + 1;
						System.out.println(x + " " + y);
					}
					if(a[k] > 81 && a[k] <= 162){
						value = (a[k] - Sudoku.SIZE) % 9;
						value = value == 0 ? 9 : value;
					}
				}
				result[x][y] = value;
			}
			for(int i = 1; i <= 9; i++){
				for(int j = 1; j <= 9; j++){
					System.out.print(result[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
