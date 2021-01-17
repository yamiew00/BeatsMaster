/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.controllers.SceneController;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author 陳立洋
 */
public class ComboNum extends GameObject{
    private ArrayList<Image> img;//數字圖
    private int comboNum;//顯示值
    public ComboNum(int x, int y, int width, int height) {
        super(x, y, width, height);
        img = new ArrayList();
        img.add(0,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber0()));
        img.add(1,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber1()));
        img.add(2,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber2()));
        img.add(3,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber3()));
        img.add(4,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber4()));
        img.add(5,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber5()));
        img.add(6,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber6()));
        img.add(7,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber7()));
        img.add(8,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber8()));
        img.add(9,SceneController.instance().irc().tryGetImage(new Path().img().objs().goldNumber9()));
        comboNum = 0;
    }
    public void setCombo(int combo){
        this.comboNum = combo;
    }
    

    @Override
    public void paintComponent(Graphics g) {
        if(comboNum < 10){//判斷顯示位數
            g.drawImage(img.get(comboNum), painter().left(), painter().top(), 100,100, null);
        }else if (comboNum <100 && comboNum > 9){
            g.drawImage(img.get(comboNum/10), painter().left(), painter().top(), 50,100, null);
            g.drawImage(img.get(comboNum%10), painter().left()+50, painter().top(), 50,100, null);
        }else {
            g.drawImage(img.get(comboNum/100), painter().left(), painter().top(), 30,100, null);
            g.drawImage(img.get(comboNum%100/10), painter().left()+40, painter().top(), 30,100, null);
            g.drawImage(img.get(comboNum%100%10), painter().left()+80, painter().top(), 30,100, null);
        }
        
    }

    @Override
    public void update() {
    }
    
}
