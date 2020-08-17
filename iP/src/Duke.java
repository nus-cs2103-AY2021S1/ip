import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(
            "Hello! I'm Duke\n" +
            "What can I do for you?");

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String user_input = "";
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (true) {
            user_input = scanner.nextLine();  // Read user input
            if (user_input.equals("bye")) {
                // quit
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (user_input.equals("list")) {
                // list task
                System.out.println("Here are the tasks in your list:");
                int index = 1;
                for (Task task : taskList) {
                    System.out.println(String.format("%s. %s", index, task));
                    index += 1;
                }

            } else if (user_input.split(" ")[0].equals("done")) {
                // mark done
                int index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                Task chosenTask = taskList.get(index);
                chosenTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(chosenTask);

            } else if (user_input.split(" ")[0].equals("todo")) {
                // make to do
                String description = user_input.split(" ", 2)[1];
                Task todo = new Todo(description);
                taskList.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));

            } else if (user_input.split(" ")[0].equals("deadline")) {
                // make deadline
                String task = user_input.split(" ", 2)[1];
                String description = task.split("/by")[0];
                String time = task.split("/by")[1];
                Task deadline = new Deadline(description, time);
                taskList.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));

            } else if (user_input.split(" ")[0].equals("event")){
                // make event
                String task = user_input.split(" ", 2)[1];
                String description = task.split("/at")[0];
                String time = task.split("/at")[1];
                Task event = new Event(description, time);
                taskList.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));

            } else {
                // add task
                Task task = new Task(user_input);
                taskList.add(task);
                System.out.println(String.format("added: %s", user_input));  // Output user input
            }
        }
    }
}
