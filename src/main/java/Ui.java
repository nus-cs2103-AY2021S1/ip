import java.util.ArrayList;

public class Ui {
    private static String divider = "************************************************\n";

    public static void introduction() {
        String intro = "Hello! I'm Duke\nWhat can i do for you?\n";
        System.out.println(divider + intro + divider);
    }

    public static String done(String doneTask) {
        String updatedLine = doneTask.substring(0, 4) + "\u2713" + doneTask.substring(5);
        System.out.println(divider + "Nice! I have marked this task as done:");
        System.out.println(updatedLine + "\n" + divider);
        return updatedLine;
    }

    public static void listTasks(ArrayList<String> lines) {
        System.out.println(divider);
        System.out.println("Here are the tasks in your list!");
        for (int i = 0; i < lines.size(); i++) {
            int numbering = i + 1;
            String task = lines.get(i);
            System.out.println(numbering + "." + task);
        }
        System.out.println(divider);
    }

    public static void bye() {
        System.out.println(divider + "Bye! See you next time!" + "\n" + divider);
    }

    public static void deletedTask(String task, int numberOfItems) {
        System.out.println(divider + "Noted, the task has been deleted");
        System.out.println(task + "\n" + divider);
        System.out.println("Now you have " + numberOfItems + " tasks in the list.");
    }

    public static void addedTask(Task task, int numberOfItems) {
        if (numberOfItems < 100) {
            System.out.println(divider + "Got it, I've added this task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + numberOfItems + " tasks in the list.");
            System.out.println(divider);
        } else {
            System.out.println(divider + "Sorry, the list is full!\n" + divider);
        }
    }

    public static void handleDukeException(DukeException e) {
        System.out.println(divider + e.getMessage() + "\n" + divider);
    }
}
