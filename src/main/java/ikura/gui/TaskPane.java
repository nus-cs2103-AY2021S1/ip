// TaskPane.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui;

import ikura.task.Task;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TaskPane extends AnchorPane {

    private final Task task;

    public TaskPane(Region parent, Task task) {
        this.task = task;

        var t = new Label(this.task.getTitle());
        t.setFont(new Font("Arial", 30));

        var s = new Text(this.task.getDescription());
        s.setFont(new Font("Arial", 18));

        var tf = new TextFlow(s);
        tf.prefWidthProperty().bind(parent.widthProperty());

        var box = new VBox(t, new Separator(), tf);
        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);
        AnchorPane.setBottomAnchor(box, 0.0);

        this.getChildren().add(box);
        this.setBackground(new Background(new BackgroundFill(Color.web("aaaaaa"), null, null)));
    }
}
