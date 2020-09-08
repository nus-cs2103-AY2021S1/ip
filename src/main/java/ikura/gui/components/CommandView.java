// CommandView.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui.components;

import ikura.gui.GuiFrontend;

import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.AnchorPane;

/**
 * A View that combines a CommandHistoryView and a CommandInputView. This makes up the left
 * pane of the GUI.
 */
public class CommandView extends AnchorPane {

    /**
     * Constructs a new CommandView, linked to the given frontend
     *
     * @param frontend the frontend to use
     */
    public CommandView(GuiFrontend frontend) {

        var cmdHistory = new CommandHistoryView(frontend);
        var cmdInput = new CommandInputView(frontend);

        var box = new VBox(cmdHistory, cmdInput);
        VBox.setVgrow(cmdHistory, Priority.ALWAYS);
        box.setFillWidth(true);

        this.getChildren().add(box);

        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);
        AnchorPane.setBottomAnchor(box, 0.0);
    }
}
