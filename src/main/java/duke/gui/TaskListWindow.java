package duke.gui;

import java.util.ArrayList;

import duke.Duke;
import duke.task.Task;
import duke.time.Time;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskListWindow {

    /**
     * Displays a popup window of all current tasks.
     * @param duke
     */
    public static void display(Duke duke) {
        // table of tasks
        TableView<Task> table = new TableView<>();

        // table columns
        TableColumn<Task, String> typeColumn = new TableColumn<>("Task Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("icon"));

        TableColumn<Task, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Task, Time> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Task, String> tagColumn = new TableColumn<>("Tag");
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tag"));

        TableColumn<Task, String> isDoneColumn = new TableColumn<>("Is Task Done?");
        isDoneColumn.setCellValueFactory(new PropertyValueFactory<>("isDone"));

        // set columns
        table.getColumns().addAll(
                typeColumn,
                descriptionColumn,
                timeColumn,
                tagColumn,
                isDoneColumn
        );

        // put tasks into the table
        initTasks(table, duke);

        // layout
        VBox layout = new VBox();
        layout.getChildren().add(table);

        // scene
        Scene scene = new Scene(layout, 500, 300);

        // stage
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Tasks");

        stage.showAndWait();
    }

    private static void initTasks(TableView<Task> table, Duke duke) {
        ArrayList<Task> tasks = duke.getTaskList().getTasks();
        table.getItems().addAll(tasks);
    }
}
