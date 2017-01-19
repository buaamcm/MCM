package Sudoku;

import java.util.ArrayList;

public class Sudoku {
	public static final int MAX_COLUMN = 324;
	public static final int MAX_ROW = 729;
	public static final int SIZE = 81;
	public ColumnHead head;
	public ColumnHead[] columnLists = new ColumnHead[MAX_COLUMN + 1];
	public int[][] input;
	
	public Sudoku(int[][] input){
		this.input = input;
		buildHead();
		resolveInput();
	}
	
	public void buildHead(){
		head = new ColumnHead(null, null, null, null, null, 0);
		columnLists[0] = head;
		for(int i = 1; i <= MAX_COLUMN; i++){
			columnLists[i] = new ColumnHead(null, null, null, null, null, i);
		}
		for(int i = 1; i < MAX_COLUMN; i++){
			columnLists[i].rightNode = columnLists[i + 1];
			columnLists[i].leftNode = columnLists[i - 1];
		}
		head.rightNode = columnLists[1];
		head.leftNode = columnLists[MAX_COLUMN];
		columnLists[MAX_COLUMN].rightNode = head;
		columnLists[MAX_COLUMN].leftNode = columnLists[MAX_COLUMN - 1];
		head.sum = 1000;
	}
	
	public void resolveInput(){
		SudokuNode[] p = new SudokuNode[MAX_COLUMN + 1];
		for(int i = 1; i <= MAX_COLUMN; i++){
			p[i] = columnLists[i];
		}
		for(int i = 1; i <= 9; i++){
			for(int j = 1; j <= 9; j++){
				if(input[i][j] != 0){
					int l = countLocation(i, j);
					int r = countRow(i, input[i][j]) + SIZE;
					int c = countColumn(j, input[i][j]) + 2 * SIZE;
					int la = countLattice(i, j, input[i][j]) + 3 * SIZE;
					p[l].downNode = new SudokuNode(p[l], null, null, null, columnLists[l]);
					p[r].downNode = new SudokuNode(p[r], null, null, null, columnLists[r]);
					p[c].downNode = new SudokuNode(p[c], null, null, null, columnLists[c]);
					p[la].downNode = new SudokuNode(p[la], null, null, null, columnLists[la]);
					p[l] = p[l].downNode;
					p[r] = p[r].downNode;
					p[c] = p[c].downNode;
					p[la] = p[la].downNode;
					p[l].downNode = columnLists[l];
					p[r].downNode = columnLists[r];
					p[c].downNode = columnLists[c];
					p[la].downNode = columnLists[la];
					p[l].rightNode = p[r]; p[r].leftNode = p[l];
					p[l].leftNode = p[la]; p[la].rightNode = p[l];
					p[r].rightNode = p[c]; p[c].leftNode = p[r];
					p[c].rightNode = p[la]; p[la].leftNode = p[c];
					columnLists[l].upNode = p[l];
					columnLists[r].upNode = p[r];
					columnLists[c].upNode = p[c];
					columnLists[la].upNode = p[la];
				}
				else {
					ArrayList<Integer> arrayList = possibleNumber(i, j);
					//System.out.println("!");
					for (Integer integer : arrayList) {
						//System.out.println("!!");
						int v = integer.intValue();
						//System.out.println(v);
						int l = countLocation(i, j);
						int r = countRow(i, v) + SIZE;
						int c = countColumn(j, v) + 2 * SIZE;
						int la = countLattice(i, j, v) + 3 * SIZE;
						p[l].downNode = new SudokuNode(p[l], null, null, null, columnLists[l]);
						p[r].downNode = new SudokuNode(p[r], null, null, null, columnLists[r]);
						p[c].downNode = new SudokuNode(p[c], null, null, null, columnLists[c]);
						p[la].downNode = new SudokuNode(p[la], null, null, null, columnLists[la]);
						p[l] = p[l].downNode;
						p[r] = p[r].downNode;
						p[c] = p[c].downNode;
						p[la] = p[la].downNode;
						p[l].downNode = columnLists[l];
						p[r].downNode = columnLists[r];
						p[c].downNode = columnLists[c];
						p[la].downNode = columnLists[la];
						p[l].rightNode = p[r]; p[r].leftNode = p[l];
						p[l].leftNode = p[la]; p[la].rightNode = p[l];
						p[r].rightNode = p[c]; p[c].leftNode = p[r];
						p[c].rightNode = p[la]; p[la].leftNode = p[c];
						columnLists[l].upNode = p[l];
						columnLists[r].upNode = p[r];
						columnLists[c].upNode = p[c];
						columnLists[la].upNode = p[la];
					}
				}
			}
		}
		for(int i = 1; i <= MAX_COLUMN; i++){
			//System.out.println(i);
			int s = 0;
			SudokuNode node = columnLists[i].downNode;
			while(node != columnLists[i]){
				s = s + 1;
				//System.out.println(node.columnHead.name);
				node = node.downNode;
			}
			columnLists[i].sum = s;
		}
	}
	
	/*public void add(SudokuNode p, ColumnHead head){
		p.downNode = new SudokuNode(p, null, null, null, head);
		p = p.downNode;
		p.downNode = head;
	}*/
	
	public int countLocation(int row, int column){
		return 9 * (row - 1) + column;
	}
	
	public int countRow(int row, int value){
		return 9 * (row - 1) + value;
	}
	
	public int countColumn(int column, int value){
		return 9 * (column - 1) + value;
	}
	
	public int countGrid(int row, int column){
		int b;
		if(row <= 3 && column <= 3){
			b = 1;
		}else if (row <= 3 && column > 3 && column <= 6) {
			b = 2;
		}else if (row <= 3 && column > 6 && column <= 9) {
			b = 3;
		}else if (row > 3 && row <= 6 && column <= 3) {
			b = 4;
		}else if (row > 3 && row <= 6 && column > 3 && column <= 6) {
			b = 5;
		}else if (row > 3 && row <= 6 && column > 6 && column <= 9) {
			b = 6;
		}else if (row > 6 && column <= 3) {
			b = 7;
		}else if (row > 6 && column > 3 && column <= 6) {
			b = 8;
		}else {
			b = 9;
		}
		return b;
	}
	
	public int countLattice(int row, int column, int value){
		return 9 * (countGrid(row, column) - 1) + value;
	}
	
	public ArrayList<Integer> possibleNumber(int row, int column){
		ArrayList<Integer> number = new ArrayList<Integer>();
		boolean[] possible = new boolean[10];
		for(int i = 1; i <= 9; i++){
			possible[i] = true;
		}
		for(int i = 1; i <= 9; i++){
			possible[input[row][i]] = false;
		}
		for(int i = 1; i <= 9; i++){
			possible[input[i][column]] = false;
		}
		int b = countGrid(row, column);
		int c = b <= 3 ? 1 : b <= 6 ? 2 : 3;
		int d = 0;
		switch (b) {
		case 1 : case 4 : case 7 :
			d = 1;
			break;
		case 2 : case 5 : case 8 :
			d = 2;
			break;
		case 3 : case 6 : case 9:
			d = 3;
		}
		for(int i = 3 * (c - 1) + 1; i <= 3 * c; i++){
			for(int j = 3 * (d - 1) + 1; j <= 3 * d; j++){
				possible[input[i][j]] = false;
			}
		}
		for(int i = 1; i <= 9; i++){
			if(possible[i]){
				number.add(new Integer(i));
			}
		}
		return number;
	}
}
