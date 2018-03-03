package javaFiles;

public class Cell {
	private Board board;
	private Cell[][] array;
	private int xPos;
	private int yPos;
	private int neighbours;
	
	private boolean live;
	private boolean top;
	private boolean bottom;
	private boolean left;
	private boolean right;
	private boolean leftTop;
	private boolean leftBottom;
	private boolean rightTop;
	private boolean rightBottom;
	
	public Cell (Board board, Cell[][] array, int xPos, int yPos, int live){
		if(live == 1)
			this.live = true;
		else 
			this.live = false;
		this.board = board;
		this.array = array;
		this.xPos = xPos;
		this.yPos = yPos;
		initialise();
	}

	/**
	 * Initialises the cell to having no neighbours.
	 */
	private void initialise(){
		top = false;
		bottom = false;
		left = false;
		right = false;
		leftTop = false;
		leftBottom = false;
		rightTop = false;
		rightBottom = false;
		neighbours = 0;
	}

	/**
	 * For a standard cell selected to make changes to others, the top,
	 * left and topLeft cells are changed. 
	 * 
	 * If the cell is one away from any of the perimeter cells, it changes
	 * them too.
	 */
	public void change(){
		Cell cell;
		if ((cell = getTop())!= null)
			cell.decideChange();
		if ((cell = getLeft())!= null)
			cell.decideChange();
		if ((cell = getLeftTop())!= null)
			cell.decideChange();
		
		if (xPos == array[0].length - 2){
			if ((cell = getRightTop())!= null)
				cell.decideChange();
			if ((cell = getRight())!= null)
				cell.decideChange();
		}
		
		if (yPos == array.length - 2){
			if ((cell = getBottom())!= null)
				cell.decideChange();
			if ((cell = getLeftBottom())!= null)
				cell.decideChange();
		}
		
		if (xPos == array[0].length - 2 && yPos == array.length - 2)
			if ((cell = getRightBottom())!= null)
				cell.decideChange();
		
		decideChange();		
	}
	
	/**
	 * Applies change rules as defined in the specification;
	 * 	if this cell is not live and has three neighbours, it is now alive
	 * 	if this cell is live and is over or under populated, it is killed.
	 */
	private void decideChange(){
		if (!live && neighbours == 3)
			live = true;
		
		if (live){
			if(neighbours < 2)
				live = false;
			if(neighbours > 3)
				live = false;
		}
		initialise();
	}
	
	/**
	 * Will return whether this cell is live
	 * @return Is this cell live
	 */
	public boolean isLive(){
		return live;
	}
	
	/**
	 * Will return whether this cell is live as an int
	 * @return Is this cell live
	 */
	public int getLive(){
		if (live)
			return 1;
		else
			return 0;
	}
	
	/**
	 * Is the cell above is not occupied by a live cell
	 */
	private void hasTop() {
		if(!top){
			top = true;
			neighbours++;
		}
	}
	
	private void hasLeft() {
		if(!left){
			left = true;
			neighbours++;
		}
	}
	
	private void hasRight() {
		if(!right){
			right = true;
			neighbours++;
		}
	}
	
	private void hasBottom() {
		if(!bottom){
			bottom = true;
			neighbours++;
		}
	}
	
	private void hasLeftTop() {
		if(!leftTop){
			leftTop = true;
			neighbours++;
		}
	}
	
	private void hasLeftBottom() {
		if(!leftBottom){
			leftBottom = true;
			neighbours++;
		}
	}
	
	private void hasRightTop() {
		if(!rightTop){
			rightTop = true;
			neighbours++;
		}
	}
	
	private void hasRightBottom() {
		if(!rightBottom){
			rightBottom = true;
			neighbours++;
		}
	}
	
	private Cell getTop(){
		return board.getCell(xPos, yPos-1);
	}
	
	private Cell getBottom(){
		return board.getCell(xPos, yPos+1);
	}
	
	private Cell getLeft(){
		return board.getCell(xPos-1, yPos);
	}
	
	private Cell getRight(){
		return board.getCell(xPos+1, yPos);
	}
	
	private Cell getRightTop(){
		return board.getCell(xPos+1, yPos-1);
	}
	
	private Cell getLeftTop(){
		return board.getCell(xPos-1, yPos-1);
	}
	
	private Cell getRightBottom(){
		return board.getCell(xPos+1, yPos+1);
	}
	
	private Cell getLeftBottom(){
		return board.getCell(xPos-1, yPos+1);
	}
	
	/**
	 * This will attempt to get all adjacent Cells, and if they exist,
	 * will alert them that this node is live.
	 */
	public void alertNeighbours(){
		Cell cell;
		if ((cell = getTop()) != null)
		 cell.hasBottom();
		if ((cell = getBottom()) != null)
			 cell.hasTop();
		if ((cell = getLeftTop()) != null)
			 cell.hasRightBottom();
		if ((cell = getRightTop()) != null)
			 cell.hasLeftBottom();
		if ((cell = getLeft()) != null)
			 cell.hasRight();
		if ((cell = getRight()) != null)
			 cell.hasLeft();
		if ((cell = getLeftBottom()) != null)
			 cell.hasRightTop();
		if ((cell = getRightBottom()) != null)
			 cell.hasLeftTop();
	}
}
