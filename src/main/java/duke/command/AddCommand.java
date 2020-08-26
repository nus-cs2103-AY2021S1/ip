package duke.command;

import duke.*;
import duke.task.*;

public class AddCommand extends Command{
    CommandType taskType;
    String taskContent;
    
    public AddCommand(CommandType taskType, String taskContent) {
        this.taskType = taskType;
        this.taskContent = taskContent;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskContent == null) {
            throw new DukeException("You have to tell me what's your task!");
        }
        
        Task task = null;
        
        switch (taskType) {
        case TODO: {
            ToDo todo = new ToDo(taskContent);
            todo.generateUniqueId();
            tasks.addTask(todo);
            storage.saveTodo(todo);
            task = todo;
            break;
        }
        case DEADLINE: {
            String[] taskContentArr = taskContent.split(" /by ");
            if (taskContentArr.length < 2) {
                throw new DukeException("You need to tell me when this task is due!");
            }
            Deadline deadline = new Deadline(taskContentArr[0], taskContentArr[1]);
            deadline.generateUniqueId();
            tasks.addTask(deadline);
            storage.saveDeadline(deadline);
            task = deadline;
            break;
        }
        case EVENT: {
            String[] taskContentArr = taskContent.split(" /at ");
            if (taskContentArr.length < 2) {
                throw new DukeException("You need to tell me when this event is happening!");
            }
            Event event = new Event(taskContentArr[0], taskContentArr[1]);
            event.generateUniqueId();
            tasks.addTask(event);
            storage.saveEvent(event);
            task = event;
        }
        }
        StringBuilder message = new StringBuilder("Alright! I've added this task:\n");
        message.append(task);
        message.append("\nNow you have ").append(tasks.size()).append(" task(s) in your list.");
        ui.botOutput(message);
    } 
}
