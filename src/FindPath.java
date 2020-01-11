import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//Okay, so what went wrong?
// 1. I assumed, wrongly that this was a search tree. it was not.
// 2. I assumed simply returning the first element without doing any sorting would work. It does not.
// 3. I was too careless to add my values to the array after I created it, so for a time I was returning an empty array.
// 4. I took out the "nopath" case and then forgot to add it back in.
// 5. When I did add it back in, I was careless enough to put return null; instead of return "nopath";

public class FindPath {
    private ArrayList<String> myPaths = new ArrayList<>();
    private void codingHelper(TreeNode root, int target, String path){
        if(root == null) return;
        if(root.info == target){
            myPaths.add(path);
        }
        codingHelper(root.left, target, path +"0");
        codingHelper(root.right, target, path + "1");
    }
    public String path(TreeNode root, int target) {
        codingHelper(root, target, "");
        if(myPaths.size() == 0) return "nopath";
        String[] retPaths = new String[myPaths.size()];
        int k = 0;
        for(String pathway : myPaths){
            retPaths[k] = pathway;
            k++;
        }
        Arrays.sort(retPaths, Comparator.comparing(String::length)); //note, after this its still inferred that they're sorted alphabetically.
        return retPaths[0];
    }
}