package demo.nio2.file_apis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BasicOperationsExample {

	public static void main(String[] args) throws IOException {

		// Create the directory tree for this test.
        createDirTree();

        // Display information about a Path.
        displayPathInfo();
        
        // Compare Paths.
        comparePaths();
        
        // Copy, move, and delete files.
        copyMoveDeleteFiles();
    }


    // This method creates the directory tree for the example under the current directory
    private static void createDirTree() throws IOException {
        
		// Create /headDir, and add 3 files.
    	File headDir = new File("C:/JavaDev/Temp/headDir");
        headDir.mkdir();
        headDir.deleteOnExit();

        File myFile0a = new File(headDir, "myFile0a");
        myFile0a.createNewFile();
        myFile0a.deleteOnExit();

        File myFile0b = new File(headDir, "myFile0b");
        myFile0b.createNewFile();
        myFile0b.deleteOnExit();

        File myFile0c = new File(headDir, "myFile0c");
        myFile0c.createNewFile();
        myFile0c.deleteOnExit();

		// Create /anotherDir, so not delete on exit!
    	File anotherDir = new File("C:/JavaDev/Temp/anotherDir");
        anotherDir.mkdir();
}
    
    // This method displays info about a Path.
	private static void displayPathInfo() throws IOException {
		
		System.out.println("---- Display Path Info Example ----");
		
        // Get a Path instance for a file (same for directory).
		Path path = Paths.get("C:/JavaDev/Temp/headDir/myFile0a");

		System.out.println("File name: " + path.getFileName());
		System.out.println("Parent:    " + path.getParent());
		System.out.println("Root:      " + path.getRoot());
		
		System.out.println("Abs path:  " + path.toAbsolutePath());
		System.out.println("Real path: " + path.toRealPath(LinkOption.NOFOLLOW_LINKS));
		System.out.println("URI:       " + path.toUri());
		
		System.out.println("\nPath elements: ");
		for (Path element : path) {
			System.out.print("  " + element);
		}
		
		System.out.println("\nAnother way to get Path elements: ");
		for (int i = 0; i < path.getNameCount(); i++) {
			System.out.print("  " + path.getName(i));
		}
	}
    
    // This method compares two Paths.
	private static void comparePaths() throws IOException {
		
		System.out.println("\n\n---- Compare Paths Example ----");
	
		// Get two Path instances that refer to the same physical file.
		Path path1 = Paths.get("C:/JavaDev/Temp/headDir/myFile0a");
		Path path2 = Paths.get("C:/JavaDev/Temp/headDir/../headDir/myFile0a");
		
		System.out.println("path1 is " + path1);
		System.out.println("path2 is " + path2);
		System.out.println("  equals:    " + path1.equals(path2));
		System.out.println("  compareTo: " + path1.compareTo(path2));
		
		path1 = path1.normalize();	// Remove redundant elements in the path.
		path2 = path2.normalize();	// Ditto.
	
		System.out.println("path1 is now " + path1);
		System.out.println("path2 is now " + path2);
		System.out.println("  equals:    " + path1.equals(path2));
		System.out.println("  compareTo: " + path1.compareTo(path2));
	}
	
	// This method uses Files to copy, move, and delete files.
	private static void copyMoveDeleteFiles() throws IOException {
	
		System.out.println("\n---- Copy, move, and delete files example ----");
	
		Path file       = Paths.get("C:/JavaDev/Temp/headDir/myFile0a");
		Path headDir    = Paths.get("C:/JavaDev/Temp/headDir");
		Path anotherDir = Paths.get("C:/JavaDev/Temp/anotherDir");
	
		Path copy1 = Files.copy(file, file.resolveSibling("myFile0a.copy1"), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(file, file.resolveSibling("myFile0a.copy2"), StandardCopyOption.REPLACE_EXISTING);
		Files.move(file, anotherDir.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
		Files.delete(copy1);
		
		System.out.println(anotherDir + " should contain myFile0a");
		System.out.println(headDir + " should contain myFile0a.copy2");
	}
}
