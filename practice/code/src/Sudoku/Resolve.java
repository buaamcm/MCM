package Sudoku;

import java.util.ArrayList;
import java.util.LinkedList;

public class Resolve {
	public Sudoku sudoku;
	public LinkedList<SudokuNode> aList;
	public ArrayList<LinkedList<SudokuNode>> allAnswers;
	public ColumnHead head;
	public ColumnHead[] columnHeads;
	
	public long runTimes;
	public long loopTimes;
	//public long branchLoopTimes;
	
	public Resolve(Sudoku sudoku){
		this.sudoku = sudoku;
		aList = new LinkedList<SudokuNode>();
		allAnswers = new ArrayList<LinkedList<SudokuNode>>();
		this.head = sudoku.head;
		this.columnHeads = sudoku.columnLists;
		runTimes = 0;
		loopTimes = 0;
	}
	
	public void resolve(int k){
		if(head.rightNode == head){
			allAnswers.add(new LinkedList<SudokuNode>(aList));
			return;
		}
		runTimes = runTimes + 1;
		int s = 1000;
		int n = 0;
		SudokuNode h = head.rightNode;
		for(;h != head; h = h.rightNode){
			if(((ColumnHead)h).sum < s){
				s = ((ColumnHead)h).sum;
				n = ((ColumnHead)h).name;
			}
		}
		//System.out.println(n);
		h = columnHeads[n];
		coverColumn(h);
		SudokuNode rNode = h.downNode;
		for(; rNode != h; rNode = rNode.downNode){
			aList.add(k, rNode);
			for(SudokuNode jNode = rNode.rightNode; jNode != rNode; jNode = jNode.rightNode){
				coverColumn(jNode.columnHead);
			}
			resolve(k + 1);
			rNode = aList.get(k);
			aList.remove(k);
			h = rNode.columnHead;
			for(SudokuNode jNode = rNode.leftNode; jNode != rNode; jNode = jNode.leftNode){
				uncoverColumn(jNode.columnHead);
			}
		}
		uncoverColumn(h);
		//System.out.println("branchLoopTime " + branchLoopTimes);
		return;
	} 
	
	public void coverColumn(SudokuNode cNode){
		cNode.rightNode.leftNode = cNode.leftNode;
		cNode.leftNode.rightNode = cNode.rightNode;
		for(SudokuNode iNode = cNode.downNode; iNode != cNode; iNode = iNode.downNode){
			SudokuNode jNode = iNode.rightNode;
			for(; jNode != iNode; jNode = jNode.rightNode){
				jNode.downNode.upNode = jNode.upNode;
				jNode.upNode.downNode = jNode.downNode;
				jNode.columnHead.sum = jNode.columnHead.sum - 1;
				loopTimes = loopTimes + 1;
				//branchLoopTimes = branchLoopTimes + 1;
			}
		}
	}
	
	public void uncoverColumn(SudokuNode cNode){
		for(SudokuNode iNode = cNode.upNode; iNode != cNode; iNode = iNode.upNode){
			for(SudokuNode jNode = iNode.leftNode; jNode != iNode; jNode = jNode.leftNode){
				jNode.columnHead.sum = jNode.columnHead.sum + 1;
				jNode.downNode.upNode = jNode;
				jNode.upNode.downNode = jNode;
				loopTimes = loopTimes + 1;
				//branchLoopTimes = branchLoopTimes + 1;
			}
		}
		cNode.rightNode.leftNode = cNode;
		cNode.leftNode.rightNode = cNode;
	}
	
	public void showRunTimes(){
		System.out.println("searched " + runTimes + " branch(es) to get the answer");
		System.out.println("looped "+ loopTimes + " times to get the answer");
		System.out.println("*****************\n");
	}
}
