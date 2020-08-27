import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public void load_tasks(File prev_tasks) {
        try {
            Scanner sc_file = new Scanner(prev_tasks);
            while (sc_file.hasNextLine()) {
                String[] saved_task = sc_file.nextLine().split("~");
                
                Task new_task = getTask(saved_task[1]);
                if (saved_task[0].compareTo("T") == 0) {
                    new_task.done();
                }
                this.tasks.add(new_task);
            }
            sc_file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void save_task(File prev_tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(prev_tasks));
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).getFileString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
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
        sc.close();
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
        
        // Read and load all previous tasks
        try {
            File prev_tasks = new File("prev_tasks.txt");
            if (!prev_tasks.exists()) {
                prev_tasks.createNewFile();
            }
            prev_tasks.setReadable(true);
            prev_tasks.setWritable(true);
            hal9000.load_tasks(prev_tasks);
            
            // Operate hal9000
            hal9000.op();

            // AFter ending, save all remaining tasks
            hal9000.save_task(prev_tasks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
/*
*/
}
