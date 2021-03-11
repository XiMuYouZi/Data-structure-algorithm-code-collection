package com.mj;

public class Queens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Queens().placeQueens(4);
	}
	
	/**
	 * 数组索引是行号，数组元素是列号，存放的是每一行摆放的皇后所在的列
	 */
	int[] cols;
	/**
	 * 一共有多少种摆法
	 */
	int ways;
	
	void placeQueens(int n) {
		if (n < 1) return;
		cols = new int[n];
		place(0);
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	
	/**
	 * 从第row行开始摆放皇后
	 * @param row
	 */
	void place(int row) {
		if (row == cols.length) {//表示已经找到了最后一行皇后的摆放位置
			ways++;
			show();
			return;
		}
		
		//从第0行0列开始摆放
		for (int col = 0; col < cols.length; col++) {
			if (isValid(row, col)) {
				// 在第row行第col列摆放皇后
				cols[row] = col;
				//切换到下一行继续摆放，如果下一行所有的col都无法摆放，也就是isValid()判断全部失败，
				//也就不会进入for循环，假设row=4，clo=0可以摆放，但是row+1=5的全部列都不能摆放，那么place(5)就会跳出for循环执行到place函数结束，
				//然后 place(5)弹出堆栈，执行place(4)，此时尝试位置col+1=1，也就是尝试row=4，col=1的位置摆放皇后，这就是回溯。
				place(row + 1);
			}
		}
	}
	
	/**
	 * 判断第row行第col列是否可以摆放皇后
	 */
	boolean isValid(int row, int col) {
		for (int i = 0; i < row; i++) {
			// 第col列已经有皇后
			if (cols[i] == col) {
				System.out.println("[" + row + "][" + col + "]=false");
				return false;
			}
			// 第i行的皇后跟第row行第col列格子处在同一斜线上时不能摆放皇后，
			//两个格子处在同一条斜对角线满足条件：他们的行的差值等于他们列的差值的绝对值
			if (row - i == Math.abs(col - cols[i])) {
				System.out.println("[" + row + "][" + col + "]=false");
				return false;
			}
		}
		System.out.println("[" + row + "][" + col + "]=true");
		return true;
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (cols[row] == col) {
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
