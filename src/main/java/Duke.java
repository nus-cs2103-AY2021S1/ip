import java.util.Scanner;

public class Duke {

    public static void greeting() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
    }

    public static void printAllTask(Task[] tasks) {
        int numTask = 0;
        while (tasks[numTask] != null) {
            System.out.println( Integer.valueOf(numTask + 1) + ". " + tasks[numTask]);
            numTask++;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTask = 0;

        greeting();
        String input;
        while(true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (input.equals("list")) {
                printAllTask(tasks);
            } else {
                tasks[numTask] = new Task(input);
                System.out.println("added: " + tasks[numTask]);
                numTask++;
            }
        }
    }
}
