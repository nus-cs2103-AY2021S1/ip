package utils.ui;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GUI implements UI {

    private final TextField inputTextField;
    private final VBox dialogContainer;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    public GUI() {
        inputTextField = new TextField();
        dialogContainer = new VBox();
    }

    public TextField getInputTextField() {
        return inputTextField;
    }

    public VBox getDialogContainer() {
        return dialogContainer;
    }

    @Override
    public void print(Object object) {
        dialogContainer.getChildren().add((Node) object);
    }

    public void printFromUser(Object object) {
        Label textToAdd = new Label(object.toString());
        textToAdd.setWrapText(true);

        print(new DialogBox(textToAdd, new ImageView(user)));
    }

    public void printFromDuke(Object object) {
        Label textToAdd = new Label(object.toString());
        textToAdd.setWrapText(true);

        print(new DialogBox(textToAdd, new ImageView(duke)).flip());
    }

    @Override
    public String read() {
        return inputTextField.getText();
    }
}
