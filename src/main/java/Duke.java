import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<String> tasks;
    private ArrayList<Integer> is_done;
    private ArrayList<TaskType> types;
    
    enum TaskType {
        ToDo,
        Deadline,
        Event,
    }
    
    public Duke() {
        this.tasks = new ArrayList<>();
        this.is_done = new ArrayList<>();
        this.types = new ArrayList<>();
    }

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
    public String get_output(String user_input) {
        return "added: " + user_input + "\n"; // level 2
    }

    public static String format_response(String output_msg) {
        return 
        "____________________________________________________________\n"+
        output_msg +
        "____________________________________________________________\n";
    }

    public String summarize() {
        String all_tasks = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            String tick = "";
            if (this.is_done.get(i) > 0) {
                tick = "✓";
            } else {
                tick = "✗";
            }

            all_tasks += String.format("%d.[%s] %s\n", i+1, tick, this.tasks.get(i));
        }

        return all_tasks;
    }

    public String mark_done(int index) {
        if (this.is_done.get(index) == 0) {
            this.is_done.set(index, 1);
            return String.format("Nice! I've marked this task as done:\n[✓] %s\n", this.tasks.get(index));
        } else {
            return String.format("Task %d already done!\n", index);
        }
    }

    public static TaskType categorize(String[] input_parts) {
        if (input_parts[0].compareTo("todo") == 0) {
            return TaskType.ToDo;
        } else if (input_parts[0].compareTo("deadline") == 0) {
            return TaskType.Deadline;
        } else {
            return TaskType.Event;
        }
    }

    public static String task_name(String[] input_parts) {
        String t_name = "";
        for (int i = 1; i < input_parts.length; i++) {
            t_name += input_parts[i] + " ";
        }
        return t_name.trim();
    }

    public static String get_task(String input) {
        String[] parts = input.split(" ");
        TaskType type = categorize(parts);
        if (type == TaskType.ToDo) {
            parts = input.split(" ");
            return task_name(parts);
        } else if (type == TaskType.Deadline) {
            String name = task_name(input.split("/")[0].split(" "));
            String deadline = input.split("/")[1];
            return String.format("%s (%s)", name, deadline); // need to insert : after by
        } else {
            String name = task_name(input.split("/")[0].split(" "));
            String time = input.split("/")[1];
            return String.format("%s (%s)", name, time); // need to insert : after by
        }
    }

    public void op() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        
        while (!end) {
            String input = get_input(sc);
            String[] parts = input.split(" ");
            if (input.compareTo("bye") == 0) {
                end = true;
            } else if (input.compareTo("list") == 0) {
                System.out.println(format_response(this.summarize()));
            } else if (parts[0].compareTo("done") == 0) {
                System.out.println(format_response(this.mark_done(Integer.parseInt(parts[1]) - 1))); 
            } else {
                // Determine type of task:
                TaskType type = categorize(parts);

                // add task to list of tasks
                this.tasks.add(get_task(input));
                this.is_done.add(0); // add 0 for false
                this.types.add(type); // Add type

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

        Duke hal9000 = new Duke();

        hal9000.op();

    }
}
