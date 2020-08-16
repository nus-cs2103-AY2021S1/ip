import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    /**
     * get_input() is in charge of asking for input from user
     * and return the input as a string to be used for something else.
     * @param sc Scanner object
     * @return a string representing user input
     */
    public static String get_input(Scanner sc) {
        String user_input = sc.nextLine().trim(); // Read user input
        return user_input;
    }

    /**
     * get_output(String) decides what Hal's output should be
     * based on user's input.
     * @param user_input input from user as String
     * @return output by Hal9000
     */
    public static String get_output(String user_input) {
        return "added: " + user_input + "\n"; // level 2
    }

    public static String format_response(String output_msg) {
        return 
        "____________________________________________________________\n"+
        output_msg +
        "____________________________________________________________\n";
    }

    public static String summarize(ArrayList<String> tasks, ArrayList<Integer> is_done) {
        String all_tasks = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            String tick = "";
            if (is_done.get(i) > 0) {
                tick = "✓";
            } else {
                tick = "✗";
            }

            all_tasks += String.format("%d.[%s] %s\n", i+1, tick, tasks.get(i));
        }

        return all_tasks;
    }

    public static String mark_done(ArrayList<String> tasks, ArrayList<Integer> is_done, int index) {
        if (is_done.get(index) == 0) {
            is_done.set(index, 1);
            return String.format("Nice! I've marked this task as done:\n[✓] %s\n", tasks.get(index));
        } else {
            return String.format("Task %d already done!\n", index);
        }
    }

    public static void hal9000_op() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        ArrayList<Integer> is_done = new ArrayList<>();
        while (!end) {
            String input = get_input(sc);
            String[] parts = input.split(" ");
            if (input.compareTo("bye") == 0) {
                end = true;
            } else if (input.compareTo("list") == 0) {
                System.out.println(format_response(summarize(tasks, is_done)));
            } else if (parts[0].compareTo("done") == 0) {
                System.out.println(format_response(mark_done(tasks, is_done, Integer.parseInt(parts[1]) - 1))); 
            } else {
                // add task to list of tasks
                tasks.add(input);
                is_done.add(0); // add 0 for false

                String output_string = format_response(get_output(input));
                System.out.println(output_string);
            }
        }
        System.out.println(format_response("Bye. Hope to see you again soon!\n"));
    }
    
    public static void main(String[] args) {
        String logo =
"   __ _____   __  ___  ___  ___  ___\n" +
"  / // / _ | / / / _ \\/ _ \\/ _ \\/ _ \\\n" +
" / _  / __ |/ /__\\_, / // / // / // /\n" +
"/_//_/_/ |_/____/___/\\___/\\___/\\___/\n";
                                     
        // Intro message
        System.out.println(logo);
        System.out.println(format_response(
            "Hello! I'm Hal9000\nWhat can I do for you?\n"
        ));

        hal9000_op();

    }
}
