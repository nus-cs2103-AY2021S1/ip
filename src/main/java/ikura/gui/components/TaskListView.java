// TaskListView.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui.components;

import java.util.stream.Collectors;

import ikura.gui.GuiFrontend;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;

public class TaskListView extends AnchorPane {

    public TaskListView(GuiFrontend frontend) {

        var items = new VBox(/* spacing: */ 40.0);
        items.setFocusTraversable(false);
        items.setFillWidth(true);

        var scroll = new ScrollPane(items);
        scroll.setPadding(new Insets(20));
        scroll.setFocusTraversable(false);

        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // TODO: deduplicate this code
        frontend.getBot().getTaskList().addObserver(list -> {
            items.getChildren().clear();
            items.getChildren().addAll(list
                .stream()
                .map(t -> new TaskView(items, t))
                .collect(Collectors.toList()));
        });

        items.getChildren().addAll(frontend.getBot().getTaskList()
            .stream()
            .map(t -> new TaskView(items, t))
            .collect(Collectors.toList()));

        this.getChildren().add(scroll);
        this.setFocusTraversable(false);

        AnchorPane.setTopAnchor(scroll, 0.0);
        AnchorPane.setLeftAnchor(scroll, 0.0);
        AnchorPane.setRightAnchor(scroll, 0.0);
        AnchorPane.setBottomAnchor(scroll, 0.0);
    }
}
