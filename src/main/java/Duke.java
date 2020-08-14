import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void respondStart() {
        System.out.println("    ----------------------------------------");
        System.out.println("     Hellowww!! I'm Alexa, your personal todo manager!");
        System.out.println("     How can I help you today?");
        System.out.println("    ----------------------------------------");
    }

    public static void respondClose() {
        System.out.println("     ----------------------------------------");
        System.out.println("     Bye? I hope it's not forever! Come back soon!");
        System.out.println("     ----------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        List<String> todoList = new ArrayList<>();

        // Flow starts here
        respondStart();

        while (sc.hasNextLine()) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }

            System.out.println("    ----------------------------------------");

            if (command.equals("list")) {
                for(int i = 0; i < todoList.size(); i++) {
                    System.out.printf("    %d. %s%n", i + 1, todoList.get(i));
                }
            } else {
                // add instructions
                todoList.add(command);
                System.out.printf("     added: %s%n", command);
            }

            System.out.println("    ----------------------------------------");
        }

        respondClose();
    }
}
