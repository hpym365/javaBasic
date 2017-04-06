package hp.basic.algorithm;

public class PPTV {

	public static void main(String[] args) {
		int[][] arr = new int[5][5];
		int[][] res = new int[5][5];

		int[] temp = new int[] { 1, 2, 3, 4,5 };
		int[] temp1 = new int[] {  6, 7, 8 ,9,10};
		int[] temp2 = new int[] {  11, 12,13,14,15 };
		int[] temp3 = new int[] { 16,17,18,19,20 };
		int[] temp4 = new int[] { 21,22,23,24,25 };
		arr[0] = temp;
		arr[1] = temp1;
		arr[2] = temp2;
		arr[3] = temp3;
		arr[4] = temp4;

		for (int x = 0; x < arr.length; x++) {
			for (int y = 0; y < arr[x].length; y++) {

				
				int nx = x;
 				
 				if(x%2==0){
 					nx=x-1<0?arr[x].length-1:x-1;
 				}else{
 					nx=x+1>arr[x].length-1?0:x+1;
 				}
//				System.out.println("x:"+y+" y:"+nx+"   "+arr[x][y]);
				
				res[y][nx]=arr[x][y];

 				System.out.println("原来数据值:"+arr[x][y]+"位置 x:"+x+" y:"+y+"   ");
 				System.out.println("新来数据值:"+res[y][nx]+"位置x:"+y+" y:"+nx+"   ");
				System.out.print(arr[y][nx] + " ");
			}
			System.out.println("=================================");
			for (int i = 0; i < res.length; i++) {
				for (int j = 0; j < res[i].length; j++) {
					System.out.print(res[i][j] + " ");
				}
				System.out.println();
			}
		}

		System.out.println("=================================");

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("=================================");
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}

	}
}
// 10    02
// 00   03
