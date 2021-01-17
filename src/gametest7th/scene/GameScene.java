package gametest7th.scene;

import gametest7th.controllers.SceneController;
import gametest7th.gameobj.*;
import gametest7th.utils.*;
import gametest7th.gameobj.ShowScore;
import gametest7th.gameobj.ShowTime;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class GameScene extends Scene{
    private ArrayList<CharStream> charStream;
    
        //測試用
            //反鍵間隔
            int period = 4;
            //hp歸零後用的
            Delay delay = new Delay(180);
            //提早結束功能
            Glitch glitch = new Glitch(80);
        //測試用
    
    //歌曲相關
    private final Musics music;    //哪一首歌
    AudioStream song;
    
    //節拍時程表
    BeatTimeList btl;
    private int bpm;
    
    //評等(rate)相關
    private int rateOrder = 0;
    private boolean oneTrial; //每個箭頭只能有一次按壓方向鍵的機會
    
    //Miss 相關
    private boolean isMiss;
    private boolean timeOutMiss = true;    //時間到卻沒按壓則判斷為miss
    private int resetOrder = 0;
    private int missCount;          //miss總數
    
    //good perfect 相關
    private boolean isPerfect;
    private boolean isGood;
    private int perfectCount;       //perfect總數
    private int goodCount;          //good總數
    
    //反鍵相關
    private int missCombo;          //若missCombo達到2則製造反鍵
    
    //計分
    private Score score;
    private boolean isGetScore;       //紀錄得分變化
    private int deltaScore;          //紀錄得分變化
    
    //combo
    private int combo = 0;
    private int maxCombo = 0;
    private ComboIcon comboIcon;
    private ComboX combox;
    private ComboNum comboNum;
    
    //rateicon
    private final RateIcon perfectIcon = new RateIcon(Rate.PERFECT, 350,650,300,120);
    private final RateIcon goodIcon = new RateIcon(Rate.GOOD, 350,650,300,120);
    private final RateIcon missIcon = new RateIcon(Rate.MISS, 1230,500,300,120);
    
    //時程 工作列表相關
    private ArrayList<Float> beatTimeList;
    private ArrayList<Integer> directionList = new ArrayList<>();
    private ArrayList<Arrows> arrowsList;
    private int order;
    
    //畫面相關
    private Spotlight spotlight;//中間背景
    private ShowScore showscore;//秀分數
    private ShowTime showtime;//秀時間
    
    //小節線
    ArrayList<MeasureLine> ml = new ArrayList<>();
    private int mlOrder = 1;
    
    //玩家
    private Panda panda;
    private int hp;
    private ArrayList<Hp> heart = new ArrayList<>();
    //敵人
    private int enemyX = Global.SCREEN_X - 200;
    private int enemyY = Global.SCREEN_Y - 300;
    private Monster monster;
    private MonsterBlood monsterBlood;
    private ShowScore monsterHp;
    
    //特效
    private ArrayList<FireRoundEffect> fireRoundEffectList = new ArrayList<FireRoundEffect>();
    private ArrayList<Arrows> attack = new ArrayList<>();
   
    //constructor
    public GameScene(Musics music){
        this.music = music;
        bpm = music.getBpm();
    }
    
    @Override
    public void sceneBegin() {
        //參數初始化                
        spotlight = new Spotlight();
        score = new Score();

        comboIcon = new ComboIcon(250,500,400,200);
        combox = new ComboX(450,500,100,100);
        comboNum = new ComboNum(700,500,400,200);
        panda = new Panda(480, 870, 250, 250);

        //建構時序表
        btl = new BeatTimeList( (int) ( (bpm / 60f) * (music.getPlayTime() / 4)  - 2 ),bpm);
        btl.setNull(0);
        btl.setNull(1);
        btl.print(btl.getBeatTimes());
        beatTimeList = btl.getBeatTimes();
        
        //背景與分數
        charStream = new ArrayList<>();
        showscore = new ShowScore(1250,125,300,50,0);
        showtime = new ShowTime(300,100,50,50,0);   

        //玩家
        hp = 3;
        heart.add(new Hp(50,600));
        heart.add(new Hp(110,600));
        heart.add(new Hp(170,600));

        //敵人
        monster = new Monster(Global.monsterX, Global.monsterY+150 ,250,250);
        monsterBlood = new MonsterBlood(Global.monsterX, Global.monsterY - 60,100, 10);
        monster.setMaxHp((int) ((btl.size() / 10f) * 1150));
        monsterBlood.setLimit(monster.getEnemyHp());
        monsterHp = new ShowScore(Global.monsterX-50,Global.monsterY-45,100,30,monster.getEnemyHp());

        
        //產生方向及箭頭
        for(int i = 0; i < beatTimeList.size();i++){
            directionList.add(Global.random(1, 4));
        }
        arrowsList = new ArrayList<>();
        
        //播放音樂
        try {
                    song=new AudioStream(new FileInputStream(".\\src\\resources\\music\\"
                                                            + music.getPath()) 
                                        );
                    AudioPlayer.player.start(song);
                    Global.startTime= System.nanoTime();
                    //定義perfect good miss 區間
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
        //敵人
        monster.paint(g);
        monsterBlood.paintComponent(g);
        monsterHp.paintComponent(g);
        
        //血量
        for (int i = 0; i < heart.size(); i++){
            heart.get(i).paint(g);
            if(heart.get(i).getState() && heart.get(i).getCount() > 50){
                heart.remove(i);
            }
        }
        
        //背景
        for (int i = 0; i < charStream.size(); i++) {
            charStream.get(i).paint(g);
        }
        spotlight.paintComponent(g);
      
       //遊戲時間
       showtime.paintComponent(g);
       
       //若perfect則運行...
       if (isPerfect){
           perfectIcon.paint(g);
       }
       //若good則運行...
       if (isGood ){
           goodIcon.paint(g);
       }
       //若miss則運行...
       if (isMiss ){
           missIcon.paint(g);
       }
       
       //總分
       g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().showscore()),1100 ,100, 50, 50, null);
       showscore.paintComponent(g);
       
       //combo
       if (combo > 1){
            comboNum.setCombo(combo);
            comboIcon.paintComponent(g);
            combox.paintComponent(g);
           comboNum.paintComponent(g);
       }
       
       //畫出箭頭
       for (int i = 0; i < arrowsList.size(); i++){
           arrowsList.get(i).paintComponent(g);
       }
        
       //外框 + 檢測區
       int diameter = 140;
       g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().fireRound())
                ,Global.SCREEN_X/2 - diameter / 2
                , 825   //圖檔問題 比預設位置低5單位
                , diameter
                , diameter
                , null);
       //玩家攻擊
       for (int i = 0; i < attack.size(); i++){
           attack.get(i).paintComponent(g);
       }
       
       //特效
        for (int i = 0; i < fireRoundEffectList.size(); i++){
            fireRoundEffectList.get(i).paint(g);
       }
        
       //小節線
       for (int i = 0; i < ml.size();i++){
           ml.get(i).paint(g);
        }
       
       //玩家
       panda.paintComponent(g);
       
       //提早切場景
       g.setColor(Color.red);
        if (!glitch.getSignal()){
            g.drawString("按 Ctrl 提早結束", Global.monsterX - 200, Global.monsterY - 100);
        }
    }

    @Override
    public void update() {
        //特效
        for (int i = 0; i < fireRoundEffectList.size(); i++){
            fireRoundEffectList.get(i).update();
        }
        for (int i = 0; i < fireRoundEffectList.size(); i++){
            if (fireRoundEffectList.get(i).getEffectOver()){
                 fireRoundEffectList.remove(i--);
            }
        }

        //攻擊
        for (int i = 0; i < attack.size(); i++){
            attack.get(i).update();
        }
        if (attack.size() > 0){
            if (attack.get(0).isCollision(monster)){
                monster.setState(true);
                attack.remove(0);
             }
        }

        //敵人
        monster.setEnenmyHp(score.getScore());
        monsterBlood.setHp(monster.getEnemyHp());
        monsterHp.setScore(monster.getEnemyHp());

       //小節線
        for (int i = 0; i < ml.size();i++){
            ml.get(i).update();
        }
        if (Global.passedTime() - Global.DELAY_SEC >= ( (4 * 60f) * mlOrder )/ bpm - Global.spendTime()){
            ml.add(new MeasureLine(Global.Player.SINGLE));
            mlOrder ++;
        }
                
        
        //提早切場景
        if (monster.getEnemyHp() == 0){
            glitch.glitch();
        }
        //血量
        for(int i=0;i<heart.size();i++){
            heart.get(i).update();
        }
        
        //按照beatTimeList的時序產生箭頭
        if (order < btl.size()){
            if (Global.passedTime() - Global.DELAY_SEC >= beatTimeList.get(order) - Global.spendTime()){   //spendtime為箭頭自產生到檢測區所需的時間
                arrowsList.add(new Arrows(directionList.get(order++), Global.Player.SINGLE));
            }
        }
        for (int i = 0; i < arrowsList.size(); i++){
            arrowsList.get(i).update();
        }
        
        //背景及分數
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
        showscore.setScore(score.getScore());
        showtime.setTime( Global.countDownClock(music.getPlayTime()) );
        spotlight.switchLight(combo);
        showscore.update();
        
        //若perfect則運行...
       if (isPerfect){
           panda.update();
       }
       //若good則運行...
       if (isGood ){
           panda.update();
       }
       //若miss則運行...
       if (isMiss ){
           
       }
       
        //p點前 (Global.MISS_INTERVAL / 2) 秒回復相關變數狀態
        if (resetOrder < btl.size()){
            if (Global.passedTime()  - Global.DELAY_SEC >= beatTimeList.get(resetOrder) - Global.MISS_INTERVAL / 2){
                resetOrder++;
                timeOutMiss = true;
                isMiss = false;
                isPerfect = false;
                isGood = false;
                oneTrial = false;
                isGetScore = false;
                deltaScore = 0;
            }
        }
        
        //p點後 (Global.MISS_INTERVAL / 2) 秒沒做動作則miss (timeout 造成的miss)
       if (rateOrder < btl.size()){     
            if (Global.passedTime()  - Global.DELAY_SEC >= beatTimeList.get(rateOrder) + Global.MISS_INTERVAL / 2){  //超過p點Global.MISS_INTERVAL / 2秒就判定為miss
                //沒按到反鍵 扣血
                rateOrder++;
                if ( timeOutMiss ){
                    missEvent();
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
            //1 2 3 4 對應 左 上 右 下
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                float now = Global.decimal(Global.passedTime() - Global.DELAY_SEC);
                if (!oneTrial && btl.tellNotMiss(now, rateOrder)){
                    oneTrial = true;
                    if (commandCode ==   ( (directionList.get(rateOrder) - 1) % 4) + 1 ){
                        //debug用
                        if(Global.IS_DEBUG){
                            System.out.println(Global.passedTime() - Global.DELAY_SEC);
                        }
                        
                        //判斷可按箭頭的時間點 且 perfect
                        if (btl.tellPerfect(now, rateOrder) ){
                            perfectEvent();
                        }
                        //非perfect 且非 miss, 則good 
                        else{
                            goodEvent();
                        }
                        isMiss = false;
                        timeOutMiss = false;
                    }
                    else{
                        //按錯方向鍵,目前處理方式視同完全沒按等待timeout,之後可以考慮錯誤動畫
                    }
                }
                
                //提早結算
                if (commandCode == 6 && monster.getEnemyHp() == 0){
                    AudioPlayer.player.stop(song);
                    toSettleScene();
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
        monster.setSettle(true);
        SceneController.instance().change(new SettlementScene(maxCombo,perfectCount,goodCount,missCount,score.getScore(),monster,music));
    }
    
    //事件處理
    public void perfectEvent(){
            //perfect處理
            perfectCount ++;
            isPerfect = true;
            isGetScore = true;

            attack.add(new Arrows(arrowsList.get(0).getX()
                                ,arrowsList.get(0).getY()
                                , arrowsList.get(0).getImg()
                                ,18 + 4 * combo));
            arrowsList.remove(0);

            missCombo = 0;
            
            //特效
            fireRoundEffectList.add(new FireRoundEffect(new Rect (Global.SCREEN_X/2 - 140 / 2
                                                                    , 825
                                                                    ,Global.SCREEN_X/2 - 140 / 2+ 140
                                                                    ,825 + 140))
                                        );
            
            //音效
            if (combo > 0 && combo % 10 == 0){
                Sound.cheer();
            }
            //combo處理
            combo ++;
            
            
            
            if (combo > maxCombo){
                maxCombo = combo;
            }
            //普通難度下 每4combo 產生反鍵
            
            if (Global.difficulty == Global.Difficulty.NORMAL && combo > 0  && combo % period == 0){
                directionList.set(order , Global.random(5, 8));
            }
            if (Global.difficulty == Global.Difficulty.HARD && combo > 0  && combo % period == 0){
                period = Global.random(4, 6);
                directionList.set(order , Global.random(5, 8));
                
            }
            if (Global.difficulty == Global.Difficulty.HARD && combo > 0  && combo % period == 0){
                period = Global.random(4, 6);
                directionList.set(order , Global.random(5, 8));
                
            }
            panda.setAttack(true);
            //計分
            score.tranScore(Rate.PERFECT,combo);
            deltaScore = score.getDeltaScore(Rate.PERFECT, combo);
    }
    public void goodEvent(){
            //good處理
            period = 4;
            goodCount ++;
            isGood = true;
            combo = 0;
            isGetScore = true;
            

            attack.add(new Arrows(arrowsList.get(0).getX()
                                ,arrowsList.get(0).getY()
                                , arrowsList.get(0).getImg()
                                ,18 + 4 * combo));
            arrowsList.remove(0);

            missCombo = 0;
            panda.setAttack(true);
            //計分
            score.tranScore(Rate.GOOD, combo);
            deltaScore = score.getDeltaScore(Rate.GOOD, combo);
    }
    public void missEvent(){
        Sound.miss();
        period = 4;
        //miss處理
            //若反鍵miss則扣血
            if (directionList.get(rateOrder - 1) > 4){ 
                if (hp == 0){
                    AudioPlayer.player.stop(song);
                    toSettleScene();
                }else{
                    heart.get(hp-1).setState(true);
                }
                //血量歸零則提前結束
                hp--;
            }
            //miss參數處理
            missCount ++;
            isMiss = true;
            combo = 0;
            arrowsList.remove(0);
        //反鍵處理  兩miss則產生反鍵
        missCombo ++;
        if (missCombo == 2){
            missCombo = 0;
            directionList.set(order , Global.random(5, 8));
        }
    }
}
