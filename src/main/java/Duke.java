import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Duke Class
public class Duke {

    private static Storage storage;
    private TaskList inputs;
    private UI ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /* 
    Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
    @Override
    public void start(Stage stage) {
        // Step 1
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
        // Step 2
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
        //Step 3
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     
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
    public String getResponse(String input) throws DukeException, UnsupportedEncodingException {
        //return "Duke heard: " + input;
        /*ByteArrayOutputStream os = new ByteArrayOutputStream();
        String utf8 = StandardCharsets.UTF_8.name();
        try (PrintStream ps = new PrintStream(os, true, utf8)) {
            new Duke("listStore.ser").run(input);
        }
        String data = new String(os.toByteArray(), utf8);
        System.out.println(data);
        return data;
        */
        return new Duke("listStore.ser").run(input);
    }
    
    /**
     * Duke runs the program, and ends process when user input is "bye"
     * @param filepath
     * @throws DukeException if an exception arises while reading input or creating parser object
     */
    public Duke(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        try {
            inputs = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            inputs = new TaskList();
        }
    }
    public Duke() {
        
    }

    /**
     * run() method constructs Duke Object, constructs UI,
     * storage and TaskList object to initialize
     * @return
     */
    public String run(String input) throws DukeException {
        while (true) {
           // String nextLine = ui.readInput();
           String nextLine = input;
            Parser parser = new Parser(nextLine, inputs);
            if (nextLine.equals("bye")) {
                return ("Bye. Hope to see you again soon!");
            }
            return parser.parse();
        }
    }

//    public static void main (String[]args) throws DukeException {
//        new Duke("listStore.ser").run();
//    }
     

    // Input class represents user inputted tasks
    public static class Input implements Serializable {
        boolean done = false;
        String content;
        String id;
        LocalDate time;
        String printTime;

        Input(String content) {
            this.content = content;
            boolean done = false;
        }

        Input(boolean done, String content) {
            this.done = done;
            this.content = content;
        }

        // Marks task as done
        public void taskDone() {
            System.out.println("check");
            this.done = true;
        }
    }

    public static class Todo extends Input {

        Todo(String content) {
            super(content);
            this.id = "[T]";
            this.time = null;
            this.printTime = "";
        }

        Todo(boolean done, String content) {
            super(done, content);
            this.id = "[T]";
            this.time = null;
            this.printTime = "";
        }
    }

    public static class Deadline extends Input {

        // Constructor for Deadline Object, converts time string to LocalDate and formats for printing
        Deadline(String content, String deadlineTime) {
            super(content);
            this.time = LocalDate.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.id = "[D]";
            this.printTime = "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }

        Deadline(boolean done, String content, String deadlineTime) {
            super(done, content);
            this.time = LocalDate.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.id = "[D]";
            this.printTime = "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    public static class Event extends Input {

        // Constructor for Event Object, converts time string to LocalDate and formats for printing
        Event(String content, String eventTime) {
            super(content);
            this.time = LocalDate.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.id = "[E]";
            this.printTime = "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }

        Event(boolean done, String content, String eventTime) {
            super(done, content);
            this.time = LocalDate.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.id = "[E]";
            this.printTime = "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }


    public static class DukeException extends Exception {
        protected String msg;

        DukeException(String msg) {
            this.msg = msg;
        }
    }

    // Storage Class deals with saving and retrieving data from a hard disk
    public static class Storage {
        private String filepath;

        Storage(String filepath) {
            this.filepath = filepath;
        }

        /*
         * Saves tasks in file specified by the filepath
         *
         * @param list of tasks
         * @catch IOException
         */
        void writeToFile(List<Input> list) {
            try {
                FileOutputStream writeData = new FileOutputStream(filepath);
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
                writeStream.writeObject(list);
                writeStream.flush();
                writeStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
         * Retrieves tasks from file specified by the filepath
         *
         * @catch FileNotFoundException if file does not exist, creates a new arraylist
         * @catch IOException and ClassNotFoundException, returns null
         */
        List<Input> readFile() {
            try {
                FileInputStream readData = new FileInputStream(filepath);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                Object obj = readStream.readObject();
                if (obj != null) {
                    List<Input> inputList = (List<Input>) obj;
                    readStream.close();
                    return inputList;
                } else {
                    return null;
                }
            } catch (FileNotFoundException e) {
                return new ArrayList<>();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    // TaskList represents the list of input tasks
    public static class TaskList {
        List<Input> inputs;

        TaskList() {
            this.inputs = new ArrayList<Input>();
        }

        TaskList(List<Input> inputs) throws DukeException {
            try {
                this.inputs = inputs;
            } catch (Exception e) {
                throw new DukeException("Error making task!");
            }
        }

        /*
         * Marks task, as specified by user, as done
         *
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify which task done, or if task specified is not in list
         */
        String taskDone(String nextLine) throws DukeException {
            if (nextLine.equals("done") || nextLine.equals("done ")) {
                throw new DukeException("OOPS!!! The description of done cannot be empty.");
            }
            int numTaskDone = Integer.valueOf(nextLine.substring(5));
            if (numTaskDone > inputs.size()) {
                throw new DukeException("OOPS!!! Task does not exist.");
            } else if (inputs.size() == 0) {
                throw new DukeException("OOPS!!! List is empty!");
            } else { 
                Input inputType = inputs.get(numTaskDone - 1);
                inputType.taskDone();
                storage.writeToFile(inputs);
                return ("Nice! I've marked this task as done:" + "\n" + "[/] " + inputType.content + " " + inputType.printTime);
            }
        }

        /*
         * removes task, as specified by the user, from list
         *
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify which task to remove or task is not in list
         */
        String taskRemove(String nextLine) throws DukeException {
            if (nextLine.equals("remove") || nextLine.equals("remove ")) {
                throw new DukeException("OOPS!!! The description of remove cannot be empty");
            }
            int numTaskDone = Integer.valueOf(nextLine.substring(7));
            if (numTaskDone > inputs.size()) {
                throw new DukeException("OOPS!!! Task does not exist.");
            }
            Input inputType = inputs.get(numTaskDone - 1);
            inputs.remove(numTaskDone - 1);
            storage.writeToFile(inputs);
            if (inputType.done) {
                return ("Noted. I've removed this task:" + "\n" + inputType.id + "[/] " + inputType.content 
                        + inputType.printTime + "\n" + "Now you have " + inputs.size() + " tasks in the list.");
            } else {
                return ("Noted. I've removed this task:" + "\n" + inputType.id + "[x] " + inputType.content
                        + inputType.printTime + "\n" + "Now you have " + inputs.size() + " tasks in the list.");
            }
        }

        /*
         * Adds a to-do object to the list, and saves the list to hard disk
         *
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify task to-do
         */
        String taskTodo(String nextLine) throws DukeException {
            if (nextLine.equals("todo") || nextLine.equals("todo ")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(nextLine.substring(5));
            inputs.add(todo);
            int count = inputs.size();
            storage.writeToFile(inputs);
            return "Got it. I've added this task: \n" + "  [T][x] " + todo.content
                    + "\n Now you have " + count + " tasks in the list";
        }

        /*
         * Adds a deadline object to the list, and saves the list to hard disk
         *
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify task
         */
        String taskDeadline(String nextLine) throws DukeException {
            try {
                if (nextLine.equals("deadline") || nextLine.equals("deadline ")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                int charLoc = nextLine.indexOf("/by");
                Deadline deadline = new Deadline(nextLine.substring(9, charLoc), nextLine.substring(charLoc + 4));
                inputs.add(deadline);
                int count = inputs.size();
                storage.writeToFile(inputs);
                return ("Got it. I've added this task: \n" + "  [D][x] " + deadline.content
                        + deadline.printTime + "\n Now you have " + count + " tasks in the list");
            } catch (java.time.format.DateTimeParseException e) {
                return "Please provide proper date format!";
            }
        }

        /*
         * Adds an event object to the list, and saves the list to hard disk
         *
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify task
         */
        String taskEvent(String nextLine) throws DukeException {
            if (nextLine.equals("event") || nextLine.equals("event ")) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            int charLoc = nextLine.indexOf("/at");
            Event event = new Event(nextLine.substring(6, charLoc), nextLine.substring(charLoc + 4));
            inputs.add(event);
            int count = inputs.size();
            storage.writeToFile(inputs);
            return ("Got it. I've added this task: \n" + "  [E][x] " + event.content
                    + event.printTime + "\n Now you have " + count + " tasks in the list");
        }

        String taskFind(String nextLine) throws DukeException {
            if (nextLine.equals("find") || nextLine.equals("find ")) {
                throw new DukeException("OOPS!!! The description of find cannot be empty");
            }
            String keyword = nextLine.substring(5);
            int len = inputs.size();
            for (int i = 0; i < len; i++) {
                Input input = inputs.get(i);
                if (input.content.contains(keyword)) {
                    if (input.done) {
                        return ((i + 1) + ". " + input.id + "[/] " + input.content
                                + input.printTime);
                    } else {
                        return ((i + 1) + ". " + input.id + "[x] " + input.content
                                + input.printTime);
                    }
                }
            }
            return "";
        }
    }

    // UI class deals with User Input
    public static class UI {
        private Scanner sc = new Scanner(System.in);

        // UI Constructor, prints DUKE as part of initialization
        UI() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            System.out.println("Hello! I'm Duke \n"
                    + "What can I do for you?");
        }

        // Reads user input using scanner
        String readInput() {
            return this.sc.nextLine();
        }

        // Prints error if DukeException is caught when constructing Duke Object
        void showLoadingError() {
            System.out.println("Loading Error!");
        }


    }

    //Parser class deals with making sense of user command
    public static class Parser {
        private final String nextLine;
        private final TaskList inputs;

        Parser(String nextLine, TaskList inputs) {
            this.nextLine = nextLine;
            this.inputs = inputs;
        }

        /** Guides the TaskList based on user command
         * @throws DukeException
         */
        String parse() throws DukeException {
            try {
                if (nextLine.startsWith("done")) {
                    return inputs.taskDone(nextLine);
                } else if (nextLine.startsWith("remove")) {
                    return inputs.taskRemove(nextLine);
                } else if (nextLine.startsWith("todo")) {
                    return inputs.taskTodo(nextLine);
                } else if (nextLine.startsWith("deadline")) {
                    return inputs.taskDeadline(nextLine);
                } else if (nextLine.startsWith("event")) {
                    return inputs.taskEvent(nextLine);
                } else if (nextLine.startsWith("find")) {
                    return inputs.taskFind(nextLine);
                } else if (nextLine.equals("list")) {
                    if (inputs.inputs.size() == 0) {
                        return "No tasks in list";
                    } else {
                        int len = inputs.inputs.size();
                        String stringList = "Here are the tasks in your list: \n";
                        for (int i = 0; i <= len-1; i++) {
                            Input inputType = inputs.inputs.get(i);
                            if (inputType.done) {
                                stringList += ((i+1) + ". " + inputType.id + "[/] " + inputType.content + inputType.printTime + "\n");
                            } else {
                                stringList += ((i+1) + ". " + inputType.id + "[x] " + inputType.content + inputType.printTime + "\n");
                            }
                        }
                        return stringList;
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.msg);
                return e.msg;
            }
        }
    }
}
