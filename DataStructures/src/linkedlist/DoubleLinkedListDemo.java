package linkedlist;

public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		//创建双向链表
		DoubleLinkedList list = new DoubleLinkedList();
		//创建节点
		HeroNode2 hero1 = new HeroNode2(1,"松江","及时雨");
		HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
		HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
		HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");
		//添加前打印
		System.out.println("初始双向链表：");
		list.list();
		
//		//添加节点
//		list.add(hero4);
//		list.add(hero3);
//		list.add(hero2);
//		list.add(hero1);
//		//无序添加后打印
//		System.out.println("添加节点后双向链表：");
//		list.list();
		
		//添加节点
		list.addByOrder(hero4);
		list.addByOrder(hero3);
		list.addByOrder(hero2);
		list.addByOrder(hero1);
		// 无序添加后打印
		System.out.println("添加节点后双向链表：");
		list.list();
		
		//修改节点
		HeroNode2 update = new HeroNode2(2, "小卢", "玉麒麟~~");
		list.update(update);
		System.out.println("修改节点后双向链表：");
		list.list();

		// 删除节点
		list.remove(hero4);
		System.out.println("刪除节点后双向链表：");
		list.list();
	}

}

//创建一个双向链表类
class DoubleLinkedList{
	//先初始化头节点,私有,不动，不存放数据
	private HeroNode2 head = new HeroNode2(0,"","");
	
	public HeroNode2 getHead() {
		return head;
	}
	
	// 显示链表：遍历
	public void list() {

		// 判断链表未空
		if (head.next == null) {
			System.out.println("链表为空。");
		}
		// 头节点不能动，定义一个辅助节点用于遍历链表
		HeroNode2 p = head.next;
		while (p != null) {
			System.out.println(p);
			p = p.next;
		}
	}
	
	//添加节点到单向链表，不考虑顺序
	// 1. 找到当前链表最后节点
	// 2. 将最后节点 next 指向新节点，新节点 pre 指向原最后节点
	public void add(HeroNode2 node) {
		// 头节点不能动，定义一个辅助节点用于遍历链表
		HeroNode2 p = head;
		while (p.next != null) {
			p = p.next;
		}
		p.next = node;
		node.pre = p;
	}
	
	// 按照排序添加节点到链表
	public void addByOrder(HeroNode2 node) {
		// 头节点不能动，定义一个辅助节点用于遍历链表
		HeroNode2 p = head;
		while (p.next != null && p.next.no < node.no) {
			p = p.next;
		}
		// 链表为空，或待添加节点排序最大
		if (p.next == null) {
			p.next = node;
			node.pre = p;
			return;
		}
		// 如果节点重复
		if (p.next.no == node.no) {
			System.out.println("英雄已存在，不可重复添加。");
			return;
		}
		// 插入节点
		node.next = p.next;
		p.next.pre = node;
		p.next = node;
		node.pre = p;
	}
	
	//修改节点信息，根据节点 no 编号修改，及 no 不可修改
	public void update(HeroNode2 node) {
		// 找到要修改的节点前一个位置(也可以找到要修改的节点位置）
		// 头节点不能动，定义一个辅助节点用于遍历链表
		HeroNode2 p = head;
		while (true) {
			if (p.next == null) {
				// 表示没有该编号的节点
				System.out.println("链表中不存在该编号的英雄。");
				break;
			} else if (p.next.no == node.no) {
				// 找到了,修改
				p.next.name = node.name;
				p.next.nickname = node.nickname;
				System.out.println("修改成功，英雄信息已更新。");
				break;
			}
			p = p.next;
		}
	}
	
	//删除节点
	public void remove(HeroNode2 node) {
		HeroNode2 p = head.next;
		boolean flag = false; // 表示找到了待删除节点前一个节点
		while (true) {
			if (p== null) {
				// 表示没有该编号的节点
				break;
			} else if (p.no == node.no) {
				// 找到了
				flag = true;
				break;
			}
			p = p.next;
		}
		if (flag) {
			// 表示找到了，删除
			p.pre.next = p.next;// 被删除的节点会被垃圾回收机制回收
			//如果要刪除的节点是最后一個節點就不需要執行下面一句話
			if(p.next!=null) {
				p.next.pre = p.pre;
			}
			System.out.printf("删除英雄%s成功，英雄列表已更新。\n", node);
		} else {
			// 表示没有该编号的节点
			System.out.println("链表中不存在该编号的英雄。");
		}
	}
}

//定义HeroNode2，每个HeroNode都是一个节点，表示一个水浒传里的英雄任务
class HeroNode2{
	public int no; //排行榜编号
	public String name; //英雄名称
	public String nickname; //英雄别名
	public HeroNode2 next; //指向下一个节点，默认为 null
	public HeroNode2 pre; //指向前一个节点，默认为 null
	//构造器
	public HeroNode2(int no,String name,String nickName) {
		this.no = no;
		this.name = name;
		this.nickname = nickName;
	}
	//显示节点
	@Override
	public String toString() {
		return "HeroNode2 [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
}