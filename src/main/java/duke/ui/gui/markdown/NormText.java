package duke.ui.gui.markdown;

import javafx.scene.Node;
import javafx.scene.text.Text;


public class NormText implements Markdown {
    protected static final int DEFAULT_TEXT_SIZE = 12;
    private String text;

    public NormText(String text) {
        this.text = text;
    }



    @Override
    public Node create() {
        Text text = new Text(this.text);
        text.setWrappingWidth(180);
        text.setLineSpacing(2.0);
        return text;
    }
}
