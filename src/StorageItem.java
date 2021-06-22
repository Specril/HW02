import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

abstract class StorageItem {
    protected String name;
    protected long timeStamp;
    StorageItem(String name) {
        this.name = name;
        /*Date minDate = new Date(2017,1,1,0,0,0);
        long minMillis = minDate.getTime();
        Date maxDate = new Date(2021,12,31,23,59,59);
        long maxMillis = maxDate.getTime();
        this.timeStamp = Math.abs(Main.rnd.nextLong())%((maxMillis-minMillis)+minMillis);*/
        Timestamp minDate = Timestamp.valueOf("2017-01-01 00:00:00");
        Timestamp maxDate = Timestamp.valueOf("2021-12-31 23:59:59");
        long LOW_MILLIS = minDate.getTime();
        long HIGH_MILLIS = maxDate.getTime();
        long RANDOM_TIME = Math.abs(Main.rnd.nextLong());
        long time = ((RANDOM_TIME)%(HIGH_MILLIS-LOW_MILLIS)+LOW_MILLIS);
        this.timeStamp = time;
    }
    abstract String getName();

    abstract int getSize();

    abstract void printTree(SortingField field);
}
