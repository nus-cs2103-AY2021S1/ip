import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StorageTest {
    @Test
    public void emptyFileTest() {
        Storage store = new Storage("data/new.txt");
        ArrayList<Task> ls = store.loadFile();
        assertEquals(new ArrayList<>(), ls);
    }

    @Test
    public void saveAndLoadTest() {
        File file = new File("data/test.text");
        file.delete();
        Storage store = new Storage("data/test.txt");
        ArrayList<Task> ls = new ArrayList<>();
        ls.add(new ToDo("dance"));
        store.saveFile(new TaskList(ls));


        assertEquals("✘ ✏ dance  \n", new TaskList(store.loadFile()).toString());
    }
}