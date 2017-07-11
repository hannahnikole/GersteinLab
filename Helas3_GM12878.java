import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Helas3_GM12878 {
	
	public static void main(String[] args){
		String[] GM12878_files = loadText("GM12878_files.txt",41);
		String[] Helas3_files = loadText("Helas3_files.txt",53);
		
		//commandWriter(GM12878_files, Helas3_files);
		
		int[] GM12878_lines = loadData("GM12878_lines.txt",41);
		int[] Helas3_lines = loadData("Helas3_lines.txt",53);
		int[] GM_GM = loadData("GM-GM_intersect.txt",1681);
		int[] GM_Hela = loadData("GM-Hela_intersect.txt",2173);
		int[] Hela_GM = loadData("Hela-GM_intersect.txt",2173);
		
		
		//listToGrid(Hela_GM, 53, 41);
		
		//heatMap("GM12878_lines.txt", 41, GM_GM, 41, 41);
		//heatMap("GM12878_lines.txt", 41, GM_Hela, 41, 53);
		heatMap("Helas3_lines.txt", 53, Hela_GM, 53, 41);		
	}
	
	public static String[] loadText(String file, int listSize){
		
		Scanner input = null;
        // File IO with exception handling for file
        try {
            input = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            System.out.println("Could not open " + file);
            System.exit(1);
        }
  
        String[] file_array = new String[listSize];
        
        for(int i = 0; i < listSize; i++){
        	file_array[i] = input.nextLine();
        	//System.out.println(file_array[i]);
        }//for loop makes file name array
        
        input.close();
		
        return file_array;
        
	}
	
	public static int[] loadData(String file, int listSize){

		Scanner input = null;
        // File IO with exception handling for file
        try {
            input = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            System.out.println("Could not open " + file);
            System.exit(1);
        }
  
        int[] data_array = new int[listSize];
        
        for(int i = 0; i < listSize; i++){
        	data_array[i] = input.nextInt();
        	//System.out.println(data_array[i]);
        }//for loop makes file name array
        
        input.close();
		
        return data_array;
	}
	
	public static void commandWriter(String[] listA, String[] listB){
		
		for(int i = 0; i < listA.length; i++){
			for(int j = 0; j < listB.length; j++){
				System.out.println("intersectBed -u -a " + listA[i] + " -b " + listB[j] + 
						" | wc -l");
			}
		}//for loop generates command lines
	
	}
	
	public static void listToGrid(int[] intersections, int columns, int rows){
		
		int[][] dataGrid = new int[rows][columns];
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				dataGrid[i][j] = intersections[(j*rows)+i];
				System.out.print(dataGrid[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void heatMap(String linesFileName, int lineCount, int[] intersections,
			int cols, int rows){
		String file_name = linesFileName;
		
		Scanner input = null;
		try{
			input = new Scanner(new File(file_name));
		} catch (FileNotFoundException e){
			System.out.println("Could not open " + file_name);
			System.exit(1);
		}
	
		//stores line count into an array
		double[] lines = new double[lineCount];
		for(int i = 0; i < lines.length; i++){
			lines[i] = Integer.parseInt(input.nextLine());
			//System.out.println(scaleCount[i]);
		}
		
		double[][] heatGrid = new double[rows][cols];
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				heatGrid[i][j] = (intersections[(j*rows)+i])/lines[j];
				System.out.print(heatGrid[i][j] + "\t");
			}
			System.out.println();
		}
		
	}
	
}//end class
