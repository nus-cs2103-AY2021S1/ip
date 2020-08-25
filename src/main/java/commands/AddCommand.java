package main.java.commands;
import main.java.tasklist.*;
import main.java.ui.*;
import main.java.storage.*;
import main.java.tasks.*;
import java.time.LocalDate;

public class AddCommand extends Command {
    protected String type;
    protected String description;
    protected LocalDate date;

    public AddCommand(String type, String description, LocalDate date){
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        switch (type) {
            case "todo":
                Todo todo = new Todo(description);
                tasks.addTask(todo);
                ui.displayAddTaskMessage(todo);
                break;
            case "deadline":
                Deadline deadline = new Deadline(description, date);
                tasks.addTask(deadline);
                ui.displayAddTaskMessage(deadline);
                break;
            case "event":
                Event event = new Event(description, date);
                tasks.addTask(event);
                ui.displayAddTaskMessage(event);
                break;
        }
    }

}
