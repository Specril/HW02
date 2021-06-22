import java.sql.Timestamp;
import java.util.Date;
public class File extends StorageItem {
    private String type;
    private String content;

    public File(String name, String type) {
        super(name);
        this.type = type;
        this.content = "";
    }
    @Override
    public String getName(){
        return super.name+"."+this.type;
    }
    @Override
    public int getSize() {
        return content.length();
    }
    public void addContent(String contentToAdd){
        this.content+=contentToAdd;
    }
    public void printContent(){
        Timestamp timestamp= Timestamp.valueOf("2017-01-01 00:00:00");
        timestamp.setTime(super.timeStamp);
        System.out.println(getName()+" Size: "+getSize()+"MB Created: "+timestamp.toString()+"\n"+this.content);
    }
    @Override
    public void printTree(SortingField field) {
        System.out.println(getName());
    }
}
