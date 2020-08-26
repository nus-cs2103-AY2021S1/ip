public class ListDirective implements Executable {
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        StringBuilder builder = new StringBuilder();
        int i = 0;

        builder.append("dO YoU ReAlLy nEeD Me tO NaMe tHeM OuT foR yOu?\n");
        for (Task task : tasks.getTaskList()) {
            builder.append(String.format("%d. %s\n", ++i, task.toString()));
        }

        return new Report(builder.toString());
    }
}
