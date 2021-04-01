package mypack;
import javax.swing.*;
import java.awt.*; 

public class pcolor {
    
    ///Aplly color to all elements
    public static void apply_color(JLabel[][] plateau, int[][] plateau_int, int SIZE) {
    
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                switch(plateau_int[i][j]) {
    
                    case 0:
                    plateau[i][j].setBackground(Color.white);
                    break;
    
                    case 2:
                    plateau[i][j].setBackground(Color.lightGray);
                    break;
    
                    case 4:
                    plateau[i][j].setBackground(Color.gray);
                    break;
    
    
                    case 8:
                    plateau[i][j].setBackground(Color.cyan);
                    break;
    
                    case 16:
                    plateau[i][j].setBackground(Color.blue);
                    break;
    
                    case 32:
                    plateau[i][j].setBackground(Color.yellow);
                    break;
    
                    case 64:
                    plateau[i][j].setBackground(Color.orange);
                    break;
                    
                    case 128:
                    plateau[i][j].setBackground(Color.red);
                    break;
    
                    case 256:
                    plateau[i][j].setBackground(Color.green);
                    break;
    
                    case 512:
                    plateau[i][j].setBackground(Color.pink);
                    break;
    
                    case 1024:
                    plateau[i][j].setBackground(new Color(160, 30, 225));
                    break;
    
                    case 2048:
                    plateau[i][j].setBackground(Color.magenta);
                    break;
                }
            }
        }
    
    }
}
