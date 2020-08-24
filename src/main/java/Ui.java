public class Ui {

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "\n____________________________________________________________"
                + "\n Hello! I'm Duke"
                + "\n What can I do for you?"
                + "\n____________________________________________________________\n";
        System.out.println(logo + greet);
    }

    public static void getListOfCommands() {
        String commands = "   ____________________________________________________________"
                + "\n    Here are all your commands:"
                + "\n     list - show all tasks"
                + "\n     todo <your task> - add task"
                + "\n     deadline <your task> /by <your deadline> - add task with deadline"
                + "\n     event <your event> /at <event's timing> - add event"
                + "\n     done <index of task> - mark task as done"
                + "\n     delete <index of task> - delete task from list"
                + "\n   ____________________________________________________________\n";
        System.out.println(commands);
    }

    public static void exit() {
        String bye = "   ____________________________________________________________"
                + "\n    Bye! Hope to see you again soon."
                + "\n   ____________________________________________________________";
        System.out.println(bye);
    }

    public static void showLoadingError() {
        System.err.println("    ERROR IN LOADING YOUR DATA :-(\n");
    }
}
