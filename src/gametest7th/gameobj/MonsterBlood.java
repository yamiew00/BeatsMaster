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

/**
 *
 * @author 陳立洋
 */
//怪物血條
public class MonsterBlood extends GameObject{
    private Image img;
    private Image img1;
    private Image img2;
    private Image img3;
    private int hpLimit;
    private int hp;

    public MonsterBlood(int x, int y, int width, int height) {
        super(x, y, width, height);
        img = SceneController.instance().irc().tryGetImage(new Path().img().objs().monsterHpBlack());
        img1 = SceneController.instance().irc().tryGetImage(new Path().img().objs().monsterHpFull());
        img2 = SceneController.instance().irc().tryGetImage(new Path().img().objs().blood2());

    }
    public void setLimit(int limit){
        hpLimit = limit;
    }
    public void setHp(int hp){
        this.hp = hp;
}
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left()-205, painter().top(), 395,40, null);
        g.drawImage(img2, painter().left()-275, painter().top()-30, 80,80, null);
        g.drawImage(img1, painter().left()-200, painter().top(),hp*400 / hpLimit,40, null);
        
    }

    @Override
    public void update() {
        
    }
    
    
}
