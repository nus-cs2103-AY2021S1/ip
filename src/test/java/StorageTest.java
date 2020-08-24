import data.task.*;
import org.junit.jupiter.api.Test;
import storage.Storage;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void load_validTxtFile() throws Exception {
        String validTxtFile = "data/storageValidTest.txt";
        Storage storage = new Storage(validTxtFile);
        TaskList dummyTaskList = createDummyTaskList();
        storage.loadTaskList(dummyTaskList);
        assertEquals(3, dummyTaskList.getTotalTask());
        assertEquals(dummyTaskList.getTask(0).toTxtFormat(), "T | 1 | buy books");
        assertEquals(dummyTaskList.getTask(1).toTxtFormat(), "D | 1 | eat bread | 6 May 2020 04:00 AM");
        assertEquals(dummyTaskList.getTask(2).toTxtFormat(), "E | 0 | eat dinner | 12 December 2018 11:00 PM to 01:00 AM");
    }

    @Test
    public void load_nonExistentFile() throws Exception {
        String validTxtFile = "data/doesNotExist.txt";
        Storage storage = new Storage(validTxtFile);
        TaskList dummyTaskList = createDummyTaskList();
        storage.loadTaskList(dummyTaskList);
        assertEquals(0, dummyTaskList.getTotalTask());
    }

    @Test
    public void save_nullTask() throws Exception {
        String validTxtFile = "data/storageValidTest.txt";
        Storage storage = new Storage(validTxtFile);
        assertThrows(NullPointerException.class, () -> storage.saveTask(null));
    }

    @Test
    public void save_validTask() throws Exception {
        emptyDummyText();
        String validTxtFile = "data/dummyText.txt";
        Storage storage = new Storage(validTxtFile);
        ToDo validToDo = createDummyToDo();
        Deadline validDeadline = createDummyDeadline();
        Event validEvent = createDummyEvent();
        storage.saveTask(validToDo);
        storage.saveTask(validDeadline);
        storage.saveTask(validEvent);
        Scanner s = new Scanner(new File(validTxtFile));
        String todo = s.nextLine();
        String deadline = s.nextLine();
        String event = s.nextLine();
        assertEquals("T | 0 | A Valid ToDo", todo);
        assertEquals("D | 0 | A Valid Deadline | 24 August 2020 08:00 PM", deadline);
        assertEquals("E | 0 | A Valid Event | 24 August 2020 08:00 PM to 01:00 AM", event);
    }

    private TaskList createDummyTaskList() {
        return new TaskList(new ArrayList<Task>(), new Ui());
    }

    private ToDo createDummyToDo() {
        return new ToDo("A Valid ToDo");
    }

    private Deadline createDummyDeadline() throws Exception {
        return new Deadline("A Valid Deadline", "2020-08-24 2000");
    }

    private Event createDummyEvent() throws Exception{
        return new Event("A Valid Event", "2020-08-24 2000-0100");
    }

    private void emptyDummyText() throws Exception {
        File dummyText = new File("data/dummyText.txt");
        FileWriter fw = new FileWriter(dummyText);

        fw.write("");
        fw.close();
    }
}
