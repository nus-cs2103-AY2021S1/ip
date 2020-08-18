import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! I'm Duke" + "\n" + "What can I do for you?");
        String current = scanner.nextLine();
        List<String> storedText = new ArrayList<>();

        while (!current.equals("bye")) {
            if (!current.equals("list")) {
                storedText.add(current);
                System.out.println("added: " + current);
            } else {
                System.out.println(readList(storedText));
            }
            current = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static String readList(List<String> list) {
        String listOfItems = "";

        for (int i = 1; i <= list.size(); i++) {
            listOfItems = listOfItems + i + ". " + list.get(i - 1) + "\n";
        }

        return listOfItems;
    }
}

