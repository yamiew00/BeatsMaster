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
 * @author r0522
 */
public class Monster extends GameObject{
    private ArrayList<Image> img; 
    private Delay delay;
    private boolean state;//判斷是否遭受攻擊
    private Delay lowBlood;//滴血亮閃爍
    private boolean isSettle;//是否導入結算畫面
    
    //測試
    private int enemyHp;//怪物血量
    private int enemyMaxHp;//怪物血量上限
    
    //沒用上
    public Monster(int enemyMaxHp){
        super(Global.monsterX,Global.monsterY,200,300);
        this.enemyMaxHp = enemyMaxHp;
        enemyHp = enemyMaxHp;
    }
    
    public Monster(int x,int y,int width,int height){
        super(x,y,width,height);
        img = new ArrayList();
        delay = new Delay(20);
        img.add(0,SceneController.instance().irc().tryGetImage(new Path().img().actors().monster()));
        img.add(1,SceneController.instance().irc().tryGetImage(new Path().img().actors().monster1()));
        img.add(2,SceneController.instance().irc().tryGetImage(new Path().img().actors().rip()));
        img.add(3,SceneController.instance().irc().tryGetImage(new Path().img().objs().xLeft()));
        img.add(4,SceneController.instance().irc().tryGetImage(new Path().img().objs().xRight()));
        state = false;
        isSettle = false;
        lowBlood = new Delay(10);
        delay.loop();
        lowBlood.loop();
    }
    public void setState(boolean b){
        state = b;
    }
    public void setSettle(boolean b){
        isSettle = b;
    }

    public void setPlace(int x,int y){
        super.translate(x, y);
    }
    public Delay getlowblood(){
        return lowBlood;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if (enemyHp < 1000) {//低血量閃爍
            if (enemyHp == 0) {
                g.drawImage(img.get(1),painter().left(), painter().top(),painter().width(), painter().height(), null);
                g.drawImage(img.get(3),painter().left(), painter().top(),painter().width(), painter().height(), null);
                g.drawImage(img.get(4),painter().left(), painter().top(),painter().width(), painter().height(), null);
            } else {
                if (lowBlood.count()) {
                    g.drawImage(img.get(0),  painter().left(), painter().top(),painter().width(), painter().height(), null);
                }else if(isSettle){
                    g.drawImage(img.get(0),  painter().left(), painter().top(),painter().width(), painter().height(), null);
                }
            }

        } else {
            if (state) {//受攻擊宦圖
                g.drawImage(img.get(0), painter().left(), painter().top(), painter().width(), painter().height(), null);
                if (delay.count()) {
                    state = false;
                }
            } else {
                g.drawImage(img.get(1), painter().left(), painter().top(),painter().width(), painter().height(), null);
            }

        }
        
        
    }

    @Override
    public void update() {
       
    }
    
    public void setEnenmyHp(int score){
        enemyHp = Math.max(0, enemyMaxHp - score);
    }
    public void setMaxHp(int enemyMaxHp){
        this.enemyMaxHp = enemyMaxHp;
        this.enemyHp = enemyMaxHp;
    }
    public int getEnemyHp(){
        return enemyHp;
    }
    public int getMaxHp(){
        return enemyMaxHp;
    }
}
