import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String open = "_______________________________________ \n"
                + "Hello! I'm Duke \n"
                + "What can I do for you? \n"
                + "_______________________________________ \n";
        String close = "_______________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "_______________________________________ \n";
        String line = "_______________________________________";
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
                    output = output + i + ". " + user_list.get(i-1) + "\n";
                }
                System.out.println(line + "\n Here are the tasks in your list: \n" + output + line);

            } else if (input_split[0].equals("done")) {  // For marking items in the to do list as done
                int task_id = Integer.parseInt(input_split[1]);
                user_list.get(task_id - 1).setCompleted();
                System.out.println(line + "\n" + "Nice! I've marked this task as done: \n"
                        + user_list.get(task_id - 1) + "\n" + line);

            } else {  // For adding new items into to do list
                user_list.add(new Task(user_input));
                System.out.println(line + "\n added: " +user_input + "\n" + line);

            }
        }

        scanner.close();
        System.out.println(close);
    }
}
