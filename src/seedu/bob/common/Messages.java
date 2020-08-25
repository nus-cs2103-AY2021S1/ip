package seedu.bob.common;

public class Messages {
    public static final String INTRO = "/*----------------- Welcome to BOB -----------------*/\n"
            + "                   _           _\n"
            + "                  | |         | |\n"
            + "                  | |__   ___ | |__\n"
            + "                  | '_ \\ / _ \\| '_ \\\n"
            + "                  | |_) | (_) | |_) |\n"
            + "                  |_.__/ \\___/|_.__/\n"
            + "            Also known as BERY ORDINARY BOT\n"
            + "\n"
            + "Hi, my name is BOB.\n"
            + "What can I do for you?";

    public static final String OUTRO = "Good bye boss, see you soon.";
    public static final String ADDMSG = "Yes boss, I have added this task to your list:\n";
    public static final String DONEMSG = "I have marked the task as done, good job boss.\n";
    public static final String DELETEMSG = "I have deleted the task.\n";
    public static final String INVALIDCOMMAND =
            "Sorry boss, I am not smart enough to understand that. Please give me a valid instruction.\n";
    public static final String EMPTYTASK =
            "Sorry, I can't guess your task that well. Please enter a description for your task.\n";
    public static final String INVALIDDATEANDTIME =
            "Sorry, I don't recognize your date and time. Please enter a valid date"
                    + " and time in this format \"YY/MM/DD HHmm\" after \"/by\" or \"/at\".\n"
                    + "For example, \"deadline return book /by 2020/08/23 1930\".\n"
                    + "Or, \"event birthday /at 2020/12/09 0000\".\n";
    public static final String INVALIDNUMBER =
            "Sorry but there is no valid task number. Please enter a valid task number.\n";
    public static String listIndexOutOfBoundsMsgGenerator(int totalNoOfTasks, int taskNo, String action) {
        return "Erm, you are asking me to " + action + " task " + taskNo + " but there is/are only "
                + totalNoOfTasks + " tasks. Please enter the correct number instead.";
    }
    public static final String LOADINGERROR =
            "Saved file containing your task list could not be load, might be corrupted. :(\n";
    public static final String UPDATEERROR =
            "Update to saved file has failed, file reader might be corrupted. :(\n";
    public static final String INVALIDPATHNAME =
            "Couldn't recognise the file name. Please restart Bob with a different file name.\n";
}