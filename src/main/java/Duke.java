import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> todoList = new ArrayList<>();

        String greeting = "Hello I'm Duke, your favourite chatbot! \n"
                + "   What can I do for you? ";
        System.out.println(formatPrint(greeting));

        while (sc.hasNextLine()) {
            String input = sc.nextLine().strip().toLowerCase();

            switch (input) {
                case "bye":
                    System.out.println(formatPrint("Bye! Hope to see you again soon! "));
                    return;
                case "list":
                    System.out.println(formatPrint(formatList(todoList)));
                    break;
                default:
                    todoList.add(input);
                    System.out.println(formatPrint("Added: " + input));
                    break;
            }
        }

        sc.close();
    }

    private static String formatPrint(String str) {
        String divider = "  ---------------------------- \n   ";
        return divider + str + "\n" + divider;
    }

    private static String formatList(List<String> list) {
        String output = "";
        int count = list.size();
        for (int i = 0; i < count; i++) {
            output += String.valueOf(i + 1) + ". " + list.get(i) + "\n   ";
        }
        return output.strip();
    }
}
