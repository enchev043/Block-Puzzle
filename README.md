# Block-Puzzle

The project showcases the difference in performance of 4 different search algorithms(BFS, DFS, IDS, A*) on the popular Block-Puzzle problem (variation of 8-puzzle). For more detailed explanation and performance results, check out Report.pdf.

#### The definition of the problem is the following:

There is a 4x4 grid with 3 letters (A, B, C), which should be moved across the grid. 
0000
0000
0000
ABCX

The goal state is a tower, with A on top, B in the middle and C at the bottom. 

0000
0A00
0B00
0C0X

To move the letters, there is an X letter. When the X is moved on a space with a letter, they switch places. This is how the X builds the tower. The movement of X is determined by a set of possible moves at each state of the grid which are generated using different rules for each of the four implemented algorithms.  
