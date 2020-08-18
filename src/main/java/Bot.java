import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// The bot that handles inputs and give responses
public class Bot {
    private static final String divider = "\t____________________________________________________________\n";
    // Before A-TextUiTesting, scanner is defined in the getInput() method,
    // and everything is fine if Duke.java is run directly in terminal.
    // However, it might cause issues when we use script to automate text ui testing (I find that out when I implement TextUiTesting),
    // From A-TextUiTesting onward, scanner is defined outside of the getInput() method.

    Scanner sc = new Scanner(System.in);
    List<Task> taskList = new LinkedList<>();

    //start the bot
    public void start() {
        String greeting = "Hello! I'm Duke\n" +
                "\t What can I do for you?";
        giveResponse(greeting);
        String input = getInput();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    displayList();
                } else if (input.startsWith("done")) {
                    completeTask(input);
                } else if (input.startsWith("delete")){
                    deleteTask(input);
                } else {
                    addTask(input);
                }
            } catch (DukeException e) {
                giveResponse(e.getMessage());
            }

            input = getInput();
        }
        giveResponse("Bye. Hope to see you again soon!");
    }

    //print out the response
    private void giveResponse(String response) {
        System.out.println(divider + "\t " + response + "\n" + divider);
    }

    //get the next input
    private String getInput() {
        return sc.nextLine();
    }

    // add a task with a given command
    private void addTask(String command) throws DukeException {
        Task newTask;
        if (command.startsWith("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            newTask = new Todo(command.substring(5));
        } else if (command.startsWith("deadline")) {
            if (command.length() <= 9) {
                throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            }
            String description = command.substring(9);
            int index = description.indexOf("/by");
            if (index == -1 || description.length() - index <= 4 ) {
                throw new DukeException("\u2639 OOPS!!! I don't know when the deadline is");
            }
            String by = description.substring(index + 4);
            description = description.substring(0, index - 1);
            newTask = new Deadline(description, by);
        } else if (command.startsWith("event")) {
            if (command.length() <= 6) {
                throw new DukeException("\u2639 OOPS!!! The description of a event cannot be empty.");
            }
            String description = command.substring(6);
            int index = description.indexOf("/at");
            if (index == -1 || description.length() - index <= 4 ) {
                throw new DukeException("\u2639 OOPS!!! I don't know when the event take place");
            }
            String at = description.substring(index + 4);
            description = description.substring(0, index - 1);
            newTask = new Event(description, at);
        } else {
            throw new DukeException();
        }
        taskList.add(newTask);
        giveResponse("Got it. I've added this task:\n       " +
                newTask +
                "\n\t Now you have " + taskList.size() +
                " task" + (taskList.size() > 1 ? "s" : "") + " in the list.");
    }

    // mark a task as completed
    private void completeTask(String command) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("\u2639 OOPS!!! I don't know which task should be marked as completed.");
        }
        int index;
        try {
            index = Integer.parseInt(command.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 OOPS!!! The task index should be a number.");
        }

        if (index < 0) {
            throw new DukeException("\u2639 OOPS!!! The task index should be a positive number.");
        }
        Task task = taskList.get(index);
        task.markAsDone();
        giveResponse("Nice! I've marked this task as done:\n       " + task);
    }

    // delete a task
    private void deleteTask(String command) throws DukeException {
        if (command.length() <= 7) {
            throw new DukeException("\u2639 OOPS!!! I don't know which task should be deleted.");
        }
        int index;
        try {
            index = Integer.parseInt(command.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 OOPS!!! The task index should be a number.");
        }

        if (index < 0) {
            throw new DukeException("\u2639 OOPS!!! The task index should be a positive number.");
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        giveResponse(" Noted. I've removed this task:\n       " +
                task +
                "\n\t Now you have " + taskList.size() +
                " task" + (taskList.size() > 1 ? "s" : "") + " in the list.");
    }

    // display the task list
    private void displayList(){
        String list = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.size(); i++) {
            list += "\t " + i + ". " + taskList.get(i - 1) + "\n";
        }

        //remove the extra "\n"
        if(!list.isEmpty()) {
            list = list.substring(0, list.length() - 1);
        }

        giveResponse(list);
    }
}

