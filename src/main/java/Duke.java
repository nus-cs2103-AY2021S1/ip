import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm duke. What can I do for you? \n" + logo);

        ArrayList<String> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            line();
            if (str.equals("list")) {
                displayTasks(tasks);
            } else {
                addTask(str, tasks);
            }
            line();
            str = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public static void line() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2500");
        }
        System.out.println("");
    }

    private static void displayTasks(ArrayList<String> tasks) {
        int i = 1;
        for (String str: tasks) {
            System.out.println(i + ". " + str);
            i++;
        }
    }

    private static void addTask(String str, ArrayList<String> tasks) {
        tasks.add(str);
        System.out.println("added: " + str);
    }

}
