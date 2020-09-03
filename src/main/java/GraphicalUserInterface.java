import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Ui that interacts with user through GUI.
 */
public class GraphicalUserInterface extends Ui {

    private final TextField textField;
    private final Label label;
    private final GridPane grid;
    private final Scene scene;
    private final Stage stage;
    private final ArrayList<String> responseHistories;

    GraphicalUserInterface(Stage stage) {
        responseHistories = new ArrayList<>();
        this.stage = stage;
        textField = new TextField();
        label = new Label(super.getWelcome());

        grid = new GridPane();
        grid.setPrefWidth(800);
        grid.setPrefHeight(500);
        grid.addRow(0, textField);
        grid.addRow(1, label);

        scene = new Scene(grid);
    }

    /**
     * Returns TextField of GraphicalUserInterface.
     * Necessary so that Duke will know when there is a new command.
     *
     * @return TextField
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * Adds response to ArrayList of responseHistories.
     * Limits number of responses to a maximum of 5.
     *
     * @param response
     */
    public void addResponse(String response) {
        if (responseHistories.size() >= 5) {
            responseHistories.remove(0);
        }
        responseHistories.add(response);
        String responses = "";
        for (String resp: responseHistories) {
            responses += resp;
        }
        label.setText(responses);
    }

    @Override
    public void showError(String error) {
        addResponse(getError(error));
    }

    @Override
    public void showWelcome() {
        addResponse(getWelcome());
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    @Override
    public void showGoodbye() {
        addResponse(getGoodbye());
        Platform.exit();
    }

    /**
     * Displays all the tasks, based on date if date is not null.
     *
     * @param tasks Tasks to display.
     * @param date Date to filter tasks by.
     */
    @Override
    public void showTaskList(TaskList tasks, Date date, String keyWord) {
        addResponse(getTaskList(tasks, date, keyWord));
    }

    @Override
    public void showDoneTask(Task task) {
        addResponse(getDoneTask(task));
    }

    @Override
    public void showDeletedTask(Task task, TaskList taskList) {
        addResponse(getDeletedTask(task, taskList));
    }

    @Override
    public void showAddTask(Task task, TaskList taskList) {
        addResponse(getAddTask(task, taskList));
    }

}
