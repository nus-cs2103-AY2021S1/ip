import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Clara! :D How may I help you? :)");

        Scanner sc = new Scanner(System.in);
        List<String> commands = new ArrayList<>();

        while (sc.hasNext()) {
            String command = sc.nextLine();

            switch(command) {
                case "bye":
                    System.out.println("\tBye! Have a great day and hope to see you soon! :D");
                    break;
                case "list":
                    for (int i = 0; i < commands.size(); i++) {
                        System.out.println(String.format("\t%d. %s", i+1, commands.get(i)));
                    }
                    break;
                default:
                    commands.add(command);
                    System.out.println("\tadded: " + command);
            }

            if (command.equals("bye")) {
                break;
            }
        }


    }
}
