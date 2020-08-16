import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
    String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    String indent = "        ";

    public void start() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        System.out.println(indent + "What can I do for you delightful human?\n" + divider);

        loop: while(scanner.hasNext()) {
            String input = scanner.nextLine();
            System.out.println(divider);

            switch(input) {
                case "list":
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(indent + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(divider);
                    break;
                case "bye": // Termination statement
                    System.out.println(indent + "Guess its time for us to part ways\n"
                            + indent + "Thanks for the memories\n" + indent + ":`(");
                    System.out.println(divider);
                    break loop;
                default: // Echo back user input
                    tasks.add(input);
                    System.out.println(indent + "Added: " + input);
                    System.out.println(divider);
            }
        }
    }
}
