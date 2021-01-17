/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.controllers.SceneController;
import gametest7th.utils.Delay;
import gametest7th.utils.Global;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author 陳立洋
 */
//遊戲區燈光變換
public class Spotlight extends GameObject{
    private ArrayList<Image> img;
    private Delay delay;
    private int count;
    
    //單人模式
    public Spotlight() {
        super(Global.SCREEN_X/2 - 250, 0,500,1050);
        img = new ArrayList<Image>();
        img.add(0, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a0()));
        img.add(1, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a1()));
        img.add(2, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a2()));
        img.add(3, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a3()));
        img.add(4, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a4()));
        img.add(5, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a5()));
        img.add(6, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().box1()));
        delay = new Delay(600);
        delay.loop();
        count = 0;
    }
    
    //雙人模式
    public Spotlight(int x) {
        super(x, 0,500,1050);
        img = new ArrayList<Image>();
        img.add(0, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a0()));
        img.add(1, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a1()));
        img.add(2, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a2()));
        img.add(3, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a3()));
        img.add(4, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a4()));
        img.add(5, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().a5()));
        img.add(6, SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().box1()));
        delay = new Delay(600);
        delay.loop();
        count = 0;
    }

    @Override
    public void paintComponent(Graphics g) { 
            g.drawImage(img.get(count),  super.painter().left() + 250, 0, 500, 1050, null);
            g.drawImage(img.get(6),  super.painter().left() + 250, 0, 500, 1050, null);
    }

    @Override
    public void update() {
    }
    //搭配combo數量更換
    public void switchLight(int combo){
        if (combo == 0){
            count = 0;
            return;
        }
        if (combo > 29){
            count = 5;
            return;
        }
        count = combo / 5;
    }
}
