
package gametest7th.gameobj;

import gametest7th.controllers.SceneController;
import gametest7th.utils.CommandSolver;
import gametest7th.utils.Global;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

//此處(x,y)都是以繪圖左上角為準   x =  Global.SCREEN_X/2 - 140 / 2
//從 y = -230  出發
//perfect點在 y = 850
//消失在 y = 1000
//從 y = -230 走到y = 850 共需要1.98秒

// 1 2 3 4: 左上右下70, 140 ,140); //只有在創建時以中心點為準
        
       

public class Arrows extends GameObject implements CommandSolver.KeyListener{
    private Image img;
    private long time;
    private int deltaY;
    
    //攻擊用的
    private boolean onBeat;
    private int originX;
    private int originY;
    private int speed;   //從18開始
    
    //單人 or 雙人
    
    //沒用上的建構子
    public Arrows(){
        super(Global.SCREEN_X/2, -250 + 70, 140 ,140); //只有在創建時以中心點為準
        img = SceneController.instance().irc().tryGetImage(new Path().img().objs().arrowDown());
        onBeat = false;
    }
    //攻擊成功時使用的創建子
    public Arrows(int x, int y, Image img,int speed){
        super(Global.SCREEN_X/2,y + 70,140 ,140);
        onBeat = true;
        this.img = img;
        this.speed = Math.min(200, speed);
    }
    //一般情況創建的建構子
    public Arrows(int direction, Global.Player player){
        super(Global.SCREEN_X/2, -250 + 70, 140 ,140); //只有在創建時以中心點為準
       //設定箭頭位置
       switch (player){
           case SINGLE:
               super.painter().setLeft(Global.SCREEN_X/2 - 70);
               break;
           case PLAYER1:
               super.painter().setLeft(Global.SCREEN_X - 500 - 100 + 250 - 70);  //500 外框width
               break;
           case PLAYER2:
               super.painter().setLeft(100 + 250 - 70);
                break;
       }
        switch(Global.difficulty){
            case NORMAL:
                deltaY = 9;
                break;
            case HARD:
                deltaY = 15;
                break;
        }
        switch(direction){
            case 1:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().arrowLeft());
                break;
            case 2:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().arrowUp());
                break;
            case 3:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().arrowRight());
                break;
            case 4:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().arrowDown());
                break;
            case 5:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().greenArrowRight());
                break;
            case 6:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().greenArrowDown());
                break;
            case 7:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().greenArrowLeft());
                break;
            case 8:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().greenArrowUp());
                break;
       }
       
    }
    
    public int getX(){
        return super.painter().left();
    }
    public int getY(){
        return super.painter().top();
    }
    public Image getImg(){
        return img;
    }
   
    public void setOnBeat(boolean onBeat){
        this.onBeat = onBeat;
    }
    
    public void setOrigin(){
        originX = super.painter().left();
        originY = super.painter().top();
    }
    
    
    //測試用
    public void printPos(){
        System.out.println("x:" + this.painter().left() + ", y:" + this.painter().top());
    }
    

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, super.painter().left(),  super.painter().top() ,140,140, null);
        
    }

    @Override
    public void update() {
        if (!onBeat){
            super.translateY(deltaY);
        }
        else{
            toMonster(speed);
        }
        


        

        //測速用的
//        if (super.painter().top() == -230){
//            time = System.nanoTime();
//        }
//        super.translateY(9);                //每次往下移9單位
//        if (super.painter().top() == 850 ){
////            System.out.println("花費 " + ( (System.nanoTime() - time)/10000000) / 100f + "秒");
//        }
    }

    @Override
    public void keyPressed(int commandCode, long trigTime) {
        
    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
        
    }

    @Override
    public void keyTyped(char c, long trigTime) {
        
    }
    
    
    public void toMonster(int speed){
        int vectorX = Global.monsterX - super.painter().left();
        int vectorY = Global.monsterY - super.painter().top();
        
        double initX = ( vectorX * (1 / ( Math.sqrt(Math.pow(vectorX, 2)) + Math.sqrt(Math.pow(vectorY, 2) ) )) );
        double initY = ( vectorY * (1 / ( Math.sqrt(Math.pow(vectorX, 2)) + Math.sqrt(Math.pow(vectorY, 2) ) )) );
        
        super.translate((int)( speed * initX) , (int) ( speed * initY) );
    }
}
