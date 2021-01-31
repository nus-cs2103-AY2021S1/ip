package duke.components;

import duke.GUI.DialogBox;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.*;

public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeIcon.png"));

    private Storage taskStore, reminderStore;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {

    }

    public Duke(String filePath) throws FileNotFoundException, DukeException {
        taskStore = new Storage(filePath);
        reminderStore = new Storage(filePath);
        tasks = new TaskList(taskStore.load(),reminderStore.load());
        ui = new Ui();
        parser = new Parser();

    }

    public void run() throws IOException {

        System.out.println("Hi, this is Duke, what can I do for you?");

        parser.parse(ui.waitForNextInput());
        while (!parser.isBye) {

            if (parser.isList) {

                ui.printTaskList(tasks.getMyList());

            } else if (parser.isDone) {

                ui.printDoneTask(
                        tasks.finishTaskNum(
                                parser.getDoneTaskNum()
                        )
                );
            } else if (parser.isFind) {

                ui.printFindTask(tasks.findTasks(parser.getFindTask()));

            } else if (parser.isTask) {

                ui.printAddTask(
                        tasks.addTask(parser.getDescription(), parser.getDate(), parser.getTime()),
                        tasks.getMyList()
                );

            } else if (parser.isDelete) {

                ui.printDeleteTask(tasks.deleteTask(parser.getDeleteTaskNum()), tasks.getMyList());

            } else if (!parser.isValid) {

                ui.returnNotValid();
            }
            parser = new Parser();
            parser.parse(ui.waitForNextInput());
            taskStore.overwriteWith(tasks.getMyList(),"duke.txt");
            reminderStore.overwriteWith(tasks.getReminderList(),"reminders.txt");

        }
        ui.printBye();

    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("duke.txt").run();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    public Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException, DukeException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException, DukeException {
        taskStore = new Storage("duke.txt");
        reminderStore = new Storage("reminders.txt");
        tasks = new TaskList(taskStore.load(),reminderStore.load());
        ui = new Ui();
        parser = new Parser();

        parser.parse(input);
        String output;

        if (parser.isList) {

            output = ui.returnTaskList(tasks.getMyList());

        } else if (parser.isDone) {

            output = ui.returnDoneTask(
                    tasks.finishTaskNum(
                            parser.getDoneTaskNum()
                    )
            );

            taskStore.overwriteWith(tasks.getMyList(),"duke.txt");
            reminderStore.overwriteWith(tasks.getReminderList(),"reminders.txt");

        } else if (parser.isFind) {

            output = ui.returnFindTask(tasks.findTasks(parser.getFindTask()));

        } else if (parser.isTask) {

            output = ui.returnAddTask(
                    tasks.addTask(parser.getDescription(), parser.getDate(), parser.getTime()),
                    tasks.getMyList()
            );

            taskStore.overwriteWith(tasks.getMyList(),"duke.txt");
            reminderStore.overwriteWith(tasks.getReminderList(),"reminders.txt");

        } else if (parser.isDelete) {

            output = ui.returnDeleteTask(tasks.deleteTask(parser.getDeleteTaskNum()), tasks.getMyList());

            taskStore.overwriteWith(tasks.getMyList(),"duke.txt");
            reminderStore.overwriteWith(tasks.getReminderList(),"reminders.txt");

        } else if (parser.isBye) {

            output = ui.returnBye(tasks.getReminderList());

        } else if (parser.isReminder) {

            Task task;
            task = tasks.addReminder(parser.getRemindTaskNum());
            if(task == null){
                output = ui.returnDuplicateReminder();
            }else {
                output = ui.returnAddReminder(task, tasks.getReminderList());

                taskStore.overwriteWith(tasks.getMyList(),"duke.txt");
                reminderStore.overwriteWith(tasks.getReminderList(),"reminders.txt");

            }

        } else if (parser.isReminderList) {

            output = ui.returnReminderList(tasks.getReminderList());

        } else if (!parser.isValid) {

            output = ui.returnNotValid();

        } else {
            output = "error";
        }

        return output;

    }


}
