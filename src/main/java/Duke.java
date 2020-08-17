import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line = "    ____________________________________________________________\n";
    static ArrayList<String> todo = new ArrayList<>();

    private static String format(String string) {
        return line + string + "\n" + line;
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equals("bye")) {
            System.out.println(format("     Bye. Hope to see you again soon!"));
        } else if (input.equals("list")) {
            StringBuilder todoList = new StringBuilder();
            for (int i = 1; i < todo.size() + 1; i++) {
                todoList.append("     ").append(i).append(". ").append(todo.get(i - 1)).append("\n");
            }
            System.out.println(format(todoList.toString()));
            echo();
        } else {
            todo.add(input);
            System.out.println(format("added: " + input));
            echo();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(format("     Hello! I'm Duke\n" +
                "     What can I do for you?"));
        echo();
    }
}
