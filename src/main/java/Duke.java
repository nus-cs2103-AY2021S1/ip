import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static String line = "____________________________________________________________";
    static String userInput = "";
    static ArrayList<String> lst = new ArrayList<>();

    public static void main(String[] args) {
        DukeBot();
    }

    public static void DukeBot() {
        Scanner input = new Scanner(System.in);

        format("Hello! I'm Duke\nWhat can I do for you?");

        userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            format(userInput);
            userInput = input.nextLine();
        }

        format("Bye. Hope to see you again soon!");
    }

    public static void format(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
}
