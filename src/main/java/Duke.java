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

/**
 * Duke is a program to keep track of and organise tasks, including todo tasks, event tasks, and deadline tasks.
 * 
 * @author Yan Lyn
 */
public class Duke {

    private static Storage storage;
    private TaskList inputs;
    private UI ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    /**
     * getResponse returns Duke's response to be shown on the user interface.
     * 
     * @throws DukeException 
     */
    public String getResponse(String input) throws DukeException {
        return new Duke("listStore.ser").run(input);
    }
    
    /**
     * Duke constructor constructs Duke object, while initializing 'ui', 'storage' and 'inputs' variables. 
     * The 'inputs' TaskList is retrieved from the file specified by the filepath.
     * 
     * @param filepath
     */
    public Duke(String filepath) {
        assert filepath == "listStore.ser";
        ui = new UI();
        storage = new Storage(filepath);
        try {
            inputs = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            inputs = new TaskList();
        }
    }
    
    //Empty Constructor
    public Duke() {
    }

    /**
     * The run() method runs the program by initializing a parser object to deal with making sense of user command 
     * and running the parse() method on the parser object.
     * The run() method directly returns the string "Bye. Hope to see you again soon!" if user input is "bye".
     * 
     * @return Duke's output in a string
     * @throws DukeException in case of DukeException when running parse() method on the parser object
     */
    public String run(String input) throws DukeException {
        while (true) {
           String nextLine = input;
            Parser parser = new Parser(nextLine, inputs);
            if (nextLine.equals("bye")) {
                return ("Bye. Hope to see you again soon!");
            }
            return parser.parse();
        }
    }

<<<<<<< HEAD
    /**
     * Parser class deals with making sense of user command.
     */
    public static class Parser {
        private final String userInput;
        private final TaskList savedTaskList;
=======
    public static void main (String[]args) throws DukeException {
        new Duke("listStore.ser").run("Hello");
    }
     
>>>>>>> branch-A-Assertions

        Parser(String nextLine, TaskList inputs) {
            this.userInput = nextLine;
            this.savedTaskList = inputs;
        }

        /**
         * Runs different methods on the TaskList based on user command.
         *
         * @throws DukeException if user command is not recognised or if methods in TaskList throw DukeException
         */
        String parse() throws DukeException {
            try {
                if (userInput.startsWith("done")) {
                    return savedTaskList.taskDone(userInput);
                } else if (userInput.startsWith("remove")) {
                    return savedTaskList.removeTask(userInput);
                } else if (userInput.startsWith("todo")) {
                    return savedTaskList.addTodoTask(userInput);
                } else if (userInput.startsWith("deadline")) {
                    return savedTaskList.addDeadlineTask(userInput);
                } else if (userInput.startsWith("event")) {
                    return savedTaskList.addEventTask(userInput);
                } else if (userInput.startsWith("find")) {
                    return savedTaskList.findTask(userInput);
                } else if (userInput.equals("list")) {
                    return savedTaskList.printList();
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.msg);
                return e.msg;
            }
        }
    }
    
    /**
     * Input class represents input from the user in the form of tasks.
     */
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

        /**
         * taskDone() method marks task as done by setting boolean 'done' as true.
         */
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

        /**
         * Constructor for Deadline Object.
         * Converts date specified by the user from a string to a LocalDate object, under the 'time' attribute.
         * Formats LocalDate and converts it back to a string for printing, under the 'printTime' attribute.
         * 
         * @param content
         * @param deadlineTime
         */
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

        /**
         * Constructor for Event Object.
         * Converts date specified by the user from a string to a LocalDate object, under the 'time' attribute.
         * Formats LocalDate and converts it back to a string for printing, under the 'printTime' attribute.
         *
         * @param content
         * @param eventTime
        */
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

    /**
     * DukeException class which inherits directly from Exception class.
     */
    public static class DukeException extends Exception {
        protected String msg;

        DukeException(String msg) {
            this.msg = msg;
        }
    }

    /** 
     * Storage Class deals with saving and retrieving data from a hard disk.
     */
    public static class Storage {
        private String filepath;

        Storage(String filepath) {
            this.filepath = filepath;
        }

        /**
<<<<<<< HEAD
         * Saves TaskList in a file which is based on the filepath in the Storage Class.
=======
         * Saves tasks in file specified by the filepath.
>>>>>>> branch-A-Assertions
         *
         * @param list of input tasks to be saved
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

        /**
         * Retrieves TaskList from a file based on the filepath in the Storage Class.
         *
         * @returns TaskList from file
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

    /** 
     * TaskList class represents the list of input tasks.
     */
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

        /**
<<<<<<< HEAD
         * Identifies task specified by the user and invokes taskDone() method from Input class to mark task as done.
         * Saves the resulting list to a hard disk.
=======
         * Marks task, as specified by user, as done.
>>>>>>> branch-A-Assertions
         *
         * @return A string representing Duke's response to be shown to user
         * @param nextLine representing user input
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

        /**
<<<<<<< HEAD
         * Identifies task specified by the user and removes the task from the list.
         * Saves the resulting list to the hard disk.
=======
         * removes task, as specified by the user, from list.
>>>>>>> branch-A-Assertions
         *
         * @return A string representing Duke's response to be shown to user
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify which task to remove or task is not in list
         */
        String removeTask(String nextLine) throws DukeException {
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
                return "Noted. I've removed this task:" + "\n" + inputType.id + "[/] " + inputType.content 
                        + inputType.printTime + "\n" + "Now you have " + inputs.size() + " tasks in the list.";
            } else {
                return "Noted. I've removed this task:" + "\n" + inputType.id + "[x] " + inputType.content
                        + inputType.printTime + "\n" + "Now you have " + inputs.size() + " tasks in the list.";
            }
        }

        /**
<<<<<<< HEAD
         * Adds a to-do object to the list based on user input.
         * Saves the resulting list to a hard disk.
=======
         * Adds a to-do object to the list, and saves the list to hard disk.
>>>>>>> branch-A-Assertions
         *
         * @return A string representing Duke's response to be shown to user
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify task to-do
         */
        String addTodoTask(String nextLine) throws DukeException {
            if (nextLine.equals("todo") || nextLine.equals("todo ")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(nextLine.substring(5));
            if (taskExist(todo.content, null)) {
                return "Could not add task. Task already exists!";
            }
            inputs.add(todo);
            int count = inputs.size();
            storage.writeToFile(inputs);
            assert inputs != null;
            return "Got it. I've added this task: \n" + "  [T][x] " + todo.content
                    + "\n Now you have " + count + " tasks in the list";
        }

        /**
         * Adds a deadline object to the list based on user input.
         * Saves the resulting list to a hard disk.
         *
         * @return A string representing Duke's response to be shown to user
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify deadline task 
         */
        String addDeadlineTask(String nextLine) throws DukeException {
            try {
                if (nextLine.equals("deadline") || nextLine.equals("deadline ")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                int charLoc = nextLine.indexOf("/by");
                Deadline deadline = new Deadline(nextLine.substring(9, charLoc), nextLine.substring(charLoc + 4));
                if (taskExist(deadline.content, deadline.time)) {
                    return "Could not add task. Task already exists!";
                }
                inputs.add(deadline);
                int count = inputs.size();
                storage.writeToFile(inputs);
                assert inputs != null;
                return ("Got it. I've added this task: \n" + "  [D][x] " + deadline.content
                        + deadline.printTime + "\n Now you have " + count + " tasks in the list");
            } catch (java.time.format.DateTimeParseException e) {
                return "Please provide proper date format!";
            }
        }

        /**
<<<<<<< HEAD
         * Adds an event object to the list based on user input.
         * Saves the resulting list to a hard disk.
=======
         * Adds an event object to the list, and saves the list to hard disk.
>>>>>>> branch-A-Assertions
         *
         * @return A string representing Duke's response to be shown to user
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify event task
         */
        String addEventTask(String nextLine) throws DukeException {
            try { 
                if (nextLine.equals("event") || nextLine.equals("event ")) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                }
                int charLoc = nextLine.indexOf("/at");
                Event event = new Event(nextLine.substring(6, charLoc), nextLine.substring(charLoc + 4));
                if (taskExist(event.content, event.time)) {
                    return "Could not add task. Task already exists!";
                }
                inputs.add(event);
                int count = inputs.size();
                storage.writeToFile(inputs);
                assert inputs != null;
                return ("Got it. I've added this task: \n" + "  [E][x] " + event.content
                        + event.printTime + "\n Now you have " + count + " tasks in the list");
            } catch (java.time.format.DateTimeParseException e) {
                return "Please provide proper date format!";
            }
<<<<<<< HEAD
=======
            int charLoc = nextLine.indexOf("/at");
            Event event = new Event(nextLine.substring(6, charLoc), nextLine.substring(charLoc + 4));
            inputs.add(event);
            int count = inputs.size();
            storage.writeToFile(inputs);
            assert inputs != null;
            return ("Got it. I've added this task: \n" + "  [E][x] " + event.content
                    + event.printTime + "\n Now you have " + count + " tasks in the list");
>>>>>>> branch-A-Assertions
        }

        /**
         * Find tasks which contains keyword specified by user.
         *
         * @return A list of tasks containing the keyword
         * @param nextLine, represents user input
         * @throws DukeException if user does not specify task to find
         */        
        String findTask(String nextLine) throws DukeException {
            if (nextLine.equals("find") || nextLine.equals("find ")) {
                throw new DukeException("OOPS!!! The description of find cannot be empty");
            }
            String keyword = nextLine.substring(5);
            int len = inputs.size();
            String stringList = "Tasks Found: \n";
            for (int i = 0; i < len; i++) {
                Input input = inputs.get(i);
                if (input.content.contains(keyword)) {
                    stringList += printTask(input, i);
                }
            }
            return stringList;
        }

        /**
         * Prints a list of the tasks in the TaskList.
         * 
         * @return list of tasks in TaskList
         */
        String printList() {
            if (this.inputs.size() == 0) {
                return "No tasks in list";
            } else {
                int len = this.inputs.size();
                String stringList = "Here are the tasks in your list: \n";
                for (int i = 0; i <= len-1; i++) {
                    Input input = this.inputs.get(i);
                    stringList += printTask(input, i);
                }
                return stringList;
            }
        }

        /**
         * Prints input task to a string, to be shown to the user as Duke's response.
         * Prints string with '[/]' if input task is done, otherwise prints string with '[x]'.
         * 
         * @param input, which represents the input task to print to string.
         * @param i, which is the index of the task in the list
         * @return string to be shown to user
         */
        String printTask(Input input, int i) {
            if (input.done) {
                return ((i+1) + ". " + input.id + "[/] " + input.content + input.printTime + "\n");
            } else {
                return ((i+1) + ". " + input.id + "[x] " + input.content + input.printTime + "\n");
            }
        }

        /**
         * Checks if task already exists to avoid duplicates.
         * @param taskContent
         * @return true if task exists, false if task does not exist
         */
        boolean taskExist(String taskContent, LocalDate taskDate) {
            int len = inputs.size();
            for(int i = 0; i < len; i++) {
                Input taskFromList = inputs.get(i);
                if (taskFromList.content.contains(taskContent) && taskDateMatch(taskFromList, taskDate)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Checks if the date of two tasks are the same. 
         * @param task 
         * @param taskDate 
         * @return true if the date of both tasks matches or the second task does not have a date 
         */
        boolean taskDateMatch(Input task, LocalDate taskDate) {
            if (taskDate == null) {
                return true;
            }
            if (task.time == null) {
                return false;
            } else if (task.time.isEqual(taskDate)) {
                return true;
            } else {
                return false;
            }
        }
        
    }

    /** 
     * UI class deals with the User Input.
     */
    public static class UI {

<<<<<<< HEAD
        /**
         * Prints 'Loading Error!' if DukeException is caught while constructing Duke Object.
=======
        /** 
         * Guides the TaskList based on user command.
         * 
         * @throws DukeException
>>>>>>> branch-A-Assertions
         */
        void showLoadingError() {
            System.out.println("Loading Error!");
        }


    }
}
