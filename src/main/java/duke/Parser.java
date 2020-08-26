package duke;

public class Parser {
    private TaskList taskList;
    private Ui ui;
    
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses a string and performs the necessary actions based on the input.
     * @param input The input String typed in by the user.
     * @return False if the command to terminate the programme is encountered, true otherwise.
     */
    public boolean parse(String input) {
        try {
            if (input.equals("bye")) {
                return false;
            } else if (input.equals("list")) {
                ui.printAllTasks(taskList);
            } else if (input.indexOf("done") == 0) {
                if (input.length() == 4) {
                    throw new DukeException("OOPS!!! Please specify a task to mark as complete.");
                } else {
                    int taskIndex = Integer.parseInt(input.substring(5));    // this is not corrected for 0 index
                    Task completedTask = taskList.getTask(taskIndex);
                    completedTask.markAsDone();

                    ui.printMarkTaskCompleteConfirmation(completedTask);
                }
            } else if (input.indexOf("delete") == 0) {
                if (input.length() == 6) {
                    throw new DukeException(" OOPS!!! Please specify a task to delete.");
                } else {
                    try {
                        int taskIndex = Integer.parseInt(input.substring(7));

                        Task removedTask = taskList.getTask(taskIndex);
                        taskList.deleteTask(taskIndex);

                        ui.printRemoveTaskConfirmation(removedTask, taskList);
                        
                    } catch (NumberFormatException e) {
                        throw new DukeException(" OOPS!!! Please specify a valid task to delete.");
                    }
                }
            } else if (input.indexOf("find") == 0) {
                if (input.length() == 4) {
                    throw new DukeException(" OOPS!!! Please specify a keyword to search for.");
                } else {
                    ui.printMessage(taskList.find(input.substring(5)));
                }
            } else {
                // use indexOf() method to find substring
                Task newTask = new Task("");    // this is to make the compiler happy

                if (input.indexOf("event") == 0) {
                    if (input.length() <= 5) {
                        throw new DukeException(" OOPS!!! The description of an event cannot be empty.");
                    } else {
                        int indexOfAtKeyword = input.indexOf(" /at ");

                        if (indexOfAtKeyword == -1) {
                            throw new DukeException(" OOPS!!! Please specify the event time.");
                        } else {
                            String eventDesc = input.substring(6, indexOfAtKeyword);
                            String eventTime = input.substring(indexOfAtKeyword + 5);

                            newTask = new Event(eventDesc, eventTime);
                            taskList.addTask(newTask);
                        }
                    }
                } else if (input.indexOf("deadline") == 0) {
                    if (input.length() <= 8) {
                        throw new DukeException(" OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        int indexOfByKeyword = input.indexOf(" /by ");

                        if (indexOfByKeyword == -1) {
                            throw new DukeException(" OOPS!!! Please specify the deadline time.");
                        } else {
                            String deadlineDesc = input.substring(9, indexOfByKeyword);
                            String deadlineTime = input.substring(indexOfByKeyword + 5);

                            newTask = new Deadline(deadlineDesc, deadlineTime);
                            taskList.addTask(newTask);
                        }
                    }
                } else if (input.indexOf("todo") == 0){
                    if (input.length() <= 4 || input.substring(5).trim().length() == 0) {
                        throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        newTask = new Todo(input.substring(5));
                        taskList.addTask(newTask);
                    }
                } else {
                    throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                ui.printAddTaskConfirmation(newTask, taskList);
                
            }
        } catch (DukeException e) {
            ui.printExceptionMessage(e);
        }
        
        return true;
        
    }
}
