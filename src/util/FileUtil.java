package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import model.Matrix;

public class FileUtil {

	@SuppressWarnings("resource")
	public static Matrix getMatrixFromFile(String filename) {
		File file = new File(filename);

		Matrix matrix = new Matrix();

		try {
			Scanner input = new Scanner(file);
			int rows = countLines(filename) + 1;
			int cols = countColumns(input.nextLine());

			input = new Scanner(file);

			matrix.setRows(rows);
			matrix.setCols(cols);
			int[][] a = new int[rows][cols];

			int i = 0;
			while (input.hasNextLine()) {
				String row = input.nextLine();
				String[] tokens = row.split(" ");
				int j = 0;
				for (String token : tokens) {
					a[i][j++] = Integer.parseInt(token);
				}
				++i;
			}
			matrix.setMatrix(a);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return matrix;
	}

	public static void writeMatrixToFile(Matrix matrix) {
		try {
			PrintWriter writer = new PrintWriter(matrix.getName() + ".txt", "UTF-8");
			int[][] a = matrix.getMatrix();
			for (int[] row : a) {
				for (int cell : row) {
					writer.print(cell + " ");
				}
				writer.println();
			}

			writer.close();
		} catch (IOException e) {
		}
	}

	private static int countColumns(String line) {
		return line.split(" ").length;
	}

	private static int countLines(String filename) {
		try {
			return countLinesEx(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static int countLinesEx(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}

	}
}
