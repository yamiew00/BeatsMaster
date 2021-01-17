/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.controllers.SceneController;
import gametest7th.utils.Delay;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author 陳立洋
 */
public class MessageChoice extends GameObject{
    private ArrayList<String> message;//資訊窗內容
    private ArrayList<Image> img;//外框
    private Delay delay;//計算顯示延遲時間
    private boolean state;//選示是否選擇中
    private boolean up;//上圖示狀態
    private boolean down;//下圖示狀態

    public MessageChoice(int x, int y, int width, int height) {
        super(x, y, width, height);
        message = new ArrayList();
        img = new ArrayList();
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().objs().actionDown()));
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().objs().actionDownRed()));
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().objs().actionUp()));
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().objs().actionUpRed()));
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().objs().box5()));
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().objs().box6()));
        state = false;
        delay = new Delay(20);    }
    public void setState(boolean isChoice){
        this.state = isChoice;
    }
     public boolean getState(){
        return state;
    }
     public void setUp(boolean b){
        this.up = b;
        delay.loop();
    }
    public void setDown(boolean b){
        this.down = b;
        delay.loop();
    }

    @Override
    public void paintComponent(Graphics g) {
        if(state){//如果在選擇中顯示紅框圖
            g.drawImage(img.get(5), painter().left(), painter().top(), painter().width(), painter().height(), null);
            if (up) {//上狀態顯示紅色箭頭
                g.drawImage(img.get(3), painter().left() + painter().width() / 2 - 25, painter().top() - 60, 50, 50, null);
            } else {
                g.drawImage(img.get(2), painter().left() + painter().width() / 2 - 25, painter().top() - 60, 50, 50, null);

            }
            if (down) {
                 g.drawImage(img.get(1), painter().left() + painter().width() / 2 - 25, painter().bottom() + 10, 50, 50, null);
            } else {
                 g.drawImage(img.get(0), painter().left() + painter().width() / 2 - 25, painter().bottom() + 10, 50, 50, null);
            }                
        } else {
            g.drawImage(img.get(4), painter().left(), painter().top(), painter().width(), painter().height(), null);
            g.drawImage(img.get(2), painter().left() + painter().width() / 2 - 25, painter().top() - 60, 50, 50, null);
            g.drawImage(img.get(0), painter().left() + painter().width() / 2 - 25, painter().bottom() + 10, 50, 50, null);

            
            
        }
       
        
    }

    @Override
    public void update() {
        if(delay.count()){  //延遲完恢復狀態        
            up = false;
            down = false;
        }
    }
    
}
