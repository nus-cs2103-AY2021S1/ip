import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static String bot = "Dave says:\n";
    static String line = "_______________________________________________________________";
    Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printGreetings() {
        System.out.println(line);
        System.out.println(bot);
        System.out.println("Greetings from me, Dave!\n" + "How can I help you? ^_^");
        System.out.println(line);
    }

    public void printBye() {
        System.out.println(line);
        System.out.println(bot);
        System.out.println("Goodbye! Hope to see you again soon! ^_^");
        System.out.println(line);
        System.exit(0);
    }

    public void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(line);
            System.out.print(bot);
            System.out.println("There are no tasks in your list yet! >_<");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.print(bot);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                //System.out.println(i + 1 + "." + " " + "[" + tasks.get(i).getStatusIcon() + "]" + " " + tasks.get(i).getTask());
                System.out.println(i + 1 + "." + " " + tasks.get(i));
            }
        }
    }

    public void printDone(ArrayList<Task> tasks, int pos) throws DukeException {
        if (pos <= tasks.size() && pos > 0) {
            tasks.get(pos - 1).markAsDone(); //marking task as done
            System.out.println(line);
            System.out.print(bot);
            System.out.println("Great work! I've marked this task as done:");
            //System.out.println("[" + tasks.get(pos - 1).getStatusIcon() + "]" + " " + tasks.get(pos - 1).getTask());
            System.out.println(tasks.get(pos - 1));
            System.out.println("Keep the ticks going! ^_^");
        } else {
            throw new DukeException("You have keyed in an invalid number!");
        }
    }



    public void printAddTodo(ToDo todoTask, TaskList tasks) {
        System.out.println(line);
        System.out.print(bot);
        System.out.println("Got it! I've added this task:");
        System.out.println(todoTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void printAddEvent(Event eventTask, TaskList tasks) {
        System.out.println(line);
        System.out.print(bot);
        System.out.println("Got it! I've added this task:");
        System.out.println(eventTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void printAddDeadline(Deadline deadlineTask, TaskList tasks) {
        System.out.println(line);
        System.out.print(bot);
        System.out.println("Got it! I've added this task:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public boolean hasMoreInput() {
        return scanner.hasNext();
    }

    public void showLoadingError() {
        System.out.println("I am unable to load your tasks! D:");
    }



}
