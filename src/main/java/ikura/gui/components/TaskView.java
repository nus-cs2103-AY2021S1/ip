// TaskView.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui.components;

// import java.util.function.Consumer;

import ikura.task.Task;

import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;

/**
 * A View that displays a Task, including its title and description.
 */
public class TaskView extends AnchorPane {

    private static final String SYMBOL_DONE     = "\u2713";
    private static final String SYMBOL_NOT_DONE = "\u2718";

    /**
     * Constructs a new TaskView with its parent, and the task to display.
     *
     * @param parent the parent JavaFX component of this TaskView (should be a TaskListView)
     * @param task   the Task to display
     */
    public TaskView(Region parent, Task task) {

        var doneButton = new Button(getDonenessSymbol(task));
        doneButton.setMaxHeight(Double.MAX_VALUE);
        doneButton.setFont(new Font("Arial", 16));
        doneButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        doneButton.setMaxSize(40, 40);

        // imagine not having first-class functions in 2020
        Runnable updateButton = () -> {
            doneButton.setText(getDonenessSymbol(task));
        };

        doneButton.setOnAction(event -> {
            if (task.isDone()) {
                task.markAsNotDone();
            } else {
                task.markAsDone();
            }

            updateButton.run();
        });

        // in case the title gets too long, use a TextFlow for that as well.
        var title = new Label(task.getTitle());

        var titleArea = new HBox(20, doneButton, title);
        titleArea.setPadding(new Insets(5.0));

        title.maxWidthProperty().bind(titleArea.widthProperty());
        title.setWrapText(true);

        // titleTF.prefWidthProperty().bind(titleArea.widthProperty());

        title.setFont(new Font("Arial", 30));

        var desc = new Text(task.getDescription());
        desc.setFont(new Font("Arial", 18));

        task.addObserver(t -> {
            title.setText(t.getTitle());
            desc.setText(t.getDescription());
            updateButton.run();
        });

        var tf = new TextFlow(desc);
        tf.prefWidthProperty().bind(parent.widthProperty());
        tf.setPadding(new Insets(10.0));

        var box = new VBox(titleArea, new Separator(), tf);
        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);
        AnchorPane.setBottomAnchor(box, 0.0);

        this.getChildren().add(box);
        this.setBackground(new Background(new BackgroundFill(Color.web("aaaaaa"), new CornerRadii(10.0), null)));

        this.setMaxHeight(Double.MAX_VALUE);
    }


    private static String getDonenessSymbol(Task task) {
        if (task.isDone()) {
            return SYMBOL_DONE;
        } else {
            return SYMBOL_NOT_DONE;
        }
    }
}
