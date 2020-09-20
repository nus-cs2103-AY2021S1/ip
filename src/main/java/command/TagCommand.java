package command;

import exceptions.DukeException;
import task.TaskList;

public class TagCommand extends Command {
    protected int taskNo;
    protected String tag;

    /**
     * Creates TagCommand object to execute tagging task with given tag.
     * @param tasks TaskLists with Tasks to process through.
     * @param taskNo int to mark task number in tasks as done.
     * @param tag String tag to tag Task.
     */
    public TagCommand(TaskList tasks, int taskNo, String tag) {
        super(tasks);
        this.taskNo = taskNo;
        this.tag = tag;
    }

    /**
     * Sets tag for task in list of tasks.
     * @return String to inform user task has been tagged.
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        return this.tasks.tagTask(tag, taskNo);
    }
}
