package lab3;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		FloydWarshall.initGraph();
		FloydWarshall.readGraphFromFile();
		FloydWarshall.initDistPredecessorMatrix();
		FloydWarshall.floydWarshall();
		FloydWarshall.printDistanceAndPath(0, 19);
	}
}
