package model;

public class MultiplierThread extends Thread {

	private final int[][] A;
	private final int[][] B;
	private final int[][] result;
	private final int firstIndex;
	private final int lastIndex;
	private final int sumLength;

	public MultiplierThread(final int[][] A, final int[][] B, final int[][] result, final int firstIndex, final int lastIndex) {
		this.A = A;
		this.B = B;
		this.result = result;
		this.firstIndex = firstIndex;
		this.lastIndex = lastIndex;

		sumLength = B.length;
	}

	private void calcValue(final int row, final int col) {
		int sum = 0;
		for (int i = 0; i < sumLength; ++i)
			sum += A[row][i] * B[i][col];
		result[row][col] = sum;
	}

	@Override
	public void run() {
		final int colCount = B[0].length;
		for (int index = firstIndex; index < lastIndex; ++index)
			calcValue(index / colCount, index % colCount);
	}
}