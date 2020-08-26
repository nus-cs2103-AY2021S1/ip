import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {

    public static Storage storage;

    public static void main(String[] args) throws DukeException {

        Path location = Path.of("duke.txt");
        storage = new Storage(location);
        try {
            ArrayList<Task> tskList = storage.showTasks();

            if (tskList.size() != 0) {
                for (Task tsk : tskList) {
                    String timestamp = tsk.getTime() == null ? "-" : tsk.getTime().toString();
                    String entry = tsk.getType() + " | " +
                            tsk.getStatus() + " | " +
                            tsk.getDescription() + " | " +
                            timestamp;
                    System.out.println(entry);
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        System.out.println("Wazzup! I am Duke the Nuke \uD83D\uDE08\n"
                + "What do you want?");

        Scanner sc = new Scanner(System.in);
        String terminate = "bye";
        String input;
        ArrayList<Task> tasks = new ArrayList<>();

        while (!(input = sc.nextLine()).equals(terminate)) {

            DateTimeFormatter df = DateTimeFormatter.ofPattern("d MMM yyyy");
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
                    System.out.println("[" + type + "][" + "\u2713" + "]" + changed);

                    tasks.get(id).markAsDone();
                    storage.saveTasks(tasks);

                    break;
                case "todo":
                    try {
                        Todo todo = Todo.makeToDo(last, false);
                        tasks.add(todo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("[T][" + "\u2718" + "] " + last);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        storage.saveTasks(tasks);
                    } catch (DukeException e) {
                        System.err.println(e.getMessage());
                    }

                    break;
                case "deadline": {

                    String job = last.split("/by")[0].trim();
                    String time = last.split("/by")[1].trim();
                    LocalDate date = LocalDate.parse(time);
                    Deadline work = new Deadline(job + " (by: " + df.format(date) + ")", false, date);
                    tasks.add(work);
                    storage.saveTasks(tasks);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[D][" + "\u2718" + "] " + work.getDescription());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    break;
                }
                case "event": {

                    String job = last.split("/at")[0].trim();
                    String time = last.split("/at")[1].trim();
                    LocalDate date = LocalDate.parse(time);
                    Event work = new Event(job + " (at: " + df.format(date) + ")", false, date);
                    tasks.add(work);
                    storage.saveTasks(tasks);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[E][" + "\u2718" + "] " + work.getDescription());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    break;
                }
                case "delete": {
                    int index = Integer.parseInt(last) - 1;
                    System.out.println("Noted. I've removed this task:");
                    String deleted = tasks.get(index).getDescription();
                    String deletedType = tasks.get(index).getType();
                    String status = tasks.get(index).getStatusIcon();
                    System.out.println("[" + deletedType + "][" + status + "] " + deleted);
                    tasks.remove(index);
                    storage.saveTasks(tasks);
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

        System.out.println("Sayonara!");
    }
}
