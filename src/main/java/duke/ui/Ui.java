package duke.ui;

import duke.tasks.Task;

public class Ui {
    private final static String LONG_LINE = "________________________________________________________________________________";
    private final static String LOGO = " __         _\n"
            + "| _ \\ _   _| | ___\n"
            + "|| | | | | | |/ / _\\\n"
            + "||_| | |_| |  <  __/\n"
            + "|___/ \\_,_ |_|\\_\\_|\n";



    public Ui(){
    }

    public static String startMessage(){
//        System.out.println("Hello from\n" + LOGO);
//        lineFormatter("Hello!!!! I'm duke.Duke\nWhat can I do for you?!?!?!" );
        return "Hello from\n" + LOGO + "Hello!!!! I'm Duke\nWhat can I do for you?!?!?!" ;
    }

    //method to segment every String that is being fed into this method
    public static void lineFormatter (String printable){
        System.out.println(LONG_LINE);
        System.out.println(printable);
        System.out.println(LONG_LINE);
    }

    // standardised goodbye greeting
    public static String byeGreetings () {
        String statement = "Bye! Hope to see you soon again?!";

        lineFormatter(statement);
        return statement;
    }

    //method to mark tasks as done
    public static String taskDone(Task task) {
        String statement = "Nice! This task is getting done!!\n" + "[" + task.getStatusIcon() + "] " + task.getTask();
        lineFormatter(statement);
        return statement;
    }

    //method to mark tasks as deleted
    public static String taskDeleted(Task task) {
        String statement = "The following duke.Tasks.Task is removed from the duke.TaskList.duke.TaskList!!\n" + "[" + task.getStatusIcon() + "] "
                + task.getTask() + "\n";
        lineFormatter(statement);
        return statement;
    }

    //method for formatting inputs into the taskList
    public static String newTaskItem (Task task){
        String statement = "Now you have a new task! :\n" + task.toString() +
                "\nType \'list\' to check your Tasklist";
        lineFormatter(statement);

        return statement;
    }

}
