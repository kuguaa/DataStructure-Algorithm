package linkedlist;

public class Josepfu {

	public static void main(String[] args) {
		//创建一个环形链表
		CircleSingleLinkedList circleList = new CircleSingleLinkedList();
		//初始化环形链表
		circleList.init(20);
		//显示链表
		circleList.show();
		//测试约瑟夫斯置换
		circleList.countBoy(1, 2, 20);
	}

}
//创建一个单向环形链表
class CircleSingleLinkedList{
	//创建一个 first 节点，当前没有编号
	Boy first;
	//根据节点数量构建单向环形链表
	public void init(int num) {
		//先校验数量
		if(num<1) {
			System.out.println("小孩数不能小于 1。");
			return;
		}
		Boy curBoy = null;
		for(int i=1;i<=num;i++) {
			//如果时第一个节点，构建环
			if(i==1) {
				curBoy = new Boy(i);
				curBoy.setNext(curBoy);
				first = curBoy;
			}else {
				Boy newBoy = new Boy(i);
				curBoy.setNext(newBoy);
				newBoy.setNext(first);
				curBoy = newBoy;
			}
		}
	}
	//遍历环形链表
	public void show() {
		//判断链表是否为空
		if(first == null) {
			System.out.println("链表为空，请初始化。");
			return;
		}
		//定义一个变量节点，遍历链表
		Boy curBoy = first;
		while(true) {
			System.out.printf("节点编号：%d\n", curBoy.getNo());
			if(curBoy.getNext() == first) {
				break;
			}else {
				curBoy = curBoy.getNext();
			}
		}
	}
	
	/**
	 * 约瑟夫斯置换
	 * @param startNo 从第几个小孩开始报数
	 * @param countNum 数几下
	 * @param nums 开始一共有几个小孩	
	 */
	public void countBoy(int startNo,int countNum,int nums) {
		//先校验数据有效性
		if(startNo<1 || countNum<1 || startNo>nums) {
			System.out.println("输入参数不合理");
			return;
		}
		//找到开始报数的节点：first，和前一个辅助节点 helper
		Boy helper = first;
		if(startNo ==1) {
			while(true) {
				if(helper.getNext()==first) {
					break;
				}
				helper = helper.getNext();
			}
		}else {
			for(int i=0;i<startNo-2;i++) {
				helper = helper.getNext();
			}
			first = helper.getNext();
		}
		//开始报数出圈
		while(true) {
			//如果最后只剩一个节点，游戏结束
			if(helper == first) {
				System.out.printf("最后幸运 boy 是：%d 号\n", first.getNo());
				return;
			}
			//循环报数出圈
			for(int i=1;i<countNum;i++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//first指向的节点即为要出圈的节点
			System.out.printf("出圈 boy 是：%d 号\n", first.getNo());
			helper.setNext(first.getNext());
			first = first.getNext();
		}
	}
}
//创建一个 Boy，表示一个节点
class Boy{
	private int no;//编号
	private Boy next;//指向下一个节点，默认为 null
	public Boy(int no) {
		super();
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}
}