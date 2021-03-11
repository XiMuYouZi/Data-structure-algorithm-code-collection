package com.mj;

//位运算优化空间复杂度
public class Queens2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Queens2().placeQueens(4);
	}
	
	/**
	 * 数组索引是行号，数组元素是列号
	 */
	int[] queens;
	/**
	 * 标记着某一列是否有皇后
	 */
	boolean[] cols;
	/**
	 * 标记着某一斜线上是否有皇后（左上角 -> 右下角）
	 */
	boolean[] leftTop;
	/**
	 * 标记着某一斜线上是否有皇后（右上角 -> 左下角）
	 */
	boolean[] rightTop;
	/**
	 * 一共有多少种摆法
	 */
	int ways;
	
	void placeQueens(int n) {
		if (n < 1) return;
		queens = new int[n];
		cols = new boolean[n];
		leftTop = new boolean[(n << 1) - 1];
		rightTop = new boolean[leftTop.length];
		place(0);
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	
	/**
	 * 从第row行开始摆放皇后
	 * @param row
	 */
	void place(int row) {
		if (row == cols.length) {
			ways++;
			show();
			return;
		}
		
		for (int col = 0; col < cols.length; col++) {
			if (cols[col]) continue;
			int ltIndex = row - col + cols.length - 1;
			if (leftTop[ltIndex]) continue;
			int rtIndex = row +col;
			if (rightTop[rtIndex]) continue;
			
			//在row行的第col列摆放皇后
			queens[row] = col;
			//设置该皇后所在的列，两条对角线都为true，即这些位置已经被占用，不能再摆放皇后了
			cols[col] = true;
			leftTop[ltIndex] = true;
			rightTop[rtIndex] = true;
			//在下一行继续摆放皇后
			place(row + 1);
			/* 能来到这里说明row+1行摆放皇后的时候失败了，也就是中了上面的三个continue中的一个条件,不然会一直递归调用place(row+1)而不会来到这里。
			 这个时候需要回溯到上一行row（本行是row+1）继续执行摆放皇后，以为之前摆放row行的皇后的时候设置皇后所在的列
			 和两条对角线被占用，现在要把皇后摆放到该row的下一列继续重新计算，所以这些信息需要还原为false释放占用，然后重新计算 
			 */
			cols[col] = false;
			leftTop[ltIndex] = false;
			rightTop[rtIndex] = false;
		}
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (queens[row] == col) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}
}
