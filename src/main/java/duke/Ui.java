package duke;

import duke.tasks.Task;

public class Ui {
    public static void showException(Exception e) {
        System.out.println(e);
    }

    public static void showExit() {
        System.out.println("*You take your leave.*");
    }

    public static void showList(TaskList tasks) {
        System.out.println("Here's the extent of our list so far:");
        System.out.println(tasks);
    }

    public static void showDone(Task doneTask) {
        System.out.println("Right. This task is now marked as done:" + doneTask);
    }

    public static void showDelete(Task deletedTask) {
        System.out.println("Begone! This task is now removed: " + deletedTask);
    }

    public static void showAddTask(Task addedTask) {
        System.out.println("Fine. I added the following to the list: " + addedTask);
    }

    public static void showWelcome() {
        System.out.println("Famed, of course, for my unique red skin and unparalleled skills as a general of " +
                "the House of War, I, the Red Prince, was raised within the vast palaces of the fabled Forbidden " +
                "City. I was destined to become the next emperor, but my ambitions suffered a bit of a setback " +
                "when I fell from grace for cavorting with demons. Now I'm exiled and hunted by assassins, but I " +
                "assure you: I remain undaunted - and as determined as ever to claim my rightful throne!");
    }

    public static void showFound(TaskList foundTasks) {
        System.out.println("I found these tasks:");
        System.out.println(foundTasks);
    }
}
