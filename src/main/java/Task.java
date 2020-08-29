package duke;

import java.time.LocalDateTime;
import java.util.Optional;

class Task{
	duke.TaskType taskType;
	boolean isDone;
	String string;
	Optional<LocalDateTime> dateTime;

	public Task(duke.TaskType taskType, boolean isDone, String string){
		this.taskType = taskType;
		this.isDone = isDone;
		this.string = string;
		this.dateTime = Optional.empty();
	}
	public Task(duke.TaskType taskType, boolean isDone, String string, Optional<LocalDateTime> date) {
		this.taskType = taskType;
		this.isDone = isDone;
		this.string = string;
		this.dateTime = dateTime;
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

	public duke.Task done(){
		return new duke.Task(taskType, true, string);
	}

	public String getTypeString(){
		String string;
		if(taskType.equals(duke.TaskType.TODO)){
			string = "[T]";
		}
		else if(taskType.equals(duke.TaskType.DEADLINE)){
			string = "[D]";
		}
		else{
			string = "[E]";
		}
		return string;
	}

	public String toString(){
		return string;
	}
}
