import Exceptions.DukeException;
import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DukeTest {
    @Test
    public void dummyTest()  {

        try {
            FileWriter fw = new FileWriter("text-ui-test/input.txt");
            fw.write("todo");
            Duke duke = new Duke("input.txt");
            duke.run();
            File f = new File("input.txt");
            Scanner sc = new Scanner(f);
            assertEquals("  '\u2639' OOPS!!! The description of a todo cannot be empty\\n",
                          sc.nextLine());
        }catch (IOException f){

        }
    }
}
