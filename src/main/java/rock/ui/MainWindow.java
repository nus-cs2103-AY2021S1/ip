package rock.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rock.admin.Rock;
import rock.exception.RockException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Rock rock;

    private final Image sadUserImage = new Image(this.getClass().getResourceAsStream("/images/PepegaSad.png"));
    private final Image happyUserImage = new Image(this.getClass().getResourceAsStream("/images/PepegaHappy.png"));
    private Image currentUserImage = sadUserImage;

    private final Image fakeRockImage = new Image(this.getClass().getResourceAsStream("/images/FakeRock.png"));
    private final Image realRockImage = new Image(this.getClass().getResourceAsStream("/images/RealRock.png"));
    private Image currentRockImage = fakeRockImage;

    /**
     * Init rock UI and say Hi to the user
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.sayHi(), currentRockImage));
    }

    /**
     * Say bye to the user and terminate program.
     * @param input User's command
     */
    public void terminateRock(String input) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.exit(0);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, currentUserImage),
                DialogBox.getDukeDialog(Ui.sayBye(), currentRockImage)
        );
    }

    public void setDuke(Rock d) {
        rock = d;
    }

    private void switchRock(String input) {
        Image nextUserImage = currentUserImage;
        if (fakeRockImage.equals(currentRockImage)) {
            currentRockImage = realRockImage;
            nextUserImage = happyUserImage;

        } else if (realRockImage.equals(currentRockImage)) {
            currentRockImage = fakeRockImage;
            nextUserImage = sadUserImage;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, currentUserImage),
                DialogBox.getDukeDialog(Ui.saySwitch(), currentRockImage)
        );
        currentUserImage = nextUserImage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        //if(isTerminated) terminateDuke();
        String input = userInput.getText();
        String response;
        try {
            response = rock.getResponse(input);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, currentUserImage),
                DialogBox.getDukeDialog(response, currentRockImage)
            );
        } catch (RockException ex) {
            switch (ex.getMessage()) {
            case "terminate":
                terminateRock(input);
                break;
            case "switch":
                switchRock(input);
                break;
            default:
                break;
            }
        }
        userInput.clear();
    }
}
