package stack;

import java.util.Scanner;

public class ArrayStackDemo {

	public static void main(String[] args) {
		//测试栈
		ArrayStack stack = new ArrayStack(3);
		String key = ""; //接收用户输入
		Scanner scan = new Scanner(System.in);
		boolean flag=true;
		while(flag) {
			System.out.println("s(show):显示栈");
			System.out.println("push(push):添加数据到栈");
			System.out.println("pop(pop):从栈取出一个数据");
			System.out.println("e(exit):退出程序");
			key = scan.next();
			switch(key) {
			case "s":
				stack.show();
				break;
			case "push":
				System.out.println("请输入一个数");
				try {
					int value = scan.nextInt();
					stack.push(value);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "pop":
				try {
					int res = stack.pop();
					System.out.printf("出栈的数据是：%d\n", res);
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "e":
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

//定义一个 ArrayStack 表示栈
class ArrayStack{
	private int maxSize; //栈的大小
	private int[] stack; //数组模拟栈，数据存入数组中
	private int top = -1; //栈顶，初始化为 -1 没有数据
	
    //构造器
	public ArrayStack(int maxSize){
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}
	
	//栈满
	public Boolean isFull() {
		return top == maxSize-1;
	}
	
	//栈空
	public Boolean isEmpty() {
		return top == -1;
	}
	
	//入栈
	public void push(int value) {
		//先判断是否沾满
		if(isFull()) {
			throw new RuntimeException("栈满，不能添加数据~");
		}
		top++;
		stack[top]=value;
	}
	
	//出栈
	public int pop() {
		//先判断是否栈空
		if(isEmpty()) {
			throw new RuntimeException("栈空，没有数据取出~");
		}
		int value = stack[top];
		top--;
		return value;
	}
	
	//遍历栈，从栈顶开始遍历
	public void show() {
		//先判断是否栈空
		if (isEmpty()) {
			System.out.println("栈空，没有数据~");
			return;
		}
		for(int i = top;i>=0;i--) {
			System.out.printf("stack[%d]:%d\n", i,stack[i]);
		}
	}
}