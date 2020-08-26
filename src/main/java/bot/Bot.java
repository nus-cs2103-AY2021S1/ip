package bot;

import java.io.IOException;
import java.util.Scanner;

public class Bot {
    private String name;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Bot(String name, String filePath) {
        this.name = name;
        this.ui = new Ui(this.name);
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try{
            this.taskList = new TaskList(storage.loadFileContents());
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList =  new TaskList();
        }
    }

    /**
     * Start the bot's interaction with user.
     */
    public void init(Scanner scanner) {
        ui.greet();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    scanner.close();
                    ui.goodbye();
                    return;
                }
                Command cmd = parser.parseInput(input);
                switch(cmd) {
                    case LIST:
                        ui.showMessage(cmdList());
                        break;
                    case TODO:
                        ui.showMessage(cmdTodo(parser.parseTodo(input)));
                        break;
                    case DEADLINE:
                        ui.showMessage(cmdDeadline(parser.parseDeadline(input)));
                        break;
                    case EVENT:
                        ui.showMessage(cmdEvent(parser.parseEvent(input)));
                        break;
                    case DONE:
                        ui.showMessage(cmdDone(parser.parseIndex(input)));
                        break;
                    case DELETE:
                        ui.showMessage(cmdDelete(parser.parseIndex(input)));
                        break;
                    default:
                        ui.showMessage("Valid cmd given. " +
                                "However it is not supported yet");
                        break;
                }
            } catch (InvalidCommandException | InvalidInputException e) {
                System.out.println(responseWrapper(e.getMessage()));
            }
        }
    }

    private String cmdTodo(String name) throws InvalidInputException {
        try {
            Todo newTodo = new Todo(name);
            this.taskList.add(newTodo);
            storage.saveUserData(this.taskList);
            return "Got it. I've added this task:\n    " +
                    newTodo + "\n    " +
                    "Now you have " + taskList.getSize() + " tasks in the list.";
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String cmdDeadline(String[] words) throws InvalidInputException {
        try {
            Deadline newTask = new Deadline(words[0], words[1]);
            this.taskList.add(newTask);
            storage.saveUserData(this.taskList);
            return "Got it. I've added this task:\n    " +
                    newTask + "\n    " +
                    "Now you have " + taskList.getSize() + " tasks in the list.";
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String cmdEvent(String[] words) throws InvalidInputException {
        try {
            Event newTask = new Event(words[0], words[1]);
            this.taskList.add(newTask);
            storage.saveUserData(this.taskList);
            return "Got it. I've added this task:\n    " +
                    newTask + "\n    " + "Now you have " +
                    taskList.getSize() + " tasks in the list.";
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String cmdList() {
        int index = 0;
        StringBuilder string = new StringBuilder("Here are the tasks in your list:\n    ");
        for (Task item : taskList.getList()) {
            index++;
            string.append(index).append(".").append(item).append("\n    ");
        }
        if (index == 0) {
            return responseWrapper("Nothing in the list");
        }
        string.delete(string.length() - 5, string.length());
        return string.toString();
    }

    private String cmdDone(int index) throws InvalidInputException {
        try {
            Task task = this.taskList.get(index);
            task.markAsDone();
            storage.saveUserData(this.taskList);
            return "Nice! I've marked this task as done: \n    " +
                    task + "\n";
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String cmdDelete(int index) throws InvalidInputException {
        try {
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            storage.saveUserData(this.taskList);
            return "Noted. I've removed this task: \n    " +
                    task + "\n    " +
                    "Now you have " + taskList.getSize() + " tasks in the list.";
        } catch (Exception e) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
    }

    private String responseWrapper(String str) {
        final String TEXT_LINE = "________________________________________________________________";
        return TEXT_LINE + "\n    " + str + "\n" + TEXT_LINE;
    }
}
