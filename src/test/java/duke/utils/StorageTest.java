package duke.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Scanner;

import duke.tasks.TaskList;

import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;



class StorageTest {

    @Test
    void readSavedFile() throws IOException {
        Label label = new Label();
        TaskList list = new TaskList();
        Storage storage = new Storage(list);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        makeBackup();
        storage.readSavedFile(label);
        String actual = label.getText() + list.printList();
        recover();

        String expected = "Hold a while, trying to retrieve where you were last time...\n"
                + "Great! We have successfully loaded the data. Enjoy~\n"
                + "Here are the tasks in your list:\n"
                + "1.[T][✗] borrow book\n"
                + "2.[D][✗] return book (by: Aug 23 2020)\n";

        assertEquals(expected, actual);

    }

    @Test
    void saveDataToFile() throws IOException {
        Label label = new Label();
        TaskList list = new TaskList();
        Storage storage = new Storage(list);
        makeBackup();
        storage.readSavedFile(label);
        list.addTask("test content", "todo");
        storage.saveDataToFile(label);
        StringBuilder sb = new StringBuilder();
        File f = new File("data/duke.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append("\n");
        }
        String actual = sb.toString();
        String expected = "todo|borrow book\n"
                + "deadline|return book|Aug 23 2020\n"
                + "todo|test content\n";
        recover();
        assertEquals(actual, expected);
    }

    private void makeBackup() throws IOException {
        File f = new File("data/duke.txt");
        File dest = new File("data/duke.bak");
        File test = new File("src/test/java/duke/utils/duke-test.txt");
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
