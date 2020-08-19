import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMsg = "Hello! I'm Duke, some call me a parrot.\n"
                + "What can I do for you?";
        System.out.println(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> userInputs = new ArrayList<>();
        while (sc.hasNext()) {
            String inputMsg = sc.nextLine();
            String firstWord = inputMsg.split(" ")[0]; // user specified action, to identify a done action

            if (inputMsg.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputMsg.equals("list")) {
                if (userInputs.size() == 0) { // user has not added any task
                    System.out.println("Nothing has been added to the list yet!");
                } else {
                    for (int i = 0; i < userInputs.size(); i++) {
                        String output = (i + 1) + ". " + userInputs.get(i).toString();
                        System.out.println(output);
                    }
                }
            } else if (firstWord.equals("done")) {
                int taskNumber = Integer.valueOf(inputMsg.split(" ")[1]); // gets the done task number
                Task currTask = userInputs.get(taskNumber - 1);
                if (currTask.getStatus()) { // task has already marked done before
                    System.out.println("Task has already been completed earlier on!");
                } else {
                    currTask.markAsComplete();
                    System.out.println("Nice! I've marked this task as done:\n" + currTask.toString());
                }
            } else {
                Task newTask = new Task(inputMsg, false);
                userInputs.add(newTask);
                System.out.println("added: " + inputMsg);
            }
        }
        sc.close();
    }
}
