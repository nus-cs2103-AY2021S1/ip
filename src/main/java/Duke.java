import Task.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // Opening
        String open = "_______________________________________ \n"
                + "Hello! I'm Duke \n"
                + "What can I do for you? \n"
                + "_______________________________________ \n";
        String line = "_______________________________________\n";
        System.out.println(open);

        ArrayList<Task> user_list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String user_input = scanner.nextLine();
            String[] input_split = user_input.split(" ", 2);
            if (user_input.equals("bye")) {  // For exiting the program
                break;

            } else if (user_input.equals("list")){  // For viewing items in to do list
                String output = "";
                for (int i = 1; i <= user_list.size(); i++) {
                    output = output + i + ". " + user_list.get(i - 1) + "\n";
                }
                System.out.println(line + "Here are the tasks in your list: \n" + output + line);

            } else if (input_split[0].equals("done")) {  // For marking items in the to do list as done
                try {
                    int task_id = Integer.parseInt(input_split[1]);
                    if (task_id <=0 || task_id > user_list.size()) {
                        System.out.println(line + "Invalid input! That task does not exist! \n" + line);
                    } else {
                        user_list.get(task_id - 1).setCompleted();
                        System.out.println(line + "Nice! I've marked this task as done: \n"
                                + user_list.get(task_id - 1) + "\n" + line);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify which task you have completed! \n" + line);
                }

            } else if (input_split[0].equals("todo")) { // Add new todo
                try {
                    user_list.add(new ToDo(input_split[1]));
                    String output = line + "Got it. I've added this task: \n"
                            + user_list.get(user_list.size() - 1) + "\n"
                            + "Now you have " + user_list.size() + " tasks in the list."
                            + "\n" + line;
                    System.out.println(output);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify your todo description! \n" + line);
                }

            } else if (input_split[0].equals("deadline")) { // Add new deadline
                try {
                    String[] details = input_split[1].split(" /by ", 2);
                    user_list.add(new Deadline(details[0], details[1]));
                    String output = line + "Got it. I've added this task: \n"
                            + user_list.get(user_list.size() - 1) + "\n"
                            + "Now you have " + user_list.size() + " tasks in the list."
                            + "\n" + line;
                    System.out.println(output);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify your deadline description and details! \n" + line);
                }

            } else if (input_split[0].equals("event")) { // Add new event
                try {
                    String[] details = input_split[1].split(" /at ", 2);
                    user_list.add(new Event(details[0], details[1]));
                    String output = line + "Got it. I've added this task: \n"
                            + user_list.get(user_list.size() - 1) + "\n"
                            + "Now you have " + user_list.size() + " tasks in the list."
                            + "\n" + line;
                    System.out.println(output);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line + "Invalid input! Please specify your event description and details! \n" + line);
                }

            } else {
                System.out.println(line + "Invalid input! Please try again! \n" + line);
            }
        }

        // Closing
        scanner.close();
        String close = "_______________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "_______________________________________ \n";
        System.out.println(close);
    }
}
