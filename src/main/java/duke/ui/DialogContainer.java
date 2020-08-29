package duke.ui;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DialogContainer extends VBox {

    public DialogContainer() {
        this.setStyles();
    }

    private void setStyles() {
        this.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }
}
