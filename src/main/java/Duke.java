import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you today? (type: \"help\" to view list of commands)\n" +
                "=========================================================================");

        Scanner scanner = new Scanner(System.in);
        List<String> pastInputs = new ArrayList<>();
        boolean terminated = false;

        while (!terminated) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                terminated = true;
                System.out.println("Duke says: Goodbye and have a nice day! :D");
                scanner.close();
            } else if (userInput.equals("help")) {
                System.out.println("list: displays a sequential view of past inputs\n" +
                        "bye: terminates program");
            } else if (userInput.equals("list")){
                if (pastInputs.size() == 0) {
                    System.out.println("Duke says: No past inputs found");
                } else {
                    for (int i = 1; i <= pastInputs.size(); i++) {
                        System.out.println(i + ". " + pastInputs.get(i - 1));
                    }
                }
            } else {
                pastInputs.add(userInput);
                System.out.println("Duke added: " + userInput);
            }
            System.out.println("=========================================================================");
        }
    }
}

