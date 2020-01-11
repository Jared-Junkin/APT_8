import java.awt.*;
import java.util.ArrayList;

public class ListShift {

    public ListNode initialize(int[] start){
        ListNode list = new ListNode(start[0]);
        ListNode front = list;
        for(int k = 1; k < start.length; k++){
            list.next = new ListNode(start[k]);
            list = list.next;
        }
        return front;
    }
    public ListNode shift(ListNode start, int key) {
        if(start == null) return null;
        if(start.next == null) return start;

        ListNode negative = new ListNode(-1);
        ListNode negativeFront = negative;
        ListNode front = new ListNode(-1);
        front.next = start;
        start = front;
        //front = start;
        while(start != null && start.next!= null){
            if(start.next.info > key){
                ListNode temp = new ListNode(start.next.info);
                negative.next = temp;
                negative = negative.next;
                start.next = start.next.next;
            }
            else{
                start = start.next;
            }
        }

        front = front.next;
        if(negativeFront.next == null) return front;
        negativeFront = negativeFront.next;
        negative.next = front; //negative front.last.next.
        return negativeFront;

    }
    public static void main(String[] args){
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8};
        int key = 5;
        ListShift test = new ListShift();
        test.shift(test.initialize(data), key);

    }
}