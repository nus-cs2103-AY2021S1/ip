package duke.ui.component;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Pane that includes the scrolling bar and dialog boxes.
 */
public class ScrollDialoguePane extends ScrollPane {

    VBox dialog;

    public ScrollDialoguePane() {
        super();
        this.dialog = new VBox();
        this.setContent(this.dialog);
        this.setFitToWidth(true);
        dialog.heightProperty().addListener((observable) -> this.setVvalue(1.0));
    }

    public void addDialog(DialogBox dialogBox) {
        dialog.getChildren().add(dialogBox);
    }


}
