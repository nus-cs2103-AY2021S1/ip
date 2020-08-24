import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private final String border = "----------------------------------------------------------------------------\n";
    private boolean isRunning = true;
    private final TaskList taskList = new TaskList();
    enum commands {
        BYE,
        DONE,
        DELETE,
        LIST,
        TODO,
        EVENT,
        DEADLINE,
        DATE
    }

    public void printGreeting() {
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Sorry :( Duke is getting some upgrades at the moment.\n"
                + "This is Tron, temporarily standing in for Duke, how may I assist you ?\n";
        System.out.println(this.border + greeting + this.border);
    }

    public void printFarewell() {
        String farewell = "Adios, pleasure to serve you!\n";
        System.out.println(this.border + farewell + this.border);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println(this.border + taskList.toString() + this.border);
    }

    public void printDoneTask(Task task) {
        System.out.println(this.border
            + "Making great progress master.\n"
            + task.toString() + "\n"
            + this.border
        );
    }

    public void printAddedNewTask(Task task, int noTask) {
        System.out.println(this.border
            + "Yes master. I've added the task to the list: \n"
            + task.toString() + "\n"
            + "You now have " + noTask + " task in the list master.\n"
            + this.border
        );
    }

    public void printDeleteTask(Task task, int noTask) {
        System.out.println(this.border
                + "Yes master. I've deleted the task from the list: \n"
                + task.toString() + "\n"
                + "You now have " + noTask + " task in the list master.\n"
                + this.border
        );
    }

    public void noIndexKeyedError() {
        System.out.println(
            this.border
            + "Master please enter a task number so that I know which to handle.\n"
            + this.border
        );
    }

    public void printIndexSizeMismatchError() {
        System.out.println(
            this.border
            + "Master that is not a valid task number.\n"
            + this.border
        );
    }

    public void printNoUndoneTaskError() {
        System.out.println(
            this.border
            + "Master there is no task that is undone.\n"
            + this.border
        );
    }

    public void printIncompleteCommandError(String command) {
        System.out.println(
            this.border
            + "Master there is no description for " + command + " !\n"
            + this.border
        );
    }

    public void printUnrecognizableCommandError() {
        System.out.println(
            this.border
            + "I am sorry master but I do not understand that command.\n"
            + this.border
        );
    }

    public void printNoTaskToDeleteError() {
        System.out.println(
            this.border
            + "I am sorry master but the task list is empty.\n"
            + this.border
        );
    }

    public void printNoDateinput(String command) {
        System.out.println(
            this.border
            + "Master Please enter the date for your " + command + " task.\n"
            + this.border
        );
    }

    public void doneHandler(String parameters) throws DukeExceptions.NoUndoneTaskException {
        if (!this.taskList.isEmpty() || this.taskList.allDone()) {
            int index = Integer.parseInt(parameters.strip()) - 1;
            this.taskList.completeTask(index);
            this.printDoneTask(this.taskList.getTask(index));
        } else {
            throw new DukeExceptions.NoUndoneTaskException();
        }
    }

    public void addTaskHadler(String command, String parameters) throws DukeExceptions.IncompleteCommandException {
        if (!parameters.isBlank()) {
            Task newTask = this.taskList.addTask(command, parameters);
            this.printAddedNewTask(newTask, this.taskList.getNoTask());
        } else {
            throw new DukeExceptions.IncompleteCommandException(command);
        }
    }

    public void deleteTaskHandler(String parameters) throws  DukeExceptions.NoTaskToDeleteException {
        if (!this.taskList.isEmpty()) {
            int index = Integer.parseInt(parameters.strip()) - 1;
            Task task = this.taskList.deleteTask(index);
            this.printDeleteTask(task, this.taskList.getNoTask());
        } else {
            throw new DukeExceptions.NoTaskToDeleteException();
        }
    }

    public void getTaskOn(String dueDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(
            this.border
            + "Master here are the tasks due on " + dueDate.strip() + " :\n"
            + this.taskList.getTaskDueOn(dueDate)
            + this.border
        );
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void run(commands command, String parameters) {
        if (command == commands.BYE) {
            this.isRunning = false;
        } else if (command == commands.LIST) {
            this.printTaskList(this.taskList);
        } else if (command == commands.DONE) {
            try {
                this.doneHandler(parameters);
            } catch (DukeExceptions.NoUndoneTaskException e) {
                this.printNoUndoneTaskError();
            } catch (IndexOutOfBoundsException e) {
                this.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                this.noIndexKeyedError();
            }
        } else if (command == commands.EVENT || command == commands.TODO || command == commands.DEADLINE) {
            try {
                this.addTaskHadler(command.toString().toLowerCase(), parameters);
            } catch (DukeExceptions.IncompleteCommandException e) {
                this.printIncompleteCommandError(command.toString().toLowerCase());
            } catch (ArrayIndexOutOfBoundsException e) {
                this.printNoDateinput(command.toString().toLowerCase());
            } catch (DateTimeParseException e) {
                System.out.println("incorrect date time format dd-mm-yyyy hhmm");
            }
        } else if (command == commands.DELETE) {
            try {
                this.deleteTaskHandler(parameters);
            } catch (DukeExceptions.NoTaskToDeleteException e) {
                this.printNoTaskToDeleteError();
            } catch (IndexOutOfBoundsException e) {
                this.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                this.noIndexKeyedError();
            }
        } else if (command == commands.DATE) {
            this.getTaskOn(parameters);
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.printGreeting();
        Scanner sc = new Scanner(System.in); //scans for input
        String command;
        String parameters;

        while (duke.isRunning()){
            command = sc.next().toUpperCase();
            parameters = sc.nextLine().toLowerCase();
            try {
                duke.run(Duke.commands.valueOf(command), parameters);
            } catch (IllegalArgumentException e) {
                duke.printUnrecognizableCommandError();
            }
        }
        duke.printFarewell();
    }
}
