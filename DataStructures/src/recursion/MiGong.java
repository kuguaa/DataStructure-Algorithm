package recursion;
/*
 * 迷宫例子
1 1 1 1 1 1 1 
1 0 0 0 0 0 1 
1 0 0 0 0 0 1 
1 1 1 0 0 0 1 
1 0 0 0 0 0 1 
1 0 0 0 0 0 1 
1 0 0 0 0 0 1 
1 1 1 1 1 1 1 
 */
public class MiGong {

	public static void main(String[] args) {
		//定义一个迷宫地图数组
		int[][] map = new int[8][7];
		//初始化迷宫数组
		//列设置两堵墙
		for(int i=0;i<8;i++) {
			map[i][0]=1;
			map[i][6]=1;
		}
		//行设置两堵墙
		for(int j=0;j<7;j++) {
			map[0][j]=1;
			map[7][j]=1;
		}
		//设置障碍
		map[3][1]=1;
		map[3][2]=1;
//		map[1][2]=1;
//		map[2][2]=1;
		
		//打印迷宫
		for(int i=0;i<8;i++) {
			for(int j=0;j<7;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
		Boolean flag = setWay(map,1,1);
		
		if(flag) {
			//找到了路径
			System.out.println("找到路径，显示为 2：");
			//打印迷宫和路径
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 7; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}else {
			System.out.println("这个迷宫无法通往出口，活动范围为3：");
			//打印迷宫和尝试过的路径
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 7; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

	/**
	 * 递归实现迷宫路径
	 * 定义规则：
	 * 1. 数字1表示墙或障碍物，不可走；数字 0 表示没有走过的路；数字2表示走过的通路；数字3表示走过的死路
	 * 2. 按照 下、右、上、左 的方向依次尝试
	 * @param map 迷宫的引用
	 * @param i 当前位置
	 * @param j 当前位置
	 * @return 是否可以走通
	 */
	public static boolean setWay(int[][] map,int i,int j) {
		//递归结束标识
		if(map[6][5]==2) {
			return true; //表示已经走到出口，可以结束递归
		}else {
			if(map[i][j]==0) {
				//假设当前是通路
				map[i][j] = 2;
				if(setWay(map,i+1,j)) {//先尝试往下走
					return true; 
				}else if(setWay(map,i,j+1)){//往下走不通，尝试往右走
					return true; 
				}else if(setWay(map,i-1,j)){//往下、右都走不通，尝试往上走
					return true; 
				}else if(setWay(map,i,j-1)){//往下、右、上都走不通，尝试往左走
					return true; 
				}else {
					//这个点不能找到出口，标记为死路
					map[i][j] = 3; //回溯
					return false;
				}
			}else {
				//如果是 1 2 3，表示不能走或是回头路
				return false;
			}
		}
	}
	
	/*
	 * 思考如何求出最短路径？
	 * 思路：修改方向策略，穷举每种走法，用存储结果，比较路径长度，找到最短的一个。
	 */
}
