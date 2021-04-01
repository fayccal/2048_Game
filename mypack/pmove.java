package mypack;
import javax.swing.*;
import java.util.Arrays; 

public class pmove {

    private int score;
    private JLabel score_text;
    private int SIZE;


    public pmove(int score, JLabel score_text, int SIZE) {
        this.score = score;
        this.score_text = score_text;
        this.SIZE = SIZE;
    }

        ///The movement method for the pressed key
        ///return true if any movement have been made
        public boolean move(JLabel[][] plateau, int pressed, int[][] plateau_int) {
    
            int [][]check_plateau = new int[plateau_int.length][];
            for(int i = 0; i <plateau_int.length; i++) {
                check_plateau[i] = plateau_int[i].clone();
            }
        switch(pressed) {
            case 1:///left
                for(int y = 0; y < SIZE; y++) {
                    for(int x = 0; x < SIZE; x++) {
                        int checkX = x, checkY = y;
                        int saved_value = plateau_int[y][x];

                            if(plateau_int[y][x] != 0){ 
                                checkX -= 1;
                                ///while there is a movement possible
                                while(checkX > -1 && (plateau_int[checkY][checkX] == 0 || plateau_int[checkY][checkX] == saved_value)) {

                                    makeTheMove(plateau, plateau_int, checkX, checkY, saved_value);

                                    plateau_int[checkY][checkX+1]= 0;
                                    if(plateau_int[checkY][checkX] == 0) {
                                        plateau[checkY][checkX].setText("");
                                    }
                                    else {
                                    plateau[checkY][checkX].setText(String.valueOf(plateau_int[checkY][checkX]));
                                    }

                                    plateau[checkY][checkX+1].setText("");
                                    checkX -= 1;
                                }
                            }
                    }
                }
                break;

            case 2:///right
                for(int y = 0; y < SIZE; y++) {
                    for(int x = SIZE-1; x >= 0 ; x--) {
                        int checkX = x, checkY = y;
                        int saved_value = plateau_int[y][x];

                            if(plateau[y][x].getText() != ""){ 
                                checkX += 1;
                                while(checkX < SIZE && (plateau_int[checkY][checkX] == 0 || plateau_int[checkY][checkX] == saved_value)) {
                                    
                                    makeTheMove(plateau, plateau_int, checkX, checkY, saved_value);
                                    
                                    plateau_int[checkY][checkX-1]= 0;
                                    if(plateau_int[checkY][checkX] == 0) {
                                        plateau[checkY][checkX].setText("");
                                    }
                                    else {
                                    plateau[checkY][checkX].setText(String.valueOf(plateau_int[checkY][checkX]));
                                    }
                                    plateau[checkY][checkX-1].setText("");
                                    checkX += 1;
                                }
                            }
                    }
                }

                break;

            case 3:///upper
                for(int x = 0; x < SIZE; x++) {
                    for(int y = 0; y < SIZE; y++) {
                        int checkX = x, checkY = y;
                        int saved_value = plateau_int[y][x];

                        if(plateau_int[y][x] != 0){ 
                            checkY -= 1;
                            while(checkY > -1 &&(plateau_int[checkY][checkX] == 0 || plateau_int[checkY][checkX] == saved_value)) {
                                
                                makeTheMove(plateau, plateau_int, checkX, checkY, saved_value);

                                plateau_int[checkY+1][checkX]= 0;
                                if(plateau_int[checkY][checkX] == 0) {
                                    plateau[checkY][checkX].setText("");
                                }
                                else {
                                plateau[checkY][checkX].setText(String.valueOf(plateau_int[checkY][checkX]));
                                }

                                plateau[checkY+1][checkX].setText("");
                                checkY -= 1;
                            }
                        }
                    }
                }
                break;

            case 4:///bottom

                for(int x = 0; x < SIZE; x++) {
                    for(int y = SIZE-1; y >= 0; y--) {
                        int checkX = x, checkY = y;
                        int saved_value = plateau_int[y][x];

                        if(plateau_int[y][x] != 0){ 
                            checkY += 1;
                            while(checkY < SIZE && (plateau_int[checkY][checkX] == 0 || plateau_int[checkY][checkX] == saved_value)) {

                                makeTheMove(plateau, plateau_int, checkX, checkY, saved_value);

                                plateau_int[checkY-1][checkX]= 0;
                                if(plateau_int[checkY][checkX] == 0) {
                                    plateau[checkY][checkX].setText("");
                                }
                                else {
                                plateau[checkY][checkX].setText(String.valueOf(plateau_int[checkY][checkX]));
                                }
                                plateau[checkY-1][checkX].setText("");
                                checkY += 1; 
                            }
                        }
                    }
                }
                break;
        }

        if(Arrays.deepEquals(plateau_int, check_plateau)) {
            return false;
        }
        else {
            return true;
        }
    }

    ///make the moves in the move method
    public void makeTheMove(JLabel[][] plateau, int[][] plateau_int, int checkX, int checkY, int saved_value) {

        if(plateau_int[checkY][checkX] == saved_value) {
            plateau_int[checkY][checkX] = saved_value * 2;
            score += saved_value * 2;
            score_text.setText("Score actuelle: " + String.valueOf(score));
        }
        else {
            plateau_int[checkY][checkX] = saved_value;
        }
    
    }

    ///A method to check if the is there still some movements possible
    public boolean stillPlayable(JLabel[][] plateau, int[][] plateau_int) {

        for(int y = 0; y < SIZE; y++) {
            for(int x = 0; x < SIZE; x++) {
                int checkX = x, checkY = y;
                int saved_value = plateau_int[y][x];

                if(plateau_int[y][x] != 0){ 
                    checkX -= 1;
                    if(checkX > -1 && (plateau_int[checkY][checkX] == 0 || plateau_int[checkY][checkX] == saved_value)) {
                        return true;
                    }
                }
            }
        }

        for(int y = 0; y < SIZE; y++) {
            for(int x = SIZE-1; x >= 0 ; x--) {
                int checkX = x, checkY = y;
                int saved_value = plateau_int[y][x];

                if(plateau[y][x].getText() != ""){ 
                    checkX += 1;
                    if(checkX < SIZE && (plateau_int[checkY][checkX] == 0 || plateau_int[checkY][checkX] == saved_value)) {
                        return true;
                    }
                }
            }
        }

        for(int x = 0; x < SIZE; x++) {
            for(int y = 0; y < SIZE; y++) {
                int checkX = x, checkY = y;
                int saved_value = plateau_int[y][x];

                if(plateau_int[y][x] != 0){ 
                    checkY -= 1;
                    if(checkY > -1 &&(plateau_int[checkY][checkX] == 0 || plateau_int[checkY][checkX] == saved_value)) {
                        return true;
                    }
                }
            }
        }

        for(int x = 0; x < SIZE; x++) {
            for(int y = SIZE-1; y >= 0; y--) {
                int checkX = x, checkY = y;
                int saved_value = plateau_int[y][x];

                if(plateau_int[y][x] != 0){ 
                    checkY += 1;
                    if(checkY < SIZE && (plateau_int[checkY][checkX] == 0 || plateau_int[checkY][checkX] == saved_value)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    ///Geter
    public int get_score() {
        return score;
    }

    ///Reseter
    public void reset_score() {
        this.score = 0;
    }
  
}