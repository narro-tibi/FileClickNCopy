import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class FileClickNCopy {
	public static void main(String args[]) throws IOException {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Which folder do you want to copy? Enter the entire path: ");
		String input = keyboard.nextLine(); // Abbruch bei for-Schleife, wenn kein korrekter Pfad angegeben wird. Siehe README.
		
		File folder = new File(input);
    		File[] listOfFiles = folder.listFiles();
		
		System.out.print("Where do you want your files to go? Enter the path and add a new folder name at the end: ");
		String targetFolderInput = keyboard.nextLine(); // Falls nicht genauer definiert, erstellt Zielordner im Ordner des ausgeführten Programms. Siehe README.
		
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
