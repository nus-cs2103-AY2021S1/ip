// MainView.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui.components;

import ikura.gui.GuiFrontend;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.control.SplitPane;

public class MainView extends StackPane {

    public MainView(GuiFrontend frontend, int preferredWidth, int preferredHeight) {
        StackPane.setAlignment(this, Pos.CENTER);

        this.setPrefWidth(preferredWidth);
        this.setPrefHeight(preferredHeight);

        var split = new SplitPane(new CommandView(frontend), new TaskListView(frontend));

        StackPane.setMargin(split, new Insets(10.0));

        this.getChildren().add(split);
        this.setFocusTraversable(false);
    }
}
