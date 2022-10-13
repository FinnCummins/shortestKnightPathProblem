# Shortest Knight Path Problem

This repository is my solution to a problem I encountered a few years ago in a competitive programming competition.

The problem features a chessboard and the location of two squares on the chessboard. One square is the target square while the other has a knight on the square.

The goal is to find the smallest number of moves necessary to place the knight on the target square.

![knightAndTargetSquare1](https://user-images.githubusercontent.com/60547277/195398325-99f74245-46ac-4b33-8397-c799bc1f3e99.gif)

In the above example one possible solution is: F5 -> E3 -> C4. You could also take the path: F5 -> D6 -> C4.

My Approach:

In order to solve this problem I considered many different approaches. 

1. My first idea was to develop a brute-force solution which would visit every square. Starting at the first sqaure I would check all surrounding squares. If the knight could legally move to the square beside it I would check 
