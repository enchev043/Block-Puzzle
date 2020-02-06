import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * This class defines the 8-puzzle problem given in the coursework specifications. I've decided to
 * use a string to represent the problem.
 *
 * EXAMPLE: 000000000000ABCX is the initial configuration and represents
 * 0 0 0 0
 * 0 0 0 0
 * 0 0 0 0
 * A B C X
 *      where,  X is the smiley face that moves the other tiles
 *              0 is an empty block
 *              {A,B,C} are the blocks that have to be moved
 *
 * The string represents the grid and is efficient and fast. I've found this approach to be
 * the fastest because it is not too complicated, it is memory efficient and easy to implement.
 */

public class BlockPuzzle implements Comparable<BlockPuzzle> {

    private static final String goalState = "ABC"; //Used for checking the goal state
    private String currentState;
    private String initialState;
    private Integer size = 16; //Size of the grid
    private Integer smileyPosition; //Position of the smiley face
    private static Integer treeDepth = 0;
    private Integer stateDepth = 0;
    private BlockPuzzle parent;
    private static final char[] symbols = {'A','B','C','X'}; //Symbols in the system that are being moved
    private Integer g; //Expanded nodes for Manhattan Distance
    private Integer f; //Manhattan distance

    public BlockPuzzle(){
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;

        for (int i = 0; i < size; i++) {
            if (i <= 11){
                stringBuilder.append("0");
            } else {
                stringBuilder.append(symbols[n]);
                n++;
            }
        }
        initialState = stringBuilder.toString();
        smileyPosition = 15;
        currentState = initialState;
        parent = this;
        g = 0;
    }


    /**
     *
     *
     * Getters and setters
     *
     *
     *
     */

    public static String getGoalState() {
        return goalState;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSmileyPosition() {
        return smileyPosition;
    }

    public void setSmileyPosition(Integer smileyPosition) {
        this.smileyPosition = smileyPosition;
    }

    public static Integer getTreeDepth() {
        return treeDepth;
    }

    public static void setTreeDepth(Integer treeDepth) {
        BlockPuzzle.treeDepth = treeDepth;
    }

    public Integer getStateDepth() {
        return stateDepth;
    }

    public void setStateDepth(Integer stateDepth) {
        this.stateDepth = stateDepth;
    }

    public BlockPuzzle getParent() {
        return parent;
    }

    public void setParent(BlockPuzzle parent) {
        this.parent = parent;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    /**
     *
     *
     * Class methods
     *
     *
     */

    /**
     * Moves the smiley face to the left
     * Checks if move is possible
     * If possible ->   If position is occupied by a letter, swap smiley and letter
     *                  If not, move smiley and empty previous smiley position
     * Else -> Don't do anything
     * @return new state with smiley to the left
     */
    private String moveLeft(){
        if (smileyPosition > 0){  //Checks if move to the left is possible
            smileyPosition--;
            //Checks if there is a letter in the next box, if there is
            //swap positions of letter and smiley
            //if not, just move smiley and leave previous smiley position blank
            if (currentState.charAt(smileyPosition) != '0'){
                char dummy = currentState.charAt(smileyPosition);
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition+1] = dummy;
                currentState = new String(tempState);
                return currentState;
            } else {
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition+1] = '0';
                currentState = new String(tempState);
                return currentState;
            }
        } else {
            return currentState;
        }

    }

    /**
     * Moves the smiley face to the right
     * Checks if move is possible
     * If possible ->   If position is occupied by a letter, swap smiley and letter
     *                  If not, move smiley and empty previous smiley position
     * Else -> Don't do anything
     * @return new state with smiley to the right
     */
    private String moveRight(){
        if (smileyPosition < 15){
            smileyPosition++;
            if (currentState.charAt(smileyPosition) != '0'){
                char dummy = currentState.charAt(smileyPosition);
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition-1] = dummy;
                currentState = new String(tempState);
                return currentState;
            } else {
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition-1] = '0';
                currentState = new String(tempState);
                return currentState;
            }
        } else {
            return currentState;
        }
    }

    /**
     * Moves the smiley face up
     * Checks if move is possible
     * If possible ->   If position is occupied by a letter, swap smiley and letter
     *                  If not, move smiley and empty previous smiley position
     * Else -> Don't do anything
     * @return new state with smiley moved up
     */
    private String moveUp(){
        if (smileyPosition <= 15 && smileyPosition >= 4){
            smileyPosition-=4;
            if (currentState.charAt(smileyPosition) != '0'){
                char dummy = currentState.charAt(smileyPosition);
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition+4] = dummy;
                currentState = new String(tempState);
                return currentState;
            } else {
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition+4] = '0';
                currentState = new String(tempState);
                return currentState;
            }
        } else {
            return currentState;
        }
    }

    /**
     * Moves the smiley face down
     * Checks if move is possible
     * If possible ->   If position is occupied by a letter, swap smiley and letter
     *                  If not, move smiley and empty previous smiley position
     * Else -> Don't do anything
     * @return new state with smiley moved down
     */
    private String moveDown(){
        if (smileyPosition <= 11 && smileyPosition >= 0){
            smileyPosition+=4;
            if (currentState.charAt(smileyPosition) != '0'){
                char dummy = currentState.charAt(smileyPosition);
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition-4] = dummy;
                currentState = new String(tempState);
                return currentState;
            } else {
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition-4] = '0';
                currentState = new String(tempState);
                return currentState;
            }
        } else {
            return currentState;
        }
    }

    /**
     *
     * Dummy methods that I've used for tests.
     *
     */
    private String dummyLeft(){
        Integer smileyPosition = getSmileyPosition();
        String currentState = getCurrentState();
        if (smileyPosition > 0){
            smileyPosition--;
            if (currentState.charAt(smileyPosition) != '0'){
                char dummy = currentState.charAt(smileyPosition);
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition+1] = dummy;
                currentState = new String(tempState);
                return currentState;
            } else {
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition+1] = '0';
                currentState = new String(tempState);
                return currentState;
            }
        } else {
            return currentState;
        }
    }

    private String dummyRight(){
        Integer smileyPosition = getSmileyPosition();
        String currentState = getCurrentState();

        if (smileyPosition < 15){
            smileyPosition++;
            if (currentState.charAt(smileyPosition) != '0'){
                char dummy = currentState.charAt(smileyPosition);
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition-1] = dummy;
                currentState = new String(tempState);
                return currentState;
            } else {
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition-1] = '0';
                currentState = new String(tempState);
                return currentState;
            }
        } else {
            return currentState;
        }
    }

    private String dummyUp(){
        Integer smileyPosition = getSmileyPosition();
        String currentState = getCurrentState();

        if (smileyPosition <= 15 && smileyPosition >= 4){
            smileyPosition-=4;
            if (currentState.charAt(smileyPosition) != '0'){
                char dummy = currentState.charAt(smileyPosition);
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition+4] = dummy;
                currentState = new String(tempState);
                return currentState;
            } else {
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition+4] = '0';
                currentState = new String(tempState);
                return currentState;
            }
        } else {
            return currentState;
        }
    }

    private String dummyDown(){
        Integer smileyPosition = getSmileyPosition();
        String currentState = getCurrentState();

        if (smileyPosition <= 11 && smileyPosition >= 0){
            smileyPosition+=4;
            if (currentState.charAt(smileyPosition) != '0'){
                char dummy = currentState.charAt(smileyPosition);
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition-4] = dummy;
                currentState = new String(tempState);
                return currentState;
            } else {
                char[] tempState = currentState.toCharArray();
                tempState[smileyPosition] = 'X';
                tempState[smileyPosition-4] = '0';
                currentState = new String(tempState);
                return currentState;
            }
        } else {
            return currentState;
        }
    }

    /**
     *
     *
     * Children generators for all methods
     *
     *
     */

    /**
     * Generates children for each state for the problem
     * @param parent of the children that are going to be generated
     * @return LinkedList with the children states
     */
    public static LinkedList<BlockPuzzle> generateChildBfs(BlockPuzzle parent){
        LinkedList<BlockPuzzle> childStates = new LinkedList<>();
        if (parent.isMovementPossible()){
            if (parent.isMoveRight()){
                BlockPuzzle child2 = new BlockPuzzle();
                child2.smileyPosition = parent.getSmileyPosition();
                child2.initialState = parent.getCurrentState();
                child2.currentState = child2.initialState;
                child2.stateDepth = parent.stateDepth+1;
                child2.parent = parent;
                child2.moveRight();
                childStates.add(child2);
            }

            if (parent.isMoveLeft()){
                BlockPuzzle child1 = new BlockPuzzle();
                child1.smileyPosition = parent.getSmileyPosition();
                child1.initialState = parent.getCurrentState();
                child1.stateDepth = parent.stateDepth+1;
                child1.currentState = child1.initialState;
                child1.parent = parent;
                child1.moveLeft();
                childStates.add(child1);
            }
            if (parent.isMoveUp()){
                BlockPuzzle child3 = new BlockPuzzle();
                child3.smileyPosition = parent.getSmileyPosition();
                child3.initialState = parent.getCurrentState();
                child3.currentState = child3.initialState;
                child3.stateDepth = parent.stateDepth+1;
                child3.parent = parent;
                child3.moveUp();
                childStates.add(child3);
            }
            if (parent.isMoveDown()){
                BlockPuzzle child4 = new BlockPuzzle();
                child4.smileyPosition = parent.getSmileyPosition();
                child4.initialState = parent.getCurrentState();
                child4.currentState = child4.initialState;
                child4.stateDepth = parent.stateDepth+1;
                child4.parent = parent;
                child4.moveDown();
                childStates.add(child4);
            }
        }
        treeDepth = parent.stateDepth+1;
        return childStates;
    }

    /**
     * Generates children for each state of the problem. The order is randomized because
     * DFS tends to get stuck.
     * @param parent of the children that are going to be generated
     * @return LinkedList with the children states
     */
    public static LinkedList<BlockPuzzle> generateChildDfs(BlockPuzzle parent){
        Random random = new Random();
        int n = random.nextInt(3)+1;
        LinkedList<BlockPuzzle> childStates = new LinkedList<>();

        if (parent.isMovementPossible()){
            if (n == 1){
                if (parent.isMoveRight()){
                    BlockPuzzle child2 = new BlockPuzzle();
                    child2.smileyPosition = parent.getSmileyPosition();
                    child2.initialState = parent.getCurrentState();
                    child2.currentState = child2.initialState;
                    child2.stateDepth = parent.stateDepth+1;
                    child2.parent = parent;
                    child2.moveRight();
                    childStates.add(child2);
                }

                if (parent.isMoveLeft()){
                    BlockPuzzle child1 = new BlockPuzzle();
                    child1.smileyPosition = parent.getSmileyPosition();
                    child1.initialState = parent.getCurrentState();
                    child1.currentState = child1.initialState;
                    child1.parent = parent;
                    child1.stateDepth = parent.stateDepth+1;
                    child1.moveLeft();
                    childStates.add(child1);
                }
                if (parent.isMoveUp()){
                    BlockPuzzle child3 = new BlockPuzzle();
                    child3.smileyPosition = parent.getSmileyPosition();
                    child3.initialState = parent.getCurrentState();
                    child3.currentState = child3.initialState;
                    child3.stateDepth = parent.stateDepth+1;
                    child3.parent = parent;
                    child3.moveUp();
                    childStates.add(child3);
                }
                if (parent.isMoveDown()){
                    BlockPuzzle child4 = new BlockPuzzle();
                    child4.smileyPosition = parent.getSmileyPosition();
                    child4.initialState = parent.getCurrentState();
                    child4.currentState = child4.initialState;
                    child4.parent = parent;
                    child4.stateDepth = parent.stateDepth+1;
                    child4.moveDown();
                    childStates.add(child4);
                }
            }
            if (n == 2){
                if (parent.isMoveUp()){
                    BlockPuzzle child3 = new BlockPuzzle();
                    child3.smileyPosition = parent.getSmileyPosition();
                    child3.initialState = parent.getCurrentState();
                    child3.currentState = child3.initialState;
                    child3.parent = parent;
                    child3.stateDepth = parent.stateDepth+1;
                    child3.moveUp();
                    childStates.add(child3);
                }
                if (parent.isMoveDown()){
                    BlockPuzzle child4 = new BlockPuzzle();
                    child4.smileyPosition = parent.getSmileyPosition();
                    child4.initialState = parent.getCurrentState();
                    child4.currentState = child4.initialState;
                    child4.parent = parent;
                    child4.stateDepth = parent.stateDepth+1;
                    child4.moveDown();
                    childStates.add(child4);
                }
                if (parent.isMoveRight()){
                    BlockPuzzle child2 = new BlockPuzzle();
                    child2.smileyPosition = parent.getSmileyPosition();
                    child2.initialState = parent.getCurrentState();
                    child2.currentState = child2.initialState;
                    child2.parent = parent;
                    child2.stateDepth = parent.stateDepth+1;
                    child2.moveRight();
                    childStates.add(child2);
                }

                if (parent.isMoveLeft()){
                    BlockPuzzle child1 = new BlockPuzzle();
                    child1.smileyPosition = parent.getSmileyPosition();
                    child1.initialState = parent.getCurrentState();
                    child1.currentState = child1.initialState;
                    child1.parent = parent;
                    child1.stateDepth = parent.stateDepth+1;
                    child1.moveLeft();
                    childStates.add(child1);
                }
            }
            if (n == 3){
                if (parent.isMoveUp()){
                    BlockPuzzle child3 = new BlockPuzzle();
                    child3.smileyPosition = parent.getSmileyPosition();
                    child3.initialState = parent.getCurrentState();
                    child3.currentState = child3.initialState;
                    child3.parent = parent;
                    child3.stateDepth = parent.stateDepth+1;
                    child3.moveUp();
                    childStates.add(child3);
                }
                if (parent.isMoveLeft()){
                    BlockPuzzle child1 = new BlockPuzzle();
                    child1.smileyPosition = parent.getSmileyPosition();
                    child1.initialState = parent.getCurrentState();
                    child1.currentState = child1.initialState;
                    child1.parent = parent;
                    child1.stateDepth = parent.stateDepth+1;
                    child1.moveLeft();
                    childStates.add(child1);
                }
                if (parent.isMoveDown()){
                    BlockPuzzle child4 = new BlockPuzzle();
                    child4.smileyPosition = parent.getSmileyPosition();
                    child4.initialState = parent.getCurrentState();
                    child4.currentState = child4.initialState;
                    child4.parent = parent;
                    child4.stateDepth = parent.stateDepth+1;
                    child4.moveDown();
                    childStates.add(child4);
                }
                if (parent.isMoveRight()){
                    BlockPuzzle child2 = new BlockPuzzle();
                    child2.smileyPosition = parent.getSmileyPosition();
                    child2.initialState = parent.getCurrentState();
                    child2.currentState = child2.initialState;
                    child2.parent = parent;
                    child2.stateDepth = parent.stateDepth+1;
                    child2.moveRight();
                    childStates.add(child2);
                }
            }
            if (n == 4){
                if (parent.isMoveLeft()){
                    BlockPuzzle child1 = new BlockPuzzle();
                    child1.smileyPosition = parent.getSmileyPosition();
                    child1.initialState = parent.getCurrentState();
                    child1.currentState = child1.initialState;
                    child1.parent = parent;
                    child1.stateDepth = parent.stateDepth+1;
                    child1.moveLeft();
                    childStates.add(child1);
                }
                if (parent.isMoveDown()){
                    BlockPuzzle child4 = new BlockPuzzle();
                    child4.smileyPosition = parent.getSmileyPosition();
                    child4.initialState = parent.getCurrentState();
                    child4.currentState = child4.initialState;
                    child4.parent = parent;
                    child4.stateDepth = parent.stateDepth+1;
                    child4.moveDown();
                    childStates.add(child4);
                }
                if (parent.isMoveRight()){
                    BlockPuzzle child2 = new BlockPuzzle();
                    child2.smileyPosition = parent.getSmileyPosition();
                    child2.initialState = parent.getCurrentState();
                    child2.currentState = child2.initialState;
                    child2.parent = parent;
                    child2.stateDepth = parent.stateDepth+1;
                    child2.moveRight();
                    childStates.add(child2);
                }
                if (parent.isMoveUp()){
                    BlockPuzzle child3 = new BlockPuzzle();
                    child3.smileyPosition = parent.getSmileyPosition();
                    child3.initialState = parent.getCurrentState();
                    child3.currentState = child3.initialState;
                    child3.parent = parent;
                    child3.stateDepth = parent.stateDepth+1;
                    child3.moveUp();
                    childStates.add(child3);
                }
            }

        }
        treeDepth = parent.stateDepth + 1;
        return childStates;
    }

    /**
     * Generates children for each state of the problem.  Not used in the implementation.
     * @param parent of the children that are going to be generated
     * @return LinkedList with the children states
     */
    public static LinkedList<BlockPuzzle> generateChildAStar(BlockPuzzle parent){
        LinkedList<BlockPuzzle> childStates = new LinkedList<>();

        if (parent.isMovementPossible()){
            if (parent.isMoveRight()){
                BlockPuzzle child2 = new BlockPuzzle();
                child2.smileyPosition = parent.getSmileyPosition();
                child2.initialState = parent.getCurrentState();
                child2.currentState = child2.initialState;
                child2.stateDepth = parent.stateDepth+1;
                child2.parent = parent;
                child2.g = parent.g + 1;
                child2.moveRight();
                child2.f = manhattanDistance(child2.getCurrentState()) + child2.g;
                childStates.add(child2);
            }

            if (parent.isMoveLeft()){
                BlockPuzzle child1 = new BlockPuzzle();
                child1.smileyPosition = parent.getSmileyPosition();
                child1.initialState = parent.getCurrentState();
                child1.stateDepth = parent.stateDepth+1;
                child1.currentState = child1.initialState;
                child1.parent = parent;
                child1.moveLeft();
                child1.g = parent.g + 1;
                child1.moveRight();
                child1.f = manhattanDistance(child1.getCurrentState()) + child1.g;
                childStates.add(child1);
            }
            if (parent.isMoveUp()){
                BlockPuzzle child3 = new BlockPuzzle();
                child3.smileyPosition = parent.getSmileyPosition();
                child3.initialState = parent.getCurrentState();
                child3.currentState = child3.initialState;
                child3.stateDepth = parent.stateDepth+1;
                child3.parent = parent;
                child3.moveUp();
                child3.g = parent.g + 1;
                child3.moveRight();
                child3.f = manhattanDistance(child3.getCurrentState()) + child3.g;
                childStates.add(child3);
            }
            if (parent.isMoveDown()){
                BlockPuzzle child4 = new BlockPuzzle();
                child4.smileyPosition = parent.getSmileyPosition();
                child4.initialState = parent.getCurrentState();
                child4.currentState = child4.initialState;
                child4.stateDepth = parent.stateDepth+1;
                child4.parent = parent;
                child4.moveDown();
                child4.g = parent.g + 1;
                child4.moveRight();
                child4.f = manhattanDistance(child4.getCurrentState()) + child4.g;
                childStates.add(child4);
            }
        }
        treeDepth = parent.stateDepth+1;
        return childStates;
    }

    /**
     * Generates children for each state of the problem. Takes into account the Manhattan distance
     * to the goal and sets the priority for the priority queue
     * @param parent of the children that are going to be generated
     * @return LinkedList with the children states
     */
    public static ArrayList<BlockPuzzle> generateChildAStarList(BlockPuzzle parent){
        ArrayList<BlockPuzzle> childStates = new ArrayList<>();

        if (parent.isMovementPossible()){
            if (parent.isMoveRight()){
                BlockPuzzle child2 = new BlockPuzzle();
                child2.smileyPosition = parent.getSmileyPosition();
                child2.initialState = parent.getCurrentState();
                child2.currentState = child2.initialState;
                child2.stateDepth = parent.stateDepth+1;
                child2.parent = parent;
                child2.g = parent.g + 1;
                child2.moveRight();
                child2.f = manhattanDistance(child2.getCurrentState()) + child2.g;
                childStates.add(child2);
            }

            if (parent.isMoveLeft()){
                BlockPuzzle child1 = new BlockPuzzle();
                child1.smileyPosition = parent.getSmileyPosition();
                child1.initialState = parent.getCurrentState();
                child1.stateDepth = parent.stateDepth+1;
                child1.currentState = child1.initialState;
                child1.parent = parent;
                child1.moveLeft();
                child1.g = parent.g + 1;
                child1.moveRight();
                child1.f = manhattanDistance(child1.getCurrentState()) + child1.g;
                childStates.add(child1);
            }
            if (parent.isMoveUp()){
                BlockPuzzle child3 = new BlockPuzzle();
                child3.smileyPosition = parent.getSmileyPosition();
                child3.initialState = parent.getCurrentState();
                child3.currentState = child3.initialState;
                child3.stateDepth = parent.stateDepth+1;
                child3.parent = parent;
                child3.moveUp();
                child3.g = parent.g + 1;
                child3.moveRight();
                child3.f = manhattanDistance(child3.getCurrentState()) + child3.g;
                childStates.add(child3);
            }
            if (parent.isMoveDown()){
                BlockPuzzle child4 = new BlockPuzzle();
                child4.smileyPosition = parent.getSmileyPosition();
                child4.initialState = parent.getCurrentState();
                child4.currentState = child4.initialState;
                child4.stateDepth = parent.stateDepth+1;
                child4.parent = parent;
                child4.moveDown();
                child4.g = parent.g + 1;
                child4.moveRight();
                child4.f = manhattanDistance(child4.getCurrentState()) + child4.g;
                childStates.add(child4);
            }
        }
        treeDepth = parent.stateDepth+1;
        return childStates;
    }

    /**
     * Simple method that calculates the Manhattan distance for each state
     * @param state of the puzzle
     * @return Integer that represents the Manhattan distance
     */
    private static Integer manhattanDistance(String state){
        int distance = 0;

        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == 'A'){
                distance+= Math.abs(i-5);
            }
            if (state.charAt(i) == 'B'){
                distance+=Math.abs(i-9);
            }
            if (state.charAt(i) == 'C'){
                distance+=Math.abs(i-13);
            }
        }

        return distance;
    }

    /**
     *
     * Simple boolean methods that check if movement is possible
     * @return true if possible, false if not possible
     */
    private boolean isMoveLeft(){
        return smileyPosition > 0;
    }

    private boolean isMoveRight(){
        return smileyPosition < 15;
    }

    private boolean isMoveUp(){
        return (smileyPosition <= 15 && smileyPosition >= 4);
    }

    private boolean isMoveDown(){
        return (smileyPosition <= 11 && smileyPosition >= 0);
    }

    private boolean isMovementPossible(){
        return isMoveDown() || isMoveLeft() || isMoveRight() || isMoveUp();
    }

    /**
     * Used to compare the Manhattan distances of the states for the order in the priority queue
     * @param o the second object that we compare
     * @return 1 if this.Object f > o.f, -1 if this.Object f < o.f, 0 if equal
     */
    @Override
    public int compareTo(BlockPuzzle o) {
        if (this.f > o.f){
            return 1;
        } else if (this.f < o.f){
            return -1;
        } else {
            return 0;
        }
    }
}
