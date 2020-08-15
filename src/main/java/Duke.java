import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String greeting = "Hello human, I'm Duke the supporting chatbot \n"
                + "What can I do for you?";

        // list of commands
        String exitCmd = "bye";
        String doneCmd = "done";

        // list of messages
        String exitMsg = "Bye human. See you again soon!";
        String doneMsg = "Good job bud! I've marked this task as done:";
        String showTasksMsg = "Here are the tasks in your lists:";
        String horizontalLine = "________________________________________";
        String cmdReq = "Your command: ";
        String lazyHumanBash = "You have nothing in your list. Why are you so lazy human?";

        ArrayList<Task> tasks = new ArrayList<>();

        // logo and greeting
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println(greeting + "\n" + horizontalLine);
        System.out.println(cmdReq);

        // getting command
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputSplitted = input.split("\\s+");
        while (!input.equals(exitCmd)) {
            System.out.println(horizontalLine);
            if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println(lazyHumanBash);
                } else {
                    System.out.println(showTasksMsg);
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                }
            } else if (inputSplitted[0].equals(doneCmd)) {
                int idx = Integer.parseInt(inputSplitted[1]) - 1;
                Task newTask = tasks.get(idx).markAsDone();
                System.out.println(doneMsg);
                System.out.println(newTask.toString());
                tasks.set(idx, newTask);
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("added: " + input);
            }
            System.out.println(horizontalLine);
            System.out.println(cmdReq);
            input = sc.nextLine();
            inputSplitted = input.split("\\s+");
        }

        // exit
        System.out.println(horizontalLine + "\n" +  exitMsg + "\n" + horizontalLine);
    }
}
