package test;

import org.junit.Assert;
import org.junit.Test;

import model.Matrix;
import util.FileUtil;
import util.MatrixSolver;

public class MatrixSolverTest {

	@Test
	public void compareCalculations() {
		Matrix A = FileUtil.getMatrixFromFile("A.txt");
		Matrix B = FileUtil.getMatrixFromFile("B.txt");
		
		int[][] a = MatrixSolver.multiplyMatrixOneThread(A, B).getMatrix();
		int[][] b = MatrixSolver.multiplyMatrixManyThreads(A, B, 5).getMatrix();
		
		Assert.assertArrayEquals(a,b);
		
	}
	
}
