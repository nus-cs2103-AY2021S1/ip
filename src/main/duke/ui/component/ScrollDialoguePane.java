package duke.ui.component;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Pane that includes the scrolling bar and dialog boxes.
 */
public class ScrollDialoguePane extends ScrollPane {

    private VBox dialogContainer;

    /**
     * Constructor for a ScrollDialoguePane containing the Dialog boxes within a ScrollPane.
     */
    public ScrollDialoguePane() {
        super();

        dialogContainer = new VBox();
        dialogContainer.heightProperty().addListener((observable) -> setVvalue(1.0));
        dialogContainer.setSpacing(20);

        setContent(dialogContainer);
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        setFitToWidth(true);


    }

    public void addDialog(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
    }


}
