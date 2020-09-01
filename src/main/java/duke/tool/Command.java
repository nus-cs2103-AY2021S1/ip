package duke.tool;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDateTime;

/**
 * Handles different command.
 */
public class Command {

    /**
     * Handles invalid user input.
     * 
     * @throws DukeException exception indicating invalid input.
     */
    public void invalidInput() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Marks task as done.
     * 
     * @param num index.
     * @param taskList a list of tasks.
     * @throws DukeException indicates that the task is not found.
     */
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

    /**
     * Lists all the tasks in the list.
     * 
     * @param taskList a list of tasks.
     */
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

    /**
     * Deletes a task from the list.
     * 
     * @param num index of the task.
     * @param taskList a list of tasks.
     * @throws DukeException indicates that the task is not found.
     */
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

    /**
     * Handles to-do task.
     * 
     * @param instruction to-do instructions
     * @param taskList a list of tasks.
     * @param ui handles system output.
     * @throws DukeException indicates that the description is empty.
     */
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

    /**
     * Handles deadline task.
     * 
     * @param instruction deadline-instructions.
     * @param taskList a list of tasks.
     * @param ui
     * @throws DukeException indicates that the description or deadline timing is missing.
     */
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

    /**
     * Handles event.
     * 
     * @param instruction event-instruction.
     * @param taskList a list of tasks.
     * @param ui Handles system output.
     * @throws DukeException indicates that the timing or the description is missing.
     */
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

    /**
     * Finds a task by searching for a keyword.
     * 
     * @param taskList a list of tasks.
     * @param input find instructions.
     * @throws DukeException indicates that the instruction is empty.
     */
    public void find(TaskList taskList, String input) throws DukeException {
        if (input.length() == 4) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'find '." + emoji ;
            throw new DukeException(exceptionMsg);
        } else {
            String query = input.substring(5);
            int count = 0;
            System.out.println("    ____________________________________________________________\n"
                    + "    Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.get(i).getTaskDescription().contains(query)) {
                    count += 1;
                    System.out.println("    " + count + "." + taskList.get(i).toString());
                }
            }
            System.out.println("    ____________________________________________________________\n");
        }
    } 
    
}
