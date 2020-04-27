package sparsearray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*
 * 包含稀疏数组的文件存储读取
 */
public class SparesArray2 {

	public static void main(String[] args) throws IOException {
		//初始化原二维数组,0 表示没有棋子，1 表示黑子，2 表示白子
		int[][] cheesArr1 = new int[11][11];
		cheesArr1[1][2]=1;
		cheesArr1[2][3]=2;
		cheesArr1[3][4]=1;
		cheesArr1[4][5]=2;
		cheesArr1[5][6]=1;
		//输出原二维数组
		System.out.println("原始的二维数组：");
		for(int[] row:cheesArr1) {
			for(int data:row) {
				System.out.printf("\t%d",data);
			}
			System.out.println();
		}
		
		//二维数组转稀疏数组
		//1. 遍历二维数组得到非零数据的个数
		int sum = 0;
		for(int i=0;i<cheesArr1.length;i++) {
			for(int j=0;j<cheesArr1[0].length;j++) {
				if(cheesArr1[i][j]!=0) {
					sum++;
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
		for(int i=0;i<cheesArr1.length;i++) {
			for(int j=0;j<cheesArr1[0].length;j++) {
				if(cheesArr1[i][j]!=0) {
					count++;
					sparesArr[count][0]=i;
					sparesArr[count][1]=j;
					sparesArr[count][2]=cheesArr1[i][j];
				}
			}
		}
		//输出稀疏数组
		System.out.println("生成的稀疏数组：");
		for(int[] row:sparesArr) {
			for(int data:row) {
				System.out.printf("\t%d",data);
			}
			System.out.println();
		}
		//将稀疏数组存入文件
		String s = "";
		for(int[] row:sparesArr) {
			for(int data:row) {
				s+=data+" ";
			}
			s+="\r\n";
		}
		File f = new File("sparesArray.txt");
		FileStream.writeString(f, s);
				
		//从文件中读取稀疏数组
		String s2 = FileStream.read(f);
		String[] row_data = s2.split("\\r\\n");
		int size = Integer.parseInt(row_data[0].split(" ")[2])+1;
		int[][] sparesArr2 = new int[size][3];
		for(int i=0;i<size;i++) {
			String[] data = row_data[i].split(" ");
			sparesArr2[i][0] = Integer.parseInt(data[0]);
			sparesArr2[i][1] = Integer.parseInt(data[1]);
			sparesArr2[i][2] = Integer.parseInt(data[2]);
		}
		
		//稀疏数组恢复原始二维数组
		//1. 读取稀疏数组第一行，创建二维数组
		int[][] cheesArr2 = new int[sparesArr2[0][0]][sparesArr2[0][1]];
		//2. 读取稀疏数组剩余行，给二维数组赋值
		for(int i=1;i<=sparesArr2[0][2];i++) {
			cheesArr2[sparesArr2[i][0]][sparesArr2[i][1]] = sparesArr2[i][2];
		}
		
		//输出原二维数组
		System.out.println("恢复后的二维数组：");
		for (int[] row : cheesArr2) {
			for (int data : row) {
				System.out.printf("\t%d", data);
			}
			System.out.println();
		}
		
		//测试一下文件类
//		File f = new File("sparesArray.txt");
//		String[] s = new String[2];
//		s[0] = "11	11	2";
//		s[1] = "1	2	1";
//		FileStream.writeStringArray(f, s);
//		FileStream.read(f);
	}
	
	//文件类，用于实现稀疏数组的存盘和读取
	public static class FileStream{
		public static void writeString(File f,String s) throws IOException{
			// 构建FileOutputStream对象,文件不存在会自动新建
			FileOutputStream fop = new FileOutputStream(f);
			// 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
			OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
			// 写入到缓冲区
			writer.append(s);	        
			// 换行
	        writer.append("\r\n");
			
	        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
	        writer.close();
	        // 关闭输出流,释放系统资源
	        fop.close();    
	        System.out.println("文件写入完成！");
		}
		public static void writeStringArray(File f,String s[]) throws IOException{
			// 构建FileOutputStream对象,文件不存在会自动新建
			FileOutputStream fop = new FileOutputStream(f);
			// 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
			OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
			// 写入到缓冲区
			for(String row:s) {
				writer.append(row);	        
				// 换行
		        writer.append("\r\n");
			}
			
	        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
	        writer.close();
	        // 关闭输出流,释放系统资源
	        fop.close();
	        System.out.println("文件写入完成！");
		}
		public static String read(File f) throws IOException {
			// 构建FileInputStream对象
			FileInputStream fip = new FileInputStream(f);
			// 构建InputStreamReader对象,编码与写入相同
			InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
	        
	        StringBuffer sb = new StringBuffer();
	        while (reader.ready()) {
	        	// 转成char加到StringBuffer对象中
	            sb.append((char) reader.read());
	            
	        }
	        System.out.print("读取到的文件内容为：\n" + sb.toString());
	        // 关闭读取流
	        reader.close(); 
	        // 关闭输入流,释放系统资源
	        fip.close();
	        return sb.toString();
		}
	}
}


