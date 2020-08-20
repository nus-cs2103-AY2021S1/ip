public class Task{
	TaskType taskType;
	boolean isDone;
	String string;

	public Task(TaskType taskType, boolean isDone, String string){
		this.taskType = taskType;
		this.isDone = isDone;
		this.string = string;
	}

	public String getString() {
		return string;
	}
	public String getDoneString(){
		String string;
		if(isDone){
			string = "[✓]";
		}
		else{
			string = "[✗]";
		}
		return string;
	}

	public Task done(){
		return new Task(taskType, true, string);
	}

	public String getTypeString(){
		String string;
		if(taskType.equals(TaskType.TODO)){
			string = "[T]";
		}
		else if(taskType.equals(TaskType.DEADLINE)){
			string = "[D]";
		}
		else{
			string = "[E]";
		}
		return string;
	}
}