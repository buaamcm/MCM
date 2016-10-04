package Sudoku;

public class SudokuNode {
	public SudokuNode upNode;
	public SudokuNode downNode;
	public SudokuNode leftNode;
	public SudokuNode rightNode;
	public ColumnHead columnHead;
	
	public SudokuNode(SudokuNode upNode, SudokuNode downNode, SudokuNode leftNode, SudokuNode rightNode, ColumnHead columnHead){
		this.upNode = upNode;
		this.downNode = downNode;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.columnHead = columnHead;
	}
}
