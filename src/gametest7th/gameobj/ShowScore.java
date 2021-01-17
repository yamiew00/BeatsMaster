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
//分數顯示
public class ShowScore extends GameObject {
    private ArrayList<Image> img;
    private int score;
    public ShowScore() {
        super(1050, 50,100, 100);
        img = new ArrayList<>();

        setScore(0);
        img.add(0,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber0()));
        img.add(1,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber1()));
        img.add(2,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber2()));
        img.add(3,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber3()));
        img.add(4,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber4()));
        img.add(5,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber5()));
        img.add(6,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber6()));
        img.add(7,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber7()));
        img.add(8,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber8()));
        img.add(9,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber9()));
   
    }

    public ShowScore(int x, int y, int width, int hieght,int num) {
        super(x, y,width, hieght);
        img = new ArrayList<>();

        setScore(num);
        img.add(0,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber0()));
        img.add(1,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber1()));
        img.add(2,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber2()));
        img.add(3,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber3()));
        img.add(4,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber4()));
        img.add(5,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber5()));
        img.add(6,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber6()));
        img.add(7,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber7()));
        img.add(8,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber8()));
        img.add(9,SceneController.instance().irc().tryGetImage(new Path().img().objs().showNumber9()));
    }

    
    public void setScore(int score){
        this.score = score;
        
   
    }

    @Override
    public void paintComponent(Graphics g) {
        if(score < 10){
            g.drawImage(img.get(score), painter().left() + painter().width() / 7 * 6 + 30, painter().top(), painter().width() / 7, painter().height(), null);
        }else if(score>9 && score < 100){
            g.drawImage(img.get(score/10), painter().left() + painter().width() / 7 * 5 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score%10), painter().left() + painter().width() / 7 * 6 + 30, painter().top(), painter().width() / 7, painter().height(), null);
        }else if(score > 99 && score < 1000){
            g.drawImage(img.get(score / 100), painter().left() + painter().width() / 7 * 4 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score / 10 % 10), painter().left() + painter().width() / 7 * 5 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score % 10), painter().left() + painter().width() / 7 * 6 + 30, painter().top(), painter().width() / 7, painter().height(), null);
        }else if(score > 999 && score < 10000){
            g.drawImage(img.get(score/1000), painter().left() + painter().width() / 7 * 3 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score/100%10), painter().left() + painter().width() / 7 * 4 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score/10%10), painter().left() + painter().width() / 7 * 5 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score%10), painter().left() + painter().width() / 7 * 6 + 30, painter().top(), painter().width() / 7, painter().height(), null);
        }else if(score > 9999 && score < 100000) {
            g.drawImage(img.get(score / 10000), painter().left() + painter().width() / 7 * 2 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score / 1000 % 10), painter().left() + painter().width() / 7 * 3 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score / 100 % 10), painter().left() + painter().width() / 7 * 4 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score / 10 % 10), painter().left() + painter().width() / 7 * 5 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score%10), painter().left() + painter().width() / 7 * 6 + 30, painter().top(), painter().width() / 7, painter().height(), null);
        }else if(score > 100000) {
            g.drawImage(img.get(score / 100000), painter().left() + painter().width() / 7 * 1 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score / 10000 % 10), painter().left() + painter().width() / 7 * 2 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score / 1000 % 10), painter().left() + painter().width() / 7 * 3 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score / 100 % 10), painter().left() + painter().width() / 7 * 4 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score / 10 % 10), painter().left() + painter().width() / 7 * 5 + 30, painter().top(), painter().width() / 7, painter().height(), null);
            g.drawImage(img.get(score%10), painter().left() + painter().width() / 7 * 6 + 30, painter().top(), painter().width() / 7, painter().height(), null);
 
        }


    }

    @Override
    public void update() {
        
    }
    
}
