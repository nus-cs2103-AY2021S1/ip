package duke.Ui;

import duke.Tasks.Task;

public class Ui {
    private final static String longLine = "________________________________________________________________________________";
    private final static String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";



    public Ui(){
    }

    public static void startMessage(){
        System.out.println("Hello from\n" + logo);
        lineFormatter("Hello!!!! I'm duke.Duke\nWhat can I do for you?!?!?!" );
    }

    //method to segment every String that is being fed into this method
    public static void lineFormatter (String printable){
        System.out.println(longLine);
        System.out.println(printable);
        System.out.println(longLine);
    }

    // standardised goodbye greeting
    public static void byeGreetings () {
        lineFormatter("Bye! Hope to see you soon again?!");
    }

    //method to mark tasks as done
    public static void taskDone(Task task) {
        lineFormatter("Nice! This task is getting done!!\n" + "[" + task.getStatusIcon() + "] " + task.getTask());
    }

    //method to mark tasks as deleted
    public static void taskDeleted(Task task) {
        lineFormatter("The following duke.Tasks.Task is removed from the duke.TaskList.duke.TaskList!!\n" + "[" + task.getStatusIcon() + "] "
                + task.getTask() + "\n"
        );
    }

    //method for formatting inputs into the taskList
    public static void newTaskItem (Task task){
        lineFormatter("Now you have a new task! :\n" + task.toString() +
                "\nType \'list\' to check your Tasklist");
    }

}
