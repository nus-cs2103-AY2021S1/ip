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
        int currId = 1;
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

                System.out.println("TO-DO LIST:");
                System.out.println(String.format("%d pending", tasks.size() - done));
                for (Task t : tasks) {
                    System.out.println("   " + t.toString());
                }


            } else if (task.startsWith("done")){

                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(task);
                int id = Integer.parseInt(m.find() ? m.group() : null);

                try {
                    int target = id - 1;
                    tasks.get(target).markAsDone();
                    done++;
                    System.out.println("Good job! This task is now marked done:");
                    System.out.println(tasks.get(target));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else {

                try {
                    String[] inputs = task.split("/");
                    String[] taskDetails = inputs[0].split(" ", 2);
                    String taskType = taskDetails[0];
                    String taskName = taskDetails[1];
                    String taskDate = inputs.length == 1 ? null : inputs[1].split(" ", 2)[1];

                    switch (taskType) {
                        case "deadline":
                            tasks.add(new Deadline(currId, taskName, taskDate));
                            break;
                        case "event":
                            tasks.add(new Event(currId, taskName, taskDate));
                            break;
                        case "todo":
                            tasks.add(new ToDo(currId, taskName));
                            break;
                    }

                    currId++;
                    int size = tasks.size();

                    System.out.println("'" + taskName + "' added to list!");
                    System.out.println(tasks.get(size - 1));
                    System.out.println("You now have " + size + " task(s) in your list.\n");
                    System.out.println("(Use 'list' command to see your updated list.)");
                } catch (Exception e) {
                    System.out.println("Sorry, we don't know what you mean...");
                }
            }

            displayBar();
            System.out.println();
        }
    }
}

