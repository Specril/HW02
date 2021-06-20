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
        Date creationDate = new Date(super.timeStamp);
        System.out.println(getName()+"Size: "+getSize()+"MB Created"+creationDate.toString()+"\n"+this.content);

    }
}
