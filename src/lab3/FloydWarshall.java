package lab3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FloydWarshall {
	
	static int graph[][];
	
	static int dist[][];
	
	static int predecessor[][];
	
	final static int INF = 99999;
	
	public static void initGraph() {
		graph = new int[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = INF;
                }
            }
        }
    }
	
	public static void readGraphFromFile() {
		String graf = "punkty.txt";
		String line = "";
		String separator = "; ";
		String[] parsedLine; 		
		try (BufferedReader br = new BufferedReader(new FileReader(graf))) {
			while ((line =br.readLine()) != null) {
				if (!line.equals("")) {
					parsedLine = line.split(separator);
					graph[Integer.valueOf(parsedLine[0])-1][Integer.valueOf(parsedLine[1])-1] = Integer.valueOf(parsedLine[1]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initDistPredecessorMatrix() {
		dist = new int[20][20];
        predecessor = new int[20][20];
        int i,j;
        
		for (i = 0; i < 20; i++) {
            for (j = 0; j < 20; j++) {
                dist[i][j] = graph[i][j];
                predecessor[i][j] = i;
            }
		}
	}
	
	public static void floydWarshall()
    {
		int i, j, k;
        for (i = 0; i < 20; i++) {
            for (j = 0; j < 20; j++) {
                for (k = 0; k < 20; k++) {
                    if (dist[j][k] > dist[j][i] + dist[i][k]) {
                        dist[j][k] = dist[j][i] + dist[i][k];
                        predecessor[j][k] = predecessor[i][k];
                    }
                }
            }
        }
    }
	
	public static void printMatrix(int matrix[][]) {
		for (int i=0; i<20; ++i) {
			for (int j=0; j<20; ++j) {
				if (matrix[i][j]==INF)
					System.out.print("INF ");
				else
					System.out.print(matrix[i][j]+"  ");
				}
			System.out.println();
			}
	}
	
	public static void printDistanceAndPath(int v1, int v2) {
		String path = " -> "+String.valueOf(v2);
		int u = predecessor[v1][v2];
		while (u != v1) {
            u = predecessor[v1][u];
            path = " -> "+String.valueOf(u).concat(path);
        }
		path = v1+path;
		System.out.println("Path between nodes " + v1 + " and " + v2 + " is " + path);
		System.out.println("Distance between " + v1 + " and " + v2 + " is " + dist[v1][v2]);
	}
}
