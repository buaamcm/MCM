package Sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProblemReader {
	public FileReader fileReader;
	public BufferedReader bufferedReader;
	
	public ProblemReader(String name) throws FileNotFoundException{
		fileReader = new FileReader(name);
		bufferedReader = new BufferedReader(fileReader);
	}
	
	public int[][] readAndGet() throws IOException{
		int[][] problem = new int[10][10];
		for(int i = 0; i < 9; i++){
			String string = bufferedReader.readLine();
			char[] chars = string.toCharArray();
			/*for (char c : chars) {
				System.out.print(c + " ");
			}
			System.out.println();*/
			char last = ' ';
			char current = ' ';
			int k = 0;
			for(int j = 0; j < chars.length; j++){
				current = chars[j];
				if(Character.isDigit(current) && k < 9){
					problem[i + 1][k + 1] = current - '0';
					k = k + 1;
				}
				else if(Character.isDigit(last) && current == ','){}
				else {
					k = k + 1;
				}
				last = current;
			}
		}
		bufferedReader.readLine();
		return problem;
	}
}
