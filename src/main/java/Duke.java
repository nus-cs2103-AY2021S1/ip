import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> items;
    private static final String DONE = "done";
    private static final String BYE = "bye";
    private static final String LIST = "list";


    public static void main(String[] args) {
        String userInput;
        items = new ArrayList<>();

        greeting();

        do {
            userInput = getUserInput();

            if (userInput.equals(BYE)) {
                exit();
            } else if (userInput.equals(LIST)) {
                displayList();
            } else {
                String[] inputArr = userInput.split(" ");

                if (inputArr[0].equals(DONE)) {
                    int itemsIdx = Integer.parseInt(inputArr[1]) - 1;
                    markAsDone(itemsIdx);
                } else {
                    addItem(userInput);
                }
            }

        } while (!userInput.equals(BYE));
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
        System.out.println("Here are the tasks in your list: ");
        for (Task t : items) {
            System.out.println(index + ". " + t);
            index++;
        }
    }

    private static void addItem(String input) {
        items.add(new Task(input));
        String text = "added: " + input;
        System.out.println(text);
    }

    private static void markAsDone(int itemsIdx) {
        if (itemsIdx < 0 || itemsIdx > items.size() - 1) {
            System.out.println("Sorry, task does not exist");
        } else {
            items.get(itemsIdx).setDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(items.get(itemsIdx));
        }
    }

}
