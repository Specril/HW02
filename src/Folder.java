import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Folder extends StorageItem {
    private ArrayList<StorageItem> content;
    public static int indent=0; // Will be used later for indentation in the printTree method.

    /**
     * Builder function for Folder.
     * @param name A string name used for building a new storage item.
     */
    public Folder(String name) {
        super(name);
        this.content = new ArrayList<>(); // Create an array list in memory.
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public int getSize() {
        int countTotalContentSize = 0;
        for (StorageItem item : this.content) { // for every item in the folder increment our counter with its size, if it's a folder use recursion.
            countTotalContentSize += item.getSize();
        }
        return countTotalContentSize;
    }

    /**
     * A functions that adds a StorageItem to a folder (can be a file or another folder) unless it an item with the same name already exists.
     * @param item An item that ought to be added to the folder.
     * @return true if the item was added successfully, false if it wasn't added.
     */
    public boolean addItem(StorageItem item) {
        if (existItem(item.getName())) {
            return false;
        }
        this.content.add(item);
        return true;
    }

    /**
     * A functions that goes over our content list and checks if one of the items there has the same name as the item given.
     * @param item The name of the item we want to find.
     * @return true if a StorageItem with this name exists in our folder, false if doesn't exist.
     */
    public boolean existItem(String item) {
        for (StorageItem sm : this.content) {
            if (item.equals(sm.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * A function that gets a String path to a file and returns the file.
     * @param path String comprised of the names of folder separated with a "/" and finally the name of the file.
     * @return null if the file was not found by using the given path, else return the file.
     */
    public File findFile(String path) {
        if (!path.contains("/") && existItem(path)) { // Check if the path only contains the name of a file and it exists in our folder.
            return (File) returnStorageItem(this.content, path); // Use the returnStorageItem method to return the requested file.
        }
        String[] pathList = path.split("/"); // Split our path string into an array.
        if (existItem(pathList[0])) { // Check if the first folder in the path exists in the folder we are currently searching.
            int firstSubFolderIndex = path.indexOf("/"); // The index of the first "/" in our path.
            String newPath = path.substring(firstSubFolderIndex + 1); // Shorten the path string by one folder.
            return ((Folder) returnStorageItem(this.content, pathList[0])).findFile(newPath); // Use recursion in order to search a folder within our folder.
        }
        return null;
    }

    /**
     * A function that runs over a given folder and returns a StorageItem by its name.
     * @param currentFolder The folder we are searching.
     * @param item The name of the StorageItem we are searching.
     * @return null if the item was not found, else return the StorageItem.
     */
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
        public int compare(StorageItem a, StorageItem b) {
            return a.getName().compareTo(b.getName());
        }
    }

    class SortBySize implements Comparator<StorageItem> {
        @Override
        public int compare(StorageItem a, StorageItem b) {
            return a.getSize() - b.getSize();
        }
    }

    class SortByDate implements Comparator<StorageItem> {
        @Override
        public int compare(StorageItem a, StorageItem b) {
            Date aDate = new Date(a.timeStamp);
            Date bDate = new Date(b.timeStamp);
            return aDate.compareTo(bDate);
        }
    }


    /**
     * A method that prints a directory tree of the given folder.
     * @param field The type of sorting we wish to enact on our folders.
     */
    @Override
    public void printTree(SortingField field) {
        switch (field) { // Check what kind of sorting we will do.
            case NAME:
                Collections.sort(this.content, new SortByName());
                break;
            case SIZE:
                Collections.sort(this.content, new SortBySize().thenComparing(new SortByName()));
                break;
            case DATE:
                Collections.sort(this.content, new SortByDate().thenComparing(new SortByName()));
                break;
            default:
        }
        indent++; // We are in a folder so increment our indent by 1.
        System.out.println(super.name); // Print the folder's name.
        for (int i = 0; i < this.content.size(); i++) {
            printIndentations(indent); // Use a method to print our indentations as "|    ".
            this.content.get(i).printTree(field); // If used on a File object it will print its name, and if on a Folder it will recursively print out its content.
        }
        indent--; // We got out of a folder so go back one indent.
    }

    /**
     * A method that prints indentations
     * @param indent Gets the number of indentations we want to use in the current directory.
     */
    public void printIndentations(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("|    ");
        }
    }
}
