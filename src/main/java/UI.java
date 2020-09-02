import java.io.IOException;
import java.util.Scanner;

/**
 * The user interface class that communicates with the user and the system to get
 * what the user wants.
 */
public class UI {

    private Storage storage;
    private Parser parser;

    private UI(Storage storage) {
        this.storage = storage;
        this.parser = new Parser();
    }

    /**
     * Initialises the UI with the storage.
     * @param storage The storage that the UI is going to have access to.
     * @return UI The UI that is now ready to communicate with the storage.
     */
    public static UI initialise(Storage storage) {
        return new UI(storage);
    }

    /**
     * Reads a starting message at the start of UI.
     */
    public String startUpMessage(){
        return "Hi! I'm Duke" + this.readSavedTasks() + "\n" + "What can I do for you?";
    }

    /**
     * Reads the list of saved tasks in the storage.
     */
    public String readSavedTasks() {
        int size = this.storage.getSizeofTasks();

        if (size == 0) {
            return "You currently have no tasks!";
        } else {
            String tasks = "Here are your current tasks:";
            for (int i = 0; i < size; i++) {
                tasks += "\n" + "  " + this.storage.getListTask(i);
            }

            return tasks;
        }
    }

    /**
     * Catches the exception and reads the corresponding message of the exception.
     */
    public String showError(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Reads the user input and communicates with the storage and parser on what to do next.
     * @throws IOException If the storage cannot find the file containing the saved tasks.
     */
    public void processInput() throws IOException {
        if (this.storage.isInitialised()) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            while (!this.parser.isTerminateCommand(input)) {
                try {
                    //Get the result of the parse
                    int parseResult = this.parser.parse(input, this.storage.getSizeofTasks());

                    if (isListCommand(parseResult)) {
                        //List Command
                        this.readSavedTasks();

                    } else if (isDoneCommand(parseResult)) {
                        //Done command
                        int index = this.parser.getIndex(input);

                        this.storage.markDone(index);

                        System.out.println("Nice! I've marked this task as done:" + "\n" + "  " + this.storage.getListTask(index).toString());
                    } else if (isDeleteCommand(parseResult)) {
                        //Delete command
                        int index = this.parser.getIndex(input);

                        System.out.println("Noted. I've removed this task:" + "\n" + "  " + this.storage.getListTask(index)
                                + "\n" + "Now you have " + (this.storage.getSizeofTasks() - 1) + " tasks in the list.");

                        this.storage.deleteTask(index);

                    } else if (isFindCommand(parseResult)) {
                        //Find command
                        String keyword = this.parser.getKeyword(input);
                        System.out.println(" Here are the matching tasks in your list:");

                        int size = this.storage.getSizeofTasks();

                        int increment = 1;
                        for (int i = 0; i < size; i++) {
                            if (this.storage.getListTask(i).toString().contains(keyword)) {
                                System.out.println("  " + increment + ". " + this.storage.getListTask(i).toString());
                                increment++;
                            }
                        }


                    } else {
                        //Is a task command
                        Task newTask = this.parser.getTask(input);

                        //Add newTask to the task list
                        this.storage.addTask(newTask);

                        System.out.println("Got it. I've added this task:" + "\n" + "  " +
                                this.storage.getListTask(this.storage.getSizeofTasks() - 1) +
                                "\n" + "Now you have " + this.storage.getSizeofTasks() + " tasks in the list.");
                    }
                    input = scanner.nextLine();
                } catch (InvalidCommandException e) {
                    System.out.println(e + "\n" + "Please enter a valid command");
                    input = scanner.nextLine();
                } catch (InvalidInputException e) {
                    System.out.println(e + "\n" + "Please enter a valid input");
                    input = scanner.nextLine();
                }
            }
            System.out.println("Bye! See you again!");
            this.storage.save();
        } else {
            System.out.println("Storage not initialised!");
        }
    }

    public String processInput(String input) throws IOException {
        if (this.storage.isInitialised()) {

            try {
                //Get the result of the parse
                int parseResult = this.parser.parse(input, this.storage.getSizeofTasks());

                if (isListCommand(parseResult)) {
                    //List Command
                    return this.readSavedTasks();
                } else if (isDoneCommand(parseResult)) {
                    //Done command
                    int index = this.parser.getIndex(input);

                    this.storage.markDone(index);

                    return "Nice! I've marked this task as done:" + "\n" + "  " + this.storage.getListTask(index).toString();
                } else if (isDeleteCommand(parseResult)) {
                    //Delete command
                    int index = this.parser.getIndex(input);
                    Task toBeDeleted = this.storage.getListTask(index);
                    this.storage.deleteTask(index);

                    return "Noted. I've removed this task:" + "\n" + "  " + toBeDeleted
                            + "\n" + "Now you have " + (this.storage.getSizeofTasks()) + " tasks in the list.";

                } else if (isFindCommand(parseResult)) {
                    //Find command
                    String keyword = this.parser.getKeyword(input);

                    int size = this.storage.getSizeofTasks();

                    int increment = 1;
                    String listOfMatches = " Here are the matching tasks in your list:";
                    for (int i = 0; i < size; i++) {
                        if (this.storage.getListTask(i).toString().contains(keyword)) {
                            listOfMatches += "\n" + "  " + increment + ". " + this.storage.getListTask(i).toString();
                            increment++;
                        }
                    }

                    return listOfMatches;

                } else if (parser.isTerminateCommand(input)) {
                    this.storage.save();

                    return "Bye see you again!";
                } else {
                    //Is a task command
                    Task newTask = this.parser.getTask(input);

                    //Add newTask to the task list
                    this.storage.addTask(newTask);

                    return "Got it. I've added this task:" + "\n" + "  " +
                            this.storage.getListTask(this.storage.getSizeofTasks() - 1) +
                            "\n" + "Now you have " + this.storage.getSizeofTasks() + " tasks in the list.";
                }
            } catch (InvalidCommandException e) {
                return e + "\n" + "Please enter a valid command";
            } catch (InvalidInputException e) {
                return e + "\n" + "Please enter a valid input";
            }

    } else {
        return "Storage not initialised!";
    }
}

    private boolean isListCommand(int num) {
        return num == 1;
    }

    private boolean isDoneCommand(int num) {
        return num == 2;
    }

    private boolean isDeleteCommand(int num) {
        return num == 3;
    }

    private boolean isFindCommand(int num) {
        return num == 4;
    }

}
