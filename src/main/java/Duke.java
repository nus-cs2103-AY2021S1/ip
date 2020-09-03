import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents an interactive bot to handle tasks
 */
public class Duke extends Application {
    // add: javafx
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
    // add: start method

    /*
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
    */

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // more code to be added here later
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    // !!!!!!!!!!!!!!! to be changed
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }


    // constant SPACE and LINE for format purposes
    public static String SPACE = "     ";
    public static String LINE = "____________________________________________________________\n";
    // add outer frame lines
    /**
     * Returns formatted string, adding SPACE and LINE
     *
     * @param input string to be formatted
     * @return formatted string
     */
    public static String format(String input) {
        return SPACE + LINE +
               SPACE + " " + input + "\n" +
               SPACE + LINE;
    }

    public static void main(String[] args) throws Exception{
        // greeting and exit messages strings
        // list and mark strings
        final String messageHello = "Hello! I'm Duke\n" + SPACE + " " + "What can I do for you?";
        final String messageBye = "Bye. Hope to see you again soon!";
        final String messageList = "Here are the task(s) in your list:\n";
        final String messageMarked = "Nice! I've marked this task as done:\n";
        final String messageAdded = "Got it. I've added this task:\n";
        final String messageDelete = "Noted. I've removed this task:\n";
        final String messageMatching = "Here are the matching tasks in your list:\n";
        final String home = System.getProperty("user.home");

        java.nio.file.Path path = java.nio.file.Paths.get(home, "ip","start.txt");
        boolean directoryExists = java.nio.file.Files.exists(path);
        File file = new File(path.toString());

        // set up scanner
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInitial = new Scanner(file);

        // set up empty list of things to do
        List<Task> list = new ArrayList<>();

        // add tasks to init list
        while (scannerInitial.hasNextLine()) {
            String task = scannerInitial.nextLine();
            list.add(Convert.add(task));
        }

        System.out.println(format(messageHello));
        System.out.print(SPACE + LINE);
        System.out.print(SPACE + messageList);
        int i = 1;
        for (Task task : list) {
            System.out.println(SPACE + " " + i + "." + task.getTypeLetter()
                    + task.getStatusIcon() + task.getPrintMessage());
            i++;
        }
        System.out.println(SPACE + LINE);

        if (!directoryExists) {
            System.out.println(format("OOPS!!! Cannot find start file"));
            throw new FileNotFoundException();
        }


        // continue if have more commands (that are not "bye")
        while (scanner.hasNextLine()) {
            String currentCommand = scanner.nextLine();
            String priorCommand = currentCommand.split(" ")[0];

            if (currentCommand.equals("bye")) {
                System.out.println(format(messageBye));
                scanner.close();
                FileWriter fileWriter = new FileWriter("start.txt");
                String content;
                for (i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    content = task.getPureTypeLetter() + " ; " + task.getStatusNum()
                            + " ; " + task.getStoreMessage() + "\n";
                    fileWriter.write(content);
                }
                fileWriter.close();
                break;
            } else if (currentCommand.equals("list")) {
                System.out.print(SPACE + LINE);
                System.out.print(SPACE + messageList);
                int counter = 1;
                for (Task task : list) {
                    System.out.println(SPACE + " " + counter + "." + task.getTypeLetter()
                            + task.getStatusIcon() + task.getPrintMessage());
                    counter++;
                }
                System.out.println(SPACE + LINE);

            } else {
                try {
                    String extraCommand = currentCommand.split(" ", 2)[1];
                    if (priorCommand.equals("done")) {
                        int index = Integer.parseInt(extraCommand) - 1;
                        if (index < 0 || index >= list.size()) {
                            throw new InvalidDoneException();
                        }
                        Task task = list.get(index);
                        task.setDone();
                        System.out.println(format(messageMarked + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()));
                    } else if (priorCommand.equals("delete")) {
                        int index = Integer.parseInt(extraCommand) - 1;
                        if (index < 0 || index >= list.size()) {
                            throw new InvalidDeletionException();
                        }
                        Task task = list.get(index);
                        list.remove(index);
                        String messageNum = "\n      Now you have " + list.size() + " task(s) in the list.";
                        System.out.println(format(messageDelete + "        " + task.getTypeLetter()
                                + task.getStatusIcon() + task.getPrintMessage() + messageNum));

                    } else if (priorCommand.equals("find")) {
                        List<Task> subList = new ArrayList<>();
                        // check whether extraCommand is a subString of any task message
                        for (Task task: list) {
                            if (task.getMessage().contains(extraCommand)) {
                                subList.add(task);
                            }
                        }
                        System.out.print(SPACE + LINE);
                        System.out.print(SPACE + messageMatching);
                        int counter = 1;
                        for (Task task : subList) {
                            System.out.println(SPACE + " " + counter + "." + task.getTypeLetter()
                                    + task.getStatusIcon() + task.getPrintMessage());
                            counter++;
                        }
                        System.out.println(SPACE + LINE);
                    } else {
                        Task task;
                        switch (priorCommand) {
                        case "deadline":
                            task = new Deadline(extraCommand);
                            break;
                        case "event":
                            task = new Event(extraCommand);
                            break;
                        case "todo":
                            task = new Todo(extraCommand);
                            break;
                        default:
                            // throw exception
                            throw new IndexOutOfBoundsException();
                        }

                        list.add(task);
                        System.out.println(format(messageAdded + SPACE + "   "
                                + task.getTypeLetter() + task.getStatusIcon() + task.getPrintMessage()
                                + "\n " + SPACE + "Now you have " + list.size() + " task(s) in the list."));
                    }
                } catch (IndexOutOfBoundsException e) {
                    // handle exception
                    IndexOutOfBoundsException ex;
                    switch (priorCommand) {
                    case "todo":
                        ex = new TodoEmptyBodyException();
                        break;
                    case "event":
                        ex = new EventEmptyBodyException();
                        break;
                    case "deadline":
                        ex = new DeadlineEmptyBodyException();
                        break;
                    case "delete":
                        ex = new DeleteEmptyBodyException();
                        break;
                    case "done":
                        ex = new DoneEmptyBodyException();
                        break;
                    default:
                        ex = new UnknownCommandException();
                        break;
                    }
                    System.out.println(format(ex.toString()));
                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println(format(new InvalidDateException().toString()));
                } catch (Exception e) {
                    System.out.println(format(e.toString()));
                }
            }
        }
    }
}