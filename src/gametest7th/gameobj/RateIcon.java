package gametest7th.gameobj;

import gametest7th.controllers.ImgController;
import gametest7th.controllers.SceneController;
import gametest7th.utils.Path;
import gametest7th.utils.Rate;
import java.awt.Graphics;
import java.awt.Image;

public class RateIcon extends GameObject{
    private Image img;
    public RateIcon(Rate rate,int x, int y,int width, int height){
        super(x,y,width,height);
        switch(rate){
            case PERFECT:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().perfect());
                break;
            case GOOD:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().good());
                break;
            case MISS:
                img = SceneController.instance().irc().tryGetImage(new Path().img().objs().miss());
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, this.painter().left(), this.painter().top(),super.painter().width(), super.painter().height(), null);
    }

    @Override
    public void update() {
    }
}
