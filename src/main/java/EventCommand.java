public class EventCommand extends Command {
    private String[] nextCommandArr;
    
    public EventCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.nextCommandArr.length < 2) {
            throw new DukeException("The description of a event cannot be empty~");
        } else {
            try {
                String[] eventArr = this.nextCommandArr[1].split("/at");
                Event newEvent = new Event(eventArr[0], eventArr[1].strip());
                taskList.add(newEvent);
                ui.addTaskText(newEvent, taskList);
            } catch (Exception e) {
                throw new DukeException("Please input a proper date for your event~");
            }
        }
        
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
