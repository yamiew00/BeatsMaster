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
public class ComboX extends GameObject{
    private Image img;//combo圖示X

    public ComboX(int x, int y, int width, int height) {
        super(x, y, width, height);
        img = SceneController.instance().irc().tryGetImage(new Path().img().objs().showCombo2());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(),painter().width(),painter().height(), null);

    }

    @Override
    public void update() {
    }
    
}
