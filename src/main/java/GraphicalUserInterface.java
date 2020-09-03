import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

/**
 * GUI that interacts with user.
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

        // set dimensions of GridPane and fill with fields
        grid = new GridPane();
        grid.setPrefWidth(800);
        grid.setPrefHeight(500);
        grid.addRow(0, textField);
        grid.addRow(1, label);

        scene = new Scene(grid);
    }

    /**
     * Returns TextField of GUI.
     * Necessary so that Duke will know when there is a new command.
     *
     * @return TextField of GUI.
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
    public void showError(String errorMsg) {
        addResponse(getError(errorMsg));
    }

    @Override
    public void showWelcome() {
        addResponse(getWelcome());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void showGoodbye() {
        addResponse(getGoodbye()); // currently does not display as platform exits immediately after
        Platform.exit();
    }

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
