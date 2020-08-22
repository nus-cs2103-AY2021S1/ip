package duke;

import exception.*;

import java.time.DateTimeException;

import java.util.Scanner;

public class Duke {
    Scanner sc;
    String input;
    TaskList taskList;
    Storage storage = new Storage();
    Ui ui;

    public Duke() {
        sc = new Scanner(System.in);
        ui = new Ui();
    }

    public void start() {
        try {
            this.taskList = new TaskList(storage.load());
            ui.showWelcomeMessage();
            handleInteraction();
            ui.showCloseMessage();
        } catch (StorageException ex) {
            ex.printStackTrace();
        }
    }


    // interaction functions
    public void handleInteraction() {
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            // stopping condition
            if (input.equals("bye")) {
                break;
            }

            ui.buildChatFence();

            // handle commands
            try {
                String[] parsed = parseInput(input);
                Commands command;

                try {
                    // modify parsed[0] to uppercase to ensure that comparison with the commands enum is standardized
                    command = Commands.valueOf(parsed[0].toUpperCase());
                } catch (IllegalArgumentException ex) {
                    // if parsed[0] not amongst valid commands, will throw an IllegalArgumentException
                    throw new UnknownCommandException();
                }

                switch (command) {
                case LIST:
                    // we ignore the argument after `list`.
                    ui.print("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        ui.print(String.format("%d. %s", i + 1, taskList.show(i)));
                    }
                    break;
                case DONE:
                    try {
                        int taskNumber = Integer.parseInt(parsed[1]) - 1;
                        // Check that the task number makes sense.
                        if (taskNumber >= 0 && taskNumber < taskList.size()) {
                            taskList.markAsDone(taskNumber);
                            storage.save(this.taskList);
                            ui.print("Good job! I've marked this task as done:");
                            ui.print(String.format("%s%n", taskList.show(taskNumber)));
                        } else {
                            ui.print("Sorry, I can't find it in your list!");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        throw new InvalidUsageException("Usage: done <task number>");
                    }
                    break;
                case DELETE:
                    try {
                        int taskNumber = Integer.parseInt(parsed[1]) - 1;
                        // Check that the task number makes sense.
                        if (taskNumber >= 0 && taskNumber < taskList.size()) {
                            ui.print("Noted. I've removed this task: ");
                            ui.print(taskList.remove(taskNumber).showTask());
                            ui.print(String.format("Now you have %d %s in the list%n",
                                    taskList.size(), taskList.size() > 1 ? "tasks" : "task"));
                            storage.save(this.taskList);
                        } else {
                            ui.print("Sorry, I can't find it in your list!");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        throw new InvalidUsageException("Usage: delete <task number>");
                    }
                    break;
                case TODO:
                    try {
                        this.addTodo(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new TodoInvalidUsageException("Todo description cannot be empty.");
                    }
                    break;
                case DEADLINE:
                    try {
                        this.addDeadline(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DeadlineInvalidUsageException("Deadline description cannot be empty.");
                    }
                    break;
                case EVENT:
                    try {
                        this.addEvent(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new EventInvalidUsageException("Event description cannot be empty.");
                    }
                    break;
                case VIEWALL:
                    try {
                        TaskList filtered = taskList.viewAll(parsed[1]);
                        ui.print("Here are the tasks on given date:");
                        for (int i = 0; i < filtered.size(); i++) {
                            ui.print(String.format("%d. %s%n", i + 1, filtered.show(i)));
                        }
                    } catch (DateTimeException | ArrayIndexOutOfBoundsException ex) {
                        throw new ViewallInvalidUsageException("Date should be in yyyy-mm-dd format.");
                    }
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (InvalidUsageException | UnknownCommandException ex) {
                System.out.println(ex.getMessage());
            }
            ui.buildChatFence();
        }
    }

    private void printAddConfirmation(int index) {
        int size = taskList.size();
        ui.print("Got it. I've added this task:");
        ui.print(taskList.show(index));
        ui.print(String.format("Now you have %d %s in the list%n", size, size > 1 ? "tasks" : "task"));
    }

    /**
     * Split the input string into first word (command) and others
     *
     * @param input user input
     * @return an array, first element is command, second element is input
     */
    private String[] parseInput(String input) {
        return input.split("\\s+", 2);
    }

    private void addTodo(String todo) {
        taskList.add(new Todo(todo));
        storage.save(this.taskList);
        printAddConfirmation(taskList.size() - 1);
    }

    private void addDeadline(String deadline) throws DeadlineInvalidUsageException {
        taskList.add(Deadline.create(deadline));
        storage.save(this.taskList);
        printAddConfirmation(taskList.size() - 1);
    }

    private void addEvent(String event) throws EventInvalidUsageException {
        taskList.add(Event.create(event));
        storage.save(this.taskList);
        printAddConfirmation(taskList.size() - 1);
    }
}
