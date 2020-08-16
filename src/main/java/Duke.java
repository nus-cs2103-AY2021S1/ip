import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void say(String text) {
        System.out.println("Duke: " + text);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> todoList = new ArrayList<String>();

        String greetingText = "Hey There! What can I do for you?";
        say(greetingText);

        String response = scanner.nextLine();
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                int count = 1;
                for (String task : todoList) {
                    System.out.println(count + ". " + task);
                    count++;
                }
            } else {
                say("Added '" + response + "' to the to-do list.");
                todoList.add(response);
            }
            response = scanner.nextLine();
        }

        String byeText = "Bye! Hope to see you again.";
        say(byeText);
    }
}
