import java.util.Stack;

/**
 *
 * Test class for IDS.
 *
 */

public class IDS {
    private static final String goalState = "ABC";
    private static int steps = 0;

    public static void main(String[] args){
        BlockPuzzle blockPuzzle = new BlockPuzzle();
        Iterative_Deepening_Search(blockPuzzle);
    }

    private static boolean isGoalState(String currentState){

        return (("" + currentState.charAt(5)+currentState.charAt(9)+currentState.charAt(13)).equals(goalState));
    }

    private static boolean Depth_Limited_Search(BlockPuzzle problem, int limit) {

        Stack<BlockPuzzle> fringe = new Stack<>();

        boolean cut_off = false;

        fringe.push(problem);
        while (fringe.size() != 0) {
            BlockPuzzle parent = fringe.pop();
            if (isGoalState(parent.getCurrentState())) {
                System.out.println();
                System.out.println("Goal reached: " + parent.getCurrentState());
                System.out.println("Depth: " + parent.getStateDepth());
                System.out.println("Steps: " + steps);
                cut_off = true;
                break;
            }

            if (parent.getStateDepth() == limit) {
                continue;
            } else {
                for (BlockPuzzle i : BlockPuzzle.generateChildDfs(parent)) {
                    fringe.push(i);
                    steps++;
                }
            }
        }
        return cut_off;
    }

    private static void Iterative_Deepening_Search(BlockPuzzle problem){
        boolean cut_off = false;
        int depth = 0;

        while (!cut_off){
            cut_off = Depth_Limited_Search(problem, depth);
            depth+=1;
        }
    }

}
