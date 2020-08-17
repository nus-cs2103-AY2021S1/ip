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
            if (inputString.length() >= 6 && inputString.substring(0, 4).equals("done")) {
                int itemNumber = Integer.parseInt(inputString.substring(inputString.length() - 1));
                if (taskArray.size() < itemNumber || itemNumber <= 0) {
                    System.out.println(divider + "Hey, no such task exists!" + "\n" + divider);
                } else {
                    Task task = taskArray.get(itemNumber - 1);
                    task.updateTask(1);
                    System.out.println(divider + "Nice! I have marked this task as done:");
                    System.out.println(task.getStatusIcon() + "\n" + divider);
                }
            } else if (inputString.equals("list")) {
                System.out.println(divider);
                System.out.println("Here are the tasks in your list!");
                for (int i = 0; i < taskArray.size(); i++) {
                    int numbering = i + 1;
                    Task task = taskArray.get(i);
                    System.out.println(numbering + "." + task.getStatusIcon());
                }
                System.out.println(divider);
            } else if (inputString.equals("bye")) {
                System.out.println(divider + "Bye! See you next time!" + "\n" + divider);
                carryOn = false;
            } else {
                if (numberOfItems < 100) {
                    taskArray.add(new Task(inputString));
                    System.out.println(divider + "added: " + inputString + "\n" + divider);
                    numberOfItems += 1;
                } else {
                    System.out.println(divider + "Sorry, the list is full!\n" + divider);
                }
            }
        }
    }
}
