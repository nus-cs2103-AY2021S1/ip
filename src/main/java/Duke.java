import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String lines = "------------------------------------------------\n";
        System.out.println(lines + "Hello! I'm Duke!\n" + "What can I do for you?\n" + lines);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        ArrayList<String> ls = new ArrayList<>();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(lines);
                for (int i = 0; i < ls.size(); i++) {
                    System.out.println("  " + (i+1) + ". " + ls.get(i) + "\n");
                }
                System.out.println(lines);
                userInput = sc.nextLine();
            } else {
                ls.add(userInput);
                System.out.println(lines + "  added: " + userInput + "\n" + lines);
                userInput = sc.nextLine();
            }
        }

        System.out.println(lines + "    Bye! Hope to see you again soon." + "\n" + lines);

    }
}