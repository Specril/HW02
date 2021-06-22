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
            return (File) ((Folder) returnStorageItem(this.content, pathList[0])).findFile(newPath);
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


    @Override
    public void printTree(SortingField field) {
    }
}
