import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Ui {
    private static final String GREETING = "Ah another dude trying to be productive. " +
                                            "Sure. What do you want, sweetie? Type help to view all commands. \n";
    private static final String BYE = "That's all? Sure. See you again (hopefully LOL).";
    private static final String LIST_REPLY = "Checking the whole list doesn't make you finish anything faster... \n";
    private static final String FIND_REPLY = "Found 'em. But at what cost... \n";
    private static final String CANNOT_FIND_REPLY = "Sorry can't find any tasks with such keyword.";
    private static final String FIND_TAG_REPLY = "These are the tasks with the tag: \n";
    private static final String CANNOT_FIND_TAG_REPLY = "Sorry can't find any tasks with such tag.";
    private static final String DELETE_REPLY = "Good good... Okay removed! Looks more apt for a lazy ass like you. \n";
    private static final String ADD_REPLY_TOP = "Wow, another task. Added. You sure you can finish them all? \n";
    private static final String ADD_REPLY_BOT = "Now you have a grand total of %d";
    private static final String DONE_REPLY = "Wah finally. Wondering how long more I need to wait... \n";
    private static final String HELP_REPLY =  "\u0AE6 todo [description] - add a todo task \n"
                                            + "\u0AE6 deadline [description] /by [YYYY-MM-DD] - add a deadline task \n"
                                            + "\u0AE6 event [description] /at [YYYY-MM-DD HH:mm] - add an event task \n"
                                            + "\u0AE6 list - view all current tasks \n"
                                            + "\u0AE6 find [keyword] - find all tasks with specified keyword \n"
                                            + "\u0AE6 done [task number] - mark chosen task as done \n"
                                            + "\u0AE6 delete [task number] - delete chosen task \n";


    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private VBox dialogContainer;

    /** Creates and initiate an Ui object. */
    public Ui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    /** Greets the user. */
    public void greet() {
        print(GREETING);
    }

    /** Says goodbye to user. */
    public void bye() {
        print(BYE);
    }

    /** Replies when user input "list" */
    public void replyList(TaskManager taskManager) {
        String taskList = taskManager.convertTaskListToString(taskManager.getTaskList());
        print(LIST_REPLY + taskList);
    }

    public void replyHelp() {
        print(HELP_REPLY);
    }

    public void replyAdd(Task taskAdded, TaskManager taskManager) {
        String task = taskAdded.toString() + "\n";
        String dukeReplyBot = String.format(ADD_REPLY_BOT, taskManager.getTotalNoOfTasks());
        print(ADD_REPLY_TOP + task + dukeReplyBot);
    }

    public void replyDelete(Task taskDeleted) {
        String task = taskDeleted.toString();
        print(DELETE_REPLY + task);
    }

    public void replyDone(Task taskDone) {
        String task = taskDone.toString();
        print(DONE_REPLY + task);
    }

    public void replyFind(TaskManager taskManager, ArrayList<Task> foundTasks) {
        if (foundTasks.size() > 0) {
            print(FIND_REPLY + taskManager.convertTaskListToString(foundTasks));
        } else {
            print(CANNOT_FIND_REPLY);
        }
    }

    public void replyFindTag(TaskManager taskManager, ArrayList<Task> foundTasks) {
        if (foundTasks.size() > 0) {
            print(FIND_TAG_REPLY + taskManager.convertTaskListToString(foundTasks));
        } else {
            print(CANNOT_FIND_TAG_REPLY);
        }
    }

    public void print(String text) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(text, dukeImage));
    }
}
