package duke.tool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.model.Interval;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Handles different command.
 */
public class Command {

    /**
     * Handles invalid user input.
     *
     * @throws DukeException exception indicating invalid input.
     */
    public String handleInvalidInput() throws DukeException {
        throw new DukeException(" OOPS!!! I'm sorry, \n     but I don't know what that means :-(");
    }

    /**
     * Marks task as done.
     *
     * @param num index.
     * @param taskList a list of tasks.
     * @throws DukeException indicates that the task is not found.
     */
    public String markAsDone(int num, TaskList taskList) throws DukeException {
        if (num > 0 && num <= taskList.getSize()) {
            taskList.get(num - 1).markAsDone();
            assert taskList.get(num - 1).getStatus() : "markAsDone not working";
            return Ui.SEPARATION_LINE
                    + "    Nice! I 've marked this task as done: \n"
                    + "       " + taskList.get(num - 1).toString() + "\n"
                    + Ui.SEPARATION_LINE;
        } else {
            throw new DukeException(
                    " OOPS!!! The task is not found. \n     Please try again."
            );
        }
    }

    /**
     * Lists all the tasks in the list.
     *
     * @param taskList a list of tasks.
     */
    public String list(TaskList taskList) {
        StringBuilder msgForList = new StringBuilder(Ui.SEPARATION_LINE);
        msgForList.append("    Here are the tasks in your list: \n");
        taskList.getList().stream().forEach((task) -> msgForList.append("    ")
                    .append(taskList.getList().indexOf(task) + 1).append(". ")
                    .append(task.toString()).append("\n"));
        //for (int i = 0; i < taskList.getSize(); i++) {
        //    msgForList.append("    ").append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
        // }

        msgForList.append(Ui.SEPARATION_LINE);
        return msgForList.toString();
    }

    /**
     * Deletes a task from the list.
     *
     * @param num index of the task.
     * @param taskList a list of tasks.
     * @throws DukeException indicates that the task is not found.
     */
    public String delete(int num, TaskList taskList) throws DukeException {
        if (num > 0 && num <= taskList.getSize()) {
            String msgForDelete = Ui.SEPARATION_LINE
                    + "    Noted. I've removed this task: \n"
                    + "       " + taskList.get(num - 1).toString() + "\n";
            taskList.remove(num - 1);
            assert taskList.getSize() >= 0 : "Deletion error";
            msgForDelete += taskList.countNum() + "\n"
                    + Ui.SEPARATION_LINE;
            return msgForDelete;
        } else {
            throw new DukeException(
                    " OOPS!!! The task is not found. \n     Please try again."
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
    public String handleTodo(String instruction, TaskList taskList, Ui ui) throws DukeException {
        assert instruction.length() >= 4 : "Parsing error";
        if (instruction.substring(4).isBlank()) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = "OOPS!!! I'm sorry, \n     but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'todo '." + emoji;
            throw new DukeException(exceptionMsg);
        }
        String toDoTitle = instruction.substring(5);
        Todo newTodo = new Todo(toDoTitle, false);
        taskList.addToDo(newTodo);
        return ui.printAddedToDo(taskList, newTodo);
    }

    /**
     * Handles deadline task.
     *
     * @param instruction deadline-instructions.
     * @param taskList a list of tasks.
     * @param ui handles system output.
     * @throws DukeException indicates that the description or deadline timing is missing.
     */
    public String handleDeadline(String instruction, TaskList taskList, Ui ui) throws DukeException {
        assert instruction.length() >= 8 : "Parsing error";
        int index = instruction.indexOf("/by");
        if (index == 8) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = " OOPS!!! I'm sorry, \n     but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'event '." + emoji;
            throw new DukeException(exceptionMsg);
        }

        if (index != -1) {
            String by = instruction.substring(index + 3);
            String description = instruction.substring(9, index);
            if (description.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = " OOPS!!! I'm sorry, \n     but the description cannot be empty. \n"
                        + "    You can do it by adding description after 'deadline '." + emoji;
                throw new DukeException(exceptionMsg);
            } else if (by.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = " OOPS!!! I'm sorry, \n     but the deadline cannot be empty. \n"
                        + "    You can do it by adding deadline after '/by '." + emoji;
                throw new DukeException(exceptionMsg);
            }
            Deadline deadline = new Deadline(
                    description, LocalDateTime.parse(by.substring(1), Parser.getValidFormat()), false);
            taskList.addDeadline(deadline);
            return ui.printAddedDdl(taskList, deadline);
        } else {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = " OOPS!!! I'm sorry, \n     but you have to indicate the deadline. \n"
                    + "    You can do it by adding '/by' after the description." + emoji;
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
    public String handleEvent(String instruction, TaskList taskList, Ui ui) throws DukeException {
        assert instruction.length() >= 5 : "Parsing error";
        int index = instruction.indexOf("/at");
        if (index == 5) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = " OOPS!!! I'm sorry, \n      but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'event '." + emoji;
            throw new DukeException(exceptionMsg);
        }
        if (index != -1) {
            String time = instruction.substring(index + 3);
            String description = instruction.substring(6, index);
            if (description.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = " OOPS!!! I'm sorry, \n     but the description cannot be empty. \n"
                        + "    You can do it by adding description after 'event '." + emoji;
                throw new DukeException(exceptionMsg);
            } else if (time.isBlank()) {
                String emoji = Emoji.SMILE.toString();
                String exceptionMsg = " OOPS!!! I'm sorry, \n     but the time cannot be empty. \n"
                        + "    You can do it by adding time after '/at '." + emoji;
                throw new DukeException(exceptionMsg);
            }
            Event event = new Event(description, LocalDateTime.parse(
                    time.substring(1), Parser.getValidFormat()), false);
            taskList.addEvent(event);
            return ui.printAddedEvent(taskList, event);
        } else {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = " OOPS!!! I'm sorry, \n     but you have to indicate the time of the event. \n"
                    + "    You can do it by adding '/at' after the description." + emoji;
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
    public String find(TaskList taskList, String input) throws DukeException {
        assert input.length() >= 4 : "Parsing error";
        if (input.length() == 4) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = " OOPS!!! I'm sorry, \n     but the description cannot be empty. \n"
                    + "    You can do it by adding description after 'find '." + emoji;
            throw new DukeException(exceptionMsg);
        } else {
            String query = input.substring(5);
            int count = 0;
            StringBuilder output = new StringBuilder();
            output.append(Ui.SEPARATION_LINE + "    Here are the matching tasks in your list: \n");
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.get(i).getTaskDescription().contains(query)) {
                    count += 1;
                    output.append("   ").append(count).append(".").append(taskList.get(i).toString()).append("\n");
                }
            }
            output.append(Ui.SEPARATION_LINE);
            return output.toString();
        }
    }

    /**
     * Outputs the free time of a day.
     *
     * @param taskList list of tasks.
     * @param input user instructions.
     * @param ui system output.
     * @return string representation of free slots in a day.
     */
    public String findFreeTime(TaskList taskList, String input, Ui ui) throws DukeException {
        if (input.length() == 9) {
            String emoji = Emoji.SMILE.toString();
            String exceptionMsg = " OOPS!!! I'm sorry, \n     but the date cannot be empty. \n"
                    + "    You can do it by adding description after 'free time  '." + emoji;
            throw new DukeException(exceptionMsg);
        } else {
            String date = input.substring(10);
            LocalDate dateTime = LocalDate.parse(date);
            ScheduleManager scheduleManager = new ScheduleManager(dateTime, dateTime, taskList, new TimeConverter());
            ArrayList<Interval> result = scheduleManager.findFreeSlots();
            return ui.showFreeSlots(result);
        }
    }
}
