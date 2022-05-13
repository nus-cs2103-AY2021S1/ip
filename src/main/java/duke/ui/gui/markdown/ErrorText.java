package duke.ui.gui.markdown;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ErrorText extends NormText {

    public ErrorText(String text) {
        super(text);
    }

    @Override
    public Node create() {
        Text text = (Text) super.create();
        text.setFill(Color.RED);
        text.setFont(Font.font(null, FontWeight.BOLD, DEFAULT_TEXT_SIZE));
        return text;
    }
}
