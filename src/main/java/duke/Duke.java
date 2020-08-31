package duke;

import duke.commands.Command;
import duke.commands.CommandHandler;
import duke.tasks.TaskManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * Main class that will oversee the running of the program.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
//    Ui ui;
//    TaskManager taskManager;
//    CommandHandler commandHandler;
//
//    Duke() {
//        this.ui = new Ui();
//        this.commandHandler = new CommandHandler();
//        try {
//            this.taskManager = new TaskManager(new Storage().load());
//        } catch (DukeException e) {
//            System.out.println(e);
//        }
//    }

    /**
     * Starts the program.
     */
    @Override
    public void start(Stage stage) {
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

        stage.show();

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
//        ui.showStartScreen();
//        boolean running = true;
//        Scanner sc = new Scanner(System.in);
//        while (running) {
//            ui.askForCommand();
//            String s = sc.nextLine();
//            Command cmd = CommandHandler.parseCommand(s);
//            cmd.setUtility(taskManager, ui, sc);
//            try {
//                boolean result = cmd.execute();
//                running = result;
//            } catch (DukeException e) {
//                System.out.println(e);
//            }
//        }
//        ui.showExitScreen();
//        sc.close();
    }

//    public static void main(String[] args) {
//        Application.launch(Duke.class, args);
//        new Duke().start();
//        return;
//    }
    //     String line = "------------------------";

    //     System.out.println(line);
    //     duke.tasks.TaskManager tm;
    //     tm = new duke.tasks.TaskManager();
    //     Scanner sc = new Scanner(System.in);
        
    //     while(true) {
    //         System.out.print("your input: ");
    //         String cmd = sc.nextLine();
    //         System.out.println("\n" + line);
    //         if (cmd.equals("bye")) {
    //             end();
    //             break;
    //         }
    //         try {
    //             handleCmd(cmd, sc, tm);
    //         } catch (duke.DukeException e) {
    //             System.out.println(e);
    //         }
    //         System.out.println(line);
    //     }
    // }

    // private static void handleCmd(String cmd, Scanner sc, duke.tasks.TaskManager tm) throws duke.DukeException {
    //     switch(cmd) {
    //         case "list":
    //             tm.listTasks();
    //             break;
    //         case "complete":
    //             complete(sc, tm);
    //             break;
    //         case "todo":
    //             try {
    //                 todo(sc, tm);
    //             } catch (duke.DukeException e) {
    //                 throw e;
    //             }
    //             break;
    //         case "deadline":
    //             try {
    //                 deadline(sc, tm);
    //             } catch (duke.DukeException e) {
    //                 throw e;
    //             }
    //             break;
    //         case "event":
    //             try {
    //                 event(sc, tm);
    //             } catch (duke.DukeException e) {
    //                 throw e;
    //             }
    //             break;
    //         case "delete":
    //             try {
    //                 delete(sc, tm);
    //             } catch (duke.DukeException e) {
    //                 throw e;
    //             }
    //             break;
    //         default:
    //             throw new duke.DukeException("That was an invalid command");
    //     }
    // }

    // private static void end() {
    //      System.out.println("Bye. Hope to see you again soon!");       
    // }

    // private static void complete(Scanner sc, duke.tasks.TaskManager tm) throws duke.DukeException {
    //     System.out.println("Which task do you wish to mark complete? ");
    //     int taskNum = Integer.parseInt(sc.nextLine());
    //     try {
    //         tm.setCompleted(taskNum);
    //     } catch (IndexOutOfBoundsException e) {
    //         throw new duke.DukeException("You did not provide a valid task number!");
    //     }
    // }

    // private static void todo(Scanner sc, duke.tasks.TaskManager tm) throws duke.DukeException {
    //     System.out.println("what is the name of the todo: ");
    //     String todoName = sc.nextLine();
    //     if (todoName.isEmpty()) {
    //         throw new duke.DukeException("You must provide a name for the todo task!");
    //     }
    //     tm.add(new duke.tasks.Todo(todoName));
    //     System.out.println("*added: " + todoName);
    // }

    // private static void deadline(Scanner sc, duke.tasks.TaskManager tm) throws duke.DukeException {
    //     System.out.println("what is the name of the task: ");
    //     String deadlineName = sc.nextLine();
    //     if (deadlineName.isEmpty()) {
    //         throw new duke.DukeException("You must provide a name for the deadline task!");
    //     }
    //     System.out.println("when is the due date (Day Month Year): ");
    //     String dueDate = sc.nextLine();
    //     if (dueDate.isEmpty()) {
    //         throw new duke.DukeException("You must provide a due date for the deadline task!");
    //     }
    //     try {
    //         tm.add(new duke.tasks.Deadline(deadlineName, dueDate));
    //         System.out.println("*added: " + deadlineName);
    //     } catch (duke.DukeException e) {
    //         throw e;
    //     }
    // }

    // private static void event(Scanner sc, duke.tasks.TaskManager tm) throws duke.DukeException {
    //     System.out.println("what is the name of the event: ");
    //     String eventName = sc.nextLine();
    //     if (eventName.isEmpty()) {
    //         throw new duke.DukeException("You must provide a name for the event!");
    //     }
    //     System.out.println("when does the event start (Day Month Year Hour:Minute): ");
    //     String start = sc.nextLine();
    //     if (start.isEmpty()) {
    //         throw new duke.DukeException("You must provide a start time for the event!");
    //     }
    //     System.out.println("when does the event end (Day Month Year Hour:Minute): ");
    //     String end = sc.nextLine();
    //     if (end.isEmpty()) {
    //         throw new duke.DukeException("You must provide an end time for the event!");
    //     }
    //     try {
    //         tm.add(new duke.tasks.Event(eventName, start, end));
    //         System.out.println("*added: " + eventName);
    //     } catch (duke.DukeException e) {
    //         throw e;
    //     }
        
    // }

    // private static void delete (Scanner sc, duke.tasks.TaskManager tm) throws duke.DukeException {
    //     System.out.println("Which task would you like to remove: ");
    //     int taskNum = Integer.parseInt(sc.nextLine());
    //     try {
    //         tm.deleteTask(taskNum);
    //     } catch (IndexOutOfBoundsException e) {
    //         throw new duke.DukeException("You did not provide a valid task number!");
    //     }
    // }

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
}
