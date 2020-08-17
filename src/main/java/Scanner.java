import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scanner {
    static void scan() throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader((System.in)));
        ItemList userItemList = new ItemList();

        scanLoop:
        while(true) {
            String line = reader.readLine();
            switch(line) {
                case "bye":
                    System.out.println("Bye. Hope to see you again");
                    break scanLoop;
                case "list":
                    userItemList.printList();
                    break;
                default:
                    userItemList.addItem(line);
                    System.out.println("added: " + line);
                    break;
            }
        }
    }
}
