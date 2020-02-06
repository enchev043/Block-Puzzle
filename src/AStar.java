import java.util.*;

/**
 * Test class for A*.
 */

public class AStar {

    private static final String goalState = "ABC";
    private static int steps = 0;

    public static void main(String[] args){
        BlockPuzzle blockPuzzle = new BlockPuzzle();
        AStar(blockPuzzle);

    }

    public static void AStar(BlockPuzzle problem){

        PriorityQueue<BlockPuzzle> pQueue = new PriorityQueue<BlockPuzzle>();
        ArrayList<BlockPuzzle> children;

        pQueue.add(problem);
        int step = 0;
        int states = 1;

        while (pQueue.size() != 0){
            BlockPuzzle parent = pQueue.poll();
            System.out.println(parent.getF() + " " + parent.getParent().getCurrentState() + " Level: " + parent.getStateDepth());
            step++;

            if ((isGoalState(parent.getCurrentState()))){
                System.out.println();
                System.out.println("Steps: " + step);
                System.out.println("Goal at depth: " + parent.getStateDepth());
                System.out.println("Queue size: " + pQueue.size());
                System.out.println("Goal state: " + parent.getCurrentState());
                System.out.println("States: " + states);
                pQueue.clear();
                break;

            } else {
                for (int i = 0; i < BlockPuzzle.generateChildAStarList(parent).size(); i++) {
                    children = BlockPuzzle.generateChildAStarList(parent);
                    pQueue.add(children.get(i));
                    states++;

                }
            }

        }

        /*
        while (pQueue.size() != 0){
            BlockPuzzle parent = pQueue.poll();
            //closed.add(parent);

            Iterator<BlockPuzzle> i = BlockPuzzle.generateChildAStar(parent).listIterator();

            while (i.hasNext()){

                BlockPuzzle node = i.next();

                if ((isGoalState(node.getCurrentState()))){
                    System.out.println();
                    System.out.println("Steps: " + step);
                    System.out.println("Goal at depth: " + node.getStateDepth());
                    System.out.println("Queue size: " + pQueue.size());
                    System.out.println("Goal state: " + node.getCurrentState());
                    pQueue.clear();
                    break;

                } else {
                    step++;
                    System.out.println(node.getCurrentState());
                    pQueue.add(node);
                }
            }
        }
        */




    }

    private static boolean isGoalState(String currentState){

        return (("" + currentState.charAt(5)+currentState.charAt(9)+currentState.charAt(13)).equals(goalState));
    }
}
