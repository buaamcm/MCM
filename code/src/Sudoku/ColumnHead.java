package Sudoku;

public class ColumnHead extends SudokuNode{
	public int sum;
	public int name;
	
	public ColumnHead(SudokuNode upNode, SudokuNode downNode, SudokuNode leftNode, SudokuNode rightNode, ColumnHead columnHead, int name){
		super(upNode, downNode, leftNode, rightNode, columnHead);
		this.name = name;
	}
}
