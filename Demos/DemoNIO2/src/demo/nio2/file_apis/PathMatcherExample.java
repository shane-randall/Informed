package demo.nio2.file_apis;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class PathMatcherExample {

	static class Finder extends SimpleFileVisitor<Path> {
        
		final PathMatcher matcher;

        Finder(String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (matcher.matches(file.getFileName())) {
                System.out.format("%s%n", file);
            }
            return CONTINUE;
        }
    }

	public static void main(String[] args) throws IOException {

		// Create the directory tree for this test.
        createDirTree();

        // Get a Path instance for the directory we want to visit.
        Path headDir = Paths.get("C:/JavaDev/Temp/headDir");

        // Now walk the file tree.
        Finder finder = new Finder("*.java");
        Files.walkFileTree(headDir, finder);
    }


    // This method creates the directory tree for the example under the current directory
    private static void createDirTree() throws IOException {
        
    	// Create /headDir, and add 3 files.
    	File headDir = new File("c:/JavaDev/Temp/headDir");
        headDir.mkdir();
        headDir.deleteOnExit();

        File myFile0a = new File(headDir, "myFile1.txt");
        myFile0a.createNewFile();
        myFile0a.deleteOnExit();

        File myFile0b = new File(headDir, "myFile2.txt");
        myFile0b.createNewFile();
        myFile0b.deleteOnExit();

        File myFile0c = new File(headDir, "myFile3.txt");
        myFile0c.createNewFile();
        myFile0c.deleteOnExit();

        // Create /headDir/mySubDirectory1, and add 2 files.
        File mySubDirectory1 = new File(headDir, "mySubDirectory1");
        mySubDirectory1.mkdir();
        mySubDirectory1.deleteOnExit();

        File myFile1a = new File(mySubDirectory1, "myFile1a.java");
        myFile1a.createNewFile();
        myFile1a.deleteOnExit();

        File myFile1b = new File(mySubDirectory1, "myFile1b.java");
        myFile1b.createNewFile();
        myFile1b.deleteOnExit();

        // Create /headDir/mySubDirectory2, and add 2 files.
        File mySubDirectory2 = new File(headDir, "mySubDirectory2");
        mySubDirectory2.mkdir();
        mySubDirectory2.deleteOnExit();

        File myFile2a = new File(mySubDirectory2, "myFile2a.xml");
        myFile2a.createNewFile();
        myFile2a.deleteOnExit();

        File myFile2b = new File(mySubDirectory2, "myFile2b.xml");
        myFile2b.createNewFile();
        myFile2b.deleteOnExit();
    }
}
