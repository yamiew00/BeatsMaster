/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.utils.Global;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author 陳立洋
 */
public class CharStream extends GameObject{
    private char[] s;
    private int down;
    private int count;

    public CharStream(int x, int y) {
        super(x, y, 1, 1);
        s = new char[Global.random(1, 15)];//隨機字串字數
        for(int i=0;i<s.length;i++){
            s[i]=(char)Global.random(34, 126);//隨機字串產生
        }
        down = Global.random(3, 10);//隨機掉落速度
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.green);
        for(int i=0;i<s.length;i++){
           String st = ""+s[i];
           g.drawString(st,painter().left(),painter().top()-15*i);//字串往上畫出 
        }       
        g.setColor(Color.black);
        if(count%10 == 0){
     
            for(int i=s.length-1;i>0;i--){//變換字串內容
              s[i] = s[i-1];    
            }
           s[0] = (char)((int) s[0]+Global.random(1,10));
        }
        
    }

    @Override
    public void update() {
        translateY(down*Global.random(70, 100)/100);//下降值
        count++;
        if(count == 100){
            count = 0;
        }
    }
    
}
