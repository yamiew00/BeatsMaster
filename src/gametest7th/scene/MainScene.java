package gametest7th.scene;

import gametest7th.controllers.SceneController;
import gametest7th.gameobj.Musics;
import gametest7th.utils.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;


public class MainScene extends Scene{
    //畫面相關
    Glitch glitch = new Glitch(20,5);
    private boolean switchScene = false;
    private Image background1;//background 
    private Image background2;//background 
    private Image note;//選項
    
    //音檔相關
    private  ArrayList<String> listname = new ArrayList<>();
    
    //測試
    private  static ArrayList<ArrayList<String>> direAndFile = new ArrayList<>();
    private  static int count = 0;
    
    //選項
    private int layer = 0;
    private int option = 0;
    private boolean opt0Stay = true;
    private boolean opt1Stay = true;
    
    @Override
    public void sceneBegin() {
        //載入已收錄音樂
        readAllFile("src/resources/music");
        for(int i = 0; i < direAndFile.size();i++){
            stringToMusics(direAndFile.get(i));
        }
        //載入圖檔
         background1 = SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().mainBackground());
         background2 = SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().mainPanda());
         note = SceneController.instance().irc().tryGetImage(new Path().img().objs().imageMusic());
    }

    @Override
    public void sceneEnd() {
        
    }

    @Override
    public void paint(Graphics g) {
        //背景圖
        g.drawImage(background1, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
        g.drawImage(background2,Global.SCREEN_X/2,Global.SCREEN_Y/2,300,300, null);
        //選項字樣
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", Font.BOLD,50));
        if (layer == 0){
            g.drawString("Press Enter", Global.SCREEN_X / 2 - 150, 600);
        }
        if ( (layer == 1  && glitch.getSignal()) || !opt0Stay){
            g.drawString("雙人對戰", Global.SCREEN_X / 2 - 150, 600);
        }
        if ( (layer == 1  && glitch.getSignal()) || !opt1Stay){
            g.drawString("練習模式", Global.SCREEN_X / 2 - 150, 750);
        }
        if (layer == 1) {
            if (Global.player == Global.player.SINGLE) {
              
                g.drawImage(note, Global.SCREEN_X / 2 - 200, 710, 50,50, null);
            } else {
 
                g.drawImage(note,Global.SCREEN_X / 2 - 200, 560, 50, 50, null);
            }
        }
    }

    @Override
    public void update() {
        //閃爍結束則切場景
        if (switchScene && !glitch.getGlitch()){
            switchScene = false;
            SceneController.instance().change(new MenuScene());
        }
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                if (commandCode == 5){
                    if (layer == 1){
                        if (option == 0){
                            opt0Stay = true;
                        }
                        if (option == 1){
                            opt1Stay = true;
                        }
                        Sound.button();
                        glitch.glitch();
                        switchScene = true;
                        
                    }
                    if (layer == 0){
                        Global.player = Global.Player.PLAYER2;
                        opt0Stay = false;
                        opt1Stay = false;
                        layer ++;
                    }
                }
                
                if (commandCode == 2 && layer == 1 && option > 0){
                    Global.player = Global.Player.PLAYER2;
                    option --;
                }
                if (commandCode == 4 && layer == 1 && option < 1){
                    Global.player = Global.Player.SINGLE;
                    option ++;
                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
                
            }

            @Override
            public void keyTyped(char c, long trigTime) {
                
            }
        };
    }
    //載入已收錄音樂
    public static void readAllFile(String filepath) {
        File file= new File(filepath);
        //不是資料夾
        if(!file.isDirectory()){
            if (!file.getName().equals("ReadMe.txt")){
                direAndFile.get(count - 1).add(file.getName());
            }
        }
        //是資料夾則遞迴
        else if(file.isDirectory()){
            direAndFile.add(new ArrayList<>());
            direAndFile.get(count++).add(file.getName());

            String[] filelist=file.list();
            for(int i = 0;i<filelist.length;i++){
              File readfile = new File(filepath);
              if (!readfile.isDirectory()) {
              } else if (readfile.isDirectory()) {
                readAllFile(filepath + "\\" + filelist[i]);//遞迴
              }
            }
        }
  }
    //讀取音檔的方法  (wav檔名格式:   曲名_bpm_?m?s)
    public static void stringToMusics(ArrayList<String> arraylist){
        for (int i = 1; i < arraylist.size();i++){
            String[] tmp = new String[3];
            tmp = arraylist.get(i).split("_");
            int playTime = Integer.parseInt(tmp[2].split("m")[0]) * 60  + Integer.parseInt(tmp[2].split("s")[0].split("m")[1]);
            
            Global.musicList.add(new Musics(tmp[0]
                                        ,Integer.parseInt(tmp[1])
                                        ,playTime
                                        ,arraylist.get(i)
                                        ,arraylist.get(0)));
        }
        
    }
    
}

    
