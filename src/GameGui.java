import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameGui extends JPanel implements KeyListener, ActionListener {
    private boolean play=false;
    private int score=0;
    private int totalBricks=28;
    private Timer time;
    private int delay=1;
    //for x and y axis
    private int playerX=300;
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private Map map;
    public GameGui() {
        addKeyListener(this);
        map= new Map(3,7);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time=new Timer(delay,this);
        time.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        //drawing map
        map.draw((Graphics2D)g);
        //border
        g.setColor(Color.black);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString(""+score,450,30);
        //paddle
        g.setColor(Color.blue);
        g.fillRect(playerX,550,100,8);

        //ball
        g.setColor(Color.white);
        g.fillOval(ballposX,ballposY,20,20);
        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      time.start();
      if(play){
          if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
              ballYdir=-ballYdir;
          }
        A:for(int i=0;i<map.map.length; i++){
              for(int j=0; j<map.map[0].length;j++){
                  if(map.map[i][j]>0){
                      int brickX=j*map.brickWidth+80;
                      int brickY= i* map.brickHeight+50;
                      int brickWidth= map.brickWidth;
                      int brickHeight= map.brickHeight;

                      Rectangle rect =new Rectangle(brickX,brickY,brickWidth,brickHeight);
                      Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
                      Rectangle brickRect=rect;
                      if(ballRect.intersects(brickRect)){
                          map.setBrickValue(0,i,j);
                          totalBricks--;
                          score +=5;
                          if(ballposX +19 <=brickRect.x || ballposX +1 >=brickRect.x +brickRect.width){
                              ballXdir=-ballXdir;
                          }
                          else{
                              ballYdir=-ballYdir;
                          }
                          break A;
                      }
                  }
              }
          }

          ballposX+=ballXdir;
          ballposY +=ballYdir;
          if(ballposX<0){
              ballXdir=-ballXdir;
          }
          if(ballposY<0){
              ballYdir=-ballYdir;
          }
          if(ballposX >560){
              ballXdir=-ballXdir;
          }

      }
      repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        if(playerX >=480){
            playerX=480;
        }
        else {
            moveRight();
        }
    }
    if(e.getKeyCode()==KeyEvent.VK_LEFT){
        if(playerX<=10){
            playerX=10;
        }
        else {
            moveLeft();
        }
    }
    }
    private void moveRight() {

        play =true;
        playerX +=20;
    }
    private void moveLeft() {
        play =true;
        playerX -=20;
    }




}