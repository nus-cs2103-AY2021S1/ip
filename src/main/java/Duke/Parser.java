package duke;

import java.util.ArrayList;

public class Parser {

    private TaskList taskList, archiveList;

    public Parser(TaskList taskList, TaskList archiveList){
        this.taskList = taskList;
        this.archiveList = archiveList;
    }

    /**
     * Run commands and seperate them according to types
     * @param command the command
     * @throws DukeException an Duke.DukeException that stores the information to be printed
     * @throws Exception
     */
    public String runCommand(Command command) throws DukeException, Exception{
        Task task;
        ArrayList<Task> allTasks;
        switch (command) {
            case BYE:
                return (Ui.print("Bye. Hope to see you again soon!"));
            case LIST:
                return (Ui.printList(taskList.getTodoList()));
            case TODO:
                task = parseTodoTask(command.getTaskContent());
                return (Ui.printTodoTask(task.toString(), taskList.getSize(), taskList.getUndoneCount()));
            case DEADLINE:
                task = parseDeadlineTask(command.getTaskContent());
                return (Ui.printDeadlineTask(task.toString(), taskList.getSize(), taskList.getUndoneCount()));
            case EVENT:
                task = parseEventTask(command.getTaskContent());
                return (Ui.printEventTask(task.toString(), taskList.getSize(), taskList.getUndoneCount()));
            case DONE:
                task = parseDone(command.getTaskContent());
                return (Ui.printDelete(task, taskList.getUndoneCount()));
            case DELETE:
                task = parseDelete(command.getTaskContent());
                return (Ui.printDelete(task, taskList.getUndoneCount()));
            case FIND:
                allTasks = parseFind(command.getTaskContent());
                return (Ui.printFind(allTasks));
            case ARCHIVE:
                task = parseArchive(command.getTaskContent());
                return (Ui.printArchive(task, taskList.getUndoneCount()));
            case INVALID:
                throw new CommandException(command.echo() + " is an invalid command.\n"+
                        "please try another one.");
            default:
                throw new DukeException("This default situation should not happen."
                        + "Please contact the idiot programmer.");

        }
    }

    /**
     * Parse the content of Duke.TodoTask and pass parsed content to taskList
     * @param content content of parsed event
     * @return a Duke.Task object
     * @throws EmptyDescriptionException
     */
    public Task parseTodoTask(String content) throws EmptyDescriptionException{
        if (content.length() == 0)
            throw new EmptyDescriptionException("TODO");
        return taskList.addTodoTask(content);
    }

    /**
     * Parse the content of Duke.DeadlineTask and pass parsed content to taskList
     * @param content content of parsed event
     * @return a Duke.Task object
     * @throws EmptyDescriptionException
     * @throws ParseErrorException
     * @throws WrongDescriptionException
     */
    public Task parseDeadlineTask(String content) throws
            EmptyDescriptionException, ParseErrorException, WrongDescriptionException{
        if (content.length() <= 0)
            throw new EmptyDescriptionException("DEADLINE");
        try {
            String[] splitedContent = content.split("/by");
            return taskList.addDeadlineTask(splitedContent[0], splitedContent[1]);
        } catch (ParseErrorException e){
            throw e;
        } catch (Exception e){
            throw new WrongDescriptionException("The format of 'DEADLINE' is:\n"
                    + "> deadline TASK /by TIME\n"
                    + "Please re-enter your command.");
        }
    }

    /**
     * Parse the content of Duke.EventTask and pass parsed content to taskList
     * @param content content of parsed task
     * @return a Duke.Task object
     * @throws EmptyDescriptionException
     * @throws ParseErrorException
     * @throws WrongDescriptionException
     */
    public Task parseEventTask(String content) throws
            EmptyDescriptionException, ParseErrorException, WrongDescriptionException{
        if (content.length() <= 0)
            throw new EmptyDescriptionException("EVENT");
        try {
            String[] splitedContent = content.split("/at");
            return taskList.addEventTask(splitedContent[0], splitedContent[1]);
        } catch (ParseErrorException e){
            throw e;
        } catch (Exception e){
            throw new WrongDescriptionException("The format of 'EVENT' is:\n"
                    + "> event TASK /at TIME /to TIME\n"
                    + "Please re-enter your command.");
        }
    }

    /**
     * Parse the content of a Delete command and ask taskList to delete it.
     * @param content content of parsed command
     * @return a Duke.Task object indicates the requested task
     * @throws DukeException
     * @throws WrongDescriptionException
     * @throws EmptyDescriptionException
     */
    public Task parseDone(String content) throws
            DukeException, WrongDescriptionException, EmptyDescriptionException{
        try {
            if (content.length() < 1)
                throw new EmptyDescriptionException("DONE");
            int index = Integer.parseInt(content) - 1;
            return taskList.doneTask(index);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Sorry, I can't do that for you.\n"+
                    "You only have " + String.format("%d",taskList.getSize()) + " tasks on your list.");
        } catch (Exception e) {
            throw new WrongDescriptionException("The description of 'DONE' should be an integer.\n"
                    + "Please re-enter your command.");
        }
    }

    /**
     * Parse the content of a  command and ask taskList to finish it.
     * @param content content of parsed command
     * @return a Duke.Task object indicates the requested task
     * @throws DukeException
     * @throws WrongDescriptionException
     * @throws EmptyDescriptionException
     */
    public Task parseDelete(String content) throws
            DukeException, WrongDescriptionException, EmptyDescriptionException{
        try {
            if (content.length() < 1)
                throw new EmptyDescriptionException("DELETE");
            int index = Integer.parseInt(content) - 1;
            return taskList.deleteTask(index);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Sorry, I can't do that for you.\n"+
                    "You only have " + String.format("%d",taskList.getSize()) + " tasks on your list.");
        } catch (Exception e) {
            throw new WrongDescriptionException("The description of 'DELETE' should be an integer.\n"
                    + "Please re-enter your command.");
        }
    }

    /**
     * Parse the content of a Done command and ask taskList to finish it.
     * @param content content of parsed command
     * @return a Duke.Task object indicates the requested task
     * @throws DukeException
     * @throws WrongDescriptionException
     * @throws EmptyDescriptionException
     */
    public Task parseArchive(String content) throws
            DukeException, WrongDescriptionException, EmptyDescriptionException{
        try {
            if (content.length() < 1)
                throw new EmptyDescriptionException("ARCHIVE");
            int index = Integer.parseInt(content) - 1;
            Task tmp = taskList.deleteTask(index);
            archiveList.addTask(tmp);
            return tmp;
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Sorry, I can't do that for you.\n"+
                    "You only have " + String.format("%d",taskList.getSize()) + " tasks on your list.");
        } catch (Exception e) {
            throw new WrongDescriptionException("The description of 'ARCHIVE' should be an integer.\n"
                    + "Please re-enter your command.");
        }
    }

    public ArrayList<Task> parseFind(String content){
        return taskList.findTask(content);
    }
}
