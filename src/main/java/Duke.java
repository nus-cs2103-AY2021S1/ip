import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Stack;


/**
 * Represents a Duke class, which is an interactive chat bot which allows you to track tasks.
 */
public class Duke {
    Parser parser;
    Storage storage;
    TaskList taskList;
    Ui ui;
    Stack<TaskList> taskListHistories;

    Duke() {
        this.parser = new Parser();
        this.ui = new Ui();
        this.taskListHistories = new Stack<TaskList>();
        this.taskList = new TaskList();

        String currentDirectory = System.getProperty("user.dir");
        this.storage = new Storage(currentDirectory + "/data/duke.txt");

        if (storage.hasExisting()) {
            taskList = new TaskList(storage.getTaskList());
        }
    }

    /**
     * Add current state of taskList to the stack of histories.
     */
    public void addHistory(TaskList taskList) {
        taskListHistories.push(taskList);
    }

    /**
     * Undo the previous action, and returns a boolean to indicate if the undo is successful.
     *
     * @return Boolean to indicate if the undo is successful
     */
    public boolean undoHistorySucceeded() {
        if (!taskListHistories.empty()) {
            TaskList taskListHistory = taskListHistories.pop();
            storage.write(taskListHistory);
            taskList = taskListHistory;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return a String depending on the input.
     *
     * @param msg A String indicating the message that was passed in.
     * @return String of message to be displayed back to the user.
     */
    public String getResponse(String msg) {
        try {
            Parser.Command command = parser.parse(msg);
            Task newTask;

            assert(command != null);

            // Navigation commands --------------------------------------
            if (command == Parser.Command.BYE) {
                // Exit program
                return Ui.messagePrint("Bye. Hope to see you again soon!");
            } else if (command == Parser.Command.UNDO) {
                if (undoHistorySucceeded()) {
                    String listMessage = "";
                    for (int i = 0; i < taskList.size(); i++) {
                        listMessage += (i + 1) + ". " + taskList.get(i).toString() + "\n";
                    }
                    return "Undo succeeded: \n" + Ui.messagePrint(listMessage);
                } else {
                    return "Undo failed, no previous history is available.";
                }
            }
            else if (command == Parser.Command.LIST) {
                // Print list to User
                String listMessage = "";
                for (int i = 0; i < taskList.size(); i++) {
                    listMessage += (i + 1) + ". " + taskList.get(i).toString() + "\n";
                }
                return Ui.messagePrint(listMessage);

            } else if (command == Parser.Command.DONE) {
                addHistory(taskList);

                /* Update status of Task to completed. */
                int updateTaskIndex = Integer.valueOf(msg.substring(5)) - 1;
                if (updateTaskIndex >= taskList.size() || updateTaskIndex < 0) {
                    throw new DukeException(DukeExceptionType.TASK_NOT_FOUND);
                }
                Task taskToUpdate = taskList.get(updateTaskIndex);
                taskToUpdate.updateStatus(true);
                taskList = taskList.set(updateTaskIndex, taskToUpdate);
                String completedMessage = "Nice! I've marked this task as done:\n" + "  " + taskList.get(updateTaskIndex).toString();
                storage.write(taskList);
                return Ui.messagePrint(completedMessage);

            } else if (command == Parser.Command.DELETE) {
                addHistory(taskList);

                // Delete task.
                int updateTaskIndex = Integer.valueOf(msg.substring(7)) - 1;
                if (updateTaskIndex >= taskList.size() || updateTaskIndex < 0) {
                    throw new DukeException(DukeExceptionType.TASK_NOT_FOUND);
                }
                Task taskToUpdate = taskList.get(updateTaskIndex);
                taskList = taskList.remove(updateTaskIndex);
                String deletedMessage = "Noted. I've removed this task:\n" +
                        "  " + taskToUpdate.toString() + "\n" +
                        "Now you have " + taskList.size() + " tasks in the list.";
                storage.write(taskList);
                return Ui.messagePrint(deletedMessage);

            } else if (command == Parser.Command.FIND) {
                //Find task according to query message.
                String query = msg.substring(5);
                String output = "Here are the matching tasks in your list: \n";
                TaskList searchedTaskList = taskList.find(query);
                for (int i = 1; i <= searchedTaskList.size(); i++) {
                    output = output + i + "." + searchedTaskList.get(i - 1).toString();

                    if (i != searchedTaskList.size()) {
                        output += "\n";
                    }
                }
                return Ui.messagePrint(output);
            }

            // Commands related with creating new tasks -------------------------------------
            else if (command == Parser.Command.DEADLINE) {
                addHistory(taskList);
                // Create deadline.
                int byIndex = msg.indexOf("/by");
                String task = msg.substring(9, byIndex - 1); //Number 9 = starting index of deadline string.
                String dateString = msg.substring(byIndex + 4);
                try {
                    LocalDate date = LocalDate.parse(dateString);
                    newTask = new Deadline(task, date);
                } catch (DateTimeParseException e) {
                    newTask = new Deadline(task, dateString);
                }

            } else if (command == Parser.Command.EVENT) {
                addHistory(taskList);
                // Create event.
                int atIndex = msg.indexOf("/at");
                String task = msg.substring(6, atIndex - 1); //Number 6 = starting index of event string.
                String dateString = msg.substring(atIndex + 4);
                try {
                    LocalDate date = LocalDate.parse(dateString);
                    newTask = new Event(task, date);
                } catch (DateTimeParseException e) {
                    newTask = new Event(task, dateString);
                }

            } else if (command == Parser.Command.TODO) {
                addHistory(taskList);
                // Create ToDo
                String task = msg.substring(5); //Number 5 = starting index of todo string.
                newTask = new ToDo(task);
            } else {
                throw new DukeException(DukeExceptionType.INVALID_INPUT);
            }

            taskList = taskList.add(newTask);
            String newTaskMsg = String.format(
                    "Got it. I've added this task:\n" +
                            "  %s\n" +
                            "Now you have %d tasks in the list.", newTask.toString(), taskList.size());
            storage.write(taskList);
            return Ui.messagePrint(newTaskMsg);
        } catch (DukeException e) {
            return Ui.messagePrint(e.toString());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.getResponse("todo sell fishes"));
    }
}



