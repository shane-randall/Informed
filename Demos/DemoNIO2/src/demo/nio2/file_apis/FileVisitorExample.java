package demo.nio2.file_apis;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class FileVisitorExample {

	public static void main(String[] args) throws IOException {

        // Create our FileVisitor implementation by overriding two of the methods defined in SimpleFileVisitor.
		FileVisitor<Path> myFileVisitor = new SimpleFileVisitor<Path>() {
		    
			@Override
		    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attribs) {
		        System.out.printf("\nAbout to visit directory %s [created %s]\n", dir, attribs.creationTime());
		        return FileVisitResult.CONTINUE;
		    }
		    
		    @Override
		    public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) {
		        System.out.printf("Visiting file %s [modified %s]\n", file, attribs.lastModifiedTime());
		        return FileVisitResult.CONTINUE;
		    }
		};

		// Create the directory tree for this test.
        createDirTree();

        // Get a Path instance for the directory we want to visit.
        Path headDir = Paths.get("C:/JavaDev/Temp/headDir");

        // Now walk the file tree created earlier.
        Files.walkFileTree(headDir, myFileVisitor);
    }


    // This method creates the directory tree for the example under the current directory
    private static void createDirTree() throws IOException {
        
    	// Create /headDir, and add 3 files.
    	File headDir = new File("c:/JavaDev/Temp/headDir");
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

        // Create /headDir/mySubDirectory1, and add 2 files.
        File mySubDirectory1 = new File(headDir, "mySubDirectory1");
        mySubDirectory1.mkdir();
        mySubDirectory1.deleteOnExit();

        File myFile1a = new File(mySubDirectory1, "myFile1a");
        myFile1a.createNewFile();
        myFile1a.deleteOnExit();

        File myFile1b = new File(mySubDirectory1, "myFile1b");
        myFile1b.createNewFile();
        myFile1b.deleteOnExit();

        // Create /headDir/mySubDirectory2, and add 2 files.
        File mySubDirectory2 = new File(headDir, "mySubDirectory2");
        mySubDirectory2.mkdir();
        mySubDirectory2.deleteOnExit();

        File myFile2a = new File(mySubDirectory2, "myFile2a");
        myFile2a.createNewFile();
        myFile2a.deleteOnExit();

        File myFile2b = new File(mySubDirectory2, "myFile2b");
        myFile2b.createNewFile();
        myFile2b.deleteOnExit();
    }
}
