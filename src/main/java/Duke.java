import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class Duke {

    public static void displayBar() {
        System.out.println("––––––––––––––––––––– *** –––––––––––––––––––––");
    }

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>(100);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcome = "Duke at your service!\n" +
                // logo + "\n" +
                // commented out due to runtest failures, for some reason.
                "How can I help you?\n" +
                "Type in your orders below.\n\n" +
                "(command list: 'list', 'deadline', 'event', 'todo', 'done', 'bye')";

        displayBar();
        System.out.println(welcome);
        displayBar();
        System.out.println();

        Scanner sc = new Scanner(System.in);
        int done = 0;

        while (sc.hasNextLine()) {

            displayBar();

            String task = sc.nextLine();
            if (task.equals("bye")) {

                System.out.println("Alright, see you soon!");
                sc.close();
                displayBar();
                break;

            } else if (task.equals("list")) {

                int currId = 1;

                System.out.println("TO-DO LIST:");
                System.out.println(String.format("%d pending", tasks.size() - done));
                for (Task t : tasks) {
                    System.out.println(String.format("   %d. %s", currId, t));
                    currId++;
                }

            } else if (task.startsWith("done")){

                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(task);
                Integer id = m.find() ? Integer.parseInt(m.group()) : null;

                try {
                    if (tasks.isEmpty()) {
                        throw new EmptyTasksException(task);
                    }

                    if (id == null) {
                        throw new NullDoneIndexException(task);
                    }

                    if (id < 1 || id > tasks.size()) {
                        throw new RangeIndexException(task);
                    }

                    int target = id - 1;

                    if (tasks.get(target).isDone) {
                        throw new AlreadyDoneIndexException(task);
                    }

                    tasks.get(target).markAsDone();
                    done++;
                    System.out.println("Good job! This task is now marked done:");
                    System.out.println(tasks.get(target));
                } catch (NullDoneIndexException | RangeIndexException | AlreadyDoneIndexException | EmptyTasksException e) {
                    System.out.println(e);
                }

            } else if (task.startsWith("delete")) {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(task);
                Integer id = m.find() ? Integer.parseInt(m.group()) : null;

                try {
                    if (tasks.isEmpty()) {
                        throw new EmptyTasksException(task);
                    }

                    if (id == null) {
                        throw new NullDoneIndexException(task);
                    }

                    if (id < 1 || id > tasks.size()) {
                        throw new RangeIndexException(task);
                    }

                    int target = id - 1;

                    System.out.println("Alright! This task is now deleted:");
                    System.out.println(tasks.get(target));
                    tasks.remove(target);
                } catch (NullDoneIndexException | RangeIndexException | EmptyTasksException e) {
                    System.out.println(e);
                }
            } else {

                try {
                    String[] inputs = task.split("/");
                    String[] taskDetails = inputs[0].split(" ", 2);
                    String taskType = taskDetails[0];
                    String taskName = taskDetails.length == 1 ? "" : taskDetails[1];
                    String taskDate = inputs.length == 1 ? "" : inputs[1].split(" ", 2)[1];

                    switch (taskType) {
                        case "deadline":
                            if (taskName.isBlank()) throw new NullTaskNameException("deadline");
                            if (taskDate.isBlank()) throw new NullTaskDateException("deadline");
                            tasks.add(new Deadline(taskName, taskDate));
                            break;
                        case "event":
                            if (taskName.isBlank()) throw new NullTaskNameException("event");
                            if (taskDate.isBlank()) throw new NullTaskDateException("event");
                            tasks.add(new Event(taskName, taskDate));
                            break;
                        case "todo":
                            if (taskName.isBlank()) throw new NullTaskNameException("todo");
                            tasks.add(new ToDo(taskName));
                            break;
                        default:
                            throw new DukeException(taskType);
                    }

                    int size = tasks.size();

                    System.out.println("'" + taskName + "' added to list!");
                    System.out.println(tasks.get(size - 1));
                    System.out.println("\nYou now have " + size + " task(s) in your list.\n");
                    System.out.println("(Use 'list' command to see your updated list.)");
                } catch (DukeException e) {
                    System.out.println(e);
                } catch (Exception e) {
                    System.out.println("I can't parse your order properly!");
                }
            }

            displayBar();
            System.out.println();
        }
    }
}

