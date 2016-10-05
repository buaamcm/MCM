package Sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NormalReader {
	public FileReader fileReader;
	public BufferedReader bufferedReader;
	public int nonzero;
	
	public NormalReader(String name) throws FileNotFoundException{
		fileReader = new FileReader(name);
		bufferedReader = new BufferedReader(fileReader);
	}
	
	public int[][] getProblem() throws IOException{
		int[][] problem = new int[10][10];
		String string = bufferedReader.readLine();
		char[] chars = string.toCharArray();
		nonzero = 0;
		for(int i = 0; i < 81; i++){
			int y = ((i + 1) % 9) == 0 ? 9 : (i + 1) % 9;
			int x = ((i + 1) - y) / 9 + 1;
			//System.out.println(y + " " + x);
			problem[x][y] = chars[i] - '0';
			if(problem[x][y] != 0){
				nonzero = nonzero + 1;
			}
		}
		return problem;
	}
}
