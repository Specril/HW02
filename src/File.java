import java.sql.Timestamp;

public class File extends StorageItem {
    private String type;
    private String content;

    /**
     * Builder function for File.
     * @param name A string name used for building a new storage item.
     * @param type A string containing the suffix for the current file's name.
     */
    public File(String name, String type) {
        super(name);
        this.type = type;
        this.content = ""; // content is set to an empty String.
    }

    @Override
    public String getName() {
        return super.name + "." + this.type;
    } // Return the name of the file and then "." + type.

    @Override
    public int getSize() {
        return content.length();
    }

    /**
     * A function that adds content to a File object.
     * @param contentToAdd A string containing the content we want to add to the file.
     */
    public void addContent(String contentToAdd) {
        this.content += contentToAdd;
    }

    public void printContent() {
        Timestamp timestamp = Timestamp.valueOf("2017-01-01 00:00:00"); // Create a timestamp with a value (the value is just for the purpose of creating a timestamp object).
        timestamp.setTime(super.timeStamp); // Set the timestamp to the date translated from milliseconds.
        System.out.println(getName() + " Size: " + getSize() + "MB Created: " + timestamp.toString() + "\n" + this.content);
    }

    /**
     * A function that prints the file's name
     * @param field is not used in this implementation of the function.
     */
    @Override
    public void printTree(SortingField field) {
        System.out.println(getName()); // For a file, only print the file's name.
    }
}
