import java.sql.Timestamp;

abstract class StorageItem {
    protected String name;
    protected long timeStamp;

    /**
     * Builder function for StorageItem.
     * @param name A string name used for building a new storage item.
     */
    StorageItem(String name) {
        this.name = name;
        Timestamp minDate = Timestamp.valueOf("2017-01-01 00:00:00"); // Using a timestamp to determine our lowest value.
        Timestamp maxDate = Timestamp.valueOf("2021-12-31 23:59:59"); // Using a timestamp to determine our highest value.
        long LOW_MILLIS = minDate.getTime(); // Translating the timestamp into milliseconds and saving it as a long value.
        long HIGH_MILLIS = maxDate.getTime(); // Translating the timestamp into milliseconds and saving it as a long value.
        long RANDOM_TIME = Math.abs(Main.rnd.nextLong()); // Use the random number generator from Main to generate a pseudo-random long value.
        this.timeStamp = ((RANDOM_TIME)%(HIGH_MILLIS-LOW_MILLIS)+LOW_MILLIS); // Set the random timestamp to be between our high and low bounds.
    }
    abstract String getName();

    abstract int getSize();

    abstract void printTree(SortingField field);
}
