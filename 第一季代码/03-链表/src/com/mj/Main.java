package com.mj;

import com.mj.circle.CircleLinkedList;
import com.mj.circle.SingleCircleLinkedList;
import com.mj.single.SingleLinkedList;
import com.mj.single.SingleLinkedList.Node;

public class Main {

	static void testList(List<Integer> list) {
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);

		list.add(0, 55); // [55, 11, 22, 33, 44]
		list.add(2, 66); // [55, 11, 66, 22, 33, 44]
		list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]

		list.remove(0); // [11, 66, 22, 33, 44, 77]
		list.remove(2); // [11, 66, 33, 44, 77]
		list.remove(list.size() - 1); // [11, 66, 33, 44]

		Asserts.test(list.indexOf(44) == 3);
		Asserts.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
		Asserts.test(list.contains(33));
		Asserts.test(list.get(0) == 11);
		Asserts.test(list.get(1) == 66);
		Asserts.test(list.get(list.size() - 1) == 44);

		System.out.println(list);
	}

	static String toString(Node<String> header,Integer size) {
		StringBuilder string = new StringBuilder();
		Node<String> node = header;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append("-->");
			}
			string.append(node.element);
			node = node.next;
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
		System.out.println("递归反转之前的链表："+toString(header1, list.size));
		Node<String> header = recursionReverseLink(list.node(0));
		System.out.println("递归反转之后的链表："+toString(header, list.size));
		
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

	static void josephus() {
		CircleLinkedList<Integer> list = new CircleLinkedList<>();
		for (int i = 1; i <= 8; i++) {
			list.add(i);
		}

		// 指向头结点（指向1）
		list.reset();

		while (!list.isEmpty()) {
			list.next();
			list.next();
			System.out.println(list.remove());
		}
	}

	public static void main(String[] args) {
		testReverseLink();
		// josephus();

		// testList(new ArrayList<>());
		// testList(new LinkedList<>());
		// testList(new SingleLinkedList<>());

		// testList(new SingleCircleLinkedList<>());

		// testList(new CircleLinkedList<>());

		/*
		 * gc root对象 1> 被局部变量指向的对象
		 */
	}

}
