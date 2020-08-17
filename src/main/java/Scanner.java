import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scanner {
    static void scan() throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader((System.in)));

        while(true) {
            String line = reader.readLine();
            // when ctrl-D is pressed, reader returns null
            if (line == null) {
                break;
            }
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again");
                break;
            }

            System.out.println(line);
        }
    }

}
