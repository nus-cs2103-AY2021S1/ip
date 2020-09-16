package duke.gui;

import java.util.HashMap;

import duke.Duke;
import duke.command.Command;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AllCommandsWindow {

    /**
     * Displays a popup window of all commands in Duke.
     * @param duke the current Duke
     */
    public static void display(Duke duke) {
        // table of commands
        TableView<Command> table = new TableView<>();

        // table columns
        TableColumn<Command, String> commandNameColumn = new TableColumn<>("Command Name");
        commandNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Command, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Command, String> formatColumn = new TableColumn<>("Format");
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("format"));

        // set columns
        table.getColumns().addAll(
                commandNameColumn,
                descriptionColumn,
                formatColumn
        );

        // put commands into table
        initTable(table, duke);

        // layout
        VBox layout = new VBox();
        layout.getChildren().add(table);

        // scene
        Scene scene = new Scene(layout, 650, 400);

        // stage
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tasks");

        stage.show();
    }

    private static void initTable(TableView<Command> table, Duke duke) {
        HashMap<String, Command> allCommands = duke.getCommandSet().getAllCommands();

        for (String key : allCommands.keySet()) {
            table.getItems().add(allCommands.get(key));
        }
    }
}
