import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Gel {
    public static void keepingList() {
        // initialise list and scanner
        List<Task> listOfText = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("    Hello! I'm Gel\n    What can I do for you?\n");

        label:
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            String keyword = inputArr[0];
            switch (keyword) {
                case "bye":  //bye
                    System.out.println("\n    Bye. Hope to see you again soon!\n");
                    break label;
                case "list":  //list
                    System.out.println("\n    Here are the task(s) in your list:");
                    for (int i = 1; i <= listOfText.size(); i++) {
                        Task task = listOfText.get(i - 1);
                        System.out.println("    " + i + "." + task.getStatusIcon()
                                + " " + task.getDescription());
                    }
                    System.out.println();
                    break;
                case "done":  //done
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    Task taskToBeDone = listOfText.remove(index);
                    taskToBeDone.markAsDone();
                    listOfText.add(index, taskToBeDone);
                    System.out.println("\n    Nice! I've marked this task as done:");
                    System.out.println("      " + taskToBeDone.getStatusIcon() + " "
                            + taskToBeDone.getDescription());
                    System.out.println();
                    break;
                default:
                    listOfText.add(new Task(input));
                    System.out.println("\n    added: " + input + "\n");
                    break;
            }
        }
    }
}
