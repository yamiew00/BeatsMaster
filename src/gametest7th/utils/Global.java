/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.utils;

import gametest7th.gameobj.Musics;
import java.util.ArrayList;

/**
 *
 * @author user1
 */
public class Global {

    public enum Direction {
        UP(3),
        DOWN(0),
        LEFT(1),
        RIGHT(2);

        private int value;

        Direction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public enum Difficulty{
        NORMAL("普通"),
        HARD("困難");
        
        private String value;
        
        Difficulty(String value){
            this.value = value;
        }
        public String getValue(){
            return value;
        }
    }
    
    //單人模式,雙人模式
    public enum Player{
        SINGLE,
        PLAYER1,
        PLAYER2;
    }
    public static Player player;

    public static final boolean IS_DEBUG = false;

    public static void log(String str) {
        if (IS_DEBUG) {
            System.out.println(str);
        }
    }
    
    // 單位大小
    public static final int UNIT_X = 32;
    public static final int UNIT_Y = 32;
    // 視窗大小
    public static final int WINDOW_WIDTH = 1680;
    public static final int WINDOW_HEIGHT = 1050;
    public static final int SCREEN_X = WINDOW_WIDTH - 8 - 8;
    public static final int SCREEN_Y = WINDOW_HEIGHT - 31 - 8;
    // 資料刷新時間
    public static final int UPDATE_TIMES_PER_SEC = 60;// 每秒更新60次遊戲邏輯
    public static final int NANOSECOND_PER_UPDATE = 1000000000 / UPDATE_TIMES_PER_SEC;// 每一次要花費的奈秒數
    // 畫面更新時間
    public static final int FRAME_LIMIT = 60;
    public static final int LIMIT_DELTA_TIME = 1000000000 / FRAME_LIMIT;

    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static boolean random(int rate) {
        return random(1, 100) <= rate;
    }
    
    //讀取音樂造成的延遲補償
    public static final float DELAY_SEC = 0.1f;
    
    //箭頭到達檢測區所需時間
    public static float spendTime(){
        switch(difficulty){
            case NORMAL:
                return 1.98f;
            case HARD:
                return 1.176f; 
        }
        
        return 0;
    }
    
    
    //已收錄音樂
    public static ArrayList<Musics> musicList = new ArrayList<>();
    public static int musicCount = 0;
    
    //音樂種類
    public static ArrayList<String> musicCategory = new ArrayList<>();
    
    //難度
    public static Difficulty difficulty;
    
    //怪物位置
    public static final int monsterX = SCREEN_X - 200;
    public static final int monsterY = SCREEN_Y - 300;
    
    
    
    //perfect good的長度
    public static float MISS_INTERVAL = 0.5f;
    public static float PERFECT_INTERVAL = 0.1f;
    public static float GOOD_INTERVAL = 0.3f;
    
    //Rates 長度
    public static float missInterval(int bpm){
        return (60f / bpm) * 5 / 6f;
    }
    public static float perfectInterval(int bpm){
        return (60f / bpm) / 6f;
    }
    
    //計算經過時間(小數點後三位)
    public static long startTime = 0;
     public static float passedTime(){
        long currentTime = System.nanoTime();
        return  ( (currentTime - startTime) / 1000000 ) /1000f;
    }
    
     //正計時
     public static int[] clock(){
         int clock[] = new int[2];
         float passedTime = passedTime();
         clock[0] = (int) (passedTime / 60);
         clock[1] = (int) (passedTime % 60);
         return clock;
     }
     //歌曲剩餘時間(倒計時) 
     public static int[] countDownClock(int playTime){
         int countDownClock[] = new int[2];
         float passedTime = passedTime();
         countDownClock[0] = (int) ( (playTime - passedTime ) / 60);
         countDownClock[1] = (int) ( (playTime - passedTime ) % 60);
         return countDownClock;
     }
     
     
     //計算到小數點後3位
     public static float decimal(float number){
         return ( (int) (number * 1000) ) / 1000f;
     }
}
