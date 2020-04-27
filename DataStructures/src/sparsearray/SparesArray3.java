package sparsearray;

import java.util.ArrayList;
import java.util.List;

/*
 * 优化：
 * 原本遍历两次二维数组，一次获取有效数据个数，一次获取有效数据
 * 改成：利用 list列表在第一次遍历时存入数据信息
 */
public class SparesArray3 {
	public static void main(String[] args) {
		long start=System.currentTimeMillis(); //获取开始时间
		
		//初始化原二维数组,0 表示没有棋子，1 表示黑子，2 表示白子
		int[][] cheesArr1 = new int[11][11];
		cheesArr1[1][2]=1;
		cheesArr1[2][3]=2;
		//输出原二维数组
		System.out.println("原始的二维数组：");
		for(int[] row:cheesArr1) {
			for(int data:row) {
				System.out.printf("\t%d",data);
			}
			System.out.println();
		}
		
		//二维数组转稀疏数组
		//1. 遍历二维数组得到非零数据的个数，及数据信息
		int sum = 0;
		List<CheesData> list = new ArrayList<CheesData>();
		for(int i=0;i<cheesArr1.length;i++) {
			for(int j=0;j<cheesArr1[0].length;j++) {
				if(cheesArr1[i][j]!=0) {
					sum++;
					CheesData d = new CheesData(i,j,cheesArr1[i][j]);
					list.add(d);
				}
			}
		}
		//2. 创建对应的稀疏数组
		int[][] sparesArr = new int[sum+1][3];
		sparesArr[0][0] = cheesArr1.length;
		sparesArr[0][1] = cheesArr1[0].length;
		sparesArr[0][2] = sum;
		//3. 将二维数组有效数据存入稀疏数组
		int count=0;
		while(!list.isEmpty()) {
			CheesData d = list.remove(0);
			count++;
			sparesArr[count][0]=d.row;
			sparesArr[count][1]=d.col;
			sparesArr[count][2]=d.data;
		}
		
		//输出稀疏数组
		System.out.println("生成的稀疏数组：");
		for(int[] row:sparesArr) {
			for(int data:row) {
				System.out.printf("\t%d",data);
			}
			System.out.println();
		}
		
		//稀疏数组恢复原始二维数组
		//1. 读取稀疏数组第一行，创建二维数组
		int[][] cheesArr2 = new int[sparesArr[0][0]][sparesArr[0][1]];
		//2. 读取稀疏数组剩余行，给二维数组赋值
		for(int i=1;i<=sparesArr[0][2];i++) {
			cheesArr2[sparesArr[i][0]][sparesArr[i][1]] = sparesArr[i][2];
		}
		
		//输出原二维数组
		System.out.println("恢复后的二维数组：");
		for (int[] row : cheesArr2) {
			for (int data : row) {
				System.out.printf("\t%d", data);
			}
			System.out.println();
		}
		
		long end=System.currentTimeMillis(); //获取结束时间
		 
		System.out.println("程序运行时间： "+(end-start)+"ms");
	}
	
	public static class CheesData{
		public int row;
		public int col;
		public int data;
		public CheesData(int row, int col, int data) {
			super();
			this.row = row;
			this.col = col;
			this.data = data;
		}
	}
}
