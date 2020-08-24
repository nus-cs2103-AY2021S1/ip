package duke;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

public class StorageTest {

    /**
     * Tests if the Storage class creates the necessary files and folders should they be missing.
     *
     * @throws Exception Exception is thrown if the test case fails.
     */
    @Test
    public void testFileCreation() throws Exception {
        File dir = new File("src/main/data/");
        if (dir.exists()) {
            for(File file: dir.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
            if (dir.listFiles().length == 0) {
                dir.delete();
            }
        }
        Storage storage1 = new Storage("src/main/data/", "src/main/data/data.txt");
        try {
            storage1.processData();
        } catch (IOException ignored) {

        }
        File newDir = new File("src/main/data/");
        if (!newDir.exists()) {
            throw new Exception("Directory does not exist when it should have been created");
        }
        File newFile = new File("src/main/data/data.txt");
        if (!newFile.exists()) {
            throw new Exception("Data file not created");
        }
        System.out.println("Test Case Passed");
        newFile.delete();
        newDir.delete();
    }
    
    @Test
    public void testDataCreation() throws Exception {
        Storage storage1 = new Storage("src/main/data/", "src/main/data/data.txt");
        storage1.processData();
        if (storage1.getData().size() != 0) {
            System.out.println("Test case 1 failed");
        }
        System.out.println("Test Case Passed");
        File newFile = new File("src/main/data/data.txt");
        if (newFile.exists()) {
            newFile.delete();
        }
        File newDir = new File("src/main/data/");
        newDir.delete();
    }

}
