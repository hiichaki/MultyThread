package util;

import model.Matrix;
import model.MultiplierThread;

public class MatrixSolver {

	public static Matrix multiplyMatrixOneThread(Matrix A, Matrix B) {
		Matrix result = new Matrix("resultOneThread");
		result.setRows(A.getRows());
		result.setCols(B.getCols());
		int[][] matrix = new int[result.getRows()][result.getCols()];

		for (int row = 0; row < result.getRows(); ++row) {
			for (int col = 0; col < result.getCols(); ++col) {
				int sum = 0;
				for (int i = 0; i < B.getRows(); ++i)
					sum += A.getMatrix()[row][i] * B.getMatrix()[i][col];
				matrix[row][col] = sum;
			}
		}

		result.setMatrix(matrix);
		return result;
	}

	public static Matrix multiplyMatrixManyThreads(Matrix A, Matrix B, int threadNumber) {
		assert threadNumber > 0;

		Matrix result = new Matrix("resultManyThreads");
		result.setRows(A.getRows());
		result.setCols(B.getCols());
		int[][] matrix = new int[result.getRows()][result.getCols()];

		final int cellsForThread = (result.getRows() * result.getCols()) / threadNumber;
		int firstIndex = 0;
		final MultiplierThread[] multiplierThreads = new MultiplierThread[threadNumber];

		for (int threadIndex = threadNumber - 1; threadIndex >= 0; --threadIndex) {
			int lastIndex = firstIndex + cellsForThread;
			if (threadIndex == 0) {

				lastIndex = result.getRows() * result.getCols();
			}
			multiplierThreads[threadIndex] = new MultiplierThread(A.getMatrix(), B.getMatrix(), matrix, firstIndex, lastIndex);
			multiplierThreads[threadIndex].start();
			firstIndex = lastIndex;
		}

		try {
			for (final MultiplierThread multiplierThread : multiplierThreads)
				multiplierThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result.setMatrix(matrix);
		return result;
	}

}
