package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void load_noFile_throwDukeException() {
        try {
            Storage storage = new Storage("data/storageTest.txt");
            storage.load();
            fail();
        } catch (DukeException e) {
            assertEquals("PROJ_ROOT/data/storageTest.txt not found!", e.getMessage());
        }
    }

    @Test
    public void load_fileExists_correctOutput() {
        try {
            File f = new File("data/storageTest.txt");
            File parent = new File(f.getParent());
            parent.mkdir();
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.write("test\ntest1");
            fw.close();
            Storage storage = new Storage("data/storageTest.txt");
            ArrayList<String> content = storage.load();
            assertEquals(2, content.size());
            assertEquals("test", content.get(0));
            assertEquals("test1", content.get(1));
            f.delete();
            parent.delete();
        } catch (DukeException | IOException e) {
            fail();
        }
    }

    @Test
    public void save_data_wroteDataToFile() {
        try {
            Storage storage = new Storage("data/storageTest.txt");
            ArrayList<String> testList = new ArrayList<>(List.of("test", "test1", "test2"));
            storage.save(testList);
            File f = new File("data/storageTest.txt");
            Scanner sc = new Scanner(f);
            int i = 0;
            while (sc.hasNextLine()) {
                assertEquals(testList.get(i), sc.nextLine());
                i++;
            }
            f.delete();
            new File(f.getParent()).delete();
        } catch (DukeException | FileNotFoundException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
