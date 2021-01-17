package gametest7th;

import com.sun.glass.events.KeyEvent;
import gametest7th.scene.MainScene;
import gametest7th.utils.GameKernel;
import static gametest7th.utils.Global.*;
import javax.swing.JFrame;

/**
 *
 * @author user1
 */
public class GameTest7th {
    
    public static void main(String[] args) {
        JFrame jf = new JFrame();// 遊戲視窗本體
        jf.setTitle("Game Test 7th");// 視窗標題
        jf.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);// 視窗大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 關閉視窗時關閉程式
        
        GI gi = new GI(new MainScene());// 遊戲的本體(邏輯 + 畫面處理)
        
        int commands[][] = {
            {KeyEvent.VK_LEFT,1},
            {KeyEvent.VK_UP,2},
            {KeyEvent.VK_RIGHT,3},
            {KeyEvent.VK_DOWN,4},
            {KeyEvent.VK_ENTER,5},
            {KeyEvent.VK_CONTROL,6},
            {KeyEvent.VK_A,11},
            {KeyEvent.VK_W,12},
            {KeyEvent.VK_D,13},
            {KeyEvent.VK_S,14}
        };
        
        
        GameKernel gk = new GameKernel.Builder(gi, LIMIT_DELTA_TIME, NANOSECOND_PER_UPDATE)
                .initListener(commands)
                .enableKeyboardTrack(gi)
                .keyTypedMode()
                .enableMouseTrack(gi)
                .gen();
        
        jf.add(gk);
        
        jf.setVisible(true);
        gk.run(IS_DEBUG);
    }
    
}
