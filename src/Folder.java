import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        if (existItem(item.getName())) {
            return false;
        }
        this.content.add(item);
        return true;
    }

    public boolean existItem(String item) {
        for (StorageItem sm : this.content) {
            if (item.equals(sm.getName())){
                return true;
            }
        }
        return false;
    }

    public File findFile(String path) {
        if (!path.contains("/") && existItem(path)) {
            return (File) returnStorageItem(this.content, path);
        }
        String[] pathList = path.split("/"); //
        if (existItem(pathList[0])) {
            int firstSubFolderIndex = path.indexOf("/");
            String newPath = path.substring(firstSubFolderIndex + 1);
            return ((Folder) returnStorageItem(this.content, pathList[0])).findFile(newPath);
        }
        return null;
    }

    public StorageItem returnStorageItem(ArrayList<StorageItem> currentFolder, String item) {
        for (int i = 0; i < currentFolder.size(); i++) {
            if (currentFolder.get(i).getName().equals(item)) {
                return currentFolder.get(i);
            }
        }
        return null;
    }
    class SortByName implements Comparator<StorageItem> {
        @Override
        public int compare(StorageItem a, StorageItem b)
        {
            return a.getName().compareTo(b.getName());
        }
    }
    class SortBySize implements Comparator<StorageItem> {
        @Override
        public int compare(StorageItem a, StorageItem b)
        {
            return a.getSize() - b.getSize();
        }
    }
    class SortByDate implements Comparator<StorageItem> {
        @Override
        public int compare(StorageItem a, StorageItem b)
        {
            return (int)(a.timeStamp - b.timeStamp);
        }
    }


    @Override
    public void printTree(SortingField field) {
        switch (field){
            case NAME:
                Collections.sort(this.content,new SortByDate());
                break;
            case SIZE:
                Collections.sort(this.content,new SortBySize().thenComparing(new SortByName()));
                break;
            case DATE:
                Collections.sort(this.content,new SortByDate().thenComparing(new SortByName()));
                break;
            default:
        }
        System.out.println(super.name);
        for (StorageItem sm:this.content) {
            System.out.print("|    ");
            sm.printTree(field);
        }
    }
}
