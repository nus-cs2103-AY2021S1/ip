package tickbot.storage;

public class DataStorageFactory {
    private static class Singleton {
        private static DataStorage instance = new DataStorage();
    }

    private DataStorageFactory() { }

    /**
     * Gets a singleton instance of {@code DataStorage}.
     * @return A singleton instance.
     */
    public static DataStorage getInstance() {
        return Singleton.instance;
    }
}
