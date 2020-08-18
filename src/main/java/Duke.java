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
            String[] arr = str.split(" ", 2);
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
            } else if (arr[0].equals("done")) {
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
            } else if (arr[0].equals("todo")) {
                assert arr.length == 2;
                Task newTask = new Todo(arr[1]);
                ls.add(newTask);
                System.out.println(horizontalDiv);
                System.out.println("Got it. I've added this task: \n" + newTask.toString());
                if(ls.size() > 1) {
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                } else {
                    System.out.println("Now you have " + ls.size() + " task in the list.");
                }
                System.out.println(horizontalDiv);
            } else if (arr[0].equals("deadline")) {
                assert arr.length == 2;
                String secondStr = arr[1];
                String[] arrOfStr = secondStr.split(" /by ", 2);
                Task newTask = new Deadline(arrOfStr[0], arrOfStr[1]);
                ls.add(newTask);
                System.out.println(horizontalDiv);
                System.out.println("Got it. I've added this task: \n" + newTask.toString());
                if(ls.size() > 1) {
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                } else {
                    System.out.println("Now you have " + ls.size() + " task in the list.");
                }
                System.out.println(horizontalDiv);
            } else if (arr[0].equals("event")) {
                assert arr.length == 2;
                String secondStr = arr[1];
                String[] arrOfStr = secondStr.split(" /at ", 2);
                Task newTask = new Event(arrOfStr[0], arrOfStr[1]);
                ls.add(newTask);
                System.out.println(horizontalDiv);
                System.out.println("Got it. I've added this task: \n" + newTask.toString());
                if (ls.size() > 1) {
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                } else {
                    System.out.println("Now you have " + ls.size() + " task in the list.");
                }
                System.out.println(horizontalDiv);
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
