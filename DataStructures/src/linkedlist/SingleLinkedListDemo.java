package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		//测试
		//先创建节点
		HeroNode hero1 = new HeroNode(1,"松江","及时雨");
		HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
		HeroNode hero3 = new HeroNode(3,"吴用","智多星");
		HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
/*
		//创建单向链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		//按照排序添加节点
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.addByOrder(hero1);
//		singleLinkedList.addByOrder(hero1);
		
		//显示链表
		singleLinkedList.list();
		
		//修改节点
		HeroNode update = new HeroNode(2,"小卢","玉麒麟~~");
		singleLinkedList.update(update);
		
		//显示链表
		singleLinkedList.list();

		//删除节点
		singleLinkedList.remove(hero1);
		
		//获取单链表的有效节点个数
		System.out.printf("当前英雄列表中的英雄个数为：%d 个\n", getLength(singleLinkedList.getHead()));
		
		//获取倒数第K个节点
		HeroNode node = getLastIndex(singleLinkedList.getHead(),4);
		System.out.printf("当前英雄列表中的倒数第4英雄个数为：%s\n", node);
		
		//反转列表
		reverseLinkedList(singleLinkedList);
		
		//无序添加节点
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero2);
//		singleLinkedList.add(hero3);
//		singleLinkedList.add(hero4);
		
		//显示链表
		singleLinkedList.list();
		
		reversePrint(singleLinkedList.getHead());
*/	
		SingleLinkedList list1 = new SingleLinkedList();
		SingleLinkedList list2 = new SingleLinkedList();
		list1.add(hero1);
		list1.add(hero3);
		list2.add(hero2);
		list2.add(hero4);
		System.out.println("list1:");
		list1.list();
		System.out.println("list2:");
		list2.list();
		System.out.println("不改变原链表合并后:");
		mergeLinkedList(list1,list2).list();
		
//		System.out.println("改变原链表合并后:");
//		mergeLinkedListChangeToFirst(list1.getHead(),list2.getHead());
//		list1.list();
	}
	
	/**
	 * 合并两个有序单链表，合并之后以然有序(改变原链表)，合并后的结果为第一个链表
	 * 链表 1 和 2 不可以是相交节点，否则会死循环
	 * @param head1 链表1 的头节点
	 * @param head2 链表2 的头节点
	 */
	public static void mergeLinkedListChangeToFirst(HeroNode head1,HeroNode head2) {
		HeroNode p = head1;
		HeroNode q = head2;
		while(p.next!=null && q.next!=null) {
			if(p.next.no<=q.next.no) {
				p = p.next;
			}else {
				HeroNode temp = q.next.next;
				q.next.next = p.next;
				p.next = q.next;
				p = q.next;
				head2.next = temp;
			}
		}
		if(q.next!=null) {
			p.next = q.next;
		}	
	}
	
	/**
	 * 合并两个有序单链表，合并之后依然有序(不改变原链表)
	 * 如果链表 1 和 2 是相交节点，则会重复相交部分的节点
	 * @param list1 链表1
	 * @param list2 链表2
	 * @return 合并后的新链表
	 */
	public static SingleLinkedList mergeLinkedList(SingleLinkedList list1,SingleLinkedList list2) {
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		HeroNode cur = singleLinkedList.getHead();
		HeroNode p = list1.getHead().next;
		HeroNode q = list2.getHead().next;
		while(p!=null && q!=null) {
			if(p.no<=q.no) {
				HeroNode temp = new HeroNode(p.no,p.name,p.nickname);
				cur.next = temp;
				cur = cur.next;
				p = p.next;
			}else {
				HeroNode temp = new HeroNode(q.no,q.name,q.nickname);
				cur.next = temp;
				cur = cur.next;
				q = q.next;
			}
		}
		if(p!=null) {
			cur.next = p;
		}
		if(q!=null) {
			cur.next = q;
		}
		return singleLinkedList;
	}
	
	//将单链表从尾到头打印
	public static void reversePrint(HeroNode head) {
		if(head.next == null) {
			return;//空链表不能打印
		}
		//创建一个栈，遍历单链表将各节点入栈,利用栈先进后出的特性
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode p = head.next;
		while(p!=null) {
			stack.add(p);
			p = p.next;
		}
		while(stack.size()>0) {
			System.out.println(stack.pop());
		}
	}
	
	//将单链表反转
	public static SingleLinkedList reverseLinkedList(SingleLinkedList singleLinkedList) {
		HeroNode head = singleLinkedList.getHead();
		//如果链表为空或只有一个节点，无需反转
		if(head.next == null||head.next.next == null) {
			return singleLinkedList;
		}
		//思路：
		//1. 生成一个新的头节点
		//2. 顺序遍历原链表，依次将每个节点插入，新链表的头部
		//3. 将原链表头指向新链表第一个节点
		HeroNode temp = new HeroNode(0,"","");
		HeroNode p = head.next;
		while(p!=null) {
			head.next = p.next;
			p.next = temp.next;
			temp.next = p;
			p = head.next;
		}
		head.next = temp.next;
		return singleLinkedList;
	}
	
	/**
	 * 查找链表中倒数第 K 个节点
	 * @param head 链表的头节点
	 * @param index 倒数第几个
	 * @return 节点
	 */
	public static HeroNode getLastIndex(HeroNode head,int index) {
		//思路：1. 获取链表有效节点个数 size	2. 遍历(size-index)找到倒数第 index 个
		int size = getLength(head);
		if(size<index || index<=0) {
			return null;
//			//通过抛出异常
//			throw new RuntimeException("链表没有"+index+"个数据");
		}
		HeroNode p = head.next;
		for(int i =0 ; i<size-index ; i++) {
			p=p.next;
		}
		return p;
	}

	/**
	 * 获取单链表的有效节点个数（不包含头节点）
	 * @param head 链表的头节点
	 * @return 有效节点个数
	 */
	public static int getLength(HeroNode head) {
		if(head.next == null) {//链表为空
			return 0;
		}
		int length = 0;
		HeroNode p = head.next;
		while(p!=null) {
			length++;
			p = p.next;
		}
		return length;
	}

}
//定义点链表管理HeroNode
class SingleLinkedList{
	//先初始化头节点,私有,不动，不存放数据
	private HeroNode head = new HeroNode(0,"","");
	
	public HeroNode getHead() {
		return head;
	}

	//添加节点到单向链表，不考虑顺序
	//1. 找到当前链表最后节点
	//2. 将最后节点 next 指向新节点
	public void add(HeroNode node) {
		//头节点不能动，定义一个辅助节点用于遍历链表
		HeroNode p = head;
		while(p.next!=null) {
			p = p.next;
		}
		p.next = node;
	}
	
	//按照排序添加节点到链表
	public void addByOrder(HeroNode node) {
		//头节点不能动，定义一个辅助节点用于遍历链表
		HeroNode p = head;
		while (p.next != null && p.next.no < node.no) {
			p = p.next;
		}
		//链表为空，或待添加节点排序最大
		if(p.next == null) {
			p.next = node;
			return;
		}
		//如果节点重复
		if(p.next.no == node.no) {
			System.out.println("英雄已存在，不可重复添加。");
			return;
		}
		//插入节点
		node.next = p.next;
		p.next = node;
	}
	
	//修改节点信息，根据节点 no 编号修改，及 no 不可修改
	public void update(HeroNode node) {
		//找到要修改的节点前一个位置(也可以找到要修改的节点位置）
		//头节点不能动，定义一个辅助节点用于遍历链表
		HeroNode p = head;
		while(true) {
			if(p.next == null) {
				//表示没有该编号的节点
				System.out.println("链表中不存在该编号的英雄。");
				break;
			}else if(p.next.no == node.no) {
				//找到了,修改
				p.next.name = node.name;
				p.next.nickname = node.nickname;
				System.out.println("修改成功，英雄信息已更新。");
				break;
			}
			p = p.next;
		}
	}
	
	//删除节点
	public void remove(HeroNode node) {
		HeroNode p = head;
		boolean flag = false; //表示找到了待删除节点前一个节点
		while(true) {
			if(p.next == null) {
				//表示没有该编号的节点
				break;
			}else if(p.next.no == node.no) {
				//找到了
				flag = true;
				break;
			}
			p = p.next;
		}
		if(flag) {
			//表示找到了，删除
			p.next = p.next.next; //被删除的节点会被垃圾回收机制回收
			System.out.printf("删除英雄%s成功，英雄列表已更新。\n",node);
		}else {
			//表示没有该编号的节点
			System.out.println("链表中不存在该编号的英雄。");
		}
	}
	
	//显示链表：遍历
	public void list() {
		
		//判断链表未空
		if(head.next == null) {
			System.out.println("链表为空。");
		}
		// 头节点不能动，定义一个辅助节点用于遍历链表
		HeroNode p = head.next;
		while (p != null) {
			System.out.println(p);
			p = p.next;
		}
	}
	
}

//定义HeroNode，每个HeroNode都是一个节点，表示一个水浒传里的英雄任务
class HeroNode{
	public int no; //排行榜编号
	public String name; //英雄名称
	public String nickname; //英雄别名
	public HeroNode next; //指向下一个节点
	//构造器
	public HeroNode(int no,String name,String nickName) {
		this.no = no;
		this.name = name;
		this.nickname = nickName;
	}
	//显示节点
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
}