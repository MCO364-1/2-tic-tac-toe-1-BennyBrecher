/*TODO                  Agenda:
   1) announcement label will stay on Draw! or O won! until changed
   so after every reset(); we must increment numGames and display instead
TODO
   2)gain clarity on button disabling and implement when needed
* */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class T3_ViewControl extends JFrame { //The VC of Our MVC model

    private T3_Model model;
    private JLabel whosTurn;
    private JPanel t3Panel;
    private JLabel announcement;
    private JLabel xWins;
    private JLabel oWins;

    public T3_ViewControl(){
        model = new T3_Model(); //Frame knows about and references model, but model doesn't ref gui
        setTitle("Tic-Tac-Toe");
        setSize(500, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel topPanel = new JPanel(new GridLayout(1,3));
        JButton newGameButton = new JButton("New Game");
        topPanel.add(newGameButton);
        whosTurn = new JLabel(String.format("It's Now %s's Turn", model.currentPlayer()));
        whosTurn.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(whosTurn);
        JButton undoMove = new JButton("Undo Last Move"); //TODO Next HW I will implement a Stack for game state reversal
        topPanel.add(undoMove);




        t3Panel = new JPanel();
        t3Panel.setLayout(new GridLayout(3,3,5,5));
        resetGridGUI();



        JPanel statusPanel = new JPanel(new GridLayout());
        xWins = new JLabel(String.format("X Wins: %d", model.xWins));
        xWins.setHorizontalAlignment(SwingConstants.RIGHT);
        announcement = new JLabel("Welcome To Tic-Tac-Toe"); //make this use model.numGames and model.winner
        announcement.setHorizontalAlignment(SwingConstants.CENTER);
        oWins = new JLabel(String.format("O Wins: %d", model.oWins));
        oWins.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(xWins);
        statusPanel.add(announcement);
        statusPanel.add(oWins);


        add(topPanel, BorderLayout.NORTH);
        add(t3Panel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);


        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        setVisible(true);
    }

    public void startNewGame(){
        model.freshBoard();
        t3Panel.removeAll();
        resetGridGUI();
        t3Panel.revalidate();
        t3Panel.repaint();
        whosTurn.setText(String.format("It's Now %s's Turn", model.currentPlayer()));
        xWins.setText(String.format("X Wins: %d", model.xWins));
        oWins.setText(String.format("O Wins: %d", model.oWins));
        if(model.numGames > 1){ //replace the welcome with a round count after round 1
            announcement.setText(String.format("Round #%d", model.numGames));
        }
    }

    public void resetGridGUI(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton playableBox = new JButton();
                t3Panel.add(playableBox);
                int finalI = i;
                int finalJ = j;
                playableBox.addActionListener(new ActionListener() { //listens for all buttons that make up our grid
                    public void actionPerformed(ActionEvent e) {

                        playableBox.setFont(new Font("Arial", Font.PLAIN, 150));
                        playableBox.setText(model.currentPlayer().toString()); //make sure to set text before calling makeMove() since makeMove() swaps current symbol causing illogical misprint
                        model.makeMove(finalI,finalJ);
                        playableBox.setEnabled(false);
                        whosTurn.setText(String.format("It's Now %s's Turn", model.currentPlayer()));

                        if(model.isDraw){
                            announcement.setText("It's a Draw!");
                            int choice = JOptionPane.showConfirmDialog(null, "Draw! Play Again?","Draw!", JOptionPane.YES_NO_OPTION);
                            if(choice == JOptionPane.YES_OPTION){
                                startNewGame();
                            }else{
                                dispose();
                            }
                        }

                        if(model.winner != T3_Model.Player.__){ //if winner is X or O
                            announcement.setText(String.format("%s HAS WON!!!", model.winner));
                            int choice = JOptionPane.showConfirmDialog(null, "Victory! Play Again?","Victory!", JOptionPane.YES_NO_OPTION);
                            if(choice == JOptionPane.YES_OPTION){
                                startNewGame();
                            }else{
                                dispose();
                            }
                        }
                    }
                });
            }
        }
    }
}
