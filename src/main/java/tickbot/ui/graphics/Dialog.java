package tickbot.ui.graphics;

import javafx.scene.control.Label;

/**
 * The class representing a dislog label.
 */
public abstract class Dialog extends Label {
    /**
     * Initializes the dialog with the text on it.
     * @param text The text on the dialog.
     */
    public Dialog(String text) {
        super(text);
        setWrapText(true);
        setMinHeight(30);
        setMinWidth(380);
        setStyle(getCustomizedStyle());
    }

    protected abstract String getCustomizedStyle();
}
