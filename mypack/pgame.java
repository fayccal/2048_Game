package mypack;
import java.io.*; 
import java.util.Scanner;
import java.awt.*;  
import java.awt.event.*;
import javax.swing.*;
  
public class pgame implements KeyListener{  

public static int SIZE = 4;
public static boolean winned = false;
public static JPanel scorePane = new JPanel();
public static JFrame f; 
public static JPanel gamePane = new JPanel();
JLabel plateau[][] = new JLabel[SIZE][SIZE]; 
int plateau_int[][] = new int[SIZE][SIZE];

public static int score = 0;
public static JLabel score_text=new JLabel("Score actuelle: " + String.valueOf(score));
public static int best_score = 0;
public static JLabel best_score_text=new JLabel("Meilleur Score: " + String.valueOf(best_score)); 

public static pmove test = new pmove(score, score_text, SIZE);
static JLabel Uwin = new JLabel("You win!", JLabel.CENTER);
static JLabel Ulose = new JLabel("You Lose!", JLabel.CENTER);

//static String levels[] = {"3x3", "4x4", "5x5"};
//static JComboBox<String> newLevel = new JComboBox<String>(levels);


public pgame(){  
    f=new JFrame();
    f.setTitle("2048");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.addKeyListener(this);
    create(plateau, plateau_int);
}  

///Create the initial game board
public static void create(JLabel[][] plateau, int[][] plateau_int) {

    JLabel spacePush = new JLabel("Press space to retry", JLabel.CENTER);
    score_text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    best_score_text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    spacePush.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    scorePane.setLayout(new BoxLayout(scorePane, BoxLayout.Y_AXIS));
    gamePane.setLayout(new GridLayout(SIZE,SIZE, 8, 8));

    ///Reading the file to get the best score
    try {
        File myobj = new File("score.txt");
        Scanner myReader = new Scanner(myobj);
        String data = myReader.nextLine();
        myReader.close();
        best_score_text.setText("Meilleur Score: " + data);
        best_score = Integer.valueOf(data);
    } catch(IOException e) {
        e.printStackTrace();
    }

    ///Adding labels to the scorePane 
    scorePane.add(score_text);
    scorePane.add(best_score_text);
    scorePane.add(spacePush);

    /*newLevel.setFocusable(false);
    newLevel.setMaximumSize(new Dimension(100, 30));

    newLevel.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            reset_Size(plateau, plateau_int);
        } 
    });

    scorePane.add(newLevel);*/

    ///Creating the game board
    for(int i = 0; i < SIZE; i++) {
        for(int j = 0; j < SIZE; j++) {
            plateau[i][j] = new JLabel("");
            plateau[i][j].setHorizontalAlignment(SwingConstants.CENTER);
            plateau[i][j].setOpaque(true);
            plateau_int[i][j] = 0;
            gamePane.add(plateau[i][j]);
        }
    }

    f.add(scorePane);
    scorePane.add(gamePane);
    f.setSize(400,400);  
    f.setVisible(true);  
    pelement.init(plateau,plateau_int, SIZE);
    
}

///Look if this is a victory and apply changes if it is
public static boolean win(JLabel[][] plateau, int[][] plateau_int) {
    
    for(int i = 0; i < SIZE; i++) {
        for(int j = 0; j < SIZE; j++) {
            ///Win condition
            if(plateau_int[i][j] == 2048) {

                ///Win message
                Uwin.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                Uwin.setFont (Uwin.getFont ().deriveFont (48.0f));
                scorePane.remove(gamePane);
                scorePane.add(Uwin);

                ///redraw the frame
                f.revalidate();
                f.repaint();

                ///Write in the file
                pelement.writeInFile(best_score);
                return true;
            }
        }
    }
    return false;
}

///Reset the game
public static void reset(JLabel[][] plateau, int[][] plateau_int) {

    winned = false;
    ///Reset the board
    for(int i = 0; i < SIZE; i++) {
        for(int j = 0; j < SIZE; j++) {
            plateau[i][j].setText("");
            plateau_int[i][j] = 0;
        }
    }
    ///Remove messages
    scorePane.remove(Uwin);
    scorePane.remove(Ulose);
    ///add again the board, init the board and reset score
    scorePane.add(gamePane);
    pelement.init(plateau,plateau_int, SIZE);
    score = 0;
    score_text.setText("Score actuelle: " + String.valueOf(score));

    f.revalidate();
    f.repaint();
}


///The reset_resize fonction which should resize the game board
/*public static void reset_Size(JLabel[][] plateau, int[][] plateau_int) {
    SIZE = presize.get_size(newLevel.getSelectedItem().toString());
    
    plateau = presize.resizemine(plateau, SIZE);
    plateau_int = presize.resizemine(plateau_int, SIZE);

    gamePane.removeAll();
    gamePane.setLayout(new GridLayout(SIZE,SIZE, 8, 8));
    for(int i = 0; i < SIZE; i++) {
        for(int j = 0; j < SIZE; j++) {
            //plateau[i][j].setText("");
            plateau[i][j] = new JLabel("");
            plateau[i][j].setHorizontalAlignment(SwingConstants.CENTER);
            plateau[i][j].setOpaque(true);
            plateau_int[i][j] = 0;
            gamePane.add(plateau[i][j]);

        }
    }
    scorePane.remove(Uwin);
    pelement.init(plateau,plateau_int, SIZE);
    score = 0;
    score_text.setText(String.valueOf(score));
    f.revalidate();
    f.repaint();
}
*/

public void keyPressed(KeyEvent e) {

    int key = e.getKeyCode();
    boolean checker;

    ///If the space bar is pressed reset the game
    if(key == KeyEvent.VK_SPACE) {
        reset(plateau, plateau_int);
        test.reset_score();
    }
    ///listen keys, apply movement and colors while we are playing
    if(!winned) {
        if (key == KeyEvent.VK_LEFT) {
            checker = test.move(plateau, 1, plateau_int);
            if(checker == true){  
                pelement.spawn(plateau, plateau_int, SIZE);
            }
        }

        if (key == KeyEvent.VK_RIGHT) {
            checker = test.move(plateau, 2, plateau_int);
            if(checker == true){  
                pelement.spawn(plateau, plateau_int, SIZE);
            }
        }

        if (key == KeyEvent.VK_UP) {
            checker = test.move(plateau, 3, plateau_int);
            if(checker == true){  
                pelement.spawn(plateau, plateau_int, SIZE);
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            checker = test.move(plateau, 4, plateau_int);
            if(checker == true){ 
                pelement.spawn(plateau, plateau_int, SIZE); 
            }
        }
        pcolor.apply_color(plateau, plateau_int, SIZE);
    }

    if(test.get_score() > best_score) {
        best_score_text.setText("Meilleur Score: " + String.valueOf(test.get_score()));
        best_score = test.get_score();
    }

    if(win(plateau, plateau_int)) {
        winned = true;
    }

    if(over(plateau, plateau_int)) {

        pelement.writeInFile(best_score);
        Ulose.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                Ulose.setFont(Ulose.getFont ().deriveFont (48.0f));
                scorePane.remove(gamePane);
                scorePane.add(Ulose);

                f.revalidate();
                f.repaint();
    }
}

public void keyTyped(KeyEvent e) {

}

public void keyReleased(KeyEvent e) {

}

///Look if it is a game over
public static boolean over(JLabel[][] plateau, int[][] plateau_int) {
    int atLeast = 0;

    for(int y = 0; y < SIZE; y++) {
        for(int x = 0; x < SIZE; x++) {
            if(plateau_int[x][y] == 0) {
                atLeast += 1;
            }
            if(atLeast != 0) {
                return false;
            }
        }
    }

    if(test.stillPlayable(plateau, plateau_int)) {
        return false;
    }

    return true;
}

}