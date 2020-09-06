package duke.storage;

import duke.exception.DukeOperationException;
import duke.exception.DukeParseException;
import duke.list.StorableList;
import duke.parser.StorageParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Represents an object that deals with loading and saving DukeLists into a text file. */
public abstract class Storage<T extends Storable> {
    protected final File file;
    protected final StorageParser<T> storageParser;

    /**
     * Constructor method.
     *
     * @param file the actual file path of the text file.
     */
    Storage(File file, StorageParser<T> storageParser) {
        this.file = file;
        this.storageParser = storageParser;
    }

    /**
     * Writes a <code>String</code> to the text file.
     *
     * @param text the <code>String</code> to be written.
     * @throws IOException if the file path does not exist.
     */
    private void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        fw.write(text);
        fw.close();
    }

    /**
     * Loads the <code>StorableList</code> from the text file.
     *
     * @param list the <code>list</code> to be loaded onto.
     * @return a <code>String</code> indicating the status of the loading.
     */
    public String loadList(StorableList<T> list) {
        Scanner sc;
        try {
            sc = new Scanner(this.file);
        } catch (IOException ignore) {
            return "I detect no task storage files. I shall create a fresh list.";
        }

        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            try {
                T storable = this.storageParser.parseStorageString(sc.nextLine());
                list.add(storable);
            } catch (DukeParseException exception) {
                sb.append(exception.getMessage());
                sb.append("\n");
            }
        }

        if (sb.length() == 0) {
            return "All tasks have been loaded.";
        } else {
            sb.deleteCharAt(sb.length() - 1);
            return "The following tasks could not be loaded:\n" + sb.toString();
        }
    }

    /**
     * Saves the <code>StorableList</code> into the text file.
     *
     * @param list the <code>StorableList</code> that is to be saved.
     * @throws DukeOperationException if the text file cannot be written onto.
     */
    public void saveToDisk(StorableList<T> list) throws DukeOperationException {
        StringBuilder sb = new StringBuilder();
        for (T storable : list) {
            String storageTask = storable.convertToStorageString();
            sb.append(storageTask);
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException exception) {
            throw new DukeOperationException("There were some problems when writing to the file. "
                    + exception.getMessage());
        }
    }
}
