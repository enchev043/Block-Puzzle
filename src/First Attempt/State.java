public class State {

    private String state;
    private String[] movement_arr = {"up", "down", "left", "right"};
    private String movement;

    public State(String state, int movement){
        this.state = state;
        this.movement = movement_arr[movement];
    }

    public String[] getMovement_arr() {
        return movement_arr;
    }

    public void setMovement_arr(String[] movement_arr) {
        this.movement_arr = movement_arr;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }


}
