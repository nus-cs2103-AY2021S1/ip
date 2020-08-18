import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> ls = new ArrayList<>();
        String horizontalDiv = "____________________________________________________________";

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalDiv);
        System.out.println("Hello! I'm Dude\n" + "What can I do for you today?");
        System.out.println(horizontalDiv);
        while(in.hasNextLine()) {
            String str = in.nextLine();

            if(str.equals("bye")) {
                System.out.println(horizontalDiv);
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println(horizontalDiv);
                break;
            } else if (str.equals("list")) {
                System.out.println(horizontalDiv);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < ls.size(); i++) {
                    Task task = ls.get(i);
                    int num = i + 1;
                    System.out.println(num + ". " + task.toString());
                }
                System.out.println(horizontalDiv);
            } else if (str.contains("done ")) {
                int numToBeMarkedAsDone = Integer.parseInt(str.substring(str.length() - 1)) - 1;
                if(numToBeMarkedAsDone >= ls.size()) {
                    System.out.println(horizontalDiv);
                    System.out.println("Sorry! The number does not exist in the list!");
                    System.out.println(horizontalDiv);
                } else {
                    Task tsk = ls.get(numToBeMarkedAsDone);
                    tsk.markAsDone();
                    ls.set(numToBeMarkedAsDone, tsk);
                    System.out.println(horizontalDiv);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tsk.toString());
                    System.out.println(horizontalDiv);
                }
            } else {
                Task newTask = new Task(str);
                ls.add(newTask);
                System.out.println(horizontalDiv);
                System.out.println("added: " + str);
                System.out.println(horizontalDiv);
            }
        }
    }
}
