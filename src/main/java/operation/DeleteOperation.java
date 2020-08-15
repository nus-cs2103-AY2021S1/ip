package operation;

import task.TaskStorage;

public class DeleteOperation extends Operation {
    private TaskStorage taskStorage;

     public DeleteOperation(String[] commands, TaskStorage taskStorage) {
        super(commands);
        this.taskStorage = taskStorage;
     }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(this.commands[1]);
        this.taskStorage.removeTask(index);
        System.out.println("Noted. I've removed this task:");
        this.taskStorage.printTask(index);
        String remainder = String.format("You now have %d tasks in the list",
                this.taskStorage.getCurrCapacity());
        System.out.println(remainder);
    }
}
