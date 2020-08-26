public class DoneCommand extends Command {
    private final int taskNum;
    
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
            // mark as done
            Task t = tasks.getTaskAt(taskNum);
            t.markTaskDone();

            // print
            ui.printOnDone(tasks.getTaskAt(taskNum));

            // save the changes
            storage.write(tasks.getTaskList());
    }

}

//if (cmd.length == 2) {
//                    if (taskNum < tasks.size()) {
//                        // mark the task as done
//                        tasks.getTaskAt(taskNum).markTaskDone();
//                    } else {
//                        System.out.println("Task number given is not in range. Please try again.\n");
//                        break;
//                    }
//                } else {
//                    System.out.println("Insufficient arguments given. What task have you done?\n");
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid Format: " + cmd[1] + "\nWhat task number are you done with?\n");
//            }