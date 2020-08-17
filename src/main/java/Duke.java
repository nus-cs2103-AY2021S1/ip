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
            if (userInput.equals("list")) {
                formatLst();
            } else {
                format("added: " + userInput);
                lst.add(userInput);
            }
            userInput = input.nextLine();
        }

        format("Bye. Hope to see you again soon!");
    }

    public static void format(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void formatLst() {
        System.out.println(line);
        for (int i = 1; i <= lst.size(); i++) {
            System.out.println(i + ". " + lst.get(i-1));
        }
        System.out.println(line);
    }
}
