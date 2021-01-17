/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.scene;

import gametest7th.utils.CommandSolver.KeyListener;
import gametest7th.utils.CommandSolver.MouseCommandListener;
import java.awt.Graphics;

/**
 *
 * @author user1
 */
public abstract class Scene{
    
    public abstract void sceneBegin();

    public abstract void sceneEnd();

    public abstract void paint(Graphics g);

    public abstract void update();
    
    public abstract MouseCommandListener mouseListener();
    
    public abstract KeyListener keyListener();
}
