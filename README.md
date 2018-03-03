# BBC-SOFTWARE-ENGINEERING-GRADUATE-TECHNICAL-TEST-LONDON-
Technical Test

I use a 0,1 csv file to represent the world cells as dead or live respectively.

I give an implementation which has worst case iteration time complexity of O(n + n/4) = O(n) and total space complexity of O(n) where n is the number of elements on the game board of two dimensions. 

My reasoning is such: on each iteration we will always have to check ALL cells on the board to see if they are live at least once. Hence, the best case time complexity is inherently O(n). This is done with a simple double for loop. If the cell is live, we alert all of its neighbours that it's alive (8 operations total) so we are still O(n). 

Once we know how many neighbours each cell has, we then need to make changes to each cell. This can be done in worst case O(n/4) time by only letting every other cell on every other row make the changes to its neighbours for them. So calls on change() bay half the elements on each row and half the columns is O(n/4). A visual aid is provided below.

https://imgur.com/a/RF7E5

ASSUMPTIONS: World size is constant.
  Assume turn based change; all cells change at the same time i.e. the time elapsed in the world between iterate() calls == 0.
  Width of the world is >= 2.
  Height of the world is >=2.
  Assumes input file is a csv file.
  Assumes output file wanted is a csv file.
  
Test files have been provided. To pass a new csv file in please make sure it's in the same folder as the source code. Sorry.

To run this code once compiled please run in the following format:

java A_Main_Of_Life <<input file>> <<number of iterations wanted>> <<output file>>

Thank you for considering me thus far, I appreciate the opportunity.

