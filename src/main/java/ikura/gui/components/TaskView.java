// TaskView.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui.components;

import ikura.task.Task;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

/**
 * A View that displays a Task, including its title and description.
 */
public class TaskView extends AnchorPane {

    /**
     * Constructs a new TaskView with its parent, and the task to display.
     *
     * @param parent the parent JavaFX component of this TaskView (should be a TaskListView)
     * @param task   the Task to display
     */
    public TaskView(Region parent, Task task) {

        var title = new Label(task.getTitle() + " - " + (task.isDone() ? "done" : "not"));
        title.setFont(new Font("Arial", 30));

        var desc = new Text(task.getDescription());
        desc.setFont(new Font("Arial", 18));

        task.addObserver(t -> {
            title.setText(t.getTitle() + " - " + (t.isDone() ? "done" : "not"));
        });

        var tf = new TextFlow(desc);
        tf.prefWidthProperty().bind(parent.widthProperty());

        var box = new VBox(title, new Separator(), tf);
        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);
        AnchorPane.setBottomAnchor(box, 0.0);

        this.getChildren().add(box);
        this.setBackground(new Background(new BackgroundFill(Color.web("aaaaaa"), null, null)));
    }
}
