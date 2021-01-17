/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.controllers.ImgController;
import gametest7th.controllers.SceneController;
import gametest7th.utils.Delay;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;

public class Hp extends GameObject{
    private Image img;
    private boolean state;
    private int count;
    public Hp(int x, int y){
        super(x,y,50,50);
        img = SceneController.instance().irc().tryGetImage(new Path().img().objs().pandaSmall());
        state = false;
        count = 0;
    }
    public void setState(boolean b){
        this.state = b;
    }
    public boolean getState(){
        return state;
    }
    public int getCount(){
        return count;
    }

    @Override
    public void paintComponent(Graphics g) {
            if(state){
                 g.drawImage(img, painter().left()-count,painter().top()-count,50+count*2,50+count*2, null);
                
            }else{
                g.drawImage(img, painter().left(),painter().top(),50,50, null);
            }
    
    }

    @Override
    public void update() {
       if(state){
           count++;
       }
    }
    
}
