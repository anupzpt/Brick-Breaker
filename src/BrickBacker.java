import javax.swing.*;
import java.awt.*;

public class BrickBacker {
    JFrame frame =new JFrame();
    GameGui panel=new GameGui();
    void BrickerFrame(){
        frame.setSize(600,600);
        frame.add(panel);
        frame.setTitle("Brick Breaker");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
