package duke.util;

import duke.storage.Storage;
import duke.stub.command.CommandExecutorStub;
import duke.stub.task.TaskListStub;
import duke.task.Task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;

public class StorageTest {
    private static final Path TEST_FILE_PATH = Paths.get("test", "data", "duke.txt");

    // recursive function to delete directory that stores the save file
    private static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    @BeforeEach
    @AfterEach
    public void clearPath() {
        Path topDir = TEST_FILE_PATH.subpath(0, 1);
        if (!java.nio.file.Files.exists(topDir)) {
            return;
        }
        File toDelete = new File(String.valueOf(topDir));
        deleteDirectory(toDelete);
    }

    @Test
    public void updateSaveFile() throws IOException {
        Storage store = new Storage(TEST_FILE_PATH);
        assertTrue(java.nio.file.Files.exists(TEST_FILE_PATH));

        TaskListStub taskListStub = new TaskListStub();
        store.updateSaveFile(taskListStub);

        FileReader fileReader = new FileReader(TEST_FILE_PATH.toString());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] lines = bufferedReader.lines().toArray(String[]::new);

        Task[] fillerTasks = TaskListStub.fillerTasks();

        String[] expected =
            Arrays
                .stream(fillerTasks)
                .map(Task::toSaveString)
                .toArray(String[]::new);

        bufferedReader.close();
        fileReader.close();

        assertEquals(String.join("", expected), String.join("", lines));
    }

    @Test
    public void loadSaveFile() throws IOException {
        Storage store = new Storage(TEST_FILE_PATH);
        assertTrue(java.nio.file.Files.exists(TEST_FILE_PATH));

        Task[] fillerTasks = TaskListStub.fillerTasks();

        FileWriter myWriter = new FileWriter(TEST_FILE_PATH.toString());
        Arrays.stream(fillerTasks).forEach((Task task) -> {
            try {
                myWriter.write(task.toSaveString() + "\n");
            } catch (IOException e) {
                System.out.println("An error has occurred when updating the save file.");
            }
        });
        myWriter.close();

        TaskListStub stub = new TaskListStub();
        CommandExecutorStub exe = new CommandExecutorStub();
        store.loadSaveFile(stub, exe);

        String actual = exe.getHistory();

        String[] expected =
            Arrays
                .stream(fillerTasks)
                .map((Task t) -> t.toSaveString().substring(1))
                .toArray(String[]::new);

        assertEquals(String.join("", expected), actual);
    }
}
