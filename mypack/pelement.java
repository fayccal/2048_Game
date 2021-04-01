package mypack;
import javax.swing.*;
import java.io.*;

public class pelement {
    
    ///Init the game board with elements
    public static void init(JLabel[][] plateau, int[][] plateau_int, int SIZE) {
        
        for(int i = 0; i < 2; i++) {
        spawn(plateau, plateau_int, SIZE);
        }
        pcolor.apply_color(plateau, plateau_int, SIZE);
    
    }
    
    ///Spawn an element on the game board
    public static void spawn(JLabel[][] plateau, int[][] plateau_int, int SIZE) {
    
        int x;
        int y;
        do {
        x = (int) (Math.random() * ((SIZE-1 - 0) +1)) + 0;
        y = (int) (Math.random() * ((SIZE-1 - 0) +1)) + 0;
        
        }while(plateau[y][x].getText() != "");
        int value = Math.random() < 0.9 ? 2 : 4;
        plateau[y][x].setText(String.valueOf(value));
        plateau_int[y][x] = value;
    }

    ///write in the file the best score
    public static void writeInFile(int best_score) {
        try {
            FileWriter fw = new FileWriter("score.txt", false);
            fw.write(String.valueOf(best_score));
            fw.close();
            } catch(IOException en) {
                en.printStackTrace();
            }
    }
}