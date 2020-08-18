import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<>();


        intro();

        String input = "";
        do {
            try {
                input = sc.nextLine();
                process(input);
            } catch (DukeException de) {
                println(de.getMessage());
            }
        } while (!(input.equals("bye") || input.equals("exit")));

    }

    /**
     * Prints output enclosed in top and bottom horizontal lines
     * @param message message to be output
     */
    private static void println(String... message) {
        System.out.println("\t____________________________________________________________");
        for (String s : message) {
            System.out.println("\t" + s);
        }
        System.out.println("\t____________________________________________________________");
    }

    private static void printAddTask(Task t) {
        println(
                "Got it. I've added this task: ", t.toString(),
                "Now you have " + taskList.size() + " tasks in the list."
        );
    }

    private static void intro() {
        println("Hello! I'm KING!", "What can I do for you?");
    }

    private static void exit() {
        println("Bye. Hope to see you again soon!");
    }

    private static void list() {
        if (taskList.size() == 0) {
            println("I can't find any task in your list...", "Try adding some task using \"todo\", \"deadline\" and \"event\" command");
            return;
        }

        String[] output = new String[taskList.size() + 1];
        output[0] = " Here are the tasks in your list:";

        for (int i = 1; i <= taskList.size(); i++) {
            output[i] = i + "." + taskList.get(i-1);
        }

        println(output);
    }

    private static void done(String num) {
        try {
            int selected = Integer.parseInt(num.strip());
            taskList.get(selected-1).setDone();

            println("Nice! I've marked this task as done: ", taskList.get(selected-1).toString() );
        } catch (NumberFormatException nfe) {
            throw new DukeException("This is not a number for \"done\" command: " + num);
        } catch (IndexOutOfBoundsException iooob) {
            throw new DukeException("I cannot check this element: " + num);
        }
    }

    private static void todo(String todo) {
        if (todo.isBlank())
            throw new DukeException("The description of \"todo\" cannot be empty");
        ToDo todo1 = new ToDo(todo.strip());
        taskList.add(todo1);
        printAddTask(todo1);
    }

    private static void deadline(String dl) {
        String[] split = dl.split("/by");
        if (split.length == 1)
            throw new DukeException("I can't find the \"/by\" keyword...");
        if (split[0].isBlank() || split[1].isBlank())
            throw new DukeException("The description or deadline of \"deadline\" cannot be empty");
        Deadline deadline = new Deadline(split[0].strip(), split[1].strip());
        taskList.add(deadline);
        printAddTask(deadline);
    }

    private static void event(String ev) {
        String[] split = ev.split("/at");
        if (split.length == 1)
            throw new DukeException("I can't find the \"/at\" keyword...");
        if (split[0].isBlank() || split[1].isBlank())
            throw new DukeException("The description or date of \"event\" cannot be empty");
        Event event = new Event(split[0].strip(), split[1].strip());
        taskList.add(event);
        printAddTask(event);
    }

    private static void delete(String num) {
        try {
            int selected = Integer.parseInt(num.strip());
            Task task = taskList.remove(selected-1);

            println( "Noted. I've removed this task: ", task.toString(),
                    "Now you have " + taskList.size() + " tasks in the list." );
        } catch (NumberFormatException nfe) {
            throw new DukeException("This is not a number for \"delete\" command: " + num);
        } catch (IndexOutOfBoundsException iooob) {
            throw new DukeException("I cannot delete this element: " + num);
        }
    }

    private static void process(String msg) {
        int idx = msg.indexOf(' ');
        CommandList command;

        try {
            command = (idx > 0) ? CommandList.valueOf(msg.substring(0, idx)) : CommandList.valueOf(msg);
        } catch (IllegalArgumentException iae) {
            throw new DukeException("This is not in my command list");
        }

        switch (command) {
            case bye:
            case exit:
                exit();
                break;
            case list:
                list();
                break;
            case todo:
                todo(msg.substring(4));
                break;
            case deadline:
                deadline(msg.substring(8));
                break;
            case event:
                event(msg.substring(5));
                break;
            case done:
                done(msg.substring(4));
                break;
            case delete:
                delete(msg.substring(6));
                break;
        }
    }
}
