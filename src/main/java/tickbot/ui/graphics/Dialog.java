package tickbot.ui.graphics;

import javafx.scene.control.Label;

public class Dialog extends Label {
    public Dialog(String text) {
        super(text);
        setWrapText(true);
        setMinHeight(50);
    }
}