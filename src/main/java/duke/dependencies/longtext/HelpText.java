package duke.dependencies.longtext;

public class HelpText {
    public static final String DIVIDER = "--------------------------------------------\n";
    public static final String HELP_TEXT = "Help: \n" +
            DIVIDER +
            " 'clear data': use this to trash your whole task list (Beware you need to authenticate yourself with the " +
            " password in order to successfully do this. I hope you have not forgotten your password.)\n" +
            DIVIDER +
            " 'todo': adds a todo item in the format - {todo <taskname>}\n" +
            DIVIDER +
            " 'event': adds an event task in the format - {event <taskname> /at yyyy-mm-dd or MM/dd/yyyy}\n" +
            DIVIDER +
            " 'deadline': adds a deadline task in the format - {deadline <taskname> /by yyyy-mm-dd or MM/dd/yyyy}\n" +
            DIVIDER +
            " 'done': completes specified task at index, format - {done 1 [2 3 ..... n]}\n" +
            DIVIDER +
            " 'delete': removes specified task at index, format - {delete 1 [2 3 .... n]}\n" +
            DIVIDER;
}
