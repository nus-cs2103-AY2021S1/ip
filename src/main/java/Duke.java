import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> tasks;
    
    public Duke() {
        this.tasks = new ArrayList<>();
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
    public String get_output(Task taskInput) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", taskInput.toString(), this.tasks.size());
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
            Task t = tasks.get(i);
            all_tasks += String.format("%d.%s\n", i+1, t.toString());
        }
        return all_tasks;
    }

    public String mark_done(int index) {
        if (!this.tasks.get(index).isDone()) {
            this.tasks.get(index).done();
            return String.format("Nice! I've marked this task as done:\n[✓] %s\n", this.tasks.get(index).getName());
        } else {
            return String.format("Task %d already done!\n", index);
        }
    }

    public String deleteTask(int index) {
        if (tasks.size() <= index) {
            return "No such task\n";
        }
        Task t = this.tasks.get(index);
        this.tasks.remove(index);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n", t.toString(), this.tasks.size());
    }

    public static TaskType categorize(String[] input_parts) throws Exception {
        if (input_parts[0].compareTo("todo") == 0) {
            return TaskType.ToDo;
        } else if (input_parts[0].compareTo("deadline") == 0) {
            return TaskType.Deadline;
        } else if (input_parts[0].compareTo("event") == 0) {
            return TaskType.Event;
        } else {
            throw new UndefinedWordException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    // Can be used to get the details too
    public static String extractTask(String[] input_parts) {
        String task = "";
        for (int i = 1; i < input_parts.length; i++) {
            task += input_parts[i] + " ";
        }
        return task.trim();
    }

    public static String extractDetails(String[] input_parts) {
        String task = "";
        for (int i = 0; i < input_parts.length; i++) {
            task += input_parts[i] + " ";
        }
        return task.trim();
    }
    
    public static Task getTask(String input) throws Exception { // catch duke exception from extractTask and categorize, throw to op()
        String[] parts = input.split(" ");
        TaskType type;
        try {
            type = categorize(parts);
        } catch (UndefinedWordException e) {
            throw e;
        }

        if (type == TaskType.ToDo) {
            parts = input.split(" ");
            if (parts.length <= 1) {
                throw new NoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            } // if not, chillax and continue
            String name = extractTask(parts);
            return new ToDo(name);
        } else if (type == TaskType.Deadline) {
            if (input.split("/by").length <= 1) {
                throw new NoDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
            } // if not, chillax and continue
            String name = extractTask(input.split("/by")[0].split(" "));
            String deadline = extractDetails(input.split("/by")[1].split(" "));
            return new Deadline(name, deadline); 
        } else {
            if (input.split("/at").length <= 1) {
                throw new NoDescriptionException("☹ OOPS!!! The description of an event cannot be empty.\n");
            } // if not, chillax and continue
            String name = extractTask(input.split("/at")[0].split(" "));
            String details = extractDetails(input.split("/at")[1].split(" "));
            
            return new Event(name, details);
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
            } else if (parts[0].compareTo("delete") == 0) {
                System.out.println(format_response(this.deleteTask(Integer.parseInt(parts[1]) - 1))); 
            } else {
                Task taskInput;
                try {
                    taskInput = getTask(input); // catch duke exception from getTask(input)
                } catch (Exception e) {
                    System.out.println(format_response(e.getMessage()));
                    continue;
                }

                // add task to list of tasks
                this.tasks.add(taskInput);
                String output_string = format_response(get_output(taskInput));
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
/*
*/
}
