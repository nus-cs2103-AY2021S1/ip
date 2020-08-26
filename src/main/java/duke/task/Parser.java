package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    protected static boolean parse(String command, TaskList tasks, Ui ui, Storage storage) throws YooException {

        try {
            String[] cmd = command.split(" ", 2);

            if (command.equals("bye")) {
                storage.upload(tasks);
                return true;
            }

            if (command.equals("list")) {
                if(tasks.length() > 0) {
                    ui.showTaskList(tasks);
                } else {
                    throw new YooException("You have no tasks!");
                }
            } else {
                switch (cmd[0]) {
                case "done":
                    markAsDone(tasks, cmd, ui);
                    break;
                case "delete":
                    deleteTask(tasks, cmd, ui);
                    break;
                case "todo":
                    addTodo(tasks, cmd, ui);
                    break;
                case "deadline":
                    addDeadline(tasks, cmd, ui);
                    break;
                case "event":
                    addEvent(tasks, cmd, ui);
                    break;
                default:
                    throw new YooException("Sorry, I didn't get that (\u3063*\u00B4\u25A1`)\u3063");
                }
            }
        } catch (IndexOutOfBoundsException e){
            throw new YooException("Please add a task description (>_<)");
        }
        return false;
    }

    private static void markAsDone(TaskList tasks, String[] temp, Ui ui) throws YooException {
        int index = Integer.parseInt(temp[1]);
        if (index > tasks.length()) {
            throw new YooException("No such task (>_<)");
        } else {
            ui.congratulate();
            Task t = tasks.get(index - 1);
            t.markAsDone();
            System.out.println(index + ". " + t);
        }
    }

    private static void deleteTask(TaskList tasks, String[] temp, Ui ui) throws YooException {
        int index = Integer.parseInt(temp[1]);
        if (index > tasks.length()) {
            throw new YooException("No such task (>_<)");
        } else {
            Task t = tasks.delete(index - 1);
            ui.confirmDelete(t, tasks);
        }
    }

    private static void addTodo(TaskList tasks, String[] temp, Ui ui) {
        Todo td = new Todo(temp[1]);
        tasks.add(td);
        ui.confirmAdd(td, tasks);
    }

    private static void addDeadline(TaskList tasks, String[] temp, Ui ui) throws YooException {
        try {
            String[] a = temp[1].split("/by ", 2);
            LocalDate by = LocalDate.parse(a[1]);
            Deadline dl = new Deadline(a[0], by);
            tasks.add(dl);
            ui.confirmAdd(dl, tasks);

        } catch (IndexOutOfBoundsException e) {
            throw new YooException("Sorry, your deadline time is missing (\u3063*\u00B4\u25A1`)\u3063");
        } catch (DateTimeParseException e) {
            throw new YooException("Invalid date! Please try again (>_<)");
        }
    }

    private static void addEvent(TaskList tasks, String[] temp, Ui ui) throws YooException {
        try {
            String[] a = temp[1].split("/at ", 2);
            LocalDate at = LocalDate.parse(a[1]);
            Event e = new Event(a[0], at);
            tasks.add(e);
            ui.confirmAdd(e, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new YooException("Sorry, your event time is missing (\u3063*\u00B4\u25A1`)\u3063");
        } catch (DateTimeParseException e) {
            throw new YooException("Invalid date! Please try again (>_<)");
        }
    }
}
