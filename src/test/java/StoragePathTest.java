import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;
import duke.utility.Storage;
import duke.utility.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StoragePathTest {
    @Test
    public void StorageSaveTest() {
        Path path = Paths.get("../../junit-test/ACTUAL_STORAGE_SAVE.txt");
        Storage storage = new Storage(path.toString());

        File actualStorageSave = new File(path.toString());

        if (actualStorageSave.exists()) {
            actualStorageSave.delete();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        TaskList taskList = new TaskList();
        String taskName = "Sample task";

        ToDoTask toDoTask = new ToDoTask(taskName);

        LocalDateTime deadlineDate1 = LocalDateTime.parse("2020-05-05 0000", formatter);
        LocalDateTime deadlineDate2 = LocalDateTime.parse("2019-05-05 0000", formatter);

        DeadlineTask deadlineTask1 = new DeadlineTask(taskName, deadlineDate1);
        deadlineTask1.setStatusToDone();
        DeadlineTask deadlineTask2 = new DeadlineTask(taskName, deadlineDate2);

        taskList.addTask(deadlineTask1);
        taskList.addTask(deadlineTask2);

        LocalDateTime eventDate1 = LocalDateTime.parse("2020-10-10 0000", formatter);
        LocalDateTime eventDate2 = LocalDateTime.parse("2019-10-10 0000", formatter);

        EventTask eventTask1 = new EventTask(taskName, eventDate1);
        EventTask eventTask2 = new EventTask(taskName, eventDate2);
        eventTask2.setStatusToDone();

        Assertions.assertDoesNotThrow(() -> {
            actualStorageSave.createNewFile();
            storage.saveTaskToFile(toDoTask);
            storage.saveTaskToFile(deadlineTask1);
            storage.saveTaskToFile(deadlineTask2);
            storage.saveTaskToFile(eventTask1);
            storage.saveTaskToFile(eventTask2);
        });

        Assertions.assertDoesNotThrow(() -> {
            File expectedStorageSave = new File("../../junit-test/EXPECTED_STORAGE_SAVE.txt");
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
    public void StorageChangeToDoneTest() {
        Path path = Paths.get("../../junit-test/ACTUAL_STORAGE_CHANGE_TO_DONE.txt");
        Storage storage = new Storage(path.toString());

        File actualStorageChangeToDone = new File(path.toString());

        if (actualStorageChangeToDone.exists()) {
            actualStorageChangeToDone.delete();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        TaskList taskList = new TaskList();
        String taskName = "Sample task";

        ToDoTask toDoTask = new ToDoTask(taskName);

        LocalDateTime deadlineDate1 = LocalDateTime.parse("2020-05-05 0000", formatter);
        LocalDateTime deadlineDate2 = LocalDateTime.parse("2019-05-05 0000", formatter);

        DeadlineTask deadlineTask1 = new DeadlineTask(taskName, deadlineDate1);
        deadlineTask1.setStatusToDone();
        DeadlineTask deadlineTask2 = new DeadlineTask(taskName, deadlineDate2);

        taskList.addTask(deadlineTask1);
        taskList.addTask(deadlineTask2);

        LocalDateTime eventDate1 = LocalDateTime.parse("2020-10-10 0000", formatter);
        LocalDateTime eventDate2 = LocalDateTime.parse("2019-10-10 0000", formatter);

        EventTask eventTask1 = new EventTask(taskName, eventDate1);
        EventTask eventTask2 = new EventTask(taskName, eventDate2);
        eventTask2.setStatusToDone();

        Assertions.assertDoesNotThrow(() -> {
            actualStorageChangeToDone.createNewFile();
            storage.saveTaskToFile(toDoTask);
            storage.saveTaskToFile(deadlineTask1);
            storage.saveTaskToFile(deadlineTask2);
            storage.saveTaskToFile(eventTask1);
            storage.saveTaskToFile(eventTask2);
            storage.changeTaskInFile(1);
            storage.changeTaskInFile(3);
        });

        Assertions.assertDoesNotThrow(() -> {
            File expectedStorageChangeToDone =
                    new File("../../junit-test/EXPECTED_STORAGE_CHANGE_TO_DONE.txt");
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
    public void StorageDeleteTest() {
        Path path = Paths.get("../../junit-test/ACTUAL_STORAGE_DELETE.txt");
        Storage storage = new Storage(path.toString());

        File actualStorageDelete = new File(path.toString());

        if (actualStorageDelete.exists()) {
            actualStorageDelete.delete();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        TaskList taskList = new TaskList();
        String taskName = "Sample task";

        ToDoTask toDoTask = new ToDoTask(taskName);

        LocalDateTime deadlineDate1 = LocalDateTime.parse("2020-05-05 0000", formatter);
        LocalDateTime deadlineDate2 = LocalDateTime.parse("2019-05-05 0000", formatter);

        DeadlineTask deadlineTask1 = new DeadlineTask(taskName, deadlineDate1);
        deadlineTask1.setStatusToDone();
        DeadlineTask deadlineTask2 = new DeadlineTask(taskName, deadlineDate2);

        taskList.addTask(deadlineTask1);
        taskList.addTask(deadlineTask2);

        LocalDateTime eventDate1 = LocalDateTime.parse("2020-10-10 0000", formatter);
        LocalDateTime eventDate2 = LocalDateTime.parse("2019-10-10 0000", formatter);

        EventTask eventTask1 = new EventTask(taskName, eventDate1);
        EventTask eventTask2 = new EventTask(taskName, eventDate2);
        eventTask2.setStatusToDone();

        Assertions.assertDoesNotThrow(() -> {
            actualStorageDelete.createNewFile();
            storage.saveTaskToFile(toDoTask);
            storage.saveTaskToFile(deadlineTask1);
            storage.saveTaskToFile(deadlineTask2);
            storage.saveTaskToFile(eventTask1);
            storage.saveTaskToFile(eventTask2);
            storage.deleteTaskInFile(3);
            storage.deleteTaskInFile(4);
        });

        Assertions.assertDoesNotThrow(() -> {
            File expectedStorageDelete =
                    new File("../../junit-test/EXPECTED_STORAGE_DELETE.txt");
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
