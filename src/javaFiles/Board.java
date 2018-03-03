package javaFiles;

/*@Author Tadiwa Mahlunge 02/03/2018
 * 
 * Note I would usually put the test files in a different folder but just to speed thing up
 * I put them in the same one.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Board {
	private int width;
	private int height;
	private Cell[][] board;
	
	public Board(String filename) {
		height = 0;
		getMap(filename);
	}
	
	/**
	 * Will return the cell in row and column i and j respectively
	 * @param i The row of the cell on the board
	 * @param j The column of the cell on the board
	 * @return The cell in the given position
	 */
	public Cell getCell(int i, int j){
		if (i < width && i >= 0 && j < height && j >=0)
			return board[j][i];
		else
			return null;
	}
	
	/**
	 * Calls iterate indefinitely. One can observe the changes over time 
	 * in the system output stream.
	 */
	public void evolve(){
		while(true){
			iterate();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Will call iterate n times 
	 * @param n_times The number of evolutions life is to have
	 */
	public void evolve (int n_times) throws IllegalArgumentException{
		if (n_times <=0)
			throw new IllegalArgumentException("The world needs a positive integer number of times to evolve.");
		
		for (int i = 0; i < n_times; i++)
			iterate();
	}
	
	/**
	 * Will cause an iteration of the game as defined in the specification.
	 * To do this we iterate through each cell checking if it's live and alerting
	 * adjacent cells if so. Then we intelligently iterate through at most n/4, 
	 * where n = total cells, cells to make changes to the the game board. Further 
	 * reasoning given on GitHub.
	 */
	public void iterate(){
		System.out.println("<-- iterate -->\n");
		
		for(Cell[] row : board){
			for (Cell cell : row){
				System.out.print(cell.getLive()+ " ");
			}
			System.out.println("");
		}
		
		System.out.println(" ... \n");
		
		for(Cell[] row : board)
			for (Cell cell : row)
				if(cell.isLive())
					cell.alertNeighbours();
			
		
		for (int j = 1; j < height; j+=2)
			for (int i = 1; i < width; i+=2)
				getCell(i, j).change();
		
		for(Cell[] row : board){
			for (Cell cell : row)
				System.out.print(cell.getLive()+ " ");
			System.out.println("");
		}
			
	}
	
	/**
	 * Reads in the CSV file from a given filename. The CSV file should have uniform width 
	 * and consist only of 0's and 1's, 0 representing a position with a dead cell and 1 with a live one
	 * @param filename
	 */
	private void getMap(String filename){
  	BufferedReader br = null;
  	String line;
  	String[] csvline = null;
  	ArrayList<String[]> csvLines = new ArrayList<>();
  	
  	try {
      br = new BufferedReader(new FileReader(filename));
      line = br.readLine();
      
      if (line == null)
      	throw new IllegalArgumentException("The input file is empty!");
      
      csvline = line.split(",");
      csvLines.add(csvline);
      
      width = csvline.length;
      height++;
      
      while ((line = br.readLine()) != null) {
      		csvline = line.split(",");
      		height++;
      		
      		if(csvline.length != width)
      			throw new IllegalArgumentException("Line : " + height+ " is of length different to the first line. All lines must be of same length.");
          
      		csvLines.add(csvline);
      }
      
      board = new Cell[height][width];
      int current ;
      
      for (int j = 0; j < height; j++)
      	for (int i = 0; i < width; i++){
      		current = Integer.parseInt(csvLines.get(j)[i]);
      		if (current != 0 && current !=1)
      			throw new IllegalArgumentException ("Line : "+ (j +1) + " element " + (i +1) + " contains value "+ current +" not equal to one or 0. Only 0, 1 allowed in file.");
      		else
      			board[j][i] = new Cell(this, board, i, j, current);
      	}
      
  	} catch (FileNotFoundException e) {
      e.printStackTrace();
  	} 
  	catch (IllegalArgumentException e) {
      e.printStackTrace();
  	}
  	catch (IOException exc) {
      exc.printStackTrace();
  	} 
  	finally {
      	if (br != null) 
          try {
              br.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
  	}
  }
	
	/**
	 * Writes the board to a CSV file with 1 representing live and 0 representing not
	 * @param filename The output file.
	 */
	public void writeToFile(String filename){
		PrintWriter pw = null;
    StringBuilder sb = new StringBuilder();

		try {
			pw = new PrintWriter(new File(filename));
			for (Cell[] row : board){
				for (int i = 0; i < row.length; i++){
					if (i == row.length -1)
						sb.append("" + row[i].getLive());
					else
						sb.append(row[i].getLive() + ",");
				}
				sb.append('\n');
				pw.write(sb.toString());
				pw.flush();
				sb = new StringBuilder(); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}
}
