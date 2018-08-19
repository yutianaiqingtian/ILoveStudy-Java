package offer.jianzhi.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 从尾部到头打印链表
 *
 * @author jhZhang
 * @date 2018/4/3
 */
public class Main5 {
Node head;

Main5() {
head = null;
head.next = null;
}

public static void main(String[] args) {
}

public List<String> printFromEndToStart() {
if (head == null) {
return null;
}
Node current = head;
List<String> nodes = new ArrayList<>();
nodes.add(head.date);
while (current.next != null) {
nodes.add(current.date);
current = current.next;
}
return nodes;
}

class Node {
String date;
Node next;
}
}
