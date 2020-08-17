import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<String> items;

    public static void main(String[] args) {
        String userInput;
        items = new ArrayList<>();

        greeting();

        do {
            userInput = getUserInput();

            if (userInput.equals("bye")) {
                exit();
            } else if (userInput.equals("list")) {
                displayList();
            } else {
                addItem(userInput);
            }

        } while (!userInput.equals("bye"));
    }

    public static void greeting() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static void displayList() {
        int size = items.size();
        int index = 1;
        for (String s : items) {
            System.out.println(index + ". " + s);
            index++;
        }
    }

    private static void addItem(String input) {
        items.add(input);
        String text = "added: " + input;
        System.out.println(text);
    }

}
