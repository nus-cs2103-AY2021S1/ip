package duke.ui;

import javafx.scene.control.ScrollPane;

public class ScrollContainer extends ScrollPane {

    public ScrollContainer() {
        this.setStyles();
    }

    private void setStyles() {
        this.setPrefSize(385, 535);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVvalue(1.0);
        this.setFitToWidth(true);
    }
}
