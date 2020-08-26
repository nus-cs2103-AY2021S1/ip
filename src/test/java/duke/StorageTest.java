package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileWriter;

public class StorageTest {

    @Test
    public void testStorageCreation() {
        String workingDir = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(workingDir, "storage", "data.txt");
        Storage s = new Storage(path);
        assertEquals(true, Files.exists(path));
    }

    @Test
    public void testWriteToFile() {
        try {
            String workingDir = System.getProperty("user.dir");
            java.nio.file.Path path = java.nio.file.Paths.get(workingDir, "storage", "data.txt");
            Storage s = new Storage(path);

            ToDo dummyToDo = new ToDo("test");
            String dummyInput = "todo test";
            s.writeToFile(dummyToDo, dummyInput);
            FileReader fr = new FileReader("storage" + File.separator + "data.txt");
            BufferedReader br = new BufferedReader(fr);
            String lineToCheck = br.readLine();
            br.close();
            fr.close();

            assertEquals("1|T|0|test", lineToCheck);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Test
    public void testLoadToArray() {
        try {
            String workingDir = System.getProperty("user.dir");
            java.nio.file.Path path = java.nio.file.Paths.get(workingDir, "mockstorage", "mockdata.txt");
            Storage s = new Storage(path);
            ToDo dummyToDo = new ToDo("test");
            ArrayList<Task> loadedTasks = s.load();
            assertEquals(dummyToDo.toString(), loadedTasks.get(0).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @AfterAll
    public static void resetDir() {
        try {
            String workingDir = System.getProperty("user.dir");
            if (java.nio.file.Files.exists(java.nio.file.Paths.get(workingDir, "storage", "data.txt"))) {
                java.nio.file.Path pathToTxt = java.nio.file.Paths.get(workingDir, "storage", "data.txt");
                Files.delete(pathToTxt);
                java.nio.file.Path pathToDir = java.nio.file.Paths.get(workingDir, "storage");
                Files.delete(pathToDir);
            }
            
            String mockDir = System.getProperty("user.dir");
            java.nio.file.Path pathToMockTxt = java.nio.file.Paths.get(workingDir, "mockstorage", "mockdata.txt");
            Files.delete(pathToMockTxt);
            java.nio.file.Path pathToMockDir = java.nio.file.Paths.get(workingDir, "mockstorage");
            Files.delete(pathToMockDir);

            File newDir = new File("mockstorage" + File.separator + "mockdata.txt");
            newDir.getParentFile().mkdirs();
            newDir.createNewFile();
            FileWriter fw = new FileWriter("mockstorage" + File.separator + "mockdata.txt", true);
            fw.write("1|T|0|test");
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
