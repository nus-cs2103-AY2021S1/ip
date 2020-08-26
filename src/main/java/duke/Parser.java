package main.java.duke;

import main.java.duke.DateTimeConverter;
import main.java.duke.SpecialFormat;

import java.time.format.FormatStyle;

public class Parser {

    public Parser() {

    }

    public String dateTimeParser(String dateTime) {
        DateTimeConverter dtc = new DateTimeConverter(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return dtc.processTime(dateTime);
    }

    public String[] memoTaskParser(String taskLine) {
        String[] temp_type = taskLine.split(SpecialFormat.split_notn, 2);
        String[] temp_details;
        if (temp_type[0].equals("T")) {
            temp_details = temp_type[1].split(SpecialFormat.split_notn, 2);
            return new String[]{temp_type[0], temp_details[0], temp_details[1]};
        } else {
            temp_details = temp_type[1].split(SpecialFormat.split_notn, 3);
            return new String[]{temp_type[0], temp_details[0], temp_details[1], temp_details[2]};
        }
    }

    public String[] commandParser(String input) {
        String COMMAND_TYPE;
        String[] output = new String[]{};
        if (input.equals("bye")) {
            COMMAND_TYPE = "bye";
            output = new String[]{COMMAND_TYPE};
        } else if (input.equals("list")) {
            COMMAND_TYPE = "list";
            output = new String[]{COMMAND_TYPE};
        } else {
            String[] input_split_arr;
            input_split_arr = input.split(" ", 2);
            COMMAND_TYPE = input_split_arr[0];
            String EXCEPTION_TYPE;
            if (COMMAND_TYPE.equals("done") || COMMAND_TYPE.equals("delete")) {
                try {
                    String ACTION_NUMBER = input_split_arr[1];
                    output = new String[]{COMMAND_TYPE, ACTION_NUMBER};
                } catch (Exception ex) {
                    COMMAND_TYPE = "exception";
                    return new String[] {COMMAND_TYPE, "empty_illegal"};
                }
            } else if (COMMAND_TYPE.equals("deadline") || COMMAND_TYPE.equals("event") || COMMAND_TYPE.equals("todo")) {
                String TASK_CONTENT;
                String DATE_TIME;
                boolean exception_absent = true;
                if (!COMMAND_TYPE.equals("todo")) {
                    try {
                        input_split_arr = input_split_arr[1].split(
                                COMMAND_TYPE.equals("event") ? " /at " : " /by ", 2);
                    } catch (Exception ex) {
                        exception_absent = false;
                        EXCEPTION_TYPE = COMMAND_TYPE.equals("deadline")
                                ? "deadline"
                                : "event";
                        COMMAND_TYPE = "exception";
                        return new String[] {COMMAND_TYPE, EXCEPTION_TYPE};
                    }
                }
                if (exception_absent) {
                    if (COMMAND_TYPE.equals("todo")) {
                        try {
                            TASK_CONTENT = input_split_arr[1];
                        } catch (Exception e) {
                            return new String[] {"exception", "todo"};
                        }
                        output = new String[] {COMMAND_TYPE, TASK_CONTENT};
                    } else {
                        try {
                            TASK_CONTENT = input_split_arr[0];
                            DATE_TIME = input_split_arr[1];
                            DATE_TIME = this.dateTimeParser(DATE_TIME);
                            output = new String[] {COMMAND_TYPE, TASK_CONTENT, DATE_TIME};
                        } catch (Exception ex) {
                            exception_absent = false;
                            EXCEPTION_TYPE = COMMAND_TYPE.equals("event")
                                    ? "event"
                                    : "deadline";
                            COMMAND_TYPE = "exception";
                            return new String[] {COMMAND_TYPE, EXCEPTION_TYPE};
                        }
                    }
                    try {

                    } catch (Exception ex) {
                        exception_absent = false;
                        EXCEPTION_TYPE = COMMAND_TYPE.equals("todo")
                                ? "todo"
                                : COMMAND_TYPE.equals("event")
                                ? "event"
                                : "deadline";
                        COMMAND_TYPE = "exception";
                        return new String[] {COMMAND_TYPE, EXCEPTION_TYPE};
                    }
                }
            } else {
                EXCEPTION_TYPE = "no_meaning";
                COMMAND_TYPE = "exception";
                return new String[] {COMMAND_TYPE, EXCEPTION_TYPE};
            }
        }
        return output;
    }
}

