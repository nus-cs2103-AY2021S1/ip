import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatterbox {
    private static final String SEPARATOR = "++++++++++++++++++++++++++++++++++++++++++++++++++++++";
    private static final List<String> ITEMS = new ArrayList<>();

    private static String format(String s) {
        return SEPARATOR + "\n" + s + "\n" + SEPARATOR;
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Chatterbox. What can I do for you?");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                if (ITEMS.size() != 0) {
                    StringBuilder fullList = new StringBuilder("\n");
                    for (int i = 0; i < ITEMS.size(); i++) {
                        fullList.append(i + 1).append(". ").append(ITEMS.get(i)).append("\n");
                    }
                    System.out.println(format(fullList.toString()));
                } else {
                    System.out.println(format("Your list is currently empty"));
                }
            } else {
                ITEMS.add(input);
                System.out.println(format("added: " + input));
            }
            input = s.nextLine();
        }

        System.out.println(format("Goodbye! Hope to see you again soon!"));
    }
}

