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

        //commands
        String exitStr = "bye";
        String listStr = "list";

        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        System.out.println("привет, меня зовут \n" + logo + "\n\n" +
                "Что ты хочешь?\n" + line);

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
        System.out.println(botName + " said: \n" +
                "Пока, надеюсь никогда больше не увидеть тебя!");
        System.out.println(line);
    }
}
