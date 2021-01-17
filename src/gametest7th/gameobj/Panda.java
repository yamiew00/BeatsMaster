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
//熊貓動圖
public class Panda extends GameObject{
    private ArrayList<Image> img;
    private Delay delay;
    private int[] attack = {1,3};
    private int count;
    private boolean state;
    private boolean isAttack;
    public Panda(int x, int y, int width, int height) {
        super(x, y, width, height);
        img = new ArrayList();
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().actors().panda1())) ;
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().actors().panda2())) ;
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().actors().panda3())) ;
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().actors().panda4())) ;
        img.add(SceneController.instance().irc().tryGetImage(new Path().img().actors().rip())) ;
        delay = new Delay(16);
        count  = 0;
        
    }
    public void setState(boolean isDead){
        state = isDead;
    }
    public void setAttack(boolean isAttack){
        this.isAttack = isAttack;
    }

    @Override
    public void paintComponent(Graphics g) {
        
        if(state){
           
               g.drawImage(img.get(4), painter().left(), painter().top(),painter().width(), painter().height(), null);
           
        }else{
            if(isAttack){  
               g.drawImage(img.get(attack[count]), painter().left(),  painter().top(),painter().width(), painter().height(), null);
              
           }else{
              g.drawImage(img.get(0), painter().left(),  painter().top(),painter().width(), painter().height(), null);  
            }
        }
        
    }

    @Override
    public void update() {
        if (isAttack) {
            delay.loop();
            if (delay.count()) {
                count = ++count % attack.length;
                if (count == 1) {
                    isAttack = false;
                }
            }
        }
        
    }
    
}
