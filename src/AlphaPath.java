public class AlphaPath {
    private class ListNode {
        String info;
        ListNode next;
        ListNode(String x){
            info = x;
        }
        ListNode(String x, ListNode node){
            info = x;
            next = node;
        }
    }

    public int findA(String firstRow){
        for(int j = 0; j < firstRow.length(); j++){
            if(firstRow.substring(j, j+1).equals("A")){
                return j;
            }
        }
        return -1;
    }

    public boolean inBounds(int row, int col, String[] maze){
        if(row < 0 || row > maze.length-1) return false;
        if(col < 0 || col > maze.length-1) return false;
        return true;
    }
    public ListNode initialize(){
        ListNode front = new ListNode("A");
        ListNode temp = front;
        String alph = "BCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int k = 0; k < alph.length(); k++){
            temp.next = new ListNode(alph.substring(k, k+1));
            temp = temp.next;
        }
        return front;
    }
    private String below;
    private String above;
    private String right;
    private String left;

    public String hasPath(String[] maze) {
        ListNode list = initialize();
        if(maze == null) return "NO";
        int Adex = findA(maze[0]);
        if(Adex == -1) return "NO";

        for(int k = 1; k < maze.length; k++){
            if(inBounds(k, Adex, maze)){
                below = maze[k].substring(Adex, Adex+1);
                if(below.equals(list.next.info)  && list.next.next == null){
                    return "YES";
                }
                if(below.equals(list.next.info)){
                    list = list.next;
                }
            }
            else if(inBounds(k - 1, Adex+1, maze)){
                right = maze[k-1].substring(Adex + 1, Adex + 2);
                if(right.equals(list.next.info) && list.next.next == null){
                    return "YES";
                }
                if(below.equals(list.next.info)){
                    list = list.next;
                    Adex = Adex + 1;
                    k = k - 1;
                }
            }
            else if(inBounds(k - 2, Adex, maze)){
                above = maze[k-1].substring(Adex, Adex+1);
                if(above.equals(list.next.info)  && list.next.next == null){
                    return "YES";
                }
                if(above.equals(list.next.info)){
                    list = list.next;
                    k = k - 2;
                }
            }
            else if(inBounds(k - 1, Adex - 1, maze)){
                left = maze[k-1].substring(Adex - 1, Adex);
                if(left.equals(list.next.info) && list.next.next == null){
                    return "YES";
                }
                if(left.equals(list.next.info)){
                    list = list.next;
                    k = k - 1;
                    Adex = Adex - 1;
                }
            }
            else{
                return "NO";
            }
        }
        return "YES";
    }
}