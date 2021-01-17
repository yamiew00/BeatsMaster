package gametest7th.scene;

import gametest7th.controllers.SceneController;
import gametest7th.gameobj.MessageChoice;
import gametest7th.utils.CommandSolver;
import gametest7th.utils.Glitch;
import gametest7th.utils.Global;
import gametest7th.utils.Path;
import gametest7th.utils.Sound;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MenuScene extends Scene{
    //畫面相關
    Glitch glitch = new Glitch(20,5);
    private boolean switchScene = false;
    //選單
    private int index = 0;          //選單索引
    private int whichMusic = 0;     //音樂索引
    private String category = "";
    //試播音樂
    private FileInputStream pilot;
    private AudioStream as;
    private Image background;
    private Image note;
    private MessageChoice bpmMes;
    private MessageChoice diffMes;
    private boolean messageBox;
    
    //選單
    private ArrayList<String> menuList = new ArrayList<>();
    private ArrayList<Integer> categoryIndex = new ArrayList<>();  //category在menuList中所在的index
    private Image categoryBlock;
    
    @Override
    public void sceneBegin() {
        //載入圖檔
        background = SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().elc());
        note = SceneController.instance().irc().tryGetImage(new Path().img().objs().imageMusic());
        categoryBlock = SceneController.instance().irc().tryGetImage(new Path().img().objs().categoryBlock());
        
        bpmMes = new MessageChoice( 430,500, 250, 200);
        diffMes = new MessageChoice( 150,500, 200, 100);
        messageBox = false;
        bpmMes.setState(true);
        
        //將musicList 轉成字串列 menuList
        for (int i = 0; i < Global.musicList.size(); i++){
            if (!Global.musicList.get(i).getCategory().equals(category)){
                category = Global.musicList.get(i).getCategory();
                menuList.add(category);
                categoryIndex.add(menuList.size() - 1);
                i --;
                continue;
            }
            menuList.add(Global.musicList.get(i).getName());
        }
        //難度(起始預設為普通)
        Global.difficulty = Global.Difficulty.NORMAL;
    }

    @Override
    public void sceneEnd() {
        
    }

    @Override
    public void paint(Graphics g) {
        //背景
        g.drawImage(background, 0,0,Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
        
        //選單格式
        int menuWidth = 450;
        int menuHeight = 200;
        int X = Global.SCREEN_X / 2 - menuWidth / 2;
        int Y = Global.SCREEN_Y / 2- menuHeight / 2;
         g.drawImage(note, X-40,Y+60,50, 50, null);
         
        //歌曲字樣呈現
        g.setColor(Color.white);
        for (int i = 0; i < menuList.size(); i++){
            g.setFont(new Font("Courier",Font.PLAIN,20));
            if(i == index){
                g.setColor(Color.red);
                g.setFont(new Font("Helvetica", Font.BOLD,50));
                if (!glitch.getSignal()){
                    g.setColor(Color.white);
                   continue;
                }
            }
            
            if (isCategory(i)){
//            if (categoryIndex.indexOf(i) > -1){
                g.drawImage(categoryBlock, X , Y + (i ) * menuHeight - index * menuHeight , 500, menuHeight, null);
            }
            
            g.drawString(menuList.get(i)
                        , X + menuHeight / 4, Y + menuHeight / 2 + (i ) * menuHeight - index * menuHeight);
            if(i == index){
                g.setColor(Color.white);
            }
        }
        
        //難度選擇
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica",Font.BOLD,50));       
        g.drawString((Global.difficulty == Global.Difficulty.NORMAL)?"普通":"困難", 100, 510);
        
        //歌曲資訊內容
        if (!isCategory(index)){
            nowWhichMusic();
            g.drawString(Global.musicList.get(whichMusic).getPlayTime()/60+"分 "+Global.musicList.get(whichMusic).getPlayTime()%60+"秒" , 325, 480);
            g.drawString(Global.musicList.get(whichMusic).getBpm()+"BPM" , 325, 550);
        }
        g.setColor(Color.white);    
            
        //歌曲資訊框
        bpmMes.paintComponent(g);
        diffMes.paintComponent(g);
    }

    @Override
    public void update() {
        //進入歌曲
        if (switchScene && !glitch.getGlitch()){
            switch(Global.player){
                case SINGLE:
                    SceneController.instance().change(new GameScene(Global.musicList.get(whichMusic)));
                    break;
                case PLAYER2:
                    SceneController.instance().change(new PvpScene(Global.musicList.get(whichMusic)));
                    break;
            }
        }
        //歌曲資訊框
        bpmMes.update();
        diffMes.update();
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
                if (!messageBox) {
                    switch (commandCode) {
                        case 1:
                            messageBox = true;
                            bpmMes.setState(false);
                            diffMes.setState(true);
                            break;
                        case 2:
                            if (index > 0 && !switchScene) {
                                index--;
                                broadCastStop();
                                broadcast();
                            }
                            if(bpmMes.getState()){
                                bpmMes.setUp(true);
                            }else{
                                diffMes.setUp(true);
                            }
                            break;
                        case 4:
                            if (index < menuList.size() - 1 && !switchScene) {
                                index++;
                                broadCastStop();
                                broadcast();
                            }
                             if(bpmMes.getState()){
                                bpmMes.setDown(true);
                            }else{
                                diffMes.setDown(true);
                            }
                            break;

                        case 5:
                            if (!isCategory(index)) {
                                glitch.glitch();
                                switchScene = true;
                                Sound.button();
                                broadCastStop();
                            }
                            break;
                    }
                } else {
                    switch (commandCode) {
                        case 2:
                            if (Global.difficulty == Global.Difficulty.HARD && !switchScene) {
                                Global.difficulty = Global.Difficulty.NORMAL;
                            }
                            if(bpmMes.getState()){
                                bpmMes.setUp(true);
                            }else{
                                diffMes.setUp(true);
                            }
                            break;
                        case 3:
                            messageBox = false;
                            bpmMes.setState(true);
                            diffMes.setState(false);
                            break;
                        case 4:
                            if (Global.difficulty == Global.Difficulty.NORMAL && !switchScene) {
                                Global.difficulty = Global.Difficulty.HARD;
                            }
                             if(bpmMes.getState()){
                                bpmMes.setDown(true);
                            }else{
                                diffMes.setDown(true);
                            }
                            break;
                        case 5:
                            if (!isCategory(index)) {
                                glitch.glitch();
                                switchScene = true;
                                Sound.button();
                                broadCastStop();
                                break;
                            }
                    }
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
    
    //音樂試播
    private void broadcast(){
        if (isCategory(index)){
            return;
        }
        
        nowWhichMusic();
        
        try {   
            pilot=new FileInputStream(".\\src\\resources\\music\\"
                                   + Global.musicList.get(whichMusic).getPath());
            as=new AudioStream(pilot);
            AudioPlayer.player.start(as);
        }catch (IOException e){
                e.printStackTrace();
        }
    }
    private void broadCastStop(){
        AudioPlayer.player.stop(as);
    }
    
    private void nowWhichMusic(){
        int offset = 0;
            for (int i = 0 ; i < categoryIndex.size(); i++){
                if (index > categoryIndex.get(i)){
                    offset ++;
                }
            }
            whichMusic = index - offset;
    }
    private boolean isCategory(int index){
        return categoryIndex.indexOf(index) != -1;
    }
}
