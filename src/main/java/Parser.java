/**
 * Class to handle parsing user command
 */
public class Parser {
    private Task task;
    private String commandType;
    private int index;
    private String description;

    /**
     * Parse a given command string to relevant information
     * @param command - input command by user
     */
    public Parser(String command) {
        this.commandType = "undefined";
        try {
            if (command.equals("bye")) {
                this.commandType = "bye";
            } else if (command.equals("list")) {
                this.commandType = "list";
            } else if (command.startsWith("done")) {
                if (command.length() < 5) {
                    throw new DukeException(":( OOPS!!! Task index not found.");
                }
                this.commandType = "done";
                this.index = Integer.parseInt(command.substring(5)) - 1;
            } else if (command.startsWith("delete")) {
                if (command.length() < 7) {
                    throw new DukeException(":( OOPS!!! Task index not found.");
                }
                this.commandType = "delete";
                this.index = Integer.parseInt(command.substring(7)) - 1;
            } else if (command.startsWith("find")) {
                if (command.length() < 5) {
                    throw new DukeException(":( OOPS!!! You need to specify a keyword to search.");
                }
                this.commandType = "find";
                this.description = command.substring(5);
            } else {
                Task newTask = null;
                String type;
                String description;
                String date;
                if (command.startsWith("todo")) {
                    if (command.length() < 5) {
                        throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                    }
                    type = "todo";
                    description = command.substring(5);
                    date = "";
                } else if (command.startsWith("deadline")) {
                    if (command.length() < 9) {
                        throw new DukeException(":( OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String rest = command.substring(9);
                    int byPosition = rest.indexOf("/by ");
                    if (byPosition == -1) {
                        throw new DukeException(":( OOPS!!! Deadline time specification not found.");
                    }
                    type = "deadline";
                    description = rest.substring(0, byPosition - 1);
                    date = rest.substring(byPosition + 4);
                } else if (command.startsWith("event")) {
                    if (command.length() < 6) {
                        throw new DukeException(":( OOPS!!! The description of an event cannot be empty.");
                    }
                    String rest = command.substring(6);
                    int atPosition = rest.indexOf("/at ");
                    if (atPosition == -1) {
                        throw new DukeException(":( OOPS!!! Event time specification not found.");
                    }
                    type = "event";
                    description = rest.substring(0, atPosition - 1);
                    date = rest.substring(atPosition + 4);
                } else {
                    throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                int prioPosition = description.indexOf("/p ");
                if (prioPosition != -1) {
                    this.index = Integer.parseInt(description.substring(prioPosition + 3)) - 1;
                    description = description.substring(0, prioPosition - 1);
                } else {
                    this.index = -1;
                }

                switch (type) {
                case "todo": {
                    newTask = new Todo(description);
                    break;
                }
                case "deadline": {
                    newTask = new Deadline(description, date);
                    break;
                }
                case "event": {
                    newTask = new Event(description, date);
                    break;
                }
                default: {
                    throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                }
                this.commandType = "add";
                this.task = newTask;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public String run(TaskList tasks, Storage storage, UI ui) throws Exception {
        String commandType = getCommandType();
        StringBuilder response = new StringBuilder();
        switch (commandType) {
        case "bye": {
            storage.saveData(tasks.getTasks());
            response.append(ui.byeMessage());
            break;
        }
        case "list": {
            response.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); ++i) {
                response.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }
            break;
        }
        case "done": {
            int index = getIndex();
            if (index >= tasks.size()) {
                throw new DukeException(":( OOPS!!! Task index not found.");
            }
            tasks.get(index).markAsDone();
            response.append("Nice! I've marked this task as done:\n");
            response.append(tasks.get(index).toString()).append("\n");
            break;
        }
        case "delete": {
            int index = getIndex();
            if (index >= tasks.size()) {
                throw new DukeException(":( OOPS!!! Task index not found.");
            }
            response.append("Noted. I've removed this task:\n");
            response.append(tasks.get(index).toString()).append("\n");
            tasks.removeTask(index);
            response.append(String.format("Now you have %d tasks in the list.\n", tasks.size()));
            break;
        }
        case "add": {
            Task newTask = getTask();
            tasks.addTask(newTask, getIndex());
            response.append("Got it. I've added this task:\n");
            response.append(newTask.toString()).append("\n");
            response.append(String.format("Now you have %d tasks in the list\n", tasks.size()));
            break;
        }
        case "find": {
            String keyword = getDescription();
            int counter = 0;
            for (Task task : tasks.getTasks()) {
                if (task.description.contains(keyword)) {
                    if (counter == 0) {
                        response.append("Here are the matching tasks in your list:\n");
                    }
                    ++counter;
                    response.append(String.format("%d.%s\n", counter, task.toString()));
                }
            }
            if (counter == 0) {
                response.append("No task was found.\n");
            }
            break;
        }
        default: {
            response.append("Unknown command.\n");
        }
        }
        return response.toString();
    }

    public int getIndex() {
        return this.index;
    }

    public Task getTask() {
        return this.task;
    }

    public String getCommandType() {
        return this.commandType;
    }

    public String getDescription() {
        return this.description;
    }
}
