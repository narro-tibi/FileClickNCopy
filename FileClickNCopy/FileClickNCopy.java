import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class FileClickNCopy {
	
	public static String isValidPath(String takenInput) {
		Scanner keyboard1 = new Scanner(System.in);
			
		while (!Paths.get(takenInput).isAbsolute()) {
			System.out.println(takenInput + " - This is not a valid path. Please enter a valid path.");
			takenInput = keyboard1.nextLine();
		}
		//System.out.println("Whee!");
		return takenInput;
	} 
	
	public static void main(String args[]) throws IOException {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Which folder do you want to copy? Enter the entire path: ");
		String input = keyboard.nextLine();
		input = isValidPath(input);
		System.out.println("This is a valid path. Great job! " + "\n(Your path: " + isValidPath(input) + ")");
		
		File folder = new File(input);
    		File[] listOfFiles = folder.listFiles();
		
		System.out.print("Where do you want your files to go? Enter the entire path. \nIf you like, you can also add one or multiple new folders at the end: ");
		String targetFolderInput = keyboard.nextLine();
		targetFolderInput = isValidPath(targetFolderInput);
		System.out.println("This is a valid path. Great job! " + "\n(Your path: " + isValidPath(targetFolderInput) + ")");
		
		Path targetDir = Paths.get(targetFolderInput); 
		Files.createDirectories(targetDir);
		
		for (File file : listOfFiles)
    		{
			String sourceString = file.getAbsolutePath();
			Path source = Paths.get(sourceString);
			//System.out.println(source + "\nIs it an absolute path? " + source.isAbsolute()); // Check, ob korrektes Path-Objekt
			
			String targetString = file.getName();
			Path target = targetDir.resolve(targetString); // create new path ending with `targetString` content
			System.out.println(targetString + " copying into " + target);
			
			//System.out.println(Files.exists(source) + " " + Files.isDirectory(source)); // Check, ob File-Typ und ob es Unterordner gibt
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		}			
	}
}
