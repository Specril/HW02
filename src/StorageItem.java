import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class StorageItem {
    protected String name;
    protected long timeStamp;
    StorageItem(String name) {
        this.name = name;
        Date minDate = new Date(2017,1,1,0,0,0);
        long minMillis = minDate.getTime();
        Date maxDate = new Date(2021,12,31,23,59,59);
        long maxMillis = maxDate.getTime();
        this.timeStamp = Math.abs(Main.rnd.nextLong())%(maxMillis-minMillis)+minMillis;
    }
    abstract String getName();

    abstract int getSize();

    void printTree(SortingField field){

    }

}
