package main;

import model.Matrix;
import util.MatrixGenerator;
import util.MatrixSolver;

public class BestSolution {
	
	public static void compareMultiplying(int threadNumber) {
		Matrix A = MatrixGenerator.getMatrix(100, 100);
		Matrix B = MatrixGenerator.getMatrix(100, 100);
		
		System.out.print("MT "+threadNumber+" :");
		long start = System.nanoTime();
		MatrixSolver.multiplyMatrixManyThreads(A, B, threadNumber);
		App.showTime(start);
		
		System.out.print("OT:");
		start = System.nanoTime();
		MatrixSolver.multiplyMatrixOneThread(A, B);
		App.showTime(start);
		
	}
	
	public static void find() {
		
	}

}
