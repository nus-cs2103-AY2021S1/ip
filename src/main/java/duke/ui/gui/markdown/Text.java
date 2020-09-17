package duke.ui.gui.markdown;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class Text implements Markdown {
    private static final int DEFAULT_TEXT_SIZE = 12;
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public Node create() {
        Label text = new Label(this.text);
        text.setMaxWidth(180);
        text.setWrapText(true);
        text.setLineSpacing(2);
        return text;
    }
}
