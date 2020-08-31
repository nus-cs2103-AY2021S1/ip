import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;
import duke.utility.Storage;

public class StorageTest {
    private Task toDoTask;
    private Task deadlineTask;
    private Task eventTask;

    @BeforeEach
    public void init() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTest = LocalDateTime.parse("2020-01-01 0000", formatter);
        String taskName = "Sample task";
        this.toDoTask = new ToDoTask(taskName);
        toDoTask.setStatusToDone();
        this.deadlineTask = new DeadlineTask(taskName, dateTest);
        deadlineTask.setStatusToDone();
        this.eventTask = new EventTask(taskName, dateTest);

    }
    @Test
    public void storageSaveTest() {
        Path path = Paths.get("junit-test/ACTUAL_STORAGE_SAVE.txt");
        Storage storage = new Storage(path.toString());
        File actualStorageSave = new File(path.toString());

        if (actualStorageSave.exists()) {
            actualStorageSave.delete();
        }

        Assertions.assertDoesNotThrow(() -> {
            actualStorageSave.createNewFile();
            storage.saveTaskToFile(toDoTask);
            storage.saveTaskToFile(deadlineTask);
            storage.saveTaskToFile(eventTask);
        });

        Assertions.assertDoesNotThrow(() -> {
            File expectedStorageSave = new File("junit-test/EXPECTED_STORAGE_SAVE.txt");
            Scanner scForActual = new Scanner(actualStorageSave);
            Scanner scForExpected = new Scanner(expectedStorageSave);

            boolean areSame = true;

            while (scForActual.hasNext() || scForExpected.hasNext()) {
                String lineActual = scForActual.nextLine();
                String lineExpected = scForExpected.nextLine();

                if (!lineActual.equals(lineExpected)) {
                    areSame = false;
                    break;
                }
            }
            Assertions.assertTrue(areSame);
        });
    }

    @Test
    public void storageChangeToDoneTest() {
        Path path = Paths.get("junit-test/ACTUAL_STORAGE_CHANGE_TO_DONE.txt");
        Storage storage = new Storage(path.toString());
        File actualStorageChangeToDone = new File(path.toString());

        if (actualStorageChangeToDone.exists()) {
            actualStorageChangeToDone.delete();
        }

        Assertions.assertDoesNotThrow(() -> {
            actualStorageChangeToDone.createNewFile();
            storage.saveTaskToFile(toDoTask);
            storage.saveTaskToFile(deadlineTask);
            storage.saveTaskToFile(eventTask);
            storage.changeTaskInFile(3);
        });

        Assertions.assertDoesNotThrow(() -> {
            File expectedStorageChangeToDone = new File("junit-test/EXPECTED_STORAGE_CHANGE_TO_DONE.txt");
            Scanner scForActual = new Scanner(actualStorageChangeToDone);
            Scanner scForExpected = new Scanner(expectedStorageChangeToDone);

            boolean areSame = true;

            while (scForActual.hasNext() || scForExpected.hasNext()) {
                String lineActual = scForActual.nextLine();
                String lineExpected = scForExpected.nextLine();

                if (!lineActual.equals(lineExpected)) {
                    areSame = false;
                    break;
                }
            }
            Assertions.assertTrue(areSame);
        });
    }

    @Test
    public void storageDeleteTest() {
        Path path = Paths.get("junit-test/ACTUAL_STORAGE_DELETE.txt");
        Storage storage = new Storage(path.toString());

        File actualStorageDelete = new File(path.toString());

        if (actualStorageDelete.exists()) {
            actualStorageDelete.delete();
        }

        Assertions.assertDoesNotThrow(() -> {
            actualStorageDelete.createNewFile();
            storage.saveTaskToFile(toDoTask);
            storage.saveTaskToFile(deadlineTask);
            storage.saveTaskToFile(eventTask);
            storage.deleteTaskInFile(2);
        });

        Assertions.assertDoesNotThrow(() -> {
            File expectedStorageDelete = new File("junit-test/EXPECTED_STORAGE_DELETE.txt");
            Scanner scForActual = new Scanner(actualStorageDelete);
            Scanner scForExpected = new Scanner(expectedStorageDelete);

            boolean areSame = true;

            while (scForActual.hasNext() || scForExpected.hasNext()) {
                String lineActual = scForActual.nextLine();
                String lineExpected = scForExpected.nextLine();

                if (!lineActual.equals(lineExpected)) {
                    areSame = false;
                    break;
                }
            }
            Assertions.assertTrue(areSame);
        });
    }
}
