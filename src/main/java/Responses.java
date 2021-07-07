public class Responses {
    public static final String BLANK = Ui.errorMsg("you haven't entered any command!");
    public static final String CLEAR = Ui.print("cleared all tasks from the list!");
    public static final String NEW_ERROR = Ui.errorMsg("enter a note name! e.g. note mynote");
    public static final String NEW_SUCCESS = Ui.print("new note added! now type in the content of your note.");
    public static final String DONE_NO_INDEX = Ui.errorMsg("you haven't entered a task number to complete!");
    public static final String DELETE_NO_INDEX = Ui.errorMsg("you haven't entered a task number to delete!");
    public static final String TASK_NO_DESCRIPTION = Ui.errorMsg("the task description cannot be nothing D:");
    public static final String EVENT_INPUT_EG = Ui.errorMsg("you haven't entered a time that this task happens at. "
            + "you can do that by typing \"event xxx /at dd/mm/yy hhmm\". \n"
            + "e.g.: event read textbook /at 12/3/20 1500");
    public static final String DEADLINE_INPUT_EG = Ui.errorMsg("you haven't entered a time that this task is due by. "
            + "you can do that by typing \"deadline xxx /by dd/mm/yy hhmm\". \n"
            + "e.g.: deadline read textbook /by 12/3/20 1500");
    public static final String WRITE = Ui.print("add another line or type 'complete' to save your note");
    public static final String VIEW_NO_INDEX = Ui.errorMsg("you haven't entered an index to view!");
    public static final String BAD_INDEX = Ui.errorMsg("that is not the number of an item in the list!");
    public static final String COMPLETE = Ui.print("your note has been saved!");
    public static final String FIND_NO_KEYWORD = Ui.errorMsg("you haven't entered a search keyword!");
    public static final String FIND_SUCCESS = Ui.print("here's the list of tasks that contain the keyword!");
    public static final String ERROR = Ui.errorMsg("i don't know what that means :(");
    public static final String LOL = "justin gets attacked.";
    public static final String JUSTIN = "LOL";
}
