# Shortest Knight Path Problem

This repository is a solution to a problem I encountered a few years ago in a competitive programming competition.

The problem features a chessboard and the location of two squares on the chessboard. One square is the target square while the other has a knight on the square.

The goal is to find the smallest number of moves necessary to place the knight on the target square.

![knightAndTargetSquare1](https://user-images.githubusercontent.com/60547277/195398325-99f74245-46ac-4b33-8397-c799bc1f3e99.gif)

In the above example one possible solution is: F5 -> E3 -> C4. You could also take the path: F5 -> D6 -> C4.

## My Approach:

In order to solve this problem I considered many different approaches. 

**1. Simple brute-force:** My first idea was to develop a brute-force solution which would visit every square. Starting at the first sqaure I would check all surrounding squares. If the knight could legally move to the square beside it I would check whether it was the target square and add up the total number of moves. I opted not to implement this as it would have led to a time limit exceeded error.

**2. Recursive brute-force:** I then considered a similar solution but this time using recursive calls to the original function and taking the minimum of each call as my answer. While I believe this may have worked in theory it wasn't practical as it required a max of 8^64 comparisons (not quite, but still too large). 

**3. Dijkstra's shortest path finding algorithm:** The final implementation I considered and the one I ended up choosing was dijkstra's shortest path finding algorithm. I turned each of the 64 squares on the chessboard into a node in an adjacency list. I then connected each node with a weight of one depending on whether a knight can legally move between the two nodes. Once I had built the graph of nodes and created methods to convert between the square name (A1-H8) and the node label (0-63), the solution was as simple as implementing dijkstra's algorithm to find the shortest path. 
