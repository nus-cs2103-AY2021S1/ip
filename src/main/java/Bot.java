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
            if (input.equals("list")) {
                displayList();
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task task = taskList.get(index);
                task.markAsDone();
                giveResponse("Nice! I've marked this task as done:\n       " + task);
            } else {
                addTask(input);
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
    private void addTask(String command) {
        Task newTask;
        if (command.startsWith("todo")) {
            newTask = new Todo(command.substring(5));
        } else if (command.startsWith("deadline")) {
            String description = command.substring(9);
            int index = description.indexOf("/by");
            String by = description.substring(index + 4);
            description = description.substring(0, index - 1);
            newTask = new Deadline(description, by);
        } else if (command.startsWith("event")) {
            String description = command.substring(6);
            int index = description.indexOf("/at");
            String at = description.substring(index + 4);
            description = description.substring(0, index - 1);
            newTask = new Event(description, at);
        } else {
            newTask = new Task(command);
        }
        taskList.add(newTask);
        giveResponse("Got it. I've added this task:\n       " +
                newTask +
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

