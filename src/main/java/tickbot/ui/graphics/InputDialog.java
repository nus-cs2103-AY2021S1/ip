package tickbot.ui.graphics;

public class InputDialog extends Dialog {
    public InputDialog(String text) {
        super(text);
    }

    @Override
    protected String getCustomizedStyle() {
        return "-fx-font-size: 14px;"
             + "-fx-font-weight: bold;"
             + "-fx-font-family: monospace;"
             + "-fx-text-fill: #228C22;"
             + "-fx-padding: 0px 0px 0px 5px;";
    }
}
