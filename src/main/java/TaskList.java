import java.util.List;

public class TaskList {
	Storage storage;

	public TaskList(Storage storage) {
		this.storage = storage;
	}

	public List<Task> getList() throws Exception {
		return storage.getFileContents();
	}

	public Task getTask(int i) throws Exception {
		List<Task> filecontents = storage.getFileContents();
		if (i <= 0 || i > filecontents.size()) {
			throw new InvalidIndexException();
		}
		return filecontents.get(i - 1);
	}

	public void addTask(String[] arr) throws Exception {
		storage.appendToFile(Task.stringFormat(arr));
	}

	public Task completeTask(int i) throws Exception {
		List<Task> filecontents = storage.getFileContents();
		Task t = filecontents.get(i - 1);
		if (i <= 0 || i > filecontents.size()) {
			throw new InvalidIndexException();
		}
		filecontents.get(i - 1).setStatus("1");
		storage.rewriteFileContents(filecontents);
		return t;
	}

	public Task deleteTask(int i) throws Exception {
		List<Task> filecontents = storage.getFileContents();
		Task t = filecontents.get(i - 1);
		if (i <= 0 || i > filecontents.size()) {
			throw new InvalidIndexException();
		}
		filecontents.remove(i - 1);
		storage.rewriteFileContents(filecontents);
		return t;
	}

	public int getSize() throws Exception {
		List<Task> filecontents = storage.getFileContents();
		return filecontents.size();
	}
}