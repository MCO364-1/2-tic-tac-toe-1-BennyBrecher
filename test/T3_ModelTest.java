import static org.junit.jupiter.api.Assertions.*;
class T3_ModelTest { //TODO this is just class notes, add soon
    @org.junit.jupiter.api.Test
    void currentPlayer() {
        T3_Model model = new T3_Model();
        T3_Model.Player actual = model.currentPlayer();
        assertEquals(T3_Model.Player.X, actual);

        model.switchPlayer();
        assertEquals(T3_Model.Player.O, model.currentPlayer());

        model.makeMove(0,0);
        assertEquals(T3_Model.Player.X, model.currentPlayer());
    }

    @org.junit.jupiter.api.Test
    void makeMove() {
        T3_Model model = new T3_Model();
        model.makeMove(0,0);
        assertEquals(T3_Model.Player.X, model.getPosition(0,0));
        assertEquals(T3_Model.Player.O, model.currentPlayer()); //checks player switches properly after move
    }

    @org.junit.jupiter.api.Test
    void makeMoveOutOfBounds() {
        T3_Model model = new T3_Model();
        boolean positiveExceptionThrown = false;
        try{
            model.makeMove(0,3);
        }
        catch(IllegalArgumentException e){
            positiveExceptionThrown = true;
        }
        if(!positiveExceptionThrown){
            fail("Make Move Failed To Throw Exception When Out Of Bounds");
        }

        boolean negativeExceptionThrown = false;
        try{
            model.makeMove(-1,0);
        }
        catch(IllegalArgumentException e){
            negativeExceptionThrown = true;
        }
        if(!negativeExceptionThrown){
            fail("Make Move Failed To Throw Exception When Out Of Bounds");
        }
    }

    @org.junit.jupiter.api.Test
    void makeMoveNotVacant() {
        T3_Model model = new T3_Model();
        model.makeMove(0,0);
        assertEquals(T3_Model.Player.X, model.getPosition(0,0));
        assertEquals(T3_Model.Player.O, model.currentPlayer());
        model.makeMove(0,0);
        assertEquals(T3_Model.Player.X, model.getPosition(0,0));
        assertEquals(T3_Model.Player.O, model.currentPlayer());
        //even though it was O's turn and makeMove was tried, it didn't affect game logic or change board, instead it waits for O to choose again
    }

    @org.junit.jupiter.api.Test
    void rowWin(){
        T3_Model model = new T3_Model();
        assertEquals(T3_Model.Player.__, model.getWinner());
        model.makeMove(0,0); //X
        model.makeMove(1,0); //O
        model.makeMove(0,1); //X
        model.makeMove(1,1); //O
        model.makeMove(0,2); //X
        assertEquals(T3_Model.Player.X, model.getWinner());
        assertEquals(1, model.xWins);
    }

    @org.junit.jupiter.api.Test
    void collumnWin(){
        T3_Model model = new T3_Model();
        assertEquals(T3_Model.Player.__, model.getWinner());
        model.makeMove(0,0); //X
        model.makeMove(0,1); //O
        model.makeMove(1,0); //X
        model.makeMove(1,1); //O
        model.makeMove(2,0); //X
        assertEquals(T3_Model.Player.X, model.getWinner());
        assertEquals(1, model.xWins);
    }

    @org.junit.jupiter.api.Test
    void diagonalWin(){
        T3_Model model = new T3_Model();
        assertEquals(T3_Model.Player.__, model.getWinner());
        model.makeMove(0,0); //X
        model.makeMove(0,2); //O
        model.makeMove(0,1); //X
        model.makeMove(1,1); //O
        model.makeMove(1,0); //X
        model.makeMove(2,0); //O
        assertEquals(T3_Model.Player.O, model.getWinner()); //I felt bad for O so i let him win for once
        assertEquals(1, model.oWins);
    }

    @org.junit.jupiter.api.Test
    void fullBoardDraw(){
        T3_Model model = new T3_Model();
        assertFalse(model.isDraw);
        assertFalse(model.isFull());
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(0, 2); // X
        model.makeMove(1, 1); // O
        model.makeMove(1, 0); // X
        model.makeMove(1, 2); // O
        model.makeMove(2, 1); // X
        model.makeMove(2, 0); // O
        model.makeMove(2, 2); // X
        assertTrue(model.isDraw);
        assertTrue(model.isFull());
        assertEquals(T3_Model.Player.__, model.getWinner());
        assertEquals(0, model.xWins);
        assertEquals(0, model.oWins);
    }
}