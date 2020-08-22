package duke.utils;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;



class StorageTest {

    @Test
    void readSavedFile() throws IOException {
        TaskList list = new TaskList();
        Storage storage = new Storage(list);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        makeBackup();
        storage.readSavedFile();
        list.printList();
        recover();

        String expected = "Hello from\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "\n" +
                "What can I do for you?\n" +
                "Welcome back! Trying to retrieve where you were last time...\n" +
                "Great! We have successfully loaded the data. Enjoy~\n" +
                "Here are the tasks in your list:\n" +
                "1.[T][✗] borrow book\n" +
                "2.[D][✗] return book (by: Aug 23 2020)\n";

        assertEquals(expected, outContent.toString());

    }

    @Test
    void saveDataToFile() throws IOException {
        TaskList list = new TaskList();
        Storage storage = new Storage(list);
        makeBackup();
        storage.readSavedFile();
        list.addTask("test content", "todo");
        storage.saveDataToFile();
        StringBuilder sb = new StringBuilder();
        File f = new File("data/duke.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append("\n");
        }
        String actual = sb.toString();
        String expected = "todo|borrow book\n" +
                "deadline|return book|Aug 23 2020\n" +
                "todo|test content\n";
        recover();
        assertEquals(actual, expected);
    }

    private void makeBackup() throws IOException {
        File f = new File("data/duke.txt");
        File dest = new File("data/duke.bak");
        File test = new File("test/duke/utils/duke-test.txt");
        dest.exists();
        if (f.exists()) {
            Files.copy(f.toPath(), dest.toPath());
        }
        if (f.delete()) {
            Files.copy(test.toPath(), f.toPath());
        }
    }

    private void recover() throws IOException {
        File dest = new File("data/duke.bak");
        File f = new File("data/duke.txt");
        if (f.delete()) {
            Files.copy(dest.toPath(), f.toPath());
        }
        dest.delete();
    }
}