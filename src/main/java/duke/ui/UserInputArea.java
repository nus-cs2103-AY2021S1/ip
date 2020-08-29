package duke.ui;

import javafx.scene.control.TextField;

public class UserInputArea extends TextField {

    public UserInputArea() {
        this.setStyles();
    }

    private void setStyles() {
        this.setPrefWidth(325.0);
    }
}
