import java.util.*;

class Pair {
	int index;
	int weight;
	Pair(int x, int y) {this.index = x; this.weight = y;}
}

class Graph {
	
	int numOfVertices;
	int numOfEdges;
	
	List<List<Pair>> adjacencyList = new ArrayList<>();
	
	Graph() {};
	
	Graph(int V) {
		this.numOfVertices = V;
		for (int i = 0; i < V; i++)
			adjacencyList.add(new ArrayList<Pair>());
	}
	
	Graph(int V, int E) {
		this.numOfVertices = V;
		this.numOfEdges = E;
		for (int i = 0; i < V; i++)
			adjacencyList.add(new ArrayList<Pair>());
	}
	
	void addEdge(int source, int destination, int weight) {
		adjacencyList.get(source).add(new Pair(destination, weight));
	}
	
	/*void printBFS(int startingNode)
	{
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> alreadyVisited = new HashSet<>();
		queue.add(startingNode);
		System.out.print("BFS Spanning Tree: ");
		while (!queue.isEmpty())
		{
			int currentNode = queue.poll();
			alreadyVisited.add(currentNode);
			for (int i = 0; i < this.adjacencyList.get(currentNode).size(); i++)
			{
				if (!alreadyVisited.contains(this.adjacencyList.get(currentNode).get(i).index))
					queue.add(this.adjacencyList.get(currentNode).get(i).index);
			}
			if (!queue.isEmpty())
				System.out.print(currentNode + " -> ");
			else
				System.out.print(currentNode);
		}
	}*/
	
	String convertNumToSquare(int num)
	{
		String square = "";
		char letter = (char) ((num/8) + 65);
		square += letter;
		int remainder = num % 8;
		remainder += 1;
		square += Integer.toString(remainder);
		return square;
	}
	
	int squareToNum(String square)
	{
		int num = 0;
		int letter = (Character.toLowerCase(square.charAt(0))-97)*8;
		num += letter;
		int number = square.charAt(1)-48;
		num += number - 1;
		return num;
	}
	
	private void printShortestPath(int startingNode, int destinationNode, int[][] shortestPath)
	{
		int prevNode = shortestPath[destinationNode][1];
		int currNode = destinationNode;
		List<String> path = new ArrayList<>();
		while (prevNode != currNode)
		{
			path.add(this.convertNumToSquare(currNode));
			currNode = prevNode;
			prevNode = shortestPath[currNode][1];
		}
		path.add(this.convertNumToSquare(startingNode));
		Collections.reverse(path);
		for (int i = 0; i < path.size(); i++)
		{
			if (i != path.size()-1)
				System.out.print(path.get(i) + " -> ");
			else
				System.out.print(path.get(i));
		}
	}
	
	int dijkstrasSPF(int startingNode, int destinationNode)
	{
		int[][] shortestPath = new int[this.numOfVertices][2];
		
		for (int i = 0; i < this.numOfVertices; i++)
		{
			shortestPath[i][0] = Integer.MAX_VALUE;
			shortestPath[i][1] = i;
		}
		
		shortestPath[startingNode][0] = 0; shortestPath[startingNode][1] = startingNode;
		
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> alreadyVisited = new HashSet<>();
		queue.add(startingNode);
		while (!queue.isEmpty())
		{
			int currentNode = queue.poll();
			alreadyVisited.add(currentNode);
			for (int i = 0; i < this.adjacencyList.get(currentNode).size(); i++)
			{
				if (!alreadyVisited.contains(this.adjacencyList.get(currentNode).get(i).index))
				{
					queue.add(this.adjacencyList.get(currentNode).get(i).index);
					if (shortestPath[currentNode][0] + this.adjacencyList.get(currentNode).get(i).weight <
							shortestPath[this.adjacencyList.get(currentNode).get(i).index][0])
					{
						shortestPath[this.adjacencyList.get(currentNode).get(i).index][0] = shortestPath[currentNode][0] + this.adjacencyList.get(currentNode).get(i).weight;
						shortestPath[this.adjacencyList.get(currentNode).get(i).index][1] = currentNode;
					}
						
				}
			}
		}
		
		this.printShortestPath(startingNode, destinationNode, shortestPath);
		return shortestPath[destinationNode][0];
	}
}


public class shortestKnightPathProblem {
	
	static void createChessBoard(Graph chessBoard)
	{
		char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
		
		for (int i = 0; i < letters.length; i++)
		{
			for (int j = 0; j < numbers.length; j++)
			{
				int currentSquare = ((letters[i] - 65)*8) + (numbers[j]-1);
				
				if (numbers[j] < 7 && letters[i] != 'H')
					chessBoard.addEdge(currentSquare, currentSquare+10, 1);
				if (numbers[j] < 7 && letters[i] != 'A')
					chessBoard.addEdge(currentSquare, currentSquare-6, 1);
				if (numbers[j] != 8 && letters[i] < 'G')
					chessBoard.addEdge(currentSquare, currentSquare+17, 1);
				if (numbers[j] != 8 && letters[i] > 'B')
					chessBoard.addEdge(currentSquare, currentSquare-15, 1);
				if (numbers[j] != 1 && letters[i] < 'G')
					chessBoard.addEdge(currentSquare, currentSquare+15, 1);
				if (numbers[j] != 1 && letters[i] > 'B')
					chessBoard.addEdge(currentSquare, currentSquare-17, 1);
				if (numbers[j] > 2 && letters[i] != 'H')
					chessBoard.addEdge(currentSquare, currentSquare+6, 1);
				if (numbers[j] > 2 && letters[i] != 'A')
					chessBoard.addEdge(currentSquare, currentSquare-10, 1);
			}
		}
	}

	public static void main(String[] args) {
		Graph chessBoard = new Graph(64);
		
		createChessBoard(chessBoard);
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your starting position: ");
		String start = scanner.next();
		System.out.print("Enter your final destination: ");
		String end = scanner.next();
		
		System.out.print("\nThe shortest path from " + start + " to " + end + " is: ");
		
		System.out.print("\nThis can be done in " + chessBoard.dijkstrasSPF(chessBoard.squareToNum(start), chessBoard.squareToNum(end)) + " moves.");
	}
	
}
