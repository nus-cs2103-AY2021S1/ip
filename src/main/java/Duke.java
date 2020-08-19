import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy pardner!! I'm\n" + logo);

        System.out.println("What can I get yer for?");

        while (true) {
            String input = userInput.nextLine();
            String[] inputArray = input.split(" ");
            String firstWord = inputArray[0];
            if (firstWord.toLowerCase().equals("bye")) { // for termination

                System.out.println("Well I'll see you around, pardner!!");
                break;

            } else if (firstWord.toLowerCase().equals("list")) { // to display list of tasks

                System.out.println("Here's yer current list of thingymajigs");

                for(int i = 0; i < toDoList.size();i++ ) {
                    Task task = toDoList.get(i);
                    System.out.println(i + 1 + ". " + "[" + task.getStatusIcon() + "] " + task.description);
                }

            } else if (firstWord.toLowerCase().equals("done")) {
                int index = Integer.parseInt(inputArray[1]);
                Task task = toDoList.get(index - 1);
                task.markAsDone();
                System.out.println("Sure thing baws! This right here is marked as done!\n" + index + ". " + "[" + task.getStatusIcon() + "] " + task.description);

            } else { // to add task to list

                Task task = new Task(input);
                toDoList.add(task);
                System.out.println("Alright, I'll put it on yer tab: " + input);

            }
        }
    }
}
