import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userInput;
        List<String> items = new ArrayList<>();

        greeting();

        do {
            userInput = getUserInput();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (userInput.equals("list")){
                int size = items.size();
                int index = 1;
                for (String s : items) {
                    System.out.println(index + ". " + s);
                    index++;
                }
            } else {
                items.add(userInput);
                String text = "added: " + userInput;
                System.out.println(text);
            }

        } while (!userInput.equals("bye"));
    }

    public static void greeting() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    private static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
