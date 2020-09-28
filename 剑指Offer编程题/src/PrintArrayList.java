import java.util.ArrayList;
/*
* 题目描述
输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
*/
public class PrintArrayList {
    public static void main(String [] args){

    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode){

        ArrayList<Integer> list = new ArrayList<>();
        ListNode tmp = listNode;
        while(tmp!=null){
            list.add(0,tmp.val);
            tmp = tmp.next;
        }
        return list;
    }
}
    class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
          this.val = val;
       }
    }