package main.java.duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    public List<Task> task_collections;
    Parser parser;
    String MEMO_FILE_DIR;
    String MEMO_FILE_NAME;

    public TaskList(List<Task> task_collections, String MEMO_FILE_DIR, String MEMO_FILE_NAME) {
        this.task_collections = task_collections;
        this.parser = new Parser();
        this.MEMO_FILE_DIR = MEMO_FILE_DIR;
        this.MEMO_FILE_NAME = MEMO_FILE_NAME;
    }

    public TaskList() {
        this.task_collections = new ArrayList<>();
    }

    public void editTask(String[] COMMAND_RESULT) {
        String TYPE = COMMAND_RESULT[0];
        String TASK_NUMBER = COMMAND_RESULT[1];
        boolean exception_absent = true;
        int action_number = -1;
        String TASK_CONTENT = "";
        try {
            action_number = Integer.parseInt(TASK_NUMBER);
        } catch (Exception ex) {
            exception_absent = false;
            HandleException.handleException(DukeException.ExceptionType.empty_illegal);
        }
        if (exception_absent) {
            try {
                if (TYPE.equals("delete")) {
                    TASK_CONTENT = this.task_collections.get(action_number - 1).toString();
                    task_collections.remove(action_number - 1);
                } else {
                    task_collections.get(action_number - 1).markAsDone();
                }
                new Storage(MEMO_FILE_DIR, MEMO_FILE_NAME).write_memory(task_collections);
                if (TYPE.equals("delete")) {
                    System.out.println(SpecialFormat.indent + "Noted. I've removed this task:");
                    System.out.println(SpecialFormat.indent + TASK_CONTENT);
                    System.out.println(SpecialFormat.indent + "Now you have " + this.task_collections.size() + " tasks in the list.");
                } else {
                    System.out.println(SpecialFormat.indent + "Nice! I've marked this task as done:");
                    System.out.println(SpecialFormat.indent + "  [\u2713] " + task_collections.get(action_number - 1).toString().split("] ", 2)[1]);
                }
            } catch (Exception ex) {
                exception_absent = false;
                HandleException.handleException(DukeException.ExceptionType.empty_illegal);
            }
        }
    }

    public void addTask(String[] COMMAND_RESULT) {
        String TYPE = COMMAND_RESULT[0];
        String TASK_CONTENT = COMMAND_RESULT[1];
        Task t;
        if (TYPE.equals("todo")) {
            t = new Todo(TASK_CONTENT);
        } else {
            t = TYPE.equals("event")
                    ? new Event(TASK_CONTENT, COMMAND_RESULT[2])
                    : new Deadline(TASK_CONTENT, COMMAND_RESULT[2]);
        }
        try {
            this.task_collections.add(t);
            new Storage(MEMO_FILE_DIR, MEMO_FILE_NAME).appendToFile(MEMO_FILE_DIR + MEMO_FILE_NAME, t);
            System.out.println(SpecialFormat.indent + "Got it. I've added ths task:");
            System.out.println(SpecialFormat.indent + "  " + task_collections.get(task_collections.size() - 1));
            System.out.println(SpecialFormat.indent + "Now you have " + task_collections.size() + " tasks in the list.");
        } catch (Exception e) {
            HandleException.handleException(DukeException.ExceptionType.read_file);
        }
    }

    /*public void editTask(String action, String[] input_split_arr) {
        boolean exception_absent = false;
        int action_number = -1;
        String success_result = "";
        try {
            action_number = Integer.parseInt(input_split_arr[1]);
        } catch (Exception ex) {
            Ui.handleException(DukeException.ExceptionType.empty_illegal);
        }
        if (exception_absent) {
            try {
                if (action.equals("delete")) {
                    success_result = this.task_collections.get(action_number - 1).toString();
                    task_collections.remove(action_number - 1);
                } else {
                    task_collections.get(action_number - 1).markAsDone();
                }
                write_memory(task_collections);
            } catch (Exception ex) {
                Ui.handleException(DukeException.ExceptionType.empty_illegal);
            }
        }
        if (exception_absent) {
            if (action.equals("delete")) {
                System.out.println(SpecialFormat.indent + "Noted. I've removed this task:");
                System.out.println(SpecialFormat.indent + success_result);
                System.out.println(SpecialFormat.indent + "Now you have " + task_collections.size() + " tasks in the list.");
            } else {
                System.out.println(SpecialFormat.indent + "Nice! I've marked this task as done:");
                System.out.println(SpecialFormat.indent + "  [\u2713] " + task_collections.get(action_number - 1).toString().split("] ", 2)[1]);
            }
        }
    }

    public void addTask(String type, String[] input_split_arr) {
        Task t = null;
        boolean exception_absent = false;
        if (!type.equals("todo")) {
            try {
                input_split_arr = input_split_arr[1].split(
                        type.equals("event") ? " /at " : " /by ", 2);
            } catch (Exception ex) {
                Ui.handleException(type.equals("deadline")
                        ? DukeException.ExceptionType.deadline_empty_incomplete
                        : DukeException.ExceptionType.event_empty_incomplete);
            }
        }

        if (exception_absent) {
            try {
                if (type.equals("todo")) {
                    t = new Todo(input_split_arr[1]);
                } else {
                    String time;
                    try {
                        time = input_split_arr[1];
                        time = parser.dateTimeParser(time);
                        t = type.equals("event")
                                ? new Event(input_split_arr[0], time)
                                : new Deadline(input_split_arr[0], time);
                    } catch (Exception ex) {
                        Ui.handleException(type.equals("event")
                                ? DukeException.ExceptionType.event_empty_incomplete
                                : DukeException.ExceptionType.deadline_empty_incomplete);
                    }
                }
            } catch (Exception ex) {
                Ui.handleException(type.equals("todo")
                        ? DukeException.ExceptionType.todo_empty
                        : type.equals("event")
                        ? DukeException.ExceptionType.event_empty_incomplete
                        : DukeException.ExceptionType.deadline_empty_incomplete);
            }
            if (exception_absent) {
                task_collections.add(t);
                appendToFile(memory_file_dir + memory_file_name, t);
                if (exception_absent) {
                    System.out.println(SpecialFormat.indent + "Got it. I've added ths task:");
                    System.out.println(SpecialFormat.indent + "  " + task_collections.get(task_collections.size() - 1));
                    System.out.println(SpecialFormat.indent + "Now you have " + task_collections.size() + " tasks in the list.");
                }
            }
        }
    }*/
}
