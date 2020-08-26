public class TaskCommand {
    public static String execute(String in, TaskList taskList, Storage storage) throws DukeException {
        TaskType taskType = Parser.parseTaskType(in);
        String taskDetails =
                in.replaceFirst(taskType.toString().toLowerCase(), "").trim();
        switch (taskType) {
        case Deadline:
        case Event:
        case Todo:
            return createTask(taskType, taskDetails, taskList, storage);
        default:
            throw new InvalidCommandException("Something went wrong during the execution of the command. :-(");
        }
    }

    private static String createTask(TaskType taskType, String details, TaskList taskList, Storage storage)
            throws InvalidTaskException {
        Task task = Task.createTask(taskType, details);
        taskList.add(task);
        storage.updateSaveFile(taskList);
        int len = taskList.size();
        return
            "Got it. I've added this task: \n" +
            "  " + task.toString() + "\n" +
            "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.";
    }
}
