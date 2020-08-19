package dependencies.storage;


/**
 * Fowler-No-Voll hash utility function.
 */
class FNV64 {
    private static final long PRIME = 0x100000001b3L;
    private static final long OFFSET = 0xcbf29ce484222325L;

    public static long hash(String k) {
        long ret = OFFSET;
        int l = k.length();
        for (int i = 0; i < l; i++) {
            ret ^= k.charAt(i);
            ret *= PRIME;
        }
        return ret;
    }
}