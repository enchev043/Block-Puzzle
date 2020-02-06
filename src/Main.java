import java.util.*;

/**
 * Main class with all algorithms. To test an algorithm, just "comment it out" and try it.
 * To test another one, comment all other algorithms.
 */
public class Main {

    private static final String goalState = "ABC";
    private static int steps = 0;

    public static void main(String[] args){
        BlockPuzzle blockPuzzle = new BlockPuzzle();
        //bfs(blockPuzzle);
        //dfs(blockPuzzle);
        Iterative_Deepening_Search(blockPuzzle);
        //AStar(blockPuzzle);

    }

    public static boolean isGoalState(String currentState){
        return (("" + currentState.charAt(5)+currentState.charAt(9)+currentState.charAt(13)).equals(goalState));
    }

    public static void bfs(BlockPuzzle initialState){
        LinkedList<BlockPuzzle> queue = new LinkedList<BlockPuzzle>();
        queue.add(initialState);

        int steps = 0;

        while (queue.size() != 0){
            BlockPuzzle state = queue.poll();

            Iterator<BlockPuzzle> i = BlockPuzzle.generateChildBfs(state).listIterator();

            while (i.hasNext()){

                BlockPuzzle node = i.next();

                if (!(isGoalState(node.getCurrentState()))){
                    queue.add(node);
                    //System.out.println(node.getCurrentState());
                    steps++;

                } else {
                    System.out.println();
                    System.out.println("Steps: " + steps);
                    System.out.println("Goal at depth: " + node.getStateDepth());
                    System.out.println("Queue size: " + queue.size());
                    System.out.println("Goal state: " + node.getCurrentState());
                    queue = new LinkedList<>();
                    break;

                }
            }
        }

    }

    public static void dfs(BlockPuzzle initialState){
        Stack<BlockPuzzle> stack = new Stack<>();
        stack.push(initialState);

        int steps = 0;

        while (stack.size() != 0){
            BlockPuzzle state = stack.pop();

            Iterator<BlockPuzzle> i = BlockPuzzle.generateChildDfs(state).listIterator();

            while (i.hasNext()){
                BlockPuzzle node = i.next();
                if (!(isGoalState(node.getCurrentState()))){
                    stack.push(node);
                    steps++;

                } else {
                    System.out.println();
                    System.out.println("Steps: " + steps);
                    System.out.println("Goal at depth: " + node.getStateDepth());
                    System.out.println("Stack size: " + stack.size());
                    System.out.println("Goal state: " + node.getCurrentState());
                    stack = new Stack<>();
                    break;

                }

            }

        }

    }

    public static boolean Depth_Limited_Search(BlockPuzzle problem, int limit) {
        Stack<BlockPuzzle> fringe = new Stack<>();
        fringe.push(problem);

        boolean cut_off = false;

        while (fringe.size() != 0) {
            BlockPuzzle parent = fringe.pop();
            if (isGoalState(parent.getCurrentState())) {
                System.out.println();
                System.out.println("Steps: " + steps);
                System.out.println("Goal at depth: " + parent.getStateDepth());
                System.out.println("Stack size: " + fringe.size());
                System.out.println("Goal state: " + parent.getCurrentState());
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

    public static void Iterative_Deepening_Search(BlockPuzzle problem){
        boolean cut_off = false;
        int depth = 0;

        while (!cut_off){
            cut_off = Depth_Limited_Search(problem, depth);
            depth+=1;
        }
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

    }
}
