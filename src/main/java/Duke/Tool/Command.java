package Duke.Tool;

import Duke.Task.*;

import java.time.LocalDateTime;

public class Command {
    
    //handle invalid input
    public void invalidInput() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    //mark a task as done
    public void markAsDone(int num, TaskList taskList) throws DukeException {
        if (num > 0 && num <= taskList.getSize()) {
            taskList.get(num - 1).markAsDone();
            String msgForDone = "    ____________________________________________________________\n"
                    + "    Nice! I 've marked this task as done: \n"
                    + "       " + taskList.get(num - 1).toString() + "\n"
                    + "    ____________________________________________________________\n";
            System.out.println(msgForDone);
        } else {
            throw new DukeException(
                    "OOPS!!! The task is not found. Please try again."
            );
        }
    }

    //list all the tasks
    public void list(TaskList taskList) {
        String msgForList = "    ____________________________________________________________\n";
        msgForList += "    Here are the tasks in your list: \n";
        for (int i = 0; i < taskList.getSize(); i++) {
            msgForList += "    " + (i + 1) + ". "
                    + taskList.get(i).toString() + "\n";
        }
        msgForList += "    ____________________________________________________________\n";
        System.out.println(msgForList);
    }

    //delete a task
    public void delete(int num, TaskList taskList) throws DukeException {
        if (num > 0 && num <= taskList.getSize()) {
            String msgForDelete = "    ____________________________________________________________\n"
                    + "    Noted. I've removed this task: \n"
                    + "       " + taskList.get(num - 1).toString() + "\n";
            taskList.remove(num - 1);
            msgForDelete += taskList.countNum() + "\n"
                    + "    ____________________________________________________________\n";
            System.out.println(msgForDelete);
        } else {
            throw new DukeException(
                    "OOPS!!! The task is not found. Please try again."
            );
        }
    }

    public void handleTodo(String instruction, TaskList taskList, Ui ui) throws DukeException {
        if (instruction.substring(4).isBlank()) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'todo '." + emoji ;
            throw new DukeException(exceptionMsg);
        }
        String toDoTitle = instruction.substring(5);
        Todo newTodo = new Todo(toDoTitle, false);
        taskList.addToDo(newTodo);
        ui.printAddedToDo(taskList, newTodo);
    }

    //handle deadline
    public void handleDeadline(String instruction, TaskList taskList, Ui ui) throws DukeException {
        int index = instruction.indexOf("/by");
        if (index == 8) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'event '." + emoji ;
            throw new DukeException(exceptionMsg);
        }

        if (index != -1) {
            String by = instruction.substring(index + 3);
            String description = instruction.substring(9, index);
            if (description.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                        + "    You can do it by adding description after 'deadline '." + emoji ;
                throw new DukeException(exceptionMsg);
            } else if (by.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = "OOPS!!! I'm sorry, but the deadline cannot be empty. \n"
                        + "    You can do it by adding deadline after '/by '." + emoji ;
                throw new DukeException(exceptionMsg);
            }
            Deadline deadline = new Deadline(description, LocalDateTime.parse(by.substring(1), Parser.validFormat), false);
            taskList.addDeadline(deadline);
            ui.printAddedDdl(taskList, deadline);
        } else {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but you have to indicate the deadline. \n"
                    + "    You can do it by adding '/by' after the description." + emoji ;
            throw new DukeException(exceptionMsg);
        }
    }

    //handle event
    public void handleEvent(String instruction, TaskList taskList, Ui ui) throws DukeException {
        int index = instruction.indexOf("/at");
        if (index == 5) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'event '." + emoji ;
            throw new DukeException(exceptionMsg);
        }

        if (index != -1) {
            String time = instruction.substring(index + 3);
            String description = instruction.substring(6, index);
            if (description.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                        + "    You can do it by adding description after 'event '." + emoji ;
                throw new DukeException(exceptionMsg);
            } else if (time.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = "OOPS!!! I'm sorry, but the time cannot be empty. \n"
                        + "    You can do it by adding time after '/at '." + emoji ;
                throw new DukeException(exceptionMsg);
            }
            Event event = new Event(description, LocalDateTime.parse(time.substring(1), Parser.validFormat), false);
            taskList.addEvent(event);
            ui.printAddedEvent(taskList, event);

        } else {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but you have to indicate the time of the event. \n"
                    + "    You can do it by adding '/at' after the description." + emoji ;
            throw new DukeException(exceptionMsg);
        }
    }
}
