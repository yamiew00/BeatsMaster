/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.utils.GameKernel;
import gametest7th.utils.Global;
import java.awt.Color;
import java.awt.Graphics;

public class MeasureLine implements GameKernel.GameInterface{
    private int x;
    private int y = -250 + 70; //跟arrows一致
    private int deltaY;        //依難度不同降落速度不同
    
    
    public MeasureLine(Global.Player player){
        switch(Global.difficulty){
            case NORMAL:
                deltaY = 9;
                break;
            case HARD:
                deltaY = 15;
                break;
        }
        switch(player){
            case SINGLE:
                x = Global.SCREEN_X/2 - 500 / 2;
                break;
            case PLAYER1:
                x = Global.SCREEN_X - 500 - 100;
                break;
            case PLAYER2:
                x = 100;
                break;
        }
    }
    
    @Override
    public void update() {
        y += deltaY;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.drawLine(x, y, x + 500, y);  // 500 = 外框width
    }
    
    public int getY(){
        return y;
    }
}
