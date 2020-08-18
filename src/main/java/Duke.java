import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //constant
        String tab = "  ";
        String lineBreaker = tab + "___________________________________________________________";
        String greet = tab + "Hello! I'm Duke" + "\n" + tab + "What can I do for you?";
        String end = tab + "Bye. Hope to see you again soon!";
        String listNotification = tab + "Here are the tasks in your list:";
        String doneNotification = tab + "Nice! I've marked this task as done:";

        //Setup
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();

        //Greeting
        System.out.println(lineBreaker);
        System.out.println(greet);
        System.out.println(lineBreaker);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(lineBreaker);

            if(input.equals("bye")) {
                System.out.println(end);
                System.out.println(lineBreaker);
                break;

            } else if(input.equals("list")) {
                int i = 1;
                System.out.println(listNotification);
                for (Task task : listOfTasks) {
                    System.out.println(tab + i++ + ". " + "  [" + task.getStatusIcon() + "] " + task.getDescription());
                }

            } else if(input.length() >= 6 && input.substring(0, 4).equals("done")) {
                int indexOfMarkingTask = Integer.parseInt(input.charAt(5) + "") - 1;
                Task markingTask = listOfTasks.get(indexOfMarkingTask);
                Task markedTask = markingTask.markAsDone();
                listOfTasks.set(indexOfMarkingTask, markedTask);

                System.out.println(doneNotification);
                System.out.println(tab + "  [" + markedTask.getStatusIcon() + "] " + markedTask.getDescription());

            } else {
                Task newTask = new Task(input);
                listOfTasks.add(newTask);
                System.out.println(tab + "added: " + input);
            }

            System.out.println(lineBreaker);
        }
    }
}
