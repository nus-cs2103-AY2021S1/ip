package java.commands;

import java.tasklist.*;
import java.storage.*;
import java.ui.*;
import java.tasks.*;
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

    public String execute(TaskList tasks, Ui ui, Storage storage){
        String output = "";
        switch (type) {
            case "todo":
                Todo todo = new Todo(description);
                tasks.addTask(todo);
                output = ui.displayAddTaskMessage(todo);
                break;
            case "deadline":
                Deadline deadline = new Deadline(description, date);
                tasks.addTask(deadline);
                output = ui.displayAddTaskMessage(deadline);
                break;
            case "event":
                Event event = new Event(description, date);
                tasks.addTask(event);
                output = ui.displayAddTaskMessage(event);
                break;
        }
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand command = (AddCommand) obj;
            return (type.equals(command.type))&&(description.equals(command.description))
                    &&(date == date||date.equals(command.date));
        } else {
            return false;
        }
    }
}
