package duke.components;

import java.time.LocalDate;

public class Parser {

    boolean isList, isTask, isDelete, isBye, isDone, isValid;
    private int doneTaskNum, deleteTaskNum;
    private String description, time;
    private LocalDate date;

    public Parser() {
        isList = false;
        isTask = false;
        isDelete = false;
        isBye = false;
        isDone = false;
        isValid = true;
        description = null;
        time = null;
        date = null;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public int getDoneTaskNum() {
        return doneTaskNum;
    }

    public int getDeleteTaskNum() {
        return deleteTaskNum;
    }

    public void parse(String input) {
        if (input.equals("list")) {

            isList = true;

        } else if (input.startsWith("done")) {

            isDone = true;
            doneTaskNum = Character.getNumericValue(input.charAt(5)) - 1;

        } else if (input.startsWith("todo")) {

            isTask = true;
            description = input;

        } else {
            if (input.startsWith("deadline")) {

                isTask = true;
                description = input.substring(0, input.indexOf('/') - 1);
                date = LocalDate.parse(input.substring(input.indexOf('/') + 4));

            } else if (input.startsWith("event")) {

                isTask = true;
                description = input.substring(0, input.indexOf('/') - 1);
                date = LocalDate.parse(input.substring(input.indexOf('/') + 4, input.indexOf('/') + 14));
                time = input.substring(input.indexOf('/') + 15);

            } else if (input.startsWith("delete")) {

                isDelete = true;
                deleteTaskNum = Character.getNumericValue(input.charAt(7)) - 1;

            } else if (input.startsWith("bye")) {

                isBye = true;

            } else {

                isValid = false;

            }
        }
    }

}
