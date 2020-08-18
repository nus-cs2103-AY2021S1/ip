import java.util.Scanner;

public class Duke {

    public static void greeting() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
    }

    public static void printAllTask(Task[] tasks) {
        int numTask = 0;
        System.out.println("Here are the tasks in your list:");
        while (tasks[numTask] != null) {
            System.out.println( Integer.valueOf(numTask + 1) + ".[" + tasks[numTask].getStatusIcon() + "] "
                    + tasks[numTask]);
            numTask++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTask = 0;

        greeting();
        String input;
        String[] inputArr;
        while(true) {
            input = sc.nextLine();
            inputArr = input.split(" ");
            if (inputArr[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (inputArr[0].equals("list")) {
                printAllTask(tasks);
            } else if (inputArr[0].equals("done")) {
                String counter = inputArr[1];
                int pos = Integer.parseInt(counter);
                tasks[pos - 1].markAsDone();
            } else {
                tasks[numTask] = new Task(input);
                System.out.println("added: " + tasks[numTask]);
                numTask++;
            }
        }
    }
}
