package mypack;
import javax.swing.*;

public class presize {

       ///Resize the given 2D array
       public static int[][] resizemine(int[][] plateau_int, int SIZE) {

              int newplateau[][] = new int[SIZE][SIZE];

              return newplateau;
       }

       public static JLabel[][] resizemine(JLabel[][] plateau, int SIZE) {

              JLabel newplateau[][] = new JLabel[SIZE][SIZE];

              return newplateau;
       }

       ///Give the size for the choosen label in the combobox
       public static int get_size(String level) {
              switch (level) {
              case "3x3":
                     return 3;

                     case "4x4":
                     return 4;
              
              case "5x5":
              return 5;
              default:
                     return 0;
              }
       }
}
