package duke;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import duke.storage.Storage;
import duke.storage.TaskList;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


public class StorageTest {

    /**
     * Tests if the Storage class creates the necessary files and folders should they be missing.
     */
    @Test
    public void processData_creatingDirectories_directoriesCreated() {
        File dir = new File("src/main/data/");
        if (dir.exists()) {
            for (File file: dir.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
            if (dir.listFiles().length == 0) {
                dir.delete();
            }
        }
        Storage storage = new Storage("src/main/data/", "src/main/data/data.txt");
        try {
            storage.processData();
            File newDir = new File("src/main/data/");
            assert newDir.exists();
            File newFile = new File("src/main/data/data.txt");
            assert newFile.exists();
            newFile.delete();
            newDir.delete();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void saveData_testSavingData_dataSaved() {
        File dir = new File("src/main/data/");
        if (dir.exists()) {
            for (File file: dir.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
            if (dir.listFiles().length == 0) {
                dir.delete();
            }
        }
        Storage storage = new Storage("src/main/data/", "src/main/data/data.txt");
        TaskList lines = new TaskList(new ArrayList<>());
        lines.addTask(new Todo("Homework").toString());
        lines.addTask(new Deadline("Project", "2020-09-09").toString());
        lines.addTask(new Event("Meeting", "2020-10-10").toString());
        try {
            storage.processData();
            storage.saveData(lines.getList());
            File newFile = new File("src/main/data/data.txt");
            Scanner fileReader = new Scanner(newFile);
            StringBuffer buffer = new StringBuffer();
            while (fileReader.hasNextLine()) {
                buffer.append(fileReader.nextLine()).append("\n");
            }
            fileReader.close();
            String fileContents = buffer.toString();
            String[] lineArray = fileContents.split("\n");
            for (int i = 0; i < lineArray.length; i++) {
                assertEquals(lines.getTask(i), lineArray[i]);
            }
        } catch (Exception e) {
            fail(); //
        }
    }
}
