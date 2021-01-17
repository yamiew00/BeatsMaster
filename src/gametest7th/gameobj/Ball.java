
package gametest7th.gameobj;

import gametest7th.controllers.SceneController;
import gametest7th.utils.Delay;
import gametest7th.utils.Global;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;


public class Ball extends GameObject{
        private Image img;
        private Delay delay = new Delay(20);
        private int speed = 0;
        
        private int destinationX;
        private int destinationY;
        
        private int player;
        
        //測試
        Transparent transparent;
        private boolean stay = false;
        Transparent transparent2;
        private boolean reach = false;
    public Ball (Global.Player player){
        super(0,0,0,0);
        
        img = SceneController.instance().irc().tryGetImage(new Path().img().objs().ball());
        delay.loop();
        
        switch(player){
            case PLAYER1:
                Rect rect1 = new Rect ((Global.SCREEN_X - 500 - 100) + 500 / 2 - 20 / 2
                                    ,825 + 50
                                    ,(Global.SCREEN_X - 500 - 100) + 500 / 2 - 20 / 2 + 20
                                    ,825 + 50 + 20);
                super.painter().setRect(rect1);
                super.collider().setRect(rect1);
                this.player = 1;
                destinationX = 2000;
                destinationY = 450;
                transparent = new Transparent(new Rect(1600,450,1610 + 5 ,450 + 5));
                transparent2 = new Transparent(new Rect(350, 0,350 + 5 ,0 + 5));
                break;
            case PLAYER2:
                Rect rect2 = new Rect ( 340
                                    ,825 + 50
                                    ,340 + 20
                                    ,825 + 50 + 20);
                super.painter().setRect(rect2);
                super.collider().setRect(rect2);
                destinationX = -350;
                destinationY = 450;
                transparent = new Transparent(new Rect(50       ,450
                                                 , 50 + 5   ,450 + 5));
                transparent2 = new Transparent(new Rect (1310    , 0
                                                   ,1310 + 5, 0 + 5));
                this.player = 2;
                break;
        }
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, super.painter().left(),  super.painter().top() ,super.painter().width(),super.painter().height(), null);
    }

    @Override
    public void update() {
        switch(player){
            case 1:
                update1();
                break;
            case 2:
                update2();
                break;
        }
        
        
        
        
        
    }
    
    public boolean getReach(){
        return reach;
    }
    
    private void translate(){
        int vectorX = destinationX - super.painter().left();
        int vectorY = destinationY - super.painter().top();

        double initX = ( vectorX * (1 / ( Math.sqrt(Math.pow(vectorX, 2)) + Math.sqrt(Math.pow(vectorY, 2) ) )) );
        double initY = ( vectorY * (1 / ( Math.sqrt(Math.pow(vectorX, 2)) + Math.sqrt(Math.pow(vectorY, 2) ) )) );

        super.translate((int)( speed * initX) , (int) ( speed * initY) );
    }
    private void update1(){
        if (!stay){
            if (super.isCollision(transparent)){
                speed = 4;
                stay = true;
                destinationX = 2050;
                destinationY = 0;
                return;
            }
            if (destinationX > 1600 ){
                destinationX -= 10;
            }
            if (speed < 16){
                speed +=2;
            }
            translate();
        }
        
        if (!reach && stay){
            if (super.isCollision(transparent2)){
                speed = 0;
                reach = true;
                return;
            }
            if (speed < 30){
                speed +=6;
            }
            if (destinationX >350 ){
                destinationX -= 100;
            }
            
            if(super.painter().width() <= 60){
                super.painter().enlarge(1);
                super.collider().enlarge(1);
            }
            translate();
        }
    }
    
    private void update2(){
        if (!stay){
            if (super.isCollision(transparent)){
                speed = 4;
                stay = true;
                destinationX = 1310 - 1600;
                destinationY = 0;
                return;
            }
            if (destinationX < 50){
                destinationX += 10;
            }
            if (speed < 16){
                speed +=2;
            }
            translate();
        }
        
        if (!reach && stay){
            if (super.isCollision(transparent2)){
                speed = 0;
                reach = true;
                return;
            }
            if (speed < 30){
                speed +=6;
            }
            if (destinationX < 1310 ){
                destinationX += 100;
            }
            
            if(super.painter().width() <= 60){
                super.painter().enlarge(1);
                super.collider().enlarge(1);
            }
            translate();
        }
    }
}
