public class DeleteCommand extends Command{
    int indexOfTaskToDelete;
    
    DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToDelete = tasks.getTask(indexOfTaskToDelete);
        tasks.delete(indexOfTaskToDelete);
        ui.showDeleted(taskToDelete, tasks.size());
        
        storage.save(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }
    
    /*
       private void delete(int indexOfTaskToDelete) {
        Task taskToDelete = taskList.get(indexOfTaskToDelete - 1);
        taskList.remove(indexOfTaskToDelete - 1);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       " + taskToDelete + "\n" +
                "     Now you have " + taskList.size() +
                (taskList.size() == 1 ? " task in the list.\n" : " tasks in the list.\n") +
                "    ____________________________________________________________");
        
        writeSaveFile();
    }
     */
}
