package main.java;

import java.security.InvalidParameterException;

public class Parser {

    public Parser() {

    }

    public Task parseTask(String resource) throws InvalidParameterException {
        Task result;
        if (resource.startsWith("todo")) {
            try {
                if (resource.substring(5).length() == 0) {
                    throw new Exception();
                }
                Todo newTodo = new Todo(resource.substring(5));
                result = newTodo;

            } catch (Exception e) {
                throw new InvalidParameterException();
            }
        } else if (resource.startsWith("deadline")) {
            try {
                String[] splited = resource.substring(9).split("/");
                Deadline newDeadline = new Deadline(splited[0], splited[1].replace(" ", ""));
                result = newDeadline;
            } catch (Exception e) {
                throw new InvalidParameterException();
            }
        } else if (resource.startsWith("event")) {
            try {
                String[] splited = resource.substring(6).split("/");
                Event newEvent = new Event(splited[0], splited[1].replace(" ", ""));
                result = newEvent;
            } catch (Exception e) {
                throw new InvalidParameterException();
            }
        } else {
            throw new InvalidParameterException();
        }
        return result;
    }

    public int parseDoneOrder(String resource, TaskList taskList)
        throws IndexOutOfBoundsException, Exception {
        if (resource.length() <= 4) {
            throw new Exception();
        }
        String numberString = resource.substring(5).replace(" ", "");
        try {
            int doneNo = Integer.parseInt(numberString);
            if (doneNo <= 0 || doneNo - 1 >= taskList.taskCount()) {
                throw new IndexOutOfBoundsException();
            } else {
                return doneNo - 1;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public int parseDeleteOrder(String resource, TaskList taskList)
        throws IndexOutOfBoundsException, Exception {
        if (resource.length() <= 6) {
            throw new Exception();
        }
        String numberString = resource.substring(7).replace(" ", "");
        try {
            int deletingNumber = Integer.parseInt(numberString);
            if (deletingNumber <= 0 || deletingNumber - 1 >= taskList.taskCount()) {
                throw new IndexOutOfBoundsException();
            } else {
                return deletingNumber - 1;
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
