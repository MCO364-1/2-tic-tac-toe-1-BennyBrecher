import static org.junit.jupiter.api.Assertions.*;
class T3_ModelTest { //TODO this is just class notes, add soon
    private T3_Model model = new T3_Model();

   /*
   @org.junit.jupiter.api.Test
    void getPosition() {
        T3_Model.Player actual = model.getPosition(0, 0);
        assertEquals(T3_Model.Player.__, actual);
    }
    */

    @org.junit.jupiter.api.Test
    void currentPlayer() {
        T3_Model.Player actual = model.currentPlayer();
        assertEquals(T3_Model.Player.X, actual);
    }

    @org.junit.jupiter.api.Test
    void makeMove() {
    }

    @org.junit.jupiter.api.Test
    void makeMoveOutOfBounds() {
        boolean exceptionThrown = false;
        try{
            model.makeMove(0,3);
        }
        catch(IllegalArgumentException e){
            exceptionThrown = true;
        }
        if(!exceptionThrown){
            fail("Make Move Failed To Throw Eception When Out Of Bounds");
        }
    }

    @org.junit.jupiter.api.Test
    void makeMoveNotVacant() {
    }

    @org.junit.jupiter.api.Test
    void getWinner() {
    }
}