import java.util.LinkedList;

/**
 * The block that moves the other blocks around the grid and 'builds' the towers
 */
public class SmileyBlock extends Block {

    public SmileyBlock(int x, int y, Grid grid) {
        super(x, y, grid);
        grid.setSmileyBlock(this);
        setOccupied(true);
    }

    /**
     * Movement of the block methods
     * @return true if movement possible and moves the block, false if not possible
     */

    public boolean moveLeft(){
        if (this.getY()-1 < 0){ //Left Boundary of the grid
            return false;
        } else {
            if (getGrid().activeBlock(this.getX(), this.getY()-1)){
                this.replace(getGrid().getGridBlocks()[getX()][getY()-1]);

                getGrid().setSmileyBlock(this);
                return true;
            } else {
                getGrid().getGridBlocks()[this.getX()][this.getY()-1] = this;
                getGrid().getGridBlocks()[this.getX()][this.getY()] = new Block(this.getX(),this.getY(),getGrid());
                getGrid().getGridBlocks()[this.getX()][this.getY()].setOccupied(false);
                this.setX(this.getX());
                this.setY(this.getY()-1);
                getGrid().setSmileyBlock(this);
                return true;
            }
        }
    }

    public String stateLeft(){
        if (!(this.getY()-1 < 0)){
            String state;
            this.moveLeft();
            state = getGrid().encodeState();
            this.moveRight();
            return state;
        } else {
            return getGrid().encodeState();
        }
    }

    public boolean isStatePossibleLeft(){
        return (!(this.getY()-1 < 0));
    }

    public boolean moveRight(){
        if (this.getY()+1 > this.getGrid().getSize()-1){ //Right Boundary of the grid
            return false;
        } else {
            if (getGrid().activeBlock(this.getX(), this.getY()+1)){
                this.replace(getGrid().getGridBlocks()[getX()][getY()+1]);
                getGrid().setSmileyBlock(this);
                return true;
            } else {
                getGrid().getGridBlocks()[this.getX()][this.getY()+1] = this;
                getGrid().getGridBlocks()[this.getX()][this.getY()] = new Block(this.getX(),this.getY(),getGrid());
                getGrid().getGridBlocks()[this.getX()][this.getY()].setOccupied(false);
                this.setX(this.getX());
                this.setY(this.getY()+1);
                getGrid().setSmileyBlock(this);
                return true;
            }
        }
    }

    public String stateRight(){
        if (!(this.getY()+1 > this.getGrid().getSize()-1)){
            String state;
            this.moveRight();
            state = getGrid().encodeState();
            this.moveLeft();
            return state;
        } else {
            return getGrid().encodeState();
        }
    }

    public boolean isStatePossibleRight(){
        return !(this.getY()+1 > this.getGrid().getSize()-1);
    }

    public boolean moveUp(){
        if (this.getX()-1 < 0){ //Upper Boundary of the grid
            return false;
        } else {
            if (getGrid().activeBlock(this.getX()-1, this.getY())){
                this.replace(getGrid().getGridBlocks()[getX()-1][getY()]);
                getGrid().setSmileyBlock(this);
                return true;
            } else {
                getGrid().getGridBlocks()[this.getX()-1][this.getY()] = this;
                getGrid().getGridBlocks()[this.getX()][this.getY()] = new Block(this.getX(),this.getY(),getGrid());
                getGrid().getGridBlocks()[this.getX()][this.getY()].setOccupied(false);
                this.setX(this.getX()-1);
                this.setY(this.getY());
                getGrid().setSmileyBlock(this);
                return true;
            }
        }
    }

    public String stateUp(){

        if (!(this.getX()-1 < 0)){
            String state;
            this.moveUp();
            state = getGrid().encodeState();
            this.moveDown();
            return state;
        } else {
            return getGrid().encodeState();
        }
    }

    public boolean isStatePossibleUp(){
        return !(this.getX()-1 < 0);
    }

    public boolean moveDown(){
        if (this.getX()+1 > this.getGrid().getSize()-1){ //Bottom Boundary of the grid
            return false;
        } else {
            if (getGrid().activeBlock(this.getX()+1, this.getY())){
                this.replace(getGrid().getGridBlocks()[getX()+1][getY()]);
                getGrid().setSmileyBlock(this);
                return true;
            } else {
                getGrid().getGridBlocks()[this.getX()+1][this.getY()] = this;
                getGrid().getGridBlocks()[this.getX()][this.getY()] = new Block(this.getX(),this.getY(),getGrid());
                getGrid().getGridBlocks()[this.getX()][this.getY()].setOccupied(false);
                this.setX(this.getX()+1);
                this.setY(this.getY());
                getGrid().setSmileyBlock(this);
                return true;
            }
        }
    }

    public String stateDown(){

        if (!(this.getX()+1 > this.getGrid().getSize()-1)){
            String state;
            this.moveDown();
            state = getGrid().encodeState();
            this.moveUp();
            return state;
        } else {
            return getGrid().encodeState();
        }
    }

    public boolean isStatePossibleDown(){
        return !(this.getX()+1 > this.getGrid().getSize()-1);
    }

    public void replace(Block block){
        Block movedBlock = new Block(this.getX(),this.getY(),block.getBlock(),getGrid());
        getGrid().getGridBlocks()[this.getX()][this.getY()] = movedBlock;
        movedBlock.setOccupied(true);
        this.setX(block.getX());
        this.setY(block.getY());
        getGrid().getGridBlocks()[block.getX()][block.getY()] = this;
    }

    public LinkedList<Block> possibleMoves(){

        return new LinkedList<>();
    }
}
