public class Parser {

    public void command(String str, TaskList tasks, Storage storage) {
        try {
            if (str.equals("list")) {
                tasks.displayTasks();
            } else {
                if (str.startsWith("done")) {
                    tasks.completeTask(str);
                } else if (str.startsWith("delete")) {
                    tasks.deleteTask(str);
                } else {
                    tasks.addTask(str);
                }
                storage.save(tasks);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
