package javaFiles;

public class A_Main_Of_Life {
	// NOTE THE FILES USED MUST BE IN THE SAME FOLDER AS THE SOURCE CODE.
	
	public static void main(String[] args) {
		Board board  = new Board ("src/javaFiles/" + args[0]);
		try{
			board.evolve(Integer.parseInt(args[1]));
			board.writeToFile("src/javaFiles/" + args[2]);
		}catch (NumberFormatException exc){
			System.out.println(exc.getMessage() + "\nSecond input should have an positive integer.");
		}catch (IllegalArgumentException exc){
			exc.printStackTrace();
		}
	}

}
