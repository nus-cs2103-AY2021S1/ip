import java.util.Scanner;

public class Bot {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String prompt = "What can I do for you?";
    static String farewellMsg = "Bye. Hope to see you again soon!";
    static String indentation = "    ";
    static String demarcation = Bot.indentation + "---------------";

    public void interact() {
        System.out.println("Hello from\n" + Bot.logo);
        System.out.println(Bot.prompt);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.next();
            if (isBye(command)) break;

            // Response from Duke
            System.out.println(Bot.demarcation);
            System.out.println(indentWord(command));
            System.out.println(Bot.demarcation);
        }
        System.out.println(farewellMsg);
        return;
    }

    private boolean isBye(String command) {
        return command.equals("bye");
    }

    private String indentWord(String word) {
        return Bot.indentation + word;
    }
}
