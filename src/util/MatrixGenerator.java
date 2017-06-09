package util;

import java.util.Random;

import model.Matrix;

public class MatrixGenerator {

	private static Random rand = new Random();

	public static Matrix getMatrix(int rows, int cols) {
		Matrix matrix = new Matrix();
		matrix.setRows(rows);
		matrix.setCols(cols);
		int[][] a = new int[rows][cols];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				a[i][j] = getRandom();
			}
		}
		matrix.setMatrix(a);
		return matrix;
	}

	private static int getRandom() {
		return rand.nextInt(1000);
	}

}
