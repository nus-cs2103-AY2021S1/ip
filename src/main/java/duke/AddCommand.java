package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;

public class AddCommand extends Command {
    private final CommandEnum command;
    private final String userInput;

    AddCommand(CommandEnum command, String userInput) {
        this.command = command;
        this.userInput = userInput;
    }


    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        switch (command) {
            case TODO:
                addToDo(taskList, storage, ui, this.userInput);
                break;
            case DEADLINE:
                addDeadline(taskList, storage, ui, this.userInput);
                break;
            case EVENT:
                addEvent(taskList, storage, ui, this.userInput);
                break;
        }
    }

    public String getUserInput() {
        return this.userInput;
    }


    public void addToDo(TaskList tasks, Storage storage, Ui ui, String userInput) throws DukeException, IOException {
        if (!userInput.substring(4).isBlank()) { //if got space behind, it will add also
            ToDo todo = new ToDo(userInput.substring(5));
            tasks.addTask(todo); //adds into tasks list
            ui.printAddTodo(todo, tasks);
            storage.writeToFile(tasks.getTasks());
        } else {
            System.out.println(Ui.line);
            System.out.println(Ui.bot);
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    public void addDeadline(TaskList tasks, Storage storage, Ui ui, String userInput) throws DukeException {
        String[] input = userInput.split(" ");
        if (!userInput.substring(8).isBlank()) {
            try {
                String de = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                String description = de.split(" /by ")[0];
                String date = de.split(" /by ")[1];
                Deadline deadline = new Deadline(description, date);
                tasks.addTask(deadline);
                ui.printAddDeadline(deadline, tasks);
                storage.writeToFile(tasks.getTasks());
            } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException ex) {
                System.out.println(Ui.line);
                System.out.println(Ui.bot);
                throw new DukeException("You have keyed in an invalid command for 'deadline'!");
            } catch (DateTimeParseException ex) {
                System.out.println(Ui.line);
                System.out.println(Ui.bot);
                throw new DukeException("Please key in your deadline in the form:\n /by <dd/MM/yyyy hh:mm AM/PM>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Ui.line);
            System.out.println(Ui.bot);
            throw new DukeException("The description of a deadline cannot be empty!");
        }
    }

    public void addEvent(TaskList tasks, Storage storage, Ui ui, String userInput) throws DukeException {
        String[] input = userInput.split(" ");
        if (!userInput.substring(5).isBlank()) {
            try {
                String ev = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                String description = ev.split(" /at ")[0];
                String dateAndTime = ev.split(" /at ")[1];
                Event event = new Event(description, dateAndTime);
                tasks.addTask(event);
                storage.writeToFile(tasks.getTasks());
                ui.printAddEvent(event, tasks);
            } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException ex ) {
                System.out.println(Ui.line);
                System.out.println(Ui.bot);
                throw new DukeException("You have keyed in an invalid command for 'event'!");
            } catch (DateTimeParseException ex) {
                throw new DukeException("Please key in your event in the form:\n /at <dd/MM/yyyy hh:mm AM/PM>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Ui.line);
            System.out.println(Ui.bot);
            throw new DukeException("The description of an event cannot be empty!");
        }
    }

}
