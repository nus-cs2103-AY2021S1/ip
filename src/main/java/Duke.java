import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "----------------------------------------------------";
        String botName = "Duke";
        String userName = "You";
        String exitStr = "bye";

        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        System.out.println("Hello, I'm\n" + logo + "\nWhat can I do for you?\n" + line);

        System.out.print(userName + " said: ");

        userInput = scanner.nextLine();

        while (!userInput.equals(exitStr)) {
            System.out.println(line);
            System.out.println(botName + " said: " + userInput);
            System.out.println(line);
            System.out.print(userName + " said: ");
            userInput = scanner.nextLine();
        }

        System.out.println(line);
        System.out.println(botName + " said: Bye! Hope to see you again!");
        System.out.println(line);
    }
}
