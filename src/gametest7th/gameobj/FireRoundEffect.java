package gametest7th.gameobj;

import gametest7th.controllers.SceneController;
import gametest7th.utils.Delay;
import gametest7th.utils.Global;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class FireRoundEffect extends GameObject{
    private ArrayList<Image> imgArr = new ArrayList<>();
    private int i;
    private Delay delay = new Delay(2);
    private boolean effectOver;
    
    public FireRoundEffect(Rect rect){
        super(rect);
        delay.loop();
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound10()));
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound20()));
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound30()));
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound40()));
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound50()));
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound60()));
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound70()));
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound80()));
        imgArr.add(SceneController.instance().irc().tryGetImage(new Path().img().effects().fireRound().fireRound90()));
    }
    
    public boolean getEffectOver(){
        return effectOver;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(imgArr.get(i), super.painter().left(), super.painter().top(), super.painter().width(), super.painter().height(), null);
    }

    @Override
    public void update() {
        if (delay.count()){
            if (i < 8){
                i ++;
                super.painter().enlarge(10);
            }
            if (i == 8){
                effectOver = true;
            }
        }
    }
    
    
}
