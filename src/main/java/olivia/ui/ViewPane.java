package olivia.ui;

import javafx.scene.control.ScrollPane;

public class ViewPane extends ScrollPane {

    public ViewPane() {
        super();
        this.setPrefSize(400, 535);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.setVvalue(1.0);
        this.setFitToWidth(true);
    }

}
