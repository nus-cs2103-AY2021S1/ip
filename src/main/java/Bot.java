import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// The bot that handles inputs and give responses
public class Bot {
    private static final String divider = "\t____________________________________________________________\n";

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
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    // add a task with a given description
    private void addTask(String description) {
        Task newTask = new Task(description);
        taskList.add(newTask);
        giveResponse("added: " + newTask);
    }

    // display the task list
    private void displayList(){
        String list = "";
        for (int i = 1; i <= taskList.size(); i++) {
            list += i + ". " + taskList.get(i - 1) + "\n\t ";
        }

        //remove the extra "\n\t "
        if(!list.isEmpty()) {
            list = list.substring(0, list.length() - 3);
        }
        giveResponse(list);
    }
}

