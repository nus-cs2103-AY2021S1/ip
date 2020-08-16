import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String bot = "Dave: \n";
        String user = "Me: \n";
        String addedText = "Added: ";
        ArrayList<String> added = new ArrayList<>();

        //Initial greetings
        System.out.println("_______________________________________________________________");
        System.out.println(bot + "Greetings from me, Dave! \n" + "How can I help you? ^_^");
        System.out.println("_______________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        //Looping and echoing user inputs
        while (scanner.hasNext()) {
            String userInput = scanner.next(); //may be string, int, etc
            if (userInput.equals("bye")) {
                System.out.println("_______________________________________________________________");
                System.out.println(bot + "Goodbye! Hope to see you again soon! ^_^");
                System.out.println("_______________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                if(added.isEmpty()) {
                    System.out.println("");
                } else {
                    System.out.println("_______________________________________________________________");
                    System.out.print(bot);
                    for (int i = 0; i < added.size(); i++) {
                        System.out.println(i + 1 + "." + " " + added.get(i));
                    }
                    System.out.println("_______________________________________________________________");
                }
            } else {
                added.add(userInput);
                System.out.println("_______________________________________________________________");
                System.out.print(bot);
                System.out.println(addedText + userInput);
                System.out.println("_______________________________________________________________");
            }

        }
    }
}
