package offer.jianzhi.chapter3;

/**
 * @author jhZhang
 * @date 2018/5/14
 */
public class Main15 {
public ListNode FindKthToTail(ListNode pListHead, int k) {
if (pListHead == null || k == 0) {
return null;
}
ListNode pAhead = pListHead;
ListNode pBehind = null;
// pAhead先走k步
for (int i = 0; i < k; i++) {
if (pAhead.nextNode != null) {
pAhead = pAhead.nextNode;
} else {
return null;
}
}
// 一起顺序顺序查找
pBehind = pListHead;
while (pAhead.nextNode != null) {
pAhead = pAhead.nextNode;
pBehind = pBehind.nextNode;
}
return pBehind;
}
}