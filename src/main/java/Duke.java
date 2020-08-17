import java.util.Scanner;

public class Duke {
    private static final String line = "----------------------";

    // for the list
    private static final Task[] tasks = new Task[100];
    private static int index = 0;
    private static boolean dukeOn = true; // flag to indicate duke is ready to receive any query

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet(logo);
        Scanner sc = new Scanner(System.in);
        while (dukeOn) {
            String input = sc.nextLine();
            String[] splittedWords = input.split("\\s", 2); // splits string based on whitespace
            String command = splittedWords[0];
            String afterCommand = null;
            if (splittedWords.length > 1) {
                afterCommand = splittedWords[1];
            }

            try {
                askDuke(command, afterCommand);
            } catch (DukeException ex) {
                format(ex.toString());
            }
        }
    }

    // main driver function for duke to tackle commands
    private static void askDuke(String command, String afterCommand) throws DukeException {
        // handle all different commands
        if (command.equals("bye")) { // 1. bye
            exit();
        } else if (command.equals("list")) { // 2. list
            displayList();
        } else if (command.equals("done")){ // 3. done (needs an after command)
            int taskNo = Integer.parseInt(afterCommand) - 1;
            if (taskNo >= index || taskNo < 0) {
                throw new DukeException("Please enter a valid task no!");
            }
            tasks[taskNo].markAsCompleted();
            displayCompletedTask(tasks[taskNo]);
        } else if (command.equals("todo")) { // 4. to do (needs an after command)
            if (afterCommand == null) {
                throw new DukeException("Please do not leave the todo description empty!");
            }
            addOnToList(new ToDo(afterCommand));
        } else if (command.equals("deadline")) { // 5. deadline (needs an after command)
            if (afterCommand == null) {
                throw new DukeException("Please do not leave the deadline description empty!");
            }
            // first chunk is the deadline details, second chunk is by when
            String[] splittedDeadline = afterCommand.split("/");

            // teach the user the format for the deadline
            if (splittedDeadline.length == 1) {
                throw new DukeException("Format of deadline recording: deadline keyword" +
                        ", deadline instructions, forward slash, by keyword with a colon, specific date/time)"
                        + "\n e.g. deadline return book /by Sunday");
            }

            String details = splittedDeadline[0].trim();
            String by = splittedDeadline[1].split("\\s", 2)[1];
            addOnToList(new Deadline(details, by));
        } else if (command.equals("event")) { // 6. event (needs an after command)
            if (afterCommand == null) {
                throw new DukeException("Please do not leave the event description empty!");
            }
            // first chunk is the event details, second chunk is at where
            String[] splittedEvent = afterCommand.split(("/"));

            // teach the user the format for the deadline
            if (splittedEvent.length == 1) {
                throw new DukeException("Format of event recording: event keyword" +
                        ", event instructions, forward slash, at keyword with a colon, start/end time)"
                        + "\n e.g. project meeting /at Mon 2-4pm");
            }

            String details = splittedEvent[0].trim();
            String at = splittedEvent[1].split("\\s", 2)[1];
            addOnToList(new Event(details, at));
        } else {
            // if a bad command is thrown at Duke
            throw new DukeException("Please key in a command I understand!");
        }
    }


    private static void greet(String logo) {
       format("Hello! I'm\n" + logo + "\n" +
                "What can I do for you?");
    }

    private static void format(String input) {
        System.out.println(line + "\n" + input + "\n" + line);
    }

    private static void exit() {
        dukeOn = false;
        format("Bye. Hope to see you again soon!");
    }

    private static void addOnToList(Task task) {
        tasks[index++] = task;
        format("Got it. I've added this task:\n" + task +  "\n"
            + "Now you have " + index + " tasks in the list.");
    }

    private static void displayList() {
        StringBuilder sb = new StringBuilder();
        if (index == 0) {
            sb.append("No tasks!");
            format(sb.toString());
            return;
        }
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < index; i++) {
            sb.append(i + 1 + "." + tasks[i] + "\n");
        }
        format(sb.toString());
    }

    private static void displayCompletedTask(Task task) {
        format("Nice! I've marked this task as done:\n" + task);
    }
}
