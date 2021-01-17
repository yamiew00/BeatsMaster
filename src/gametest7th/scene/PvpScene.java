
package gametest7th.scene;

import gametest7th.controllers.SceneController;
import gametest7th.gameobj.Arrows;
import gametest7th.gameobj.Ball;
import gametest7th.gameobj.CharStream;
import gametest7th.gameobj.ComboIcon;
import gametest7th.gameobj.ComboNum;
import gametest7th.gameobj.ComboX;
import gametest7th.gameobj.FireRoundEffect;
import gametest7th.gameobj.MeasureLine;
import gametest7th.gameobj.Musics;
import gametest7th.gameobj.RateIcon;
import gametest7th.gameobj.Rect;
import gametest7th.gameobj.ShowScore;
import gametest7th.gameobj.ShowTime;
import gametest7th.gameobj.Spotlight;
import gametest7th.utils.BeatTimeList;
import gametest7th.utils.CommandSolver;
import gametest7th.utils.Delay;
import gametest7th.utils.Global;
import gametest7th.utils.Path;
import gametest7th.utils.Rate;
import gametest7th.utils.Score;
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

public class PvpScene extends Scene{
    //背景
    private ArrayList<CharStream> charStream;
    
    //攻擊
    private ArrayList<Ball> ballList1 = new ArrayList<>();
    private ArrayList<Ball> ballList2 = new ArrayList<>();

    //特效
    private ArrayList<FireRoundEffect> fireRoundEffectList1 = new ArrayList<>();
    private ArrayList<FireRoundEffect> fireRoundEffectList2 = new ArrayList<>();
    private FireRoundEffect fireRoundEffect = new FireRoundEffect(new Rect ( 100 + 250 - 140 / 2
                                                                    ,825
                                                                    ,100 + 250 - 140 / 2 + 140
                                                                    ,825 + 140));
    //hp歸零後用的
    Delay delay = new Delay(180);

    //小節線
    ArrayList<MeasureLine> ml1 = new ArrayList<>();
    ArrayList<MeasureLine> ml2 = new ArrayList<>();
    private int mlOrder = 1;
    
    //歌曲相關
    private Musics music;    
    AudioStream song;
    
    //節拍時程表
    BeatTimeList btl;
    private int bpm;
    
    //評等(rate)相關
    private int rateOrder1;
    private int rateOrder2;
    private boolean oneTrial1 = false; //每個箭頭只能有一次按壓方向鍵的機會
    private boolean oneTrial2 = false;
    
    //Miss 相關
    private boolean isMiss1 = false;
    private boolean isMiss2 = false;
    private boolean timeOutMiss1= true;    //時間到卻沒按壓則判斷為miss
    private boolean timeOutMiss2= true;
    private int resetOrder;
    private int missCount1;          //miss總數
    private int missCount2;
    
    //good perfect 相關
    private boolean isPerfect1 = false;
    private boolean isPerfect2 = false;
    private boolean isGood1 = false;
    private boolean isGood2 = false;
    private int perfectCount1;       //perfect總數
    private int perfectCount2;
    private int goodCount1;          //good總數
    private int goodCount2;
    
    //反鍵相關
    private int missCombo1;          //若missCombo達到2則製造反鍵
    private int missCombo2;
    
    
    //計分
    private Score score1 = new Score();
    
    private Score score2  = new Score();
    private boolean isGetScore1 = false;       //紀錄得分變化
    private boolean isGetScore2 = false;
    private int deltaScore1;          //紀錄得分變化
    private int deltaScore2;
    
    //combo
    private int combo1;
    private int combo2;
    private int maxCombo1;
    private int maxCombo2;
    
    //rateicon
//    SINGLE 的設定    
//    private RateIcon perfectIcon1 = new RateIcon(Rate.PERFECT, 300,650);
//    private RateIcon goodIcon1 = new RateIcon(Rate.GOOD, 300,650);
//    private RateIcon missIcon1 = new RateIcon(Rate.MISS, 1230,500);
    //玩家1
    private RateIcon perfectIcon1 = new RateIcon(Rate.PERFECT, Global.SCREEN_X - 130,700,250,100);
    private RateIcon goodIcon1 = new RateIcon(Rate.GOOD, Global.SCREEN_X - 150,700,250,100);
    private RateIcon missIcon1 = new RateIcon(Rate.MISS, Global.SCREEN_X / 2 + 200 / 2 + 40,500,200,80);
    //玩家2
    private RateIcon perfectIcon2 = new RateIcon(Rate.PERFECT, 150,700,250,100);
    private RateIcon goodIcon2 = new RateIcon(Rate.GOOD,150,700,250,100);
    private RateIcon missIcon2 = new RateIcon(Rate.MISS, 710,500,200,80);
    
    //雙人 時程 工作列表相關
    private ArrayList<Integer> directionList1 = new ArrayList<>();
    private ArrayList<Integer> directionList2 = new ArrayList<>();
    private ArrayList<Arrows> arrowsList1;
    private ArrayList<Arrows> arrowsList2;
    private ArrayList<Float> beatTimeList;
    private int order= 0;
    
    //雙人 畫面相關
    private Spotlight spotlight1;//中間背景
    private Spotlight spotlight2;
    private ShowScore showscore1;//秀分數
    private ShowScore showscore2;
    private Image imgScore1;
    private Image imgScore2;
    private ShowTime showtime;//秀時間
    

    
    //雙人 玩家
    private ComboNum comboNum1;
    private ComboIcon comboIcon1;
    private ComboX combox1;
    private ComboNum comboNum2;
    private ComboIcon comboIcon2;
    private ComboX combox2;
   
    //constructor
    public PvpScene(Musics music){
        this.music = music;
        bpm = music.getBpm();
    }
    
    @Override
    public void sceneBegin() {
        //參數初始化                
        spotlight1 = new Spotlight(Global.SCREEN_X - 500 - 100);
        spotlight2 = new Spotlight(100);

        //combo圖樣
        comboIcon1 = new ComboIcon(Global.SCREEN_X / 2  + 200,600,360,150);
        combox1 = new ComboX(Global.SCREEN_X / 2  + 160,700,100,100);
        comboNum1 = new ComboNum(Global.SCREEN_X / 2  + 270,860,300,150);
        comboIcon2 = new ComboIcon(Global.SCREEN_X / 2  - 200,600,360,150);
        combox2 = new ComboX(Global.SCREEN_X / 2  -180,700,100,100);
        comboNum2 = new ComboNum(Global.SCREEN_X / 2  - 80,860,300,150);

        //beatTimeList設定
        btl = new BeatTimeList( (int) ( (bpm / 60f) * (music.getPlayTime() / 4)  - 2 ),bpm);         //Sunburst
        btl.setNull(0);
        btl.setNull(1);
        btl.print(btl.getBeatTimes());
        beatTimeList = btl.getBeatTimes();

        //背景
        charStream = new ArrayList<>();
        //分數
        showscore1 = new ShowScore(910, 200, 270, 50, 0);
        imgScore1 = SceneController.instance().irc().tryGetImage(new Path().img().objs().showscore());
        showscore2 = new ShowScore(660, 200, 270, 50, 0);
        imgScore2 = SceneController.instance().irc().tryGetImage(new Path().img().objs().showscore());
        //時間
        showtime = new ShowTime(Global.SCREEN_X / 2 - 100,100,50,50,0);
        
        //產生方向及箭頭
        for(int i = 0; i < beatTimeList.size();i++){
            directionList1.add(Global.random(1, 4));
            directionList2.add(Global.random(1, 4));
        }
        arrowsList1 = new ArrayList<>();
        arrowsList2 = new ArrayList<>();
        
        //播放音樂
        try {
                    song=new AudioStream(new FileInputStream(".\\src\\resources\\music\\"
                                                            + music.getPath()) 
                                        );
                    AudioPlayer.player.start(song);
                    Global.startTime= System.nanoTime();
                    //使用bpm處理
                    Global.MISS_INTERVAL = Global.missInterval(bpm);
                    Global.PERFECT_INTERVAL = Global.perfectInterval(bpm);
            }catch (IOException e){
                    e.printStackTrace();
            }
    }

    @Override
    public void sceneEnd() {
        
    }

    @Override
    public void paint(Graphics g) {
        //背景
        for (int i = 0; i < charStream.size(); i++) {
            charStream.get(i).paint(g);
        }
        
        //外框
        spotlight1.paintComponent(g);
        spotlight2.paintComponent(g);
        
       //遊戲時間
       showtime.paintComponent(g);
       
       //若perfect則運行...
       if (isPerfect1){
           perfectIcon1.paint(g);
       }
       if (isPerfect2){
           perfectIcon2.paint(g);
       }
       //若good則運行...
       if (isGood1){
           goodIcon1.paint(g);
       }
       if (isGood2){
           goodIcon2.paint(g);
       }
       //若miss則運行...
       if (isMiss1){
           missIcon1.paint(g);
       }
       if (isMiss2){
           missIcon2.paint(g);
       }
       
       //總分
       g.drawImage(imgScore1, 900, 125, 70, 50, null);
       g.drawImage(imgScore2, 700, 125, 70, 50, null);
       showscore1.paintComponent(g);
       showscore2.paintComponent(g);
       //combo
       if (combo1 > 1){
            comboNum1.setCombo(combo1);
            comboIcon1.paintComponent(g);
            comboNum1.paintComponent(g);
            combox1.paintComponent(g);

       }
       if (combo2 > 1){
            comboNum2.setCombo(combo2);
            comboIcon2.paintComponent(g);
            comboNum2.paintComponent(g);
            combox2.paintComponent(g);
       }
       
       //得分變化
       if (isGetScore1 && deltaScore1 > 0){
           g.drawString("+" + deltaScore1, 1200, 300);
       }
       
       //畫出箭頭
       for (int i = 0; i < arrowsList1.size(); i++){
           arrowsList1.get(i).paintComponent(g);           
       }
       for (int i = 0; i < arrowsList2.size(); i++){
           arrowsList2.get(i).paintComponent(g);           
       }
        
       //外框 + 檢測區
       int width = 500;
       int diameter = 140;
       fireRoundEffect.paint(g);
       g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().fireRound())
                ,(Global.SCREEN_X - width - 100) + width / 2 - diameter / 2
                , 825
                , diameter
                , diameter
                , null);
       
        //攻擊
        for (int i = 0; i < ballList1.size();i++){
            ballList1.get(i).paint(g);
        }
        for (int i = 0; i < ballList2.size();i++){
            ballList2.get(i).paint(g);
        }
        //特效
        for (int i = 0; i < fireRoundEffectList1.size(); i++){
            fireRoundEffectList1.get(i).paint(g);
       }
        for (int i = 0; i < fireRoundEffectList2.size(); i++){
            fireRoundEffectList2.get(i).paint(g);
       }
        //小節線
        for (int i = 0; i < ml1.size();i++){
            ml1.get(i).paint(g);
         }
        for (int i = 0; i < ml2.size();i++){
            ml2.get(i).paint(g);
         }
       
    }

    @Override
    public void update() {
        //小球 
        for (int i = 0; i < ballList1.size(); i++){
            ballList1.get(i).update();
        }
        for (int i = 0; i < ballList1.size();i++){
            if (ballList1.get(i).getReach()){
                ballList1.remove(i--);
                //球一消失就對對手生反鍵
                if (order < beatTimeList.size()){
                     directionList2.set(order , Global.random(5, 8));
                }
            }
        }
        for (int i = 0; i < ballList2.size(); i++){
            ballList2.get(i).update();
        }
        for (int i = 0; i < ballList2.size();i++){
            if (ballList2.get(i).getReach()){
                ballList2.remove(i--);
                //球一消失就對對手生反鍵
                if (order < beatTimeList.size()){
                     directionList1.set(order , Global.random(5, 8));
                }
            }
        }
        
        //特效
        for (int i = 0; i < fireRoundEffectList1.size(); i++){
             fireRoundEffectList1.get(i).update();
        }
        for (int i = 0; i < fireRoundEffectList1.size(); i++){
            if (fireRoundEffectList1.get(i).getEffectOver()){
                 fireRoundEffectList1.remove(i--);
            }
        }
        for (int i = 0; i < fireRoundEffectList2.size(); i++){
             fireRoundEffectList2.get(i).update();
        }
        for (int i = 0; i < fireRoundEffectList2.size(); i++){
            if (fireRoundEffectList2.get(i).getEffectOver()){
                 fireRoundEffectList2.remove(i--);
            }
        }

        //小節線
         for (int i = 0; i < ml1.size();i++){
             ml1.get(i).update();
         }
         for (int i = 0; i < ml2.size();i++){
             ml2.get(i).update();
         }
         if (Global.passedTime() - Global.DELAY_SEC >= ( (4 * 60f) * mlOrder )/ bpm - Global.spendTime()){
             ml1.add(new MeasureLine(Global.Player.PLAYER1));
             ml2.add(new MeasureLine(Global.Player.PLAYER2));
             mlOrder ++;
         }
              
        //按照beatTimeList的時序產生箭頭
        if (order < btl.size()){
            if (Global.passedTime() - Global.DELAY_SEC >= beatTimeList.get(order) - Global.spendTime()){   //spendtime為箭頭自產生到檢測區所需的時間
                arrowsList1.add(new Arrows(directionList1.get(order),Global.Player.PLAYER1));
                arrowsList2.add(new Arrows(directionList2.get(order),Global.Player.PLAYER2));
                order ++;
            }
        }
        //更新arrowsList
        for (int i = 0; i < arrowsList1.size(); i++){
            arrowsList1.get(i).update();
        }
        for (int i = 0; i < arrowsList2.size(); i++){
            arrowsList2.get(i).update();
        }
        //背景
        if ( charStream.size() < 1000) {
            charStream.add(new CharStream(Global.random(1,84)*20,0));
        }
         for(int i=0;i<charStream.size();i++){
            if (charStream.get(i).outOfScreen()) {
                charStream.remove(i--);
                continue;
            }
            charStream.get(i).update();
        }
        //總分 背景 時間
        showscore1.setScore(score1.getScore());
        showscore2.setScore(score2.getScore());
        showtime.setTime( Global.countDownClock(music.getPlayTime()));
        spotlight1.switchLight(combo1);
        spotlight2.switchLight(combo2);
        showscore1.update();
        showscore2.update();
        
        //p點前 (Global.MISS_INTERVAL / 2) 秒回復相關變數狀態
        if (resetOrder < btl.size()){
            if (Global.passedTime()  - Global.DELAY_SEC >= beatTimeList.get(resetOrder) - Global.MISS_INTERVAL / 2){
                resetOrder++;
                
                timeOutMiss1 = true;
                timeOutMiss2 = true;
                isMiss1 = false;
                isMiss2 = false;
                isPerfect1 = false;
                isPerfect2 = false;
                isGood1 = false;
                isGood2 = false;
                oneTrial1 = false;
                oneTrial2 = false;
                isGetScore1 = false;
                isGetScore2 = false;
                deltaScore1 = 0;
                deltaScore2 = 0;
            }
        }
        
        //p點後 (Global.MISS_INTERVAL / 2) 秒沒做動作則miss (timeout 造成的miss)
       if (rateOrder1 < btl.size()){     
            if (Global.passedTime()  - Global.DELAY_SEC >= beatTimeList.get(rateOrder1) + Global.MISS_INTERVAL / 2){  //超過p點Global.MISS_INTERVAL / 2秒就判定為miss
                rateOrder1++;
                if ( timeOutMiss1 ){
                    missEvent1();
                }
            }
       }
       if (rateOrder2 < btl.size()){     
            if (Global.passedTime()  - Global.DELAY_SEC >= beatTimeList.get(rateOrder2) + Global.MISS_INTERVAL / 2){  //超過p點Global.MISS_INTERVAL / 2秒就判定為miss
                rateOrder2++;
                if ( timeOutMiss2 ){
                    missEvent2();
                }
            }
       }
       
       //切至結算畫面
        if(delay.count()){
            toSettleScene();
        }
        if(Global.passedTime()> music.getPlayTime()){
            toSettleScene();
        }
        
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener(){
            //1 2 3 4     對應 玩家1 左 上 右 下
            //11 12 13 14 對應  玩家2  左 上 右 下 
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                float now = Global.decimal(Global.passedTime() - Global.DELAY_SEC);
                
                //玩家1
                if (commandCode <= 4 && commandCode >= 1){
                    if (!oneTrial1 && btl.tellNotMiss(now, rateOrder1)){
                        oneTrial1 = true;
                        if (commandCode ==   ( (directionList1.get(rateOrder1) - 1) % 4) + 1 ){

                            //判斷可按箭頭的時間點 且 perfect
                            if (btl.tellPerfect(now, rateOrder1) ){
                                perfectEvent1();
                            }
                            //非perfect 且非 miss, 則good 
                            else{
                                goodEvent1();
                            }
                            isMiss1 = false;
                            timeOutMiss1 = false;
                        }
                        else{
                            //按錯方向鍵,目前處理方式視同完全沒按等待timeout,之後可以考慮錯誤動畫
                        }
                    }
                }
                
                //玩家2
                if (commandCode >= 11 && commandCode <= 14){
                    if (!oneTrial2 && btl.tellNotMiss(now, rateOrder2)){
                        oneTrial2 = true;
                        if ( (commandCode -10 ) ==   ( (directionList2.get(rateOrder2) - 1) % 4) + 1 ){

                            //判斷可按箭頭的時間點 且 perfect
                            if (btl.tellPerfect(now, rateOrder2) ){
                                perfectEvent2();
                            }
                            //非perfect 且非 miss, 則good 
                            else{
                                goodEvent2();
                            }
                            isMiss2 = false;
                            timeOutMiss2 = false;
                        }
                        else{
                            //按錯方向鍵,目前處理方式視同完全沒按等待timeout,之後可以考慮錯誤動畫
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
    
    //切換至結算畫面
    public void toSettleScene(){
        SceneController.instance().change(new SettlementScene(maxCombo1,perfectCount1,goodCount1,missCount1,score1.getScore()
                                                     ,maxCombo2,perfectCount2,goodCount2,missCount2,score2.getScore()
                                                    ,music)); //結算場景
    }
    
    //事件處理
    public void perfectEvent1(){
            //perfect處理
            perfectCount1 ++;
            isPerfect1 = true;
            isGetScore1 = true;

            arrowsList1.remove(0);

            missCombo1 = 0;
            
                //測試用
                    //特效
                    fireRoundEffectList1.add(new FireRoundEffect(new Rect ((Global.SCREEN_X - 500 - 100) + 500 / 2 - 140 / 2
                                                                    ,825
                                                                    ,(Global.SCREEN_X - 500 - 100) + 500 / 2 - 140 / 2 + 140
                                                                    ,825 + 140))
                                        );
                    
                //測試用
            //音效
            if (combo1 > 0 && combo1 % 10 == 0){
                Sound.cheer();
            }

            //combo處理
            combo1 ++;
            
            
            if (combo1 > maxCombo1){
                maxCombo1 = combo1;
            }
            
            //攻擊玩家2
            if (deliverAttack(combo1)){
                //產生小球
                ballList1.add(new Ball(Global.Player.PLAYER1));
            }
            
            //計分
            score1.tranScore(Rate.PERFECT,combo1);
            deltaScore1 = score1.getDeltaScore(Rate.PERFECT, combo1);
    }
    public void perfectEvent2(){
            //perfect處理
            perfectCount2 ++;
            isPerfect2 = true;
            isGetScore2 = true;

            arrowsList2.remove(0);

            missCombo2 = 0;
            
            //測試用
            fireRoundEffectList2.add(new FireRoundEffect(new Rect ( 100 + 250 - 140 / 2
                                                            ,825
                                                            ,100 + 250 - 140 / 2 + 140
                                                            ,825 + 140))
                                        );
            //測試用
            
            //音效
            if (combo2 > 0 && combo2 % 10 == 0){
                Sound.cheer();
            }
            //combo處理
            combo2 ++;
            
            if (combo2 > maxCombo2){
                maxCombo2 = combo2;
            }
            
            //攻擊玩家1
            if (deliverAttack(combo2)){
                //產生小球
                ballList2.add(new Ball(Global.Player.PLAYER2));
            }
            
            //計分
            score2.tranScore(Rate.PERFECT,combo2);
            deltaScore2 = score2.getDeltaScore(Rate.PERFECT, combo2);
    }
    public void goodEvent1(){
            //good處理
            goodCount1 ++;
            isGood1 = true;
            combo1 = 0;
            isGetScore1 = true;

            arrowsList1.remove(0);

            missCombo1 = 0;

            //計分
            score1.tranScore(Rate.GOOD, combo1);
            deltaScore1 = score1.getDeltaScore(Rate.GOOD, combo1);
    }
    public void goodEvent2(){
            //good處理
            goodCount2 ++;
            isGood2 = true;
            combo2 = 0;
            isGetScore2 = true;

            arrowsList2.remove(0);

            missCombo2 = 0;

            //計分
            score2.tranScore(Rate.GOOD, combo2);
            deltaScore2 = score2.getDeltaScore(Rate.GOOD, combo2);
    }
    public void missEvent1(){
        Sound.miss();
        //miss處理
            //miss參數處理
            missCount1 ++;
            isMiss1 = true;
            combo1 = 0;
            arrowsList1.remove(0);
    }
    public void missEvent2(){
        Sound.miss();
        //miss處理
            //miss參數處理
            missCount2 ++;
            isMiss2 = true;
            combo2 = 0;
            arrowsList2.remove(0);
    }
    
    public boolean deliverAttack(int combo){
        if (combo == 0){
            return false;
        }
        if (combo % 10 == 0){
            return true;
        }
        if (combo <= 10){
            if (combo % 3 == 0){
                return true;
            }
            return false;
        }
        if (combo <= 20){
            if (combo % 2 == 0){
                return true;
            }
            return false;
        }
        if (combo > 20){
            if (Global.random(1, 3) < 3 ){
                return true;
            }
            return false;
        }
        if (combo > 30){
            if (Global.random(1, 4) < 4 ){
                return true;
            }
            return false;
        }
        if (combo > 40){
            if (Global.random(1, 5) < 5 ){
                return true;
            }
            return false;
        }
        return true;
    }
}
