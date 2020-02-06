/**
 * Class that represents each block in the grid
 */
public class Block {

    private int x; //X coordinate in the grid
    private int y; //Y coordinate in the grid
    private char block; //Individual block name
    private boolean occupied; //Tells if the block is occupied by a block
    private Grid grid; //The grid that hold the block


    /**
     * Standard block in the grid
     * @param x coordinate
     * @param y coordinate
     * @param block char value for the block in the grid
     */
    public Block(int x, int y, char block, Grid grid){
        this.x = x;
        this.y = y;
        this.block = block;
        this.grid = grid;
    }

    /**
     * Constructor for the Block
     * @param x coordinate in the grid
     * @param y coordinate in the grid
     */
    public Block(int x, int y, Grid grid){
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    public Block(int x, int y, char block, Grid grid,Boolean occupied){
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.occupied = true;
    }

    /**
     *
     * Getters and Setters
     *
     */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getBlock() {
        return block;
    }

    public void setBlock(char block) {
        this.block = block;
    }

    public void setOccupied(boolean occupied){
        this.occupied = occupied;
    }

    public boolean isOccupied(){
        return this.occupied;
    }

    public void setGrid(Grid grid){
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    /**
     *
     *
     * Class methods
     *
     *
     */

    public boolean isEqual(Block b){
        return  (this.getBlock() > b.getBlock());
    }
}
