package tictactoe;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class Controller {
    static class Move
    {
        int row, col;
    };
    private boolean isFirstPlayer = true;
    private boolean computer = true;

    String board[][];

    @FXML
    Button b1;
    @FXML
    Button b2;
    @FXML
    Button b3;
    @FXML
    Button b4;
    @FXML
    Button b5;
    @FXML
    Button b6;
    @FXML
    Button b7;
    @FXML
    Button b8;
    @FXML
    Button b9;

    @FXML
    GridPane gameBoard;

    public void buttonClickHandler(ActionEvent evt) {

        Button clickedButton = (Button) evt.getTarget();
        String buttonLabel = clickedButton.getText();

        if ("".equals(buttonLabel) && isFirstPlayer) {
            clickedButton.setText("X");
            isFirstPlayer = false;
            find3InARow();
            if (computer) {
                computerAi();
            }
        }else{
            clickedButton.setText("O");
            isFirstPlayer = true;
            find3InARow();
        }
    }

    public int computerAi() {
        isFirstPlayer = true;
        MiniMax.Move bestMove;
        char board[][] = new char[3][3];
        int e=-1,f=0;
        Node btn = null;
        char cache;
        ObservableList<Node> buttons = gameBoard.getChildren();

        for (int i=0;i<9;i++){

            if (e==2) {
                e = 0;
                f++;
            }else {
                e++;
            }
            //System.out.println("e: " + e + ", f: " + f);
            btn = buttons.get(i);
            try {
                cache = ((Button) btn).getText().charAt(0);
            }catch (Exception exception1) {
                cache = ' ';
            }
            //System.out.println(cache);
            board[f][e] = cache;
        }

        System.out.println(Arrays.deepToString(board));

        bestMove = MiniMax.findBestMove(board);
        System.out.println(bestMove.col + ", " + bestMove.row);

        if (bestMove.row == 0){
            btn = buttons.get(bestMove.col);
        }else if (bestMove.row == 1){
            btn = buttons.get(bestMove.col + 3);
        }else if (bestMove.row == 2){
            btn = buttons.get(bestMove.col + 6);
        }else return -1;

        ((Button) btn).setText("O");
        find3InARow();
        return 0;
    }


    private boolean find3InARow() {
        //Row 1
        if ("" != b1.getText() && b1.getText() == b2.getText()
                && b2.getText() == b3.getText()) {
            highlightWinningCombo(b1, b2, b3);
            return true;
        }
        //Row 2
        if ("" != b4.getText() && b4.getText() == b5.getText()
                && b5.getText() == b6.getText()) {
            highlightWinningCombo(b4, b5, b6);
            return true;
        }
        //Row 3
        if ("" != b7.getText() && b7.getText() == b8.getText()
                && b8.getText() == b9.getText()) {
            highlightWinningCombo(b7, b8, b9);
            return true;
        }
        //Column 1
        if ("" != b1.getText() && b1.getText() == b4.getText()
                && b4.getText() == b7.getText()) {
            highlightWinningCombo(b1, b4, b7);
            return true;
        }
        //Column 2
        if ("" != b2.getText() && b2.getText() == b5.getText()
                && b5.getText() == b8.getText()) {
            highlightWinningCombo(b2, b5, b8);
            return true;
        }
        //Column 3
        if ("" != b3.getText() && b3.getText() == b6.getText()
                && b6.getText() == b9.getText()) {
            highlightWinningCombo(b3, b6, b9);
            return true;
        }
        //Diagonal 1
        if ("" != b1.getText() && b1.getText() == b5.getText()
                && b5.getText() == b9.getText()) {
            highlightWinningCombo(b1, b5, b9);
            return true;
        }
        //Diagonal 2
        if ("" != b3.getText() && b3.getText() == b5.getText()
                && b5.getText() == b7.getText()) {
            highlightWinningCombo(b3, b5, b7);
            return true;
        }
        return false;
    }

    private void highlightWinningCombo(Button first, Button second, Button third) {
        first.getStyleClass().add("winning-square");
        second.getStyleClass().add("winning-square");
        third.getStyleClass().add("winning-square");
    }


    public void menuClickHandler(ActionEvent evt) {

        MenuItem clickedMenu = (MenuItem) evt.getTarget();
        String menuLabel = clickedMenu.getText();
        System.out.println("Got the menulabel: " + menuLabel);

        switch (menuLabel){
            case "Play/Restore":
                isFirstPlayer = true;
                reset();
                break;
            case "Quit":
                System.exit(1);
                break;
            case "Multiplayer":
                computer = false;
                reset();
                break;
            case "Computer":
                computer = true;
                reset();
                break;
        }
    }

    public void reset(){
        ObservableList<Node> buttons = gameBoard.getChildren();

        buttons.forEach(btn -> {
            btn.getStyleClass().remove("winning-square");
            ((Button) btn).setText("");
        });
    }

}