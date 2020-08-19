import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class Duke {

    public static void displayBar() {
        System.out.println("––––––––––––––––––––– *** –––––––––––––––––––––");
    }

    public static void displayTasks(List<Task> tasks) {
        System.out.println("TO-DO LIST:");
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>(100);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcome = "Duke at your service!\n"
                + logo + "\n" +
                "How can I help you?\n" +
                "Type in your orders below.";

        displayBar();
        System.out.println(welcome);
        displayBar();
        System.out.println();

        Scanner sc = new Scanner(System.in);
        int currId = 1;

        while (sc.hasNextLine()) {

            displayBar();

            String task = sc.nextLine();
            if (task.equalsIgnoreCase("bye")) {

                System.out.println("Alright, see you soon!");
                sc.close();
                displayBar();
                break;

            } else if (task.equalsIgnoreCase("list")) {

                displayTasks(tasks);

            } else if (task.startsWith("done")){

                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(task);
                int id = Integer.parseInt(m.find() ? m.group() : null);

                try {
                    int target = id - 1;
                    tasks.get(target).markAsDone();
                    System.out.println("Good job! This task is now marked done:");
                    System.out.println(tasks.get(target));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else {

                Task newTask = new Task(currId, task);
                tasks.add(newTask);
                currId++;

                System.out.println("'" + task + "' added to list!");
                System.out.println(newTask);

            }

            displayBar();
            System.out.println();
        }
    }
}

