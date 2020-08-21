import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> todoList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcome = "Hello! I'm Duke!\n" +
                "What can I do for you?";
        System.out.println(logo + "\n" + welcome);
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        while (!word.equals("bye")) {
            if (word.equals("list")) {
                for (Task task : todoList) {
                    System.out.println(task.toString());
                }
            } else {
                System.out.println("added: " + word);
                storeTask(word);
            }
            word = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void storeTask(String item) {
        int count = todoList.size() + 1;
        if (count > 100) {
            System.out.println("You have far too many pending tasks!");
        } else {
            todoList.add(new Task(item, count));
        }
    }
}
