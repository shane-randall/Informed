package demo.nio2.file_apis;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

class FileAttributesExample {

    public static void main(String[] args) throws Exception {
    
    	// Create the file we will be using in this example
        File attribFile = new File("attribFile");
        Path attribPath = attribFile.toPath();
        attribFile.createNewFile();
        attribFile.deleteOnExit();

        // Execute example code for BasicFileAttributeView
        basicAttributesExample(attribPath);

        // Execute example code for FileOwnerAttributeView
        fileOwnerAttributesExample(attribPath);

        // Execute example code for UserDefinedFileAttributeView
        userAttributesExample(attribPath);
    }

    // Demonstrates the BasicFileAttributeView
    private static void basicAttributesExample(Path attribPath) throws Exception {
    
    	System.out.println("---- BasicFileAttributeView Example ----");

        // Get the BasicFileAttributeView and the BasicFileAttributes for this file
		BasicFileAttributeView basicView = Files.getFileAttributeView(attribPath, BasicFileAttributeView.class);
		BasicFileAttributes basicAttribs = basicView.readAttributes();

        // Print the attributes for this file
		System.out.println("File " + attribPath + " has these BasicFileAttributes:");
		System.out.println("  Created:       " + basicAttribs.creationTime());
		System.out.println("  Last access:   " + basicAttribs.lastAccessTime());
		System.out.println("  Last modified: " + basicAttribs.lastModifiedTime());
		System.out.println("  Size:          " + basicAttribs.size());
		System.out.println("  Directory?     " + basicAttribs.isDirectory());
		System.out.println("  Regular file?  " + basicAttribs.isRegularFile());
		System.out.println("  Symbolic link? " + basicAttribs.isSymbolicLink());
		System.out.println("  Other?         " + basicAttribs.isOther());
        
        // Now move the last modified time a minute into the future!
        FileTime newModTime = FileTime.fromMillis(basicAttribs.lastModifiedTime().toMillis() + 60000);
        basicView.setTimes(newModTime, null, null);

        // Now re-read the attributes and print the file times again
        // The last modified time should be 1 minute ahead from the previous time it was printed
        basicAttribs = basicView.readAttributes();
        System.out.println("After changing the last modified time:");
        System.out.println("  Created:       " + basicAttribs.creationTime());
        System.out.println("  Last access:   " + basicAttribs.lastAccessTime());
        System.out.println("  Last modified: " + basicAttribs.lastModifiedTime());
        System.out.println();
    }

    // Demonstrates the FileOwnerAttributeView
    private static void fileOwnerAttributesExample(Path attribPath) throws Exception {
        System.out.println("---- FileOwnerAttributeView Example ----");

        // Get the FileOwnerAttributeView and UserPrincipal for this file
		FileOwnerAttributeView ownerView = Files.getFileAttributeView(attribPath, FileOwnerAttributeView.class);
		UserPrincipal owner = ownerView.getOwner();

        // Print the owner of this file
        System.out.println("The owner of this file is: " + owner);
        System.out.println();
    }

    // Demonstrates the UserDefinedFileAttributeView
    private static void userAttributesExample(Path attribPath) throws Exception {
        System.out.println("---- UserDefinedFileAttributeView Example ----");

        if (File.separatorChar != '\\') {
            System.out.println("This example is only available on Windows, sorry!");
            return;
        }

        // Get the UserDefinedFileAttributeView for this file
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(attribPath, UserDefinedFileAttributeView.class);

        // Get the list of user defined attributes for this file - we expect this to be empty
        List<String> attribList = userView.list();
        if (attribList.size() == 0) {
            System.out.println("The file " + attribPath + " has no user defined attributes yet, as expected");
        } else {
            System.out.println("ERROR: The file " + attribPath + " has some user defined attributes that were not expected");
        }

        // Now we'll set our own attribute on this file
        System.out.println("Setting a user defined attribute on " + attribPath);
        String name = "Our Attribute Name!";
        String value = "This is out attribute value!";
        userView.write(name, Charset.defaultCharset().encode(value));

        // Let's try inspecting the user defined attributes again
        attribList = userView.list();
        if (attribList.size() == 1) {
            System.out.println("The file " + attribPath + " now has 1 user defined attribute, as expected:");
            for (String attribName : attribList) {
                // Allocate a ByteBuffer large enough to hold the attribute's value
                ByteBuffer attribValue = ByteBuffer.allocate(userView.size(attribName));

                // Get the value of the attribute and display it
                userView.read(attribName, attribValue);
                attribValue.flip();
                System.out.println("   Attribute Name: " + attribName);
                System.out.println("   Attribute Value: " + Charset.defaultCharset().decode(attribValue));
            }
        } else {
            System.out.println("ERROR: The file " + attribPath + " has " + attribList.size() + " user defined attribute(s), but we expected 1!");
        }

        // Now delete the user defined attribute we just created
        userView.delete(name);

        // And list the attributes again to check they're back to 0
        attribList = userView.list();
        if (attribList.size() == 0) {
            System.out.println("After deleting the attribute, the file " + attribPath + " has no user defined attributes left, as expected");
        } else {
            System.out.println("ERROR: The file " + attribPath + " has some user defined attributes that were not expected");
        }
    }
}
