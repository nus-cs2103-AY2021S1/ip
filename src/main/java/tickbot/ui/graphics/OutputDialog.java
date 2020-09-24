package tickbot.ui.graphics;

public class OutputDialog extends Dialog {
    public OutputDialog(String text) {
        super(text);
    }

    @Override
    protected String getCustomizedStyle() {
        return "-fx-font-size: 14px;"
             + "-fx-border-color: grey;"
             + "-fx-border-width: 0px 0px 0px 2px;"
             + "-fx-padding: 0px 0px 0px 10px;";
    }
}
