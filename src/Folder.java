import java.util.ArrayList;

public class Folder extends StorageItem {
    private ArrayList<StorageItem> content;

    public Folder(String name) {
        super(name);
        this.content = new ArrayList<>();
    }

    public ArrayList<StorageItem> getContent() {
        return content;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public int getSize() {
        int countTotalContentSize = 0;
        for (StorageItem item : this.content) {
            countTotalContentSize += item.getSize();
        }
        return countTotalContentSize;
    }

    public boolean addItem(StorageItem item) {
        for (StorageItem sm : this.content) {
            if (item.getName() == sm.getName()) {
                return false;
            }
        }
        this.content.add(item);
        return true;
    }

    public File findFile(String path) {
        return null;
    }


    @Override
    public void printTree(SortingField field) {
    }
}
