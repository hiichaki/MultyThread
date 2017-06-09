package main;

import java.util.Scanner;

import model.Matrix;
import util.FileUtil;
import util.MatrixSolver;

public class App {
	
	private static void mainTask() {
		Matrix A = FileUtil.getMatrixFromFile("A.txt");
		Matrix B = FileUtil.getMatrixFromFile("B.txt");
		Matrix result;
		System.out.println("Please, write number of threads:");
		int numberOfThreads = getInt();
		
		long start = System.nanoTime();
		
		
		result = MatrixSolver.multiplyMatrixManyThreads(A, B, numberOfThreads);
		showTime(start);
		FileUtil.writeMatrixToFile(result);
		
		showMatrix(A);
		showMatrix(B);
		showMatrix(result);
	}
	

	public static void main(String[] args) {
		
		mainTask();
//		BestSolution.compareMultiplying(5);
		
	}
	
	private static void showMatrix(Matrix matrix) {
		for(int[] row : matrix.getMatrix()) {
			for(int cell: row) {
				System.out.print(cell+ " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------");
	}
	
	public static void showTime(long start) {
		long end = System.nanoTime();
		System.out.println("Calculations took " + (double)(end-start)/1000000000.0+"s");
	}
	
	@SuppressWarnings("resource")
	private static int getInt() {
		return new Scanner(System.in).nextInt();
	}

}
