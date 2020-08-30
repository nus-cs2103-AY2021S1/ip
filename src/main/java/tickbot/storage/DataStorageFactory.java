package tickbot.storage;

public class DataStorageFactory {
    private static class Singleton {
        private static DataStorage instance = new DataStorage();
    }

    private DataStorageFactory() { }

    /**
     * Get a singleton instance of {@code DataStorage}.
     * @return a singleton instance.
     */
    public static DataStorage getInstance() {
        return Singleton.instance;
    }
}
