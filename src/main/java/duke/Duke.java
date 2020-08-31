package duke;

import duke.commands.Command;
import duke.commands.CommandHandler;
import duke.tasks.TaskManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * Main class that will oversee the running of the program.
 */
public class Duke extends Application {
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
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show();
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
}
