package Sudoku;

import java.util.ArrayList;
import java.util.Vector;

class SudokuStrategies {
	
	public static int  StrategiesNumber = 3;
	public static int  SudokuNumber = 9;
	public static int  SudokuGridNum = 3;

	public int[] Solve(int Sudoku[][],Vector<Integer> Candidate[][]){
		int[] UseTimes = new int[StrategiesNumber];
		boolean[] Flags = new boolean[StrategiesNumber];
		
		for(int i=0;i<StrategiesNumber;i++){
			UseTimes[i] = 0;
			Flags[i] = false;
		}
		
		Strategy_ExplicitUnique explicitUnique = new Strategy_ExplicitUnique(1,"explicitUnique");
		Strategy_ImplicitUnique implicitUnique = new Strategy_ImplicitUnique(2,"implicitUnique");
		Strategy_ExplicitDouble explicitDouble = new Strategy_ExplicitDouble(3,"explicitUnique");
		
		while(true){
			for(int i=0;i<StrategiesNumber;i++){
				Flags[i] = false;
			}
			if(explicitUnique.UseStrategy(Sudoku, Candidate) == true){
				UseTimes[explicitUnique.Code-1]++; 
				Flags[explicitUnique.Code-1] = true;
			} else if(implicitUnique.UseStrategy(Sudoku, Candidate) == true){
				UseTimes[implicitUnique.Code-1]++; 
				Flags[implicitUnique.Code-1] = true;
			} else if(explicitDouble.UseStrategy(Sudoku, Candidate) == true){
				UseTimes[explicitDouble.Code-1]++; 
				Flags[explicitDouble.Code-1] = true;
			}
			
			boolean tmp = false;
			for(int i=0;i<StrategiesNumber;i++){
				tmp = tmp || Flags[i];
			}
			if(tmp==false){
				break;
			}
		}
		
		return UseTimes;
	}
	
	public void CreateCandidate(int Sudoku[][],Vector<Integer> Candidate[][]){
		for(int i=0;i<SudokuNumber;i++){
			for(int j=0;j<SudokuNumber;j++){
				if(Sudoku[i][j] == 0){
					for(int k=1;k<=SudokuNumber;k++){
						if(CanFillIn(k, Sudoku,i,j)==true){
							Candidate[i][j].add(k);
						}
					}
				}
			}
		}
	}
	
	public boolean CanFillIn(int num,int Sudoku[][],int line,int column){
		for(int i=0;i<SudokuNumber;i++){
			if(Sudoku[line][i]==num || Sudoku[i][column]==num){
				return false;
			}
		}
		
		int linetmp = line - line % SudokuGridNum;
        int columntmp = column - column % SudokuGridNum;
        
        for(int i=0;i<SudokuGridNum;i++){
            for(int j=0;j<SudokuGridNum;j++){
                if(Sudoku[linetmp+i][columntmp+j] == num){
                    return false;
                }
            }
        }
		return true;
	}
}

class Point{
	public int line = 0;
	public int column = 0;
	
	public Point(int a,int b){
		line = a;
		column = b;
	}
}

abstract class Strategy{
	public int Code = 0;
	public String Name = "";
	public static int  SudokuNumber = 9;
	public static int  SudokuGridNum = 3; 
	
	public Strategy(){
		
	}
	
	public Strategy(int Code, String name) {
		this.Code = Code;
		this.Name = name;
	}
	
	public abstract boolean UseStrategy(int Sudoku[][],Vector<Integer> Candidate[][]);
	
	public ArrayList<Point> GetPeers(int line,int column){
		ArrayList<Point> Peers = new ArrayList<Point>();
		
		for(int i=0;i<SudokuNumber;i++){
			if(i!=line){
				Peers.add(new Point(i, column));
			}
		}
		
		for(int i=0;i<SudokuNumber;i++){
			if(i!=column){
				Peers.add(new Point(line, i));
			}
		}
		
		int linetmp = line - line % SudokuGridNum;
        int columntmp = column - column % SudokuGridNum;
        
        for(int i=0;i<SudokuGridNum;i++){
            for(int j=0;j<SudokuGridNum;j++){
            	if(!(i==line && j==column)){
            		Peers.add(new Point(linetmp+i, columntmp+j));
            	}
            }
        }
		
		return Peers;
	}
	
	public ArrayList<Point> GetGridPeers(int line,int column){
		ArrayList<Point> Peers = new ArrayList<Point>();
		int linetmp = line - line % SudokuGridNum;
        int columntmp = column - column % SudokuGridNum;
        
        for(int i=0;i<SudokuGridNum;i++){
            for(int j=0;j<SudokuGridNum;j++){
            	if(!(i==line && j==column)){
            		Peers.add(new Point(linetmp+i, columntmp+j));
            	}
            }
        }
		
		return Peers;
	}
	
	public void RemovePeersCandidate(int num,int line,int column,int Sudoku[][],Vector<Integer> Candidate[][]){
		ArrayList<Point> Peers = GetPeers(line, column);
		for(Point tmp:Peers){
			if(Sudoku[tmp.line][tmp.column]==0 && Candidate[tmp.line][tmp.column]!=null){
				if(Candidate[tmp.line][tmp.column].contains(num)){
					Candidate[tmp.line][tmp.column].removeElement(num);
				}
			}
		}
	}
	
	public void RemoveGridPeersCandidate(int num,int line,int column,int Sudoku[][],Vector<Integer> Candidate[][]){
		ArrayList<Point> Peers = GetGridPeers(line, column);
		for(Point tmp:Peers){
			if(Sudoku[tmp.line][tmp.column]==0 && Candidate[tmp.line][tmp.column]!=null){
				if(Candidate[tmp.line][tmp.column].contains(num)){
					Candidate[tmp.line][tmp.column].removeElement(num);
				}
			}
		}
	}
	
	public boolean IsPeer(Point a,Point b){
		if(a.line == b.line || a.column == b.column){
			return true;
		} else {
			int linetmp = a.line - a.line % SudokuGridNum;
	        int columntmp = a.column - a.column % SudokuGridNum;
	        if(linetmp<=b.line && b.line<=linetmp+SudokuGridNum && columntmp<=b.column && b.column<=columntmp+SudokuGridNum){
	        	return true;
	        } else {
	        	return false;
	        }
			
		}
	}
	
	public boolean IsGridPeer(Point a,Point b){
		int linetmp = a.line - a.line % SudokuGridNum;
        int columntmp = a.column - a.column % SudokuGridNum;
        if(linetmp<=b.line && b.line<=linetmp+SudokuGridNum && columntmp<=b.column && b.column<=columntmp+SudokuGridNum){
        	return true;
        } else {
        	return false;
        }	
	}
	
}


class Strategy_ExplicitUnique extends Strategy{
	
	public Strategy_ExplicitUnique(int i, String string) {
	}
	
	public boolean UseStrategy(int Sudoku[][],Vector<Integer> Candidate[][]){
		for(int i=0;i<SudokuNumber;i++){
			for(int j=0;j<SudokuNumber;j++){
				if(Sudoku[i][j]==0 && Candidate[i][j]!=null && Candidate[i][j].size()==1){
					Sudoku[i][j] = Candidate[i][j].firstElement();
					Candidate[i][j].removeAllElements();
					RemovePeersCandidate(Sudoku[i][j], i, j, Sudoku, Candidate);
					return true;
				}
			}
		}
		return false;
	}
}

class Strategy_ImplicitUnique extends Strategy{
	
	public Strategy_ImplicitUnique(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public boolean UseStrategy(int[][] Sudoku, Vector<Integer>[][] Candidate) {
		Vector<Integer> PeersCandidate = new Vector<Integer>();
		ArrayList<Point> Peers = new ArrayList<Point>();
		
		for(int i=0;i<SudokuNumber;i++){
			for(int j=0;j<SudokuNumber;j++){
				if(Sudoku[i][j]==0 && Candidate[i][j]!=null){
					Peers = GetPeers(i, j);
					PeersCandidate.removeAllElements();
					for(Point tmp:Peers){
						if(Sudoku[tmp.line][tmp.column]==0 && Candidate[tmp.line][tmp.column]!=null){
							for(int num:Candidate[tmp.line][tmp.column]){
								if(PeersCandidate.contains(num) == false){
									PeersCandidate.add(num);
								}
							}
						}
					}
					
					for(int candi:Candidate[i][j]){
						if(PeersCandidate.contains(candi)==false){
							Sudoku[i][j] = candi;
							Candidate[i][j].removeAllElements();
							return true;
						}
					}
					
				}
				
			}
		}		
		return false;
	}
}

class Strategy_ExplicitDouble extends Strategy{
	public Strategy_ExplicitDouble(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public boolean UseStrategy(int[][] Sudoku, Vector<Integer>[][] Candidate) {
		for(int i=0;i<SudokuNumber;i++){
			for(int j=0;j<SudokuNumber;j++){
				if(Sudoku[i][j]==0 && Candidate[i][j]!=null && Candidate[i][j].size()==2){
					ArrayList<Point> Peers = GetGridPeers(i, j);
					for(Point p:Peers){
						if(Sudoku[p.line][p.column]==0 && Candidate[p.line][p.column]!=null && Candidate[p.line][p.column].size()==2 
								&& Candidate[p.line][p.column].equals(Candidate[i][j])){
							for(Integer num:Candidate[i][j]){
								RemoveGridPeersCandidate(num, i, j, Sudoku, Candidate);
								RemoveGridPeersCandidate(num, p.line, p.column, Sudoku, Candidate);
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
}
