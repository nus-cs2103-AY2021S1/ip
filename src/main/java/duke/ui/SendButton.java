package duke.ui;

import javafx.scene.control.Button;

public class SendButton extends Button {

    public SendButton(String text) {
        super(text);
    }

    private void setStyles() {
        this.setPrefWidth(55.0);
    }

}
