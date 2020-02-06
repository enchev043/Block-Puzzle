import java.util.LinkedList;

/**
 * Class that represents the grid full of blocks in the 8-puzzle game
 */
public class Grid {

    private SmileyBlock smileyBlock;
    private Integer size; //NxN grid
    private Block[][] gridBlocks; //Location of the blocks in the grid

    public Grid(Integer size){
        this.size = size;
        gridBlocks = new Block[size][size];
        generateGrid();
        generateLetters();
    }

    public SmileyBlock getSmileyBlock() {
        return smileyBlock;
    }

    public void setSmileyBlock(SmileyBlock smileyBlock) {
        gridBlocks[smileyBlock.getX()][smileyBlock.getY()] = smileyBlock;
        this.smileyBlock = smileyBlock;
    }

    public Block[][] getGridBlocks() {
        return gridBlocks;
    }

    public Block getBlock(int x, int y){
        return gridBlocks[x][y];
    }

    public Integer getSize() {
        return size;
    }

    /**
     * Creates the grid with empty blocks
     */
    private void generateGrid(){
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size-1 && j == size-1){
                    this.smileyBlock = new SmileyBlock(i,j,this);
                    gridBlocks[i][j] = this.smileyBlock;
                   // System.out.println("in");
                } else {
                    gridBlocks[i][j] = new Block(i,j,this);
                }

            }
        }
    }

    /**
     * Names the bottom blocks so that they can be manipulated
     */
    private void generateLetters(){
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J',
                'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        Integer n = 0;
        for (int i = 0; i < size; i++) {
            if (!(gridBlocks[size-1][i] instanceof SmileyBlock)) {
                gridBlocks[size-1][i].setBlock(alphabet[n]);
                gridBlocks[size-1][i].setOccupied(true);
                n++;
            } else {
                gridBlocks[size-1][i].setBlock('X');
            }
        }
    }

    /**
     * Checks if a block is active (is named with letter)
     * @param x coordinate
     * @param y coordinate
     * @return true if occupied, false if not occupied
     */
    public boolean activeBlock(int x, int y){
        return gridBlocks[x][y].isOccupied();
    }

    /*
    x
    public String encodeState(){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gridBlocks[i][j].isOccupied()){
                    stringBuilder.append(gridBlocks[i][j].getBlock());
                } else {
                    stringBuilder.append("0");
                }
            }
        }
        return stringBuilder.toString();
    }
    */

    /**
     * Gets the current grind configuration and prints it out as a String
     * @return string representation of the configuration
     */
    public String encodeState(){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gridBlocks[i][j].isOccupied()){
                    stringBuilder.append(gridBlocks[i][j].getBlock());
                } else {
                    stringBuilder.append("0");
                }
            }
        }
        //State state = new State(stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * Defines a goal state and checks the current state
     * @return true if the state is reached, false if not
     */
    public boolean checkGoal(String state){
        String goalState = "ABC";
        return (("" + state.charAt(1)+state.charAt(5)+state.charAt(9)).equals(goalState));
    }

    public LinkedList<String> statesList(LinkedList<String> states){
        if (states.size() == 0){
            return states;
        } else {
            if (getSmileyBlock().moveDown()){
                getSmileyBlock().moveDown();
                states.add(encodeState());
            }
            if (getSmileyBlock().moveUp()){
                getSmileyBlock().moveUp();
                states.add(encodeState());
            }
            if  (getSmileyBlock().moveLeft()){
                getSmileyBlock().moveLeft();
                states.add(encodeState());
            }
            if (getSmileyBlock().moveRight()){
                getSmileyBlock().moveRight();
                states.add(encodeState());
            }
            return states;
        }
    }
}
