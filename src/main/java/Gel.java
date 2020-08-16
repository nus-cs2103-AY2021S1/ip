import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Gel {
    public static void keepingList() {
        // initialise list and scanner
        List<String> listOfText = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("    Hello! I'm Gel\n    What can I do for you?\n");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("\n    Bye. Hope to see you again soon!\n");
                break;
            } else if (line.equals("list")){
                System.out.println();
                for (int i = 1; i <= listOfText.size(); i++) {
                    System.out.println("    "+ i + ". " + listOfText.get(i - 1));
                }
                System.out.println();
            } else {
                listOfText.add(line);
                System.out.println("\n    added: " + line + "\n");
            }
        }
    }
}
