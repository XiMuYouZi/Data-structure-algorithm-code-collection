package com.mj;

import com.mj.circle.DoubleCircleLinkedList;
import com.mj.circle.SingleCircleLinkedList;
import com.mj.single.SingleLinkedList;
import com.mj.single.SingleLinkedList.Node;

public class 链表笔试题 {

	static String toString(Node<String> header) {
		StringBuilder string = new StringBuilder();
        Integer i =0;
		Node<String> node = header;
		while (node.next != null) {
			if (i != 0) {
				string.append("-->");
			}
			string.append(node.element);
			node = node.next;
            i++;
            if (i > 20) break;
		}
		return string.toString();
	}

	static void testReverseLink() {
		SingleLinkedList<String> list = new SingleLinkedList<>();
		list.add("11");
		list.add("22");
		list.add("33");
		list.add("44");
		Node<String> header1 = list.node(0);
		System.out.println("递归反转之前的链表："+toString(header1));
		Node<String> header = recursionReverseLink(list.first);
		System.out.println("递归反转之后的链表："+toString(header));
		
        SingleLinkedList<String> list2 = new SingleLinkedList<>();
		list2.add("11");
		list2.add("22");
		list2.add("33");
		list2.add("44");
        Node<String> header2 = iterativeReverseLink(list2.first);
		System.out.println("迭代反转之后的链表："+toString(header2));

	}


	//递归反转单链表
	static Node<String> recursionReverseLink(Node<String> node) {
		Node<String> head = node;
		if (head == null || head.next == null)
			return head;
		Node<String> last = recursionReverseLink(head.next);
		head.next.next = head;
		head.next = null;
		return last;
	}

	//迭代反转链表
	static Node<String> iterativeReverseLink(Node<String> head) {
		Node<String> prev = null;
		Node<String> curr = head;
		while (curr != null) {
			Node<String> next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;

	}

    static void testCycleLink() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
		list.add("11");
        list.add("22");
		list.add("33");
        list.add("44");
		list.add("55");

        list.node(4).next = list.node(1);
        boolean cycle =  hasCycle(list.first);
        System.out.println(cycle);
    
    }


//快慢指针：慢指针后移每次一步，快指针每次后移两部，相遇就是有环
    static boolean hasCycle(Node<String> head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node<String> slow = head;
        Node<String> fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


	public static void main(String[] args) {
		// testReverseLink();
        testCycleLink();
		
	}

}
