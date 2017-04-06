package hp.basic.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListAndLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		removeList(new ArrayList());
		readList(new ArrayList());
		removeList(new LinkedList());
		readList(new LinkedList());
	}

	public static void addList(List list) {

		long time = System.currentTimeMillis();
//		System.out.println(list.getClass() + "开始增加");
		for (int i = 0; i < 300000; i++) {
			list.add(new String("abcd"));
		}
//		System.out.println("增加结束共执行了:" + (System.currentTimeMillis() - time));
	}

	public static void removeList(List list) {
		addList(list);
		long time = System.currentTimeMillis();
		System.out.println(list.getClass() + "开始remove");
		for (int i = 0; i < 300000; i++) {
			list.remove(0);
		}
		System.out.println("remove结束共执行了:" + (System.currentTimeMillis() - time));
	}

	public static void readList(List list) {
		addList(list);
		long time = System.currentTimeMillis();
		System.out.println(list.getClass() + "开始readList");
		for (int i = 0; i < 300000; i++) {
			String str = (String) list.get(i);
		}
		System.out.println("readList结束共执行了:" + (System.currentTimeMillis() - time));
	}
}
