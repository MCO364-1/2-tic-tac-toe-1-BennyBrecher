public class T3_Model{ //The M of our MVC model (model doesn't reference View/Control)
    public enum Player{       //when __ refers to a board slot it means empty,
        X, O, __            //when __ refers to a winner it means none OR a draw when verified by isFull()
    }
    private Player currentPlayer;
    private Player[][] board = new Player[3][3];
    public int xWins, oWins, numGames;
    public boolean isDraw;
    public Player winner;

    public T3_Model(){ //unit test if its safe to not have "isDraw = false" in ctor since freshBoard() already does it
        numGames = 0;
        freshBoard();
        xWins = 0;
        oWins = 0;
        winner = Player.__; //unit test if instantiating base model as __ can somehow trigger false draw
    }
    public void freshBoard(){
        numGames++;
        isDraw = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Player.__; //reinstantiate every board spot as empty
            }
        }
        currentPlayer = Player.X; //default to have X start
    }
    public boolean isFull(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == Player.__){ //as long as there is one empty slot board will never be full
                    return false;
                }
            }
        }
        return true;
    }

    public Player currentPlayer(){
        return currentPlayer;
    }

    public Player getPosition(int i, int j){ //added for testing makeMove();
        if((0 > i || i > 2)||(0>j || j>2)){ //keeps i/j input within bounds of 0-2
            throw new IllegalArgumentException("Invalid board index");
        }
        return board[i][j];
    }

    public void switchPlayer(){ //can this be a shorter ternary statement?
        if(currentPlayer == Player.X){
            currentPlayer = Player.O;
        }else{
            currentPlayer = Player.X;
        }
    }

    public void makeMove(int i, int j) {
        if((0 > i || i > 2)||(0>j || j>2)){ //check bounds for args
            throw new IllegalArgumentException("Invalid board index");
        }
        if (board[i][j] == Player.__) {               //if the given spot is empty
            board[i][j] = currentPlayer();           //place the current player's enum symbol
            winner = getWinner();                   //check for a winner and store getWinner() result so we don't need to check entire board 3x
            if (winner == Player.__ && isFull()) { //if no winner is found and board is full
                isDraw = true;
                return;
            }
            if(winner == Player.__){ //if winner isn't X or O (board not empty)
                switchPlayer();     //swap turns and carry on
                return;
            }
            if(winner == Player.X){
                xWins++;
            } else if (winner == Player.O) {
                oWins++;
            }
        }
    }
    public Player getWinner() {
        // Check rows
        if (board[0][0] != Player.__ && board[0][0] == board[0][1] && board[0][1] == board[0][2]) return board[0][0];
        if (board[1][0] != Player.__ && board[1][0] == board[1][1] && board[1][1] == board[1][2]) return board[1][0];
        if (board[2][0] != Player.__ && board[2][0] == board[2][1] && board[2][1] == board[2][2]) return board[2][0];

        // Check columns
        if (board[0][0] != Player.__ && board[0][0] == board[1][0] && board[1][0] == board[2][0]) return board[0][0];
        if (board[0][1] != Player.__ && board[0][1] == board[1][1] && board[1][1] == board[2][1]) return board[0][1];
        if (board[0][2] != Player.__ && board[0][2] == board[1][2] && board[1][2] == board[2][2]) return board[0][2];

        // Check diagonals
        if (board[0][0] != Player.__ && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return board[0][0];
        if (board[0][2] != Player.__ && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return board[0][2];

        // No winner found (could be draw so always check for that case by calling this with isFull() )
        return Player.__; //only means no winner found, can't be sure if draw until check for isFull() as well
    }

}
