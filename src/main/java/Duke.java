import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        int index = 0;
        String divider = "____________________________________________________________";
        String intro = "Hello! I'm Bob\n" +
                "What can I do for you?\n";

        System.out.println(divider + "\n" + intro + "\n" + divider);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(divider);
                if (list.size() == 0) {
                    System.out.println("List is empty, you have free time (for now)! YAY :D");
                } else {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < index; i++) {
                        if (list.get(i) instanceof Todo) {
                            System.out.println(list.get(i).id + ".[T][" + list.get(i).getStatusIcon() + "] " + list.get(i).description);
                        } else if (list.get(i) instanceof Deadline) {
                            System.out.println(list.get(i).id + ".[D][" + list.get(i).getStatusIcon() + "] " + list.get(i).description +
                                    "(" + ((Deadline) list.get(i)).dueDateTime + ")");
                        } else if (list.get(i) instanceof Event) {
                            System.out.println(list.get(i).id + ".[E][" + list.get(i).getStatusIcon() + "] " + list.get(i).description +
                                    "(" + ((Event) list.get(i)).duration + ")");
                        }
                    }
                }
                System.out.println(divider);
            } else if (input.contains("done")) {
                try {
                    int number = Integer.parseInt(input.substring(5)); // retrieve number after "done "
                    Task task = list.get(number - 1);
                    if (task.isDone) {
                        System.out.println("Task already done!");
                    } else {
                        task.markedDone();
                        String message = "Good job! I've marked this task as done: ";
                        System.out.println(divider + "\n" + message + "\n" +
                                "   [" + task.getStatusIcon() + "] " + task.description + "\n" + divider);
                    }
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(divider);
                    System.out.println("Whoops, you did not enter a valid number.");
                    System.out.println(divider);
                }
            } else {
                String addedMessage = "Got it. I've added this task: ";
                String totalMessage = "Now you have " + (index + 1) + " tasks in the list.";

                if (input.contains("todo")) {
                    try {
                        String description = input.substring(5);

                        System.out.println(divider + "\n" + addedMessage);
                        list.add(index, new Todo(description, index + 1));
                        System.out.println(list.get(index));
                        System.out.println(totalMessage + "\n" + divider);
                        index++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(divider);
                        System.out.println("Whoops, you did not fill in the details of the Todo properly :(");
                        System.out.println(divider);
                    }
                } else if (input.contains("deadline")) {
                    try {
                        String[] info = input.split("/");
                        String description = info[0].substring(9);
                        String dueDateTime = info[1];

                        System.out.println(divider + "\n" + addedMessage);
                        list.add(index, new Deadline(description, index + 1, dueDateTime));
                        System.out.println(list.get(index));
                        System.out.println(totalMessage + "\n" + divider);
                        index++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(divider);
                        System.out.println("Whoops, you did not fill in the details of the Deadline properly :(");
                        System.out.println(divider);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(divider);
                        System.out.println("Whoops, you did not fill in the due date/time of the Deadline.");
                        System.out.println(divider);
                    }
                } else if (input.contains("event")) {
                    try {
                        String[] info = input.split("/");
                        String description = info[0].substring(6);
                        String duration = info[1];

                        System.out.println(divider + "\n" + addedMessage);
                        list.add(index, new Event(description, index + 1, duration));
                        System.out.println(list.get(index));
                        System.out.println(totalMessage + "\n" + divider);
                        index++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(divider);
                        System.out.println("Whoops, you did not fill in the details of the Event properly :(");
                        System.out.println(divider);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(divider);
                        System.out.println("Whoops, you did not fill in the duration of the Event.");
                        System.out.println(divider);
                    }
                } else {
                    System.out.println(divider);
                    System.out.println("Sorry, you did not enter a valid command! Please try again.");
                    System.out.println(divider);
                }
            }
            input = sc.nextLine();
        }

        String message = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}

