import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Duke dk = new Duke();
        ArrayList<Task> tasks = new ArrayList<Task>();

        dk.greet();
        Scanner sc = new Scanner( System.in );
        while (sc.hasNext()) {
            System.out.println("____________________________________________________________");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                dk.endConversation();
            } else if (input.equals("list")) {
                dk.showTask(tasks);
            } else {
                String[] parseArray = input.split(" ", 2);
                String type = parseArray[0];

                String rest = parseArray[1];
                String description = "";
                String time = "";

                if (type.equals("done")) {
                    int index = Integer.parseInt(rest) - 1;
                    Task newTask = tasks.get(index).markAsDone();
                    tasks.set(index, newTask);
                } else {
                    switch (type) {
                        case "todo":
                            Todo newTodo = new Todo(rest);
                            tasks.add(newTodo);
                            System.out.println(newTodo);
                            break;
                        case "deadline":
                            description = rest.split(" /")[0];
                            time = rest.split(" /")[1].split(" ", 2)[1];
                            Deadline newDeadline = new Deadline(description, time);
                            tasks.add(newDeadline);
                            System.out.println(newDeadline);
                            break;
                        case "event":
                            description = rest.split(" /")[0];
                            time = rest.split(" /")[1].split(" ", 2)[1];
                            Event newEvent = new Event(description, time);
                            tasks.add(newEvent);
                            System.out.println(newEvent);
                            break;
                        default:
                            System.out.println("Task type not available.");
                    }
                    System.out.println("Now you have " + Task.numberOfTasks + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
            }
        }
    }

    public void greet(){
        System.out.println("Hello! I am YURINA Chan.\nWhat can I do for you? ᕕ( ᐛ )ᕗ");
    }

    public void showTask(ArrayList<Task> tasks){
        int no = 1;
        for (Task task : tasks) {
            String state = "[" + task.getStatusIcon() + "] ";
            System.out.println(state + no + ". " + task.description);
            no++;
        }
    }

    public void endConversation(){
        System.out.println("Bye~ Hope to see you again soon! ∠( ᐛ 」∠)＿");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }


}
