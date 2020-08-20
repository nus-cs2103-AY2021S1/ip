import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I am Duke\n"
                + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String terminate = "bye";
        String input;
        ArrayList<Task> tasks = new ArrayList<>();

        while (!(input = sc.nextLine()).equals(terminate)) {

            String trimmed = input.trim();
            String first = trimmed.split(" ")[0].trim(); // checking the first word
            String last = input.substring(first.length()).trim(); // get rid of the first word

            switch (first) {
                case "":
                    continue;
                case "done":
                    int id = Integer.parseInt(last) - 1;

                    System.out.println("Nice! I've marked this task as done:");
                    String changed = tasks.get(id).getDescription();
                    String type = tasks.get(id).getType();
                    System.out.println("[" + "\u2713" + "]" + changed);

                    if (type.equals("T")) {
                        Todo updatedTask = new Todo(changed, true);
                        tasks.set(id, updatedTask);
                    } else if (type.equals("D")) {
                        Deadline updatedTask = new Deadline(changed, true);
                        tasks.set(id, updatedTask);
                    } else {
                        Event updatedTask = new Event(changed, true);
                        tasks.set(id, updatedTask);
                    }

                    break;
                case "todo":
                    try {
                        Todo todo = Todo.makeToDo(last, false);
                        tasks.add(todo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[T][" + "\u2718" + "] " + last);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DukeException e) {
                        System.err.println(e.getMessage());
                    }

                    break;
                case "deadline": {

                    String job = last.split("/by")[0].trim();
                    String time = last.split("/by")[1].trim();
                    Deadline work = new Deadline(job + " (by: " + time + ")", false);
                    tasks.add(work);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[D][" + "\u2718" + "] " + work.getDescription());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    break;
                }
                case "event": {

                    String job = last.split("/at")[0].trim();
                    String time = last.split("/at")[1].trim();
                    Event work = new Event(job + " (at: " + time + ")", false);
                    tasks.add(work);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[E][" + "\u2718" + "] " + work.getDescription());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    break;
                }
                case "list":
                    Iterator<Task> iter = tasks.iterator();
                    int index = 1;
                    System.out.println("Here are the tasks in your list:");
                    while (iter.hasNext()) {
                        Task currentTask = iter.next();
                        String next = currentTask.getDescription();
                        System.out.println(index + "." + "[" + currentTask.getType() + "][" + currentTask.getStatusIcon() + "] " + next);
                        index++;
                    }
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
