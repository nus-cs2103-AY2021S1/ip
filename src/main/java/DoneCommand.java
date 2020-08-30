public class DoneCommand extends Command {
    public DoneCommand(String[] words) {
        super(words);
    }

    @Override
    public void execute(TaskList ls, Ui ui) throws DukeException {
        int number = Integer.parseInt(words[1]);
        if (number > ls.size()) {
            throw new DukeNotSureException("This task doesn't seem to exist? :s");
        } else {
            Task oldTask = ls.get(number - 1);
            oldTask.checkTask();
            ui.printResult(("Nice! I've marked this task as done:" + "\n" + oldTask.getStatus()));
        }
    }
}
