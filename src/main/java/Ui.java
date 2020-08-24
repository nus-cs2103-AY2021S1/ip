public class Ui {
    final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    final String LINE = "------------------------";

    public void showStartScreen() {
        System.out.println(LINE);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println(LINE + "\n");
    }

    public void askForCommand() {
        System.out.println(LINE);
        System.out.println("What is your command?"); 
        System.out.println(LINE);
        System.out.print("> ");
    }

    public void showErrorScreen(String errorMessage) {
        System.out.println(LINE);
        System.out.println("Oh no, something went wrong!");
        System.out.println(errorMessage);
        System.out.println(LINE);
    }

    public void showExitScreen() {
        System.out.println(LINE);
        System.out.println("GOODBYE!");
        System.out.println(LINE);
    }

    public void askTodo() {
        System.out.println(LINE);
        System.out.println("What is the name of your Todo?"); 
        System.out.println(LINE);
        System.out.print("> ");
    }

    public void askDeadlineName() {
        System.out.println(LINE);
        System.out.println("What is the name of your Deadline?"); 
        System.out.println(LINE);
        System.out.print("> ");
    }

    public void askDeadlineDate() {
        System.out.println(LINE);
        System.out.println("When is the deadline? (Give in this format: day month year)"); 
        System.out.println(LINE);
        System.out.print("> ");
    }

    public void askEventName() {
        System.out.println(LINE);
        System.out.println("What is the name of your Event?"); 
        System.out.println(LINE);
        System.out.print("> ");
    }
    public void askEventStartTime() {
        System.out.println(LINE);
        System.out.println("When is the start of your event? (Give in this format: day month year hour:min)"); 
        System.out.println(LINE);
        System.out.print("> ");
    }
    public void askEventEndTime() {
        System.out.println(LINE);
        System.out.println("When is the end of your event? (Give in this format: day month year hour:min)"); 
        System.out.println(LINE);
        System.out.print("> ");
    }

    public void askTaskNumToComplete() {
        System.out.println(LINE);
        System.out.println("What is the number of the task you wish to mark as complete?"); 
        System.out.println(LINE);
        System.out.print("> ");
    }

    public void taskCompleted(Task task) {
        System.out.println("Nice, I've marked this task as done!");
        System.out.println(task);
    }

    public void askTaskNumToDelete() {
        System.out.println(LINE);
        System.out.println("What is the number of the task you wish to delete?"); 
        System.out.println(LINE);
        System.out.print("> ");
    }
}