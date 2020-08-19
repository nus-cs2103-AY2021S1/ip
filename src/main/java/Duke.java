import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> userInputs = new ArrayList<>();

    public static void viewTasks() {
        if (userInputs.size() == 0) { // user has not added any task
            System.out.println("Nothing has been added to the list yet!");
        } else {
            for (int i = 0; i < userInputs.size(); i++) {
                String output = (i + 1) + ". " + userInputs.get(i).toString();
                System.out.println(output);
            }
        }
    }

    public static void markDone(String inputMsg) {
        int taskNumber = Integer.valueOf(inputMsg.split(" ")[1]); // gets the done task number
        Task currTask = userInputs.get(taskNumber - 1);
        if (currTask.getStatus()) { // task has already marked done before
            System.out.println("Task has already been completed earlier on!");
        } else {
            currTask.markAsComplete();
            System.out.println("Nice! I've marked this task as done:\n" + currTask.toString());
        }
    }

    public static void addToList(String inputMsg, String actionType) {
        String outputMsg = "";
        if (actionType.equals("todo")) {
            String taskName = inputMsg.substring(5);
            Task newTask = new Todo(taskName, false);
            userInputs.add(newTask);
            outputMsg += "Got it. I've added this task:\n" + newTask.toString();
        } else if (actionType.equals("deadline")) {
            String taskName = inputMsg.split("/")[0].substring(9);
            String deadline = inputMsg.split("/")[1].substring(3);
            Task newTask = new Deadline(taskName, false, deadline);
            userInputs.add(newTask);
            outputMsg += "Got it. I've added this task:\n" + newTask.toString();
        } else if (actionType.equals("event")) {
            String taskName = inputMsg.split("/")[0].substring(6);
            String deadline = inputMsg.split("/")[1].substring(3);
            Task newTask = new Deadline(taskName, false, deadline);
            userInputs.add(newTask);
            outputMsg += "Got it. I've added this task:\n" + newTask.toString();
        } else { // when user keys in unregistered action
            outputMsg += "Specified action is not recognised.";
        }
        outputMsg += "\nYou have " + userInputs.size() + " tasks in the list.";
        System.out.println(outputMsg);
    }

    public static void main(String[] args) {
        String welcomeMsg = "Hello! I'm Duke, some call me a parrot.\n"
                + "What can I do for you?";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String inputMsg = sc.nextLine();
            String actionType = inputMsg.split(" ")[0]; // user specified action, to identify type of action

            if (inputMsg.equals("bye")) { // ends the bot
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputMsg.equals("list")) { // sees all tasks
                viewTasks();
            } else if (actionType.equals("done")) { // mark task as done
                markDone(inputMsg);
            } else { // add task to list
                addToList(inputMsg, actionType);
            }
        }
        sc.close();
    }
}
