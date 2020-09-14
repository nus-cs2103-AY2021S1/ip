package duke.ui;

import duke.TaskList;
import duke.task.Task;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {

    //Image retrieved from https://line.fandom.com/wiki/Brown
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/brown.png"));

    private VBox dialogContainer;

    /** Creates and initiates an Ui object. */
    public Ui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    /** Greets the user. */
    public void greet() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                "Hello! I'm Duke\nWhat can I do for you?\nType \"help\" to see the list of commands available.",
                dukeImage,
                false));
    }

    /** Say bye to the user. */
    public void bye() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                "Bye. Hope to see you again soon!", dukeImage, false));
    }

    /**
     * Informs user that the task is marked as done.
     *
     * @param task The task that has being marked as done.
     */
    public void done(Task task) {
        String text = "Nice! I've marked this task as done:\n" + task.toString();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(text, dukeImage, false));
    }

    /**
     * Displays the error message.
     *
     * @param message The message to be displayed.
     */
    public void showError(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage, true));
    }

    /**
     * Informs the user that the task is deleted.
     *
     * @param task The task that has being deleted.
     * @param taskList The list of task remaining.
     */
    public void deleteTask(Task task, TaskList taskList) {
        String text = "Noted. I've removed this task:" + "\n"
                + task.toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(text, dukeImage, false));
    }

    /**
     * Informs the user that the task is added to the list.
     *
     * @param task The task that is added.
     * @param taskList The list containing all tasks.
     */
    public void addTask(Task task, TaskList taskList) {
        String text = "Got it! I have added this task to the list!\n"
                + task.toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(text, dukeImage, false));
    }

    /**
     * Prints the text given.
     *
     * @param text Text to be printed.
     */
    public void print(String text) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(text, dukeImage, false));
    }
}
