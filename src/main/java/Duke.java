import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Famed, of course, for my unique red skin and unparalleled skills as a general of the House of War, I, the Red Prince, was raised within the vast palaces of the fabled Forbidden City. I was destined to become the next emperor, but my ambitions suffered a bit of a setback when I fell from grace for cavorting with demons. Now I'm exiled and hunted by assassins, but I assure you: I remain undaunted - and as determined as ever to claim my rightful throne!");

        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        String input = sc.nextLine();
        while (true) {
            Scanner inputSc = new Scanner(input);
            String task = inputSc.next();
            switch (task) {
            case "bye":
                System.out.println("*You take your leave.*");
                return;
            case "list":
                System.out.println("Here's the extent of our list so far:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                break;
            case "done":
                try {
                    int taskIndex = inputSc.nextInt();
                    Task markedTask = taskList.get(taskIndex - 1); // shown list is base 1, implemented list is base 0
                    markedTask.markDone();
                    System.out.println("Right. This task is now marked as done:" + markedTask);
                } catch (IndexOutOfBoundsException | NumberFormatException | InputMismatchException e) {
                    System.out.println("Hmm? Please mention \"done\" followed by the number of the task we're marking as done.");
                }
                break;
            case "todo":
                try {
                    String name = inputSc.nextLine();
                    Todo newTodo = new Todo(name);
                    taskList.add(newTodo);
                    System.out.println("Fine. I added the following to the list: " + newTodo);
                } catch (DukeException |NoSuchElementException e) {
                    System.out.println(e);
                }
                break;
            case "deadline":
                try {
                    String[] nameAndDeadline = inputSc.nextLine().split(" /by ");
                    Deadline newDeadline = new Deadline(nameAndDeadline[0], nameAndDeadline[1]);
                    taskList.add(newDeadline);
                    System.out.println("Fine. I added the following to the list: " + newDeadline);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "event":
                try {
                    String[] nameAndEvent = inputSc.nextLine().split(" /at ");
                    Event newEvent = new Event(nameAndEvent[0], nameAndEvent[1]);
                    taskList.add(newEvent);
                    System.out.println("Fine. I added the following to the list: " + newEvent);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("What's that? Please mention one of \"list\", \"done\", \"todo\", \"deadline\", \"event\", or \"bye\".");

            }
            /*
            if (input.equals("list")) {
                System.out.println("Here's the extent of our list so far:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else if (input.startsWith("done")) {
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    Task markedTask = taskList.get(taskIndex - 1); // shown list is base 1, implemented list is base 0
                    markedTask.markDone();
                    System.out.println("Right. This task is now marked as done:" + markedTask);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Hmm? Please mention \"done\" followed by the number of the task we're marking as done.");
                }
            } else if (input.startsWith("todo")) {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("Fine. I added the following to the list: " + newTask);
            }*/

            input = sc.nextLine();
        }
    }
}
