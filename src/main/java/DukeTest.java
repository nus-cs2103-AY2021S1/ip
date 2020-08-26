import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void Test1() {
        try {
            String dir = System.getProperty("user.dir");
            String textDir = dir + "/data/test1.txt";
            Storage file = new Storage(textDir);
            Todo test = new Todo("read book");
            ArrayList<Task> arr = file.load();
            assertEquals(arr.get(0).toString(), (new Todo("read book")).toString());
        } catch (IOException e) {
            System.out.println("Fail");
        }
    }

    @Test
    public void Test2() {
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(new Task("Hello World!"));
        System.out.println(arr.get(0));
    }
}
