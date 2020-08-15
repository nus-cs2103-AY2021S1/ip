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
                    if (list[i] instanceof Todo) {
                        System.out.println(list[i].id + ".[T][" + list[i].getStatusIcon() + "] " + list[i].description);
                    } else if (list[i] instanceof Deadline) {
                        System.out.println(list[i].id + ".[D][" + list[i].getStatusIcon() + "] " + list[i].description +
                            "(" + ((Deadline) list[i]).dueDateTime + ")");
                    } else if (list[i] instanceof Event) {
                        System.out.println(list[i].id + ".[E][" + list[i].getStatusIcon() + "] " + list[i].description +
                                "(" + ((Event) list[i]).duration + ")");
                    }
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
                String addedMessage = "Got it. I've added this task: ";
                String totalMessage = "Now you have " + (index + 1) + " tasks in the list.";

                if (input.contains("todo")) {
                    String description = input.substring(5);

                    System.out.println(divider + "\n" + addedMessage);
                    System.out.println(list[index] = new Todo(description, index + 1));
                    System.out.println(totalMessage + "\n" + divider);
                } else if (input.contains("deadline")) {
                    String[] info = input.split("/");
                    String description = info[0].substring(9);
                    String dueDateTime = info[1].substring(0, 2) + ":" +
                            info[1].substring(2);

                    System.out.println(divider + "\n" + addedMessage);
                    System.out.println(list[index] = new Deadline(description, index + 1, dueDateTime));
                    System.out.println(totalMessage + "\n" + divider);
                } else if (input.contains("event")) {
                    String[] info = input.split("/");
                    String description = info[0].substring(6);
                    String duration = info[1].substring(0, 2) + ":" +
                            info[1].substring(2);

                    System.out.println(divider + "\n" + addedMessage);
                    System.out.println(list[index] = new Event(description, index + 1, duration));
                    System.out.println(totalMessage + "\n" + divider);
                }
                index++;
            }
            input = sc.nextLine();
        }

        String message = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}

