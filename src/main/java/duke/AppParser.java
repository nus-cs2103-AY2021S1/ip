package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AppParser extends Parser {

    public AppUi appUi;
    public Storage storage;

    /**
     * Constructs a AppParser object
     * @throws FileNotFoundException for handling file
     */
    public AppParser() throws FileNotFoundException {
        appUi = new AppUi();
        storage = new Storage("data/main.java.duke.txt");
    }

    /**
     * Parses for the app
     * @param inputCommand user input
     * @param list user's TaskList
     * @return response for user input
     * @throws IOException for handling file
     */
    public String appParse(String inputCommand, TaskList list) throws IOException {
        String[] command = inputCommand.split(" ");
        int ptr = 0;

        // if the user input is empty, continue the loop
        if (command.length <= 0 || inputCommand.equals("")) {
            return appUi.getInputEmptyErrorMsg();
        }

        while (command[ptr].equals("")) {
            ptr++;
        }

        if (command[ptr].equals("bye")) {
            storage.writeFile(list);
            return appUi.getByeMessage();
        } else if (command[ptr].equals("list")) {
            return list.toString();
        } else if (command[ptr].equals("done")) {
            try {
                int taskNumber = Integer.parseInt(command[ptr + 1]);
                System.out.println("____________________________________________________________");
                Task t = list.markTaskDone(taskNumber);
                if (t != null) {
                    return appUi.getMarkAsDoneMsg(t);
                } else {
                    return appUi.getTaskNumberExceedMsg(taskNumber, list.getSize());
                }
            } catch (Exception e) {
                return appUi.getWrongFormatAfterDoneMsg();
            }
        } else if (command[ptr].equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(command[ptr + 1]);
                Task t = list.delete(taskNumber);
                if (t == null) {
                    return appUi.getTaskNumberExceedMsg(taskNumber, list.getSize());
                } else {
                    return appUi.getDeleteMessage(t, list);
                }
            } catch (Exception e) {
                return appUi.getWrongFormatAfterDeleteMsg();
            }
        } else if (command[ptr].equals("find")) {
            if (ptr + 1 >= command.length) {
                return appUi.getWrongFindFormatMsg();
            }
            return appUi.getFindResult(Finder.appFind(list, command[ptr + 1]));
        } else if (command[ptr].equals("todo")) {
            ArrayList<Todo> newTodos = Todo.of(inputCommand);
            if (newTodos == null) {
                return appUi.getDescriptionEmptyMsg();
            }
            for(Todo newTodo : newTodos){
                list.add(newTodo);
            }
            return appUi.getAfterAddMsgVarargs(TODO, list.getSize(), newTodos.toArray(new Task[newTodos.size()]));
        } else if (command[ptr].equals("deadline")) {
            Deadline deadline = Deadline.of(inputCommand);
            if (deadline == null) {
                return appUi.getWrongDeadlineFormatMsg();
            }
            list.add(deadline);
            String res = "";
            if (deadline.getDate() == null) {
                res += appUi.getWrongDateFormatMsg();
            }
            if (deadline.getTime() == null) {
                res += appUi.getWrongTimeFormatMsg();
            }
            return res + appUi.getAfterAddMsg(deadline, DEADLINE, list.getSize());
        } else if (command[ptr].equals("event")) {
            Event event = Event.of(inputCommand);
            if (event == null) {
                return appUi.getWrongEventFormatMsg();
            }
            list.add(event);
            String res = "";
            if (event.getDate() == null) {
                res += appUi.getWrongDateFormatMsg();
            }
            if (event.getTime() == null) {
                res += appUi.getWrongTimeFormatMsg();
            }
            return res + appUi.getAfterAddMsg(event, EVENT, list.getSize());
        } else {
            return appUi.getNoSuchCommandMsg();
        }
    }
}
