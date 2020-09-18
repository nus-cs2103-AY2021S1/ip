package olivia.ui;

import javafx.scene.control.ScrollPane;

/**
 * ViewPane class that represents that background of the chat interface.
 */

public class ViewPane extends ScrollPane {

    /**
     * Constructor that creates a ViewPane object.
     */

    public ViewPane() {
        super();
        this.setPrefSize(420, 585);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.setVvalue(1.0);
        this.setFitToWidth(true);
    }

}
