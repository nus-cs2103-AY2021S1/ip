import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class DukeTest {
    @Test
    public void dummyTest(){
        String data = "deadline help /by 23/2/2020 2359";
        InputStream stdin = System.in;
        Duke duke = new Duke("Data/duke.txt");
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            System.out.println(scanner.nextLine());
        } finally {
            System.setIn(stdin);
        }
        duke.run();
    }

}
