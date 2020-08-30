package tickbot.ui.graphics;

import javafx.scene.control.Label;

/**
 * The class representing a dislog label.
 */
public class Dialog extends Label {
    /**
     * Initialize the dialog with the text on it.
     * @param text The text on the dialog.
     */
    public Dialog(String text) {
        super(text);
        setWrapText(true);
        setMinHeight(50);
    }
}
