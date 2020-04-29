package recursion;

public class Queen8 {
	
	//定义一个 max 表示皇后的数量
	static int max = 8;
	//定义一个数组，存放解法，如 {0,4,7,5,2,6,1,3}表示：第一个皇后放在第一行第一列，第二个皇后放在二行第5列...
	//arr[i]=val,表示第 i+1 个皇后放在第 i+1 行的第 val+1 列
	static int[] array = new int[max];
	static int count = 0; //统计解法个数
	static int judgeCount = 0; //统计解法个数

	public static void main(String[] args) {
		check(0);
		System.out.printf("一共有%d中解法\n",count);
		System.out.printf("一共判断了%d次\n",judgeCount);
	}
	
	//放置第 n 个皇后（第一个皇后用 0 表示）
	private static void check(int n) {
		if(n == max) { //表示以经放好所有皇后
			print(); //输出本次结果
			return;
		}
		
		//依次从第 n+1 行的第 1 列开始放入皇后，并判断是否冲突
		for(int i=0;i<max;i++) {
			array[n] = i;
			//判断是否冲突
			if(judge(n)) {//不冲突
				check(n+1); //继续放第 n+1 行
			}
			//冲突则循环进入下一次尝试，即将改行冲突的皇后又移。
		}
	}

	//查看放置的第 n 个皇后，是否和前 n-1 个皇后冲突（第一个皇后用 0 表示）
	private static boolean judge(int n) {
		judgeCount++;
		for(int i=0;i<n;i++) {
			//array[n]==array[i] 表示在同一列
			//Math.abs(n-i)==Math.abs(array[n]-array[i]) 表示在同一45°斜线
			if(array[n]==array[i] || Math.abs(n-i)==Math.abs(array[n]-array[i])) {
				return false; //表示冲突
			}
		}
		return true; //表示不冲突
	}
	//将皇后拜访的位置输出
	private static void print() {
		count++;
		for(int i=0;i<array.length;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
}
