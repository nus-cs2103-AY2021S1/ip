import java.util.Scanner;

public class Duke {
    private final String border = "----------------------------------------------------------------------------\n";
    private final String greeting = "Sorry :( Duke is getting some upgrades at the moment.\n"
            + "This is Tron, temporarily standing in for Duke, how may I assist you ?\n";
    private final String farewell = "Adios, pleasure to serve you!\n";
    private final String logo =
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private boolean isRunning = true;
    private TaskList taskList = new TaskList();

    public void printGreeting() {
        System.out.println("Hello from\n" + logo);
        System.out.println(this.border + this.greeting + this.border);
    }

    public void printFarewell() {
        System.out.println(this.border + this.farewell + this.border);
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

    public boolean isRunning() {
        return this.isRunning;
    }

    public void run(String command, String parameters) throws DukeExceptions.UnrecognizableCommandException {
        if (command.equals("bye")) {
            this.isRunning = false;
        } else if (command.equals("list")) {
            this.printTaskList(this.taskList);
        } else if (command.equals("done")) {
            try {
                this.doneHandler(parameters);
            } catch (DukeExceptions.NoUndoneTaskException e) {
                this.printNoUndoneTaskError();
            } catch (IndexOutOfBoundsException e) {
                this.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                this.noIndexKeyedError();
            }
        } else if (command.equals("todo")||command.equals("event")||command.equals("deadline")) {
            try {
                this.addTaskHadler(command, parameters);
            } catch (DukeExceptions.IncompleteCommandException e) {
                this.printIncompleteCommandError(command);
            }
        } else if (command.equals("delete")){
            try {
                this.deleteTaskHandler(parameters);
            } catch (DukeExceptions.NoTaskToDeleteException e) {
                this.printNoTaskToDeleteError();
            } catch (IndexOutOfBoundsException e) {
                this.printIndexSizeMismatchError();
            } catch (NumberFormatException e) {
                this.noIndexKeyedError();
            }
        } else {
            throw new DukeExceptions.UnrecognizableCommandException();
        }
    }


    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.printGreeting();
        Scanner sc = new Scanner(System.in); //scans for input
        String command;
        String parameters;

        while (duke.isRunning()){
            command = sc.next().toLowerCase();
            parameters = sc.nextLine().toLowerCase();
            try {
                duke.run(command, parameters);
            } catch (DukeExceptions.UnrecognizableCommandException e) {
                duke.printUnrecognizableCommandError();
            }
        }

    }


}
