package duke;

import duke.dukehelper.uiparts.DialogBox;
import duke.dukehelper.uiparts.Statistics;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final static String USER_AVATAR = "/images/dnh.jpg";
    private final static String DUKE_AVATAR = "/images/duke.jpg";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private VBox sideMenu;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_AVATAR));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(DUKE_AVATAR));

    @FXML
    public void initialize(Duke duke) {
        sideMenu.getStylesheets().add(this.getClass().getClassLoader().getResource("style/side_menu.css").toExternalForm());
        //sideMenu.getChildren().add(new Statistics(new int[]{1,2,3}));
        this.duke = duke;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.init(),
                dukeImage));
        setUpFunctionality();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void addDialog() {
        String userText = userInput.getText();
        String dukeText = duke.getResponse(userInput.getText());
        if(dukeText.equals("GET_CHART")) {
            //dialogContainer.getChildren().add();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText,userImage),
                    DialogBox.getStatDialog(new Statistics(), dukeImage)
            );
        } else {
            //System.out.println("HERE");
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText,userImage),
                    DialogBox.getDukeDialog(dukeText, dukeImage)
            );
        }
        userInput.clear();
    }

    private void setUpFunctionality() {
        sendButton.setOnMouseClicked((event) -> {
            if (!userInput.getText().strip().equals("")) {
                addDialog();
            }
        });

        userInput.setOnAction((event) -> {
            if (!userInput.getText().strip().equals("")) {
                addDialog();
            }
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and
     * the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if(response == "GET_CHART") {
            //dialogContainer.getChildren().add();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input,userImage),
                    DialogBox.getStatDialog(new Statistics(), dukeImage)
            );
        } else {
            //System.out.println("HERE");
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input,userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
