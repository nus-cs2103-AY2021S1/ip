import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<String> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String inputLine = sc.nextLine();
        while (!inputLine.equals("bye")) {
            if (inputLine.equals("list")) {
                displayList();
            } else {
                addToDo(inputLine);
            }
            inputLine = sc.nextLine();
        }
        goodbyeMessage();
    }

    private static void hrTag() {
        System.out.println("____________________________________________________________");
    }

    private static void welcomeMessage() {
        hrTag();
        System.out.println("Hello! I am your mother!");
        System.out.println("What can I do for you son!");
        hrTag();
    }

    private static void addToDo(String toDo) {
        hrTag();
        System.out.println("Chore added: " + toDo);
        toDoList.add(toDo);
        hrTag();
    }

    private static void displayList() {
        hrTag();
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println((i+1) + ". " + toDoList.get(i));
        }
        hrTag();
    }

    private static void goodbyeMessage() {
        hrTag();
        System.out.println("Goodbye my child!");
        hrTag();
    }
}
