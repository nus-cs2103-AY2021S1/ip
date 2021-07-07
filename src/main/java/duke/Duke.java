package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Creates the Duke to run the program.
 */
public class Duke extends Application {
    private final Storage storage;
    private final TaskList list;
    private final Parser parser;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/doge.jpg"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/catie.jpg"));

    Instruction thisInstruction;
    Deadline thisDeadline;
    String thisTaskname;
    String thisTime;
    String keyword;
    String output;
    Task thisTask;
    int number;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("Duke.txt");
        list = new TaskList(storage.load());
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    protected void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    protected String getResponse(String input) {
        return getOutput(input);
    }

    protected String getUndoResponse() {
        list.updateList();
        output = ui.printListMessage(list.getList());
        return output;
    }

    protected String getListResponse() {
        output = ui.printListMessage(list.getList());
        return output;
    }

    protected String getDoneResponse(String input) throws DukeException {
        // Duke does not support undone a task
        list.updateLastList();
        number = Character.getNumericValue(input.charAt(input.length() - 1));
        thisTask = list.get(number - 1);
        thisTask.markAsDone();
        list.set(number - 1, thisTask);
        output = ui.markAsDoneMessage(thisTask);
        storage.updateList(list.getList());
        return output;
    }

    protected String getDeleteResponse(String input) throws DukeException {
        list.updateLastList();
        number = Character.getNumericValue(input.charAt(input.length() - 1));
        thisTask = list.get(number - 1);
        list.remove(number - 1);
        output = ui.deletedTaskMessage(thisTask, list.getList());
        storage.updateList(list.getList());
        return output;
    }

    protected String getFindResponse(String input) {
        keyword = input.substring(5);
        output = ui.printFoundTaskMessage(keyword, list.getList());
        return output;
    }

    protected String getByeResponse() {
        output = ui.byeMessage();
        return output;
    }

    protected String getDeadlineResponse(String input) throws DukeException {
        list.updateLastList();
        if (input.length() < 10) {
            throw new DukeException("     The taskname of a deadline cannot be empty.");
        }
        output = ui.printSuccessMessage();
        thisTaskname = input.substring(9, input.indexOf('/') - 1);
        thisTime = input.substring(input.indexOf('/') + 4);
        thisDeadline = new Deadline(thisTaskname, false, thisTime);
        thisDeadline.updateDateTime();
        list.add(thisDeadline);
        storage.updateList(list.getList());
        output = output + ui.updatedTaskMessage(list.getList());
        return output;
    }

    protected String getEventResponse(String input) throws DukeException {
        list.updateLastList();
        if (input.length() < 7) {
            throw new DukeException("     The taskname of a event cannot be empty.");
        }
        output = ui.printSuccessMessage();
        thisTaskname = input.substring(6, input.indexOf('/') - 1);
        thisTime = input.substring(input.indexOf('/') + 4);
        list.add(new Event(thisTaskname, false, thisTime));
        storage.updateList(list.getList());
        output = output + ui.updatedTaskMessage(list.getList());
        return output;
    }

    protected String getTodoResponse(String input) throws DukeException {
        list.updateLastList();
        if (input.length() < 6) {
            throw new DukeException("     The taskname of a todo cannot be empty.");
        }
        output = ui.printSuccessMessage();
        thisTaskname = input.substring(5);
        list.add(new Todo(thisTaskname, false));
        storage.updateList(list.getList());
        output = output + ui.updatedTaskMessage(list.getList());
        return output;
    }

    protected String getOutput(String input) {

        thisInstruction = parser.load(input);
        try {
            if (thisInstruction == Instruction.UNDO) {
                output = getUndoResponse();
            } else if (thisInstruction == Instruction.LIST) {
                output = getListResponse();
            } else if (thisInstruction == Instruction.DONE) {
                output = getDoneResponse(input);
            } else if (thisInstruction == Instruction.DELETE) {
                output = getDeleteResponse(input);
            } else if (thisInstruction == Instruction.FIND) {
                output = getFindResponse(input);
            } else if (thisInstruction == Instruction.BYE) {
                output = getByeResponse();
            } else if (thisInstruction == Instruction.DEADLINE) {
                output = getDeadlineResponse(input);
            } else if (thisInstruction == Instruction.EVENT) {
                output = getEventResponse(input);
            } else if (thisInstruction == Instruction.TODO) {
                output = getTodoResponse(input);
            } else {
                throw new DukeException("     I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            output = ui.printErrorMessage(ex);
        }
        return output;
    }
}
