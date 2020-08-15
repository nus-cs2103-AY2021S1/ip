import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int index = 0;
        String divider = "____________________________________________________________";
        String intro = "Hello! I'm Bob\n" +
                "What can I do for you?\n";

        System.out.println(divider + "\n" + intro + "\n" + divider);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(divider);
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < index; i++) {
                    System.out.println(list[i].id + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
                }
                System.out.println(divider);
            } else if (input.contains("done")) {
                int number = Integer.parseInt(input.substring(5)); // retrieve number after "done "
                Task task = list[number - 1];
                if (task.isDone) {
                    System.out.println("Task already done!");
                } else {
                    task.markedDone();
                    String message = "Good job! I've marked this task as done: ";
                    System.out.println(divider + "\n" + message + "\n" +
                            "   [" + task.getStatusIcon() + "] " + task.description + "\n" + divider);
                }
            } else {
                list[index] = new Task(input, index + 1);
                index++;
                System.out.println(divider + "\n" + "added: " + input + "\n" + divider);
            }
            input = sc.nextLine();
        }

        String message = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}

