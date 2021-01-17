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
//顯示時間
public class ShowTime extends GameObject {
    private ArrayList<Image> img;
    private ArrayList<Integer> time;
    private Image colon;
    
    private int clock;

    public ShowTime(int clock) {
        super(300, 100, 50, 50); // 單人模式起始位置
        this.clock = clock;
        img = new ArrayList<>();
        time = new ArrayList<>();
        setTime(new int[]{12,21});
        colon = SceneController.instance().irc().tryGetImage(new Path().img().objs().colon());
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
    public ShowTime(int x,int y,int width,int height,int clock) {
        super(x, y, width, height); // 單人模式起始位置
        this.clock = clock;
         img = new ArrayList<>();
        time = new ArrayList<>();
        setTime(new int[]{12,21});
        colon = SceneController.instance().irc().tryGetImage(new Path().img().objs().colon());
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
    public void setTime(int[] arr){
        time.add(0, arr[0] / 10);
        time.add(1, arr[0] % 10);
        time.add(2, arr[1] / 10);
        time.add(3, arr[1] % 10);
        
    }
    

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img.get(time.get(0)),painter().left(), painter().top(), painter().width(), painter().height(), null);
        g.drawImage(img.get(time.get(1)),painter().left() + 50,  painter().top(), painter().width(), painter().height(), null);
        g.drawImage(colon,painter().left() + 100,  painter().top(), painter().width(), painter().height(), null);
        g.drawImage(img.get(time.get(2)),painter().left() + 150,  painter().top(), painter().width(), painter().height(), null);
        g.drawImage(img.get(time.get(3)),painter().left() + 200,  painter().top(), painter().width(), painter().height(), null);
        
    }

    @Override
    public void update() {
        
    }
    
}
