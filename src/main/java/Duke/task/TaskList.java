package Duke.task;

import Duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and represents tasks in the application. All the tasks in the program,
 * and its states, are stored in this list.
 */
public class TaskList {
	private List<Task> lst;

	/**
	 * Creates a new TaskList Object.
	 */
	public TaskList() {
		lst = new ArrayList<>();
	}

	/**
	 * Creates a new TaskList Object from a String source. If the String is valid, a new
	 * TaskList with pre-existing tasks would be constructed. Otherwise, a DukeException will be thrown.
	 *
	 * @param load String to be parsed and converted to list of tasks(TaskList).
	 * @throws DukeException when String cannot be successfully parsed.
	 */
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


	/**
	 * Returns the list of tasks in the program.
	 *
	 * @return list of tasks.
	 */
	public List<Task> getList() {
		return this.lst;
	}

	/**
	 * Return the task at a specified index of the list.
	 *
	 * @param index index of task.
	 * @return Task Object from the list.
	 */
	public Task get(int index) {
		return this.lst.get(index);
	}

	/**
	 * Adds a Task into the TaskList Object.
	 *
	 * @param tsk Task to be added.
	 */
	public void add(Task tsk) {
		this.lst.add(tsk);
	}


	/**
	 * String representation of the TaskList.
	 *
	 * @return String representation of the TaskList.
	 */
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

	/**
	 * Return size of the task list.
	 *
	 * @return number of lists.
	 */
	public int getSize() {
		return this.getList().size();
	}

	/**
	 * Mark an item in the task list as done.
	 *
	 * @param itemNumber task number in the list(not index number)
	 */
	public void markDone(int itemNumber) {
		Task doneItem = this.lst.get(itemNumber - 1);
		doneItem.markAsDone();
	}

	/**
	 * Deletes a Task in the list at the specified task number.
	 *
	 * @param itemNumber task number in the list(not index number)
	 * @return Task that was deleted from the list.
	 */
	public Task delete(int itemNumber) {
		Task item = this.lst.get(itemNumber - 1);
		this.lst.remove(item);
		return item;
	}

}
