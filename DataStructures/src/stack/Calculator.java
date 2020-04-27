package stack;

public class Calculator {

	public static void main(String[] args) {
		//实现算数表达式（只包含正整数和+-*/运算符）的计算功能
		//定义需要用到的类和变量
		ArrayStack2 numStack = new ArrayStack2(6);
		ArrayStack2 operStack = new ArrayStack2(5);
		String expression = "5*3+6-8/2";//17
		int index = 0,num1,num2,length = expression.length();
		char c;
		String keepNum = "";
		while(index<length) {
			c = expression.charAt(index++);		//取出当前 index位置的字符后，索引后移	
			if(operStack.isOper(c)) {
				//如果是符号就分如下情况
				//如果符号栈为空就直接入符号栈
				if(operStack.isEmpty()) {
					operStack.push(c);
				}else {
					//如果符号栈不为空则对符号栈顶符号和索引符号进行对比
					char oper = operStack.peek();
					if(operStack.priority(oper)>operStack.priority(c)) {
						//栈顶操作符的优先级高于索引：数栈 pop 两个数，符号栈 pop 一个符号，进行运算，并将运算结果入数栈，索引符号入符号栈
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						int res = operStack.calculate(num1, num2, oper);
						numStack.push((char)(res+48));
						operStack.push(c);
					}else {
						//索引符号优先级高于或等于栈顶：索引符号入栈
						operStack.push(c);
					}
				}
			}else {
				//如果当前 index 对应数字，需考虑是否有多位数
				keepNum += c;
				//由于前面取字符后已经后移索引了，所以直接判断当前索引是否超出字符串边界
				if(index == length) {
					//c 是表达式最后一位，keepNum转换成char类型入栈
					numStack.push((char)(Integer.parseInt(keepNum)+48));
				}else {
					//不是最后一位，则判断当前索引位置，如果是字符则keepNum转换成char类型入栈,不是就继续循环
					if(operStack.isOper(expression.charAt(index))) {
						numStack.push((char)(Integer.parseInt(keepNum)+48));
						//keepNum 清空
						keepNum = "";
					}
				}
			}
		}
		//当表达式遍历完毕，就顺序从数栈、符号栈 pop 出对应的数据和符号，并运算，结果入栈
		while(!operStack.isEmpty()) {
			num1 = numStack.pop();
			num2 = numStack.pop();
			c = operStack.pop();
			int res = operStack.calculate(num1, num2, c);
			numStack.push((char)(res+48));
		}
		//最后数栈只有一个数据，就是表达式的结果
		int res = numStack.pop()-48;
		System.out.printf("表达试 %s 的运算结果为：%d \n",expression,res);
	}

}

class ArrayStack2{
	private int maxSize; //栈的大小
	private char[] stack; //数组模拟栈，数据存入数组中
	private int top = -1; //栈顶，初始化为 -1 没有数据
	
    //构造器
	public ArrayStack2(int maxSize){
		this.maxSize = maxSize;
		stack = new char[maxSize];
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
	public void push(char value) {
		//先判断是否沾满
		if(isFull()) {
			throw new RuntimeException("栈满，不能添加数据~");
		}
		top++;
		stack[top]=value;
	}
	
	//出栈
	public char pop() {
		//先判断是否栈空
		if(isEmpty()) {
			throw new RuntimeException("栈空，没有数据取出~");
		}
		char value = stack[top];
		top--;
		return value;
	}
	
	//查看栈顶数据
	public char peek() {
		return stack[top];
	}
	
	//判断是否为操作符,规定只有四个基本运算符
	public Boolean isOper(char c) {
		if(c == '*' || c == '/' || c == '+' || c == '-') {
			return true;
		}
		return false;
	}
	
	//定义算数运算优先级
	public int priority(char c) {
		if(c == '*' || c == '/') {
			return 2;
		}else if(c == '+' || c == '-') {
			return 1;
		}else {
			return -1; //表示符号不合法
		}
	}
	
	//计算
	public int calculate(int num1,int num2,char oper) {
		int value = 0;
		switch (oper) {
		case '+':
			value = (num2-48)+(num1-48); //char 类型 ASCII 值的 48 对应 int 类型数字 0
			break;
		case '-':
			value = num2-num1;
			break;
		case '*':
			value = (num2-48)*(num1-48);
			break;
		case '/':
			value = (num2-48)/(num1-48);
			break;
		default:
			break;
		}
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