import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hi! I'm Duke" + "\n" + "What can I do for you?");
        String current = scanner.nextLine();
        List<String> storedText = new ArrayList<>();

        while (isNotTerminateCommand(current)) {
            if (!isListCommand(current) && !isDoneCommand(current)) {
                storedText.add("[✗] " +  current);
                System.out.println("added: " + current);
            } else if (isDoneCommand(current)) {
            //Mark item

                //Getting the index
                String[] parts = current.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;

                if (index >= storedText.size() || index < 0) {
                    System.out.println("Sorry, invalid command!");
                } else {
                    storedText = markDone(storedText, index);
                    System.out.println("Nice! I've marked this task as done:" + "\n" + storedText.get(index));
                }
            } else {
                System.out.println(readList(storedText));
            }
                current = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static boolean isDoneCommand(String input) {
        String[] parts = input.split(" ");
        return parts[0].equals("done");
    }

    public static boolean isListCommand(String input) {
        return input.equals("list");
    }

    public static boolean isNotTerminateCommand(String input) {
        return !input.equals("bye");
    }

    public static List<String> markDone(List<String> list, int position) {
        String doneTask = list.get(position);
        String[] parts = doneTask.split(" ", 2);
        String markDone = "[✓] " + parts[1];

        list.set(position, markDone);
        return list;
    }

    public static String readList(List<String> list) {
        String listOfItems = "";

        for (int i = 1; i <= list.size(); i++) {
            listOfItems = listOfItems + i + ". " + list.get(i - 1) + "\n";
        }

        return listOfItems;
    }
}

