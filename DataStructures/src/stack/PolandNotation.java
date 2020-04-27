package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class PolandNotation {

	public static void main(String[] args) {
		
		//中缀表达式转后缀表达式功能，思路：
		//例子："1+((2+3)*4)-5" => 1 2 3 + 4 * + 5 -
		//1. 定义一个中缀表达式字符串，并将其转换成 ArrayList 方便遍历
		//例子："1+((2+3)*4)-5" =>[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
		String expresssion = "1+((2+3)*4)-5"; 
		List<String> list = toInfixExpressionList(expresssion);
		System.out.println("中缀表达式表达式InfixList:"+list);
		//2. 将 ArrayList 传递给一个方法，该方法遍历 ArrayList 配合栈实现中缀表达式转后缀表达式
		List<String> list2 = parseSuffixExpressionList(list);
		System.out.println("后缀表达式表达式SuffixList:"+list2);
		
		int res = calculate(list2);
		System.out.println("结果为:"+res); //16
		
		/*
		//先定义一个逆波兰表达式
		//(30+4)*5-6 => 30 4 + 5 * 6 -
		//说明为了方便用空格隔开
		String suffixExpression = "30 4 + 5 * 6 -"; //164
		//思路
		//1. 先将"3 4 + 5 * 6 -" 存入 ArrayList中
		List<String> list = getListString(suffixExpression);
		System.out.println("逆波兰表达式:"+list);
		//2. 将 ArrayList 传递给一个方法，该方法遍历 ArrayList 配合栈完成计算
		int res = calculate(list);
		System.out.println("结果为:"+res);
		*/
	}
	
	//将中缀表达式 ArrayList转换成后缀表达式ArrayList
	public static List<String> parseSuffixExpressionList(List<String> list){
		//定义一个栈，存放运算符
		Stack<String> stack = new Stack<String>();
		//定义一个ArrayList存放后缀表达式结果
		List<String> SuffixExpressionList = new ArrayList<String>();
		
		//遍历 list
		/*
		 * 1. 如果是数字，添加到 SuffixExpressionList
		 * 2. 如果是"(",入栈
		 * 3. 如果是")",依次弹出栈顶元素，并添加到 SuffixExpressionList 中，直到弹出"("结束
		 * 4. 如果栈为空或栈顶为"("，直接将运算符入栈，否则比较当前运算符与栈顶运算符的优先级
		 * 		4.1 如果当前运算符优先级高于栈顶，入栈
		 * 		4.2 否则将栈顶元素弹出，添加到 SuffixExpressionList，比较下一个栈顶元素
		 * 5. 重复 1~4 直到 list 遍历完成
		 * 6. 将栈中剩余的运算符弹出，添加到 SuffixExpressionList
		 * 7. SuffixExpressionList 即为后缀表达式结果
		 */
		for(String item:list) {
			if(item.matches("\\d+")) {//匹配多位数字
				//是数字，添加到 SuffixExpressionList
				SuffixExpressionList.add(item);
			}else if(item.equals("(") || stack.isEmpty()) {
				//是"("或栈为空,入栈
				stack.push(item);
			}else if(item.equals(")")) {
				//是")",依次弹出栈顶元素，并添加到 SuffixExpressionList 中，直到弹出"("结束
				while(!stack.peek().contentEquals("(") ){
					SuffixExpressionList.add(stack.pop());
				}
				stack.pop();//消除最后的"("
			}else {
				// 比较当前运算符与栈顶运算符的优先级
				// 如果栈为空，或栈顶为"("，或当前运算符优先级高于栈顶，入栈,否则将栈顶元素弹出，添加到 SuffixExpressionList，比较下一个栈顶元素
				while (stack.size() != 0 && !stack.peek().equals("(") && Operation.getPriority(stack.peek()) >= Operation.getPriority(item)) {
					SuffixExpressionList.add(stack.pop());
				}
				stack.push(item);
			}
		}
		//将栈中剩余的运算符弹出，添加到 SuffixExpressionList
		while(!stack.empty()) {
			SuffixExpressionList.add(stack.pop());
		}
		return SuffixExpressionList;
	}
	
	//将中缀表达式转换成 ArrayList
	public static List<String> toInfixExpressionList(String s){
		List<String> list = new ArrayList<String>();
		int index=0,length = s.length();
		String str; //做多位数的拼接
		char c; //遍历到的字符
		while(index<length) {
			c = s.charAt(index);
			if(c<48 ||c>57) {
				//表示不是数字，是运算符
				list.add(""+c);
				index++;
			}else {
				//是数字，需考虑多位拼接
				str= ""+c;
				index++;
				while(index<length && s.charAt(index)>=48 && s.charAt(index)<=57) {
					str += s.charAt(index);
					index++;
				}
				list.add(str);
			}
		}
		return list;
	}
	
	//将逆波兰表达式，存入 ArrayList中
	public static List<String> getListString(String suffixExpression){
		//因为逆波兰表达式每个数与运算符由空格隔开，使用split拆分
		String[] arr = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for(String item:arr) {
			list.add(item);
		}
		return list;
	}
	
	//完成对逆波兰表达式的运算，思路：
	/*
	 * [3, 4, +, 5, *, 6, -]
	 * 1. 遍历 ArrayList，如果是数字则压入栈中
	 * 2. 如果是运算符，则弹出两个数，进行运算，将值压入栈中
	 * 3. 重复 1.2. 的过程，直到 ArrayList 遍历完成
	 * 4. 返回栈中的最后一个数据，就是结果
	 */
	public static int calculate(List<String> list) {
		//创建栈
		Stack<String> stack = new Stack<String>();
		for(String item:list) {
			//这里使用正则表达式来匹配多位数
			if(item.matches("\\d+")) {
				stack.push(item);
			}else {
				int num1 = Integer.parseInt(stack.pop());
				int num2 = Integer.parseInt(stack.pop());
				int res = 0;
				if(item.equals("+")) {
					res = num2 + num1;
				}else if(item.equals("-")) {
					res = num2 - num1;
				}else if(item.equals("*")) {
					res = num2 * num1;
				}else if(item.equals("/")) {
					res = num2 / num1;
				}else {
					throw new RuntimeException("运算符有误。");
				}
				stack.push(""+res);
			}
		}
		return Integer.parseInt(stack.pop());
	}
}

//编写一个类 Operation 可以返回一个运算符的优先级
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	
	//写一个方法，返回对应的优先级
	public static int getPriority(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("不存在该运算符");
			break;
		}
		return result;
	}
}
