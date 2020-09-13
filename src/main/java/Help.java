/**
 * Help class deals with helping a user if he/she is lost.
 */
public class Help {
    private String divider = "____________________________________________________________";
    private String header = "List of commands:\n";
    private String info = "list: shows you the whole list of tasks\n"
            + "bye: exits the program\n\n"
            + divider + "\n"
            + "To add tasks:\n\n"
            + "todo <item description>\n\n"
            + "deadline <item description> /<connector word> <dd/mm/yyyy> <HHmm>\n\n"
            + "event <item description> /<connector word> <dd/mm/yyyy> <HHmm>-<HHmm>\n\n"
            + divider + "\n"
            + "To mark a task as complete:\n"
            + "done <task number>\n\n"
            + "To delete a task:\n"
            + "delete <task number>\n\n"
            + "To search for a task:\n"
            + "find <keyword>\n\n"
            + divider + "\n"
            + "To find task(s) due by a certain date:\n"
            + "items due by <dd/mm/yyyy>\n\n"
            + "To find task(s) due before a certain date and/or time:\n"
            + "items due before <dd/mm/yyyy> <HHmm>";

    protected String get() {
        return header + divider + "\n" + info + "\n" + divider;
    }
}
