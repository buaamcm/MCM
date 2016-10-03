package mcm;

public class SudokuPoint {
	public SudokuPoint topPoint;
	public SudokuPoint bottomPoint;
	public SudokuPoint leftPoint;
	public SudokuPoint rightPoint;
	public int value;
	
	public SudokuPoint(){
		topPoint = null;
		bottomPoint = null;
		leftPoint = null;
		rightPoint = null;
		value = 0;
	}
	
	public SudokuPoint(SudokuPoint topPoint, SudokuPoint bottomPoint, SudokuPoint leftPoint, SudokuPoint rightPoint, int value){
		this.topPoint = topPoint;
		this.bottomPoint = bottomPoint;
		this.leftPoint = leftPoint;
		this.rightPoint = rightPoint;
		this.value = value;
	}
}
