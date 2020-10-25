package storage;

/**
 * A Storage object deals with loading data from a file and saving data to the same file.
 *
 * @author ameliatjy
 * @version 2.0
 * @since 2020-09-08
 */
public class Storage {

    protected String filePath;

    /**
     * Public constructor of Storage object.
     *
     * @param filePath path to store information.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
    }
}
