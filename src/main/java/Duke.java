import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String divider = "************************************************\n";
        String intro = "Hello! I'm Duke\nWhat can i do for you?\n";
        System.out.println(divider + intro + divider);
        boolean carryOn = true;
        ArrayList<Task> taskArray = new ArrayList<>();
        int numberOfItems = 0;
        while(carryOn) {
            String inputString = input.nextLine();
            if (inputString.contains("done")) {
                int itemNumber = Integer.parseInt(inputString.substring(inputString.length() - 1));
                if (taskArray.size() < itemNumber || itemNumber <= 0) {
                    System.out.println(divider + "Hey, no such task exists!" + "\n" + divider);
                } else {
                    Task task = taskArray.get(itemNumber - 1);
                    task.updateTask(1);
                    System.out.println(divider + "Nice! I have marked this task as done:");
                    System.out.println(task + "\n" + divider);
                }
            } else if (inputString.equals("list")) {
                System.out.println(divider);
                System.out.println("Here are the tasks in your list!");
                for (int i = 0; i < taskArray.size(); i++) {
                    int numbering = i + 1;
                    Task task = taskArray.get(i);
                    System.out.println(numbering + "." + task);
                }
                System.out.println(divider);
            } else if (inputString.equals("bye")) {
                System.out.println(divider + "Bye! See you next time!" + "\n" + divider);
                carryOn = false;
                input.close();
            } else {
                Task task;
                if (inputString.indexOf("todo") == 0) {
                    task = new Todo(inputString.substring(5));
                } else if (inputString.indexOf("deadline") == 0 && inputString.contains("/by")) {
                    int byIndex = inputString.indexOf("/by");
                    task = new Deadline(inputString.substring(9, byIndex - 1), inputString.substring(byIndex + 4));
                } else if (inputString.indexOf("event") == 0 && inputString.contains("/at")) {
                    int atIndex = inputString.indexOf("/at");
                    task = new Deadline(inputString.substring(6, atIndex - 1), inputString.substring(atIndex + 4));
                } else {
                    task = null;
                }
                if (task == null) {
                    System.out.println(divider + "Hey, that is not a valid task!");
                    System.out.println("You still have " + numberOfItems + " tasks left!");
                } else {
                    if (numberOfItems < 100) {
                        taskArray.add(task);
                        numberOfItems += 1;
                        System.out.println(divider + "Got it, I've added this task:");
                        System.out.println(" " + task);
                        System.out.println("Now you have " + numberOfItems + " tasks in the list.");
                        System.out.println(divider);
                    } else {
                        System.out.println(divider + "Sorry, the list is full!\n" + divider);
                    }
                }
            }
        }
    }
}
