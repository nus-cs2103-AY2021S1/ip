import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);

        ArrayList<Task> lst = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String firstWord = input.split(" ")[0];
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                String listIntro = "Here are the tasks in your list:";
                System.out.println(listIntro);
                for (int i = 0; i < lst.size(); i ++) {
                    Task currentTask = lst.get(i);
                    String num = Integer.toString(i + 1);
                    System.out.println(num + "." + currentTask);
                }
            } else if (input.contains("done")) {
                String[] splitted = input.split("\\s+");
                int taskIndex = Integer.parseInt(splitted[1]) - 1;
                Task selectedTask = lst.get(taskIndex);
                selectedTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + selectedTask);
            } else if (firstWord.matches("todo|deadline|event")) {
                Task newTask;
                if (firstWord.equals("todo")) {
                    String tsk = input.split("todo ")[1];
                    newTask = new ToDo(tsk);
                } else if (firstWord.equals("deadline")) {
                    String[] split = input.split("/by ");
                    String deadline = split[1];
                    String tsk = split[0].split("deadline ")[1];
                    newTask = new Deadline(tsk, deadline);
                } else {
                    String[] split = input.split("/at ");
                    String at = split[1];
                    String tsk = split[0].split("event ")[1];
                    newTask = new Event(tsk, at);
                }
                lst.add(newTask);
                System.out.println("Got it. I've added this task:\n  " + newTask);
                System.out.println("Now you have " + lst.size() + " tasks in the list.");
            } else {
                lst.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }
}
