package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
	private List<Task> lst;

	public TaskList() {
		lst = new ArrayList<>();
	}

	public TaskList(String load) throws DukeException {
		//initialize  list of tasks
		lst = new ArrayList<>();

		try {
			//loop through load to form task list
			String[] tasks = load.split("\\|");
			for (String task : tasks) {
				String[] instruction = task.split(",");
				if (instruction[0].equals("T")) {
					Task tsk = new Todo(instruction[1], instruction[2]);
					lst.add(tsk);
				} else if (instruction[0].equals("D")) {
					Task tsk = new Deadline(instruction[1], instruction[2], instruction[3]);
					lst.add(tsk);

				} else if (instruction[0].equals("E")) {
					Task tsk = new Event(instruction[1], instruction[2], instruction[3]);
					lst.add(tsk);
				} else {
					throw new DukeException("error loading from file");
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DukeException(e.getMessage());
		} catch (Exception e) {
			throw new DukeException("file could not be parsed");
		}
	}


	public List<Task> getList() {
		return this.lst;
	}

	public Task get(int index) {
		return this.lst.get(index);
	}

	public void add(Task tsk) {
		this.lst.add(tsk);
	}


	public String toString() {
		//initialize String
		String taskList = "";

		//get list to loop through tasks
		List<Task> tasks = this.getList();

		for (int i = 0; i < tasks.size(); i++) {
			Task targetTask = tasks.get(i);

			//append item index
			taskList += String.valueOf(i + 1);
			taskList += ".";

			//append item
			taskList += targetTask.toString();

			//append newLine
			if (i < tasks.size() - 1) {
				taskList += "\n";
			}
		}

		return taskList;
	}

	public int getSize() {
		return this.getList().size();
	}

	public void markDone(int itemNumber) {
		Task doneItem = this.lst.get(itemNumber - 1);
		doneItem.markAsDone();
	}

	public Task delete(int itemNumber) {
		Task item = this.lst.get(itemNumber - 1);
		this.lst.remove(item);
		return item;
	}

}
