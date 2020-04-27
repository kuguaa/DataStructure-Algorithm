package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

	public static void main(String[] args) {
		//测试环形队列
		CircleArrayQueue queue = new CircleArrayQueue(4); //说明设置4，其队列对大有效数据数量为 3
		char key = ' '; //接收用户输入
		Scanner scan = new Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("s(show):显示队列");
			System.out.println("a(add):添加数据到队列");
			System.out.println("r(remove):从队列取出一个数据");
			System.out.println("g(getHead):显示队列的第一个数据");
			System.out.println("e(exit):退出程序");
			key = scan.next().charAt(0);
			switch(key) {
			case 's':
				queue.show();
				break;
			case 'a':
				System.out.println("请输入一个数");
				int value = scan.nextInt();
				queue.add(value);
				break;
			case 'r':
				try {
					queue.remove();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'g':
				try {
					System.out.printf("队列的第一个数据是：%d\n",queue.getHead());
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'e':
				flag=false;
				System.out.printf("结束测试\n");
				scan.close();
				break;
			default:
				break;					
			}
		}
	}

}

//使用数组模拟队列，编写一个环形队列类
class CircleArrayQueue{
	private int maxSize; //表示数组的最大容量
	private int front; //队列头，指向队列的第一个数据，默认值为0
	private int rear; //队列尾，指向队列最后一个数据的后一个位置，默认值为0
	private int[] arr; //该数组用于存放数据，模拟队列
	
	//创建队列的构造器
	public CircleArrayQueue(int maxSize) {
		super();
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}
	
	//判断队列是否满
	public boolean isFull() {
		return (rear+1)%maxSize==front; //预留一个空位置
	}
	
	//判断队列是否为空
	public boolean isEmpty() {
		return rear==front;
	}
	
	//添加数据到队列
	public void add(int data) {
		//先判断队列是否满
		if(isFull()) {
			System.out.println("队列已满，添加失败。");
		}else {
			arr[rear]=data;
			rear = (rear+1)%maxSize;
		}
	}
	
	//从队列取出一个数据
	public int remove() {
		//先判断队列是否为空
		if(isEmpty()) {
			//通过抛出异常
			throw new RuntimeException("队列为空。");
		}else {
			int data = arr[front];
			front = (front+1)%maxSize;
			return data;
		}
	}
	//获取队列第一个数据值
	public int getHead() {
		// 先判断队列是否为空
		if (isEmpty()) {
			//通过抛出异常
			throw new RuntimeException("队列为空。");
		} else {
			return arr[front];
		}
	}
	//输出队列
	public void show() {
		// 先判断队列是否为空
		if (isEmpty()) {
			System.out.println("队列为空。");
		} else {
			//遍历队列,从 front 开始，遍历多少个
			for(int i=front;i<front+size();i++) {
				System.out.printf("queue[%d]:%d\n",i%maxSize,arr[i%maxSize]);
			}
		}
	}
	//计算队列数据个数
	public int size() {
//		System.out.printf("队列头：%d\n",front);
//		System.out.printf("队列尾+1：%d\n",rear);
//		System.out.printf("队列个数为：%d\n",(rear+maxSize-front)%maxSize);
		return (rear+maxSize-front)%maxSize;
	}
}