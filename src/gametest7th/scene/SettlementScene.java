package gametest7th.scene;
import gametest7th.controllers.SceneController;
import gametest7th.gameobj.Monster;
import gametest7th.gameobj.Musics;
import gametest7th.gameobj.ShowScore;
import gametest7th.utils.CommandSolver;
import gametest7th.utils.Global;
import gametest7th.utils.Path;
import gametest7th.utils.Sound;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class SettlementScene extends Scene{
    //分數顯示
    private ShowScore maxComboA;
    private ShowScore perfectCountA;
    private ShowScore goodCountA;
    private ShowScore missCountA;
    private ShowScore scoreA;
    private ShowScore maxComboB;
    private ShowScore perfectCountB;
    private ShowScore goodCountB;
    private ShowScore missCountB;
    private ShowScore scoreB;
    private ShowScore monsterHp;
    private ShowScore monsterMaxHp;
    //怪獸血量
    private Monster monster;
    //待確定位置基準
    private int widthOut = 1100;
    private int heightOut = 750;
    private int X_out = Global.SCREEN_X / 2 - widthOut / 2;
    private int Y_out = 80;
    private  int widthIn = widthOut * 8 / 10;
    private int heightIn = heightOut * 8 / 10;
    private int X_in = Global.SCREEN_X / 2 - widthIn / 2;
    private int Y_in = Y_out + (heightOut - heightIn) / 2; 
    private int firstSegment = 110;
    private int firstInterval = 120;
    private int iconY = Y_in + firstInterval / 2;
    private int player1Y = Y_in + firstInterval + (heightIn - firstInterval) / 2 / 2;
    private int player2Y = Y_in + firstInterval + (heightIn - firstInterval) / 2 + (heightIn - firstInterval) / 2 / 2;
    private int playerX = X_in + firstSegment / 4;
    private int maxComboX = X_in + firstSegment + (widthIn - firstSegment) / 5 / 4;
    private int perfectX = X_in + firstSegment + (widthIn - firstSegment) / 5 + (widthIn - firstSegment) / 5 / 3;
    private int goodX = X_in + firstSegment + 2 * (widthIn - firstSegment) / 5 + (widthIn - firstSegment) / 5 / 3;
    private int missX = X_in + firstSegment + 3 * (widthIn - firstSegment) / 5 + (widthIn - firstSegment) / 5 / 3;
    private int scoreX = X_in + firstSegment + 4 * (widthIn - firstSegment) / 5 + (widthIn - firstSegment) / 5 / 3;
    //選項
    Image again;
    Image menu;
    Image home;
    Image outRing;
    private int option;
    //音樂
    Musics music;
     
    //單人建構子
    public SettlementScene(int maxcombo,int perfectCount, int goodCount,int missCount,int score,Monster monster, Musics music){
      
        this.maxComboA = new ShowScore(maxComboX,player1Y,120,80,maxcombo);
        this.perfectCountA = new ShowScore(perfectX, player1Y,120,80,perfectCount);
        this.goodCountA = new ShowScore(goodX, player1Y,120,80,goodCount);
        this.missCountA = new ShowScore(missX, player1Y,120,80,missCount);
        this.scoreA = new ShowScore( scoreX, player1Y,120,80,score);
        this.monster = monster;
        this.monsterHp = new ShowScore( missX, player2Y,120,80,monster.getEnemyHp());;
        this.monsterMaxHp = new ShowScore( scoreX, player2Y,120,80,monster.getMaxHp());;
        this.music = music;
        System.out.println(missCount+"miss");
        //若獲勝 
//        int overkill = score - monster.getMaxHp();  //多造成的傷害
        
        //若失敗
//        int enemyHp = monster.getHp(score);        //怪物剩餘血量
    }
    
    
    //雙人結算建構子
    public SettlementScene(int maxcombo,int perfectCount, int goodCount,int missCount,int score
                        ,int maxcombo2, int perfectCount2, int goodCount2, int missCount2, int score2
                        , Musics music){
      
        this.maxComboA = new ShowScore(maxComboX,player1Y,120,80,maxcombo);
        this.perfectCountA = new ShowScore(perfectX, player1Y,120,80,perfectCount);
        this.goodCountA = new ShowScore(goodX, player1Y,120,80,goodCount);
        this.missCountA = new ShowScore(missX, player1Y,120,80,missCount);
        this.scoreA = new ShowScore( scoreX, player1Y,120,80,score);
        this.maxComboB = new ShowScore(maxComboX,player2Y,120,80,maxcombo2);
        this.perfectCountB = new ShowScore(perfectX, player2Y,120,80,perfectCount2);
        this.goodCountB = new ShowScore(goodX, player2Y,120,80,goodCount2);
        this.missCountB = new ShowScore(missX, player2Y,120,80,missCount2);
        this.scoreB = new ShowScore( scoreX, player2Y,120,80,score2);
        this.music = music;
        //若獲勝 
//        int overkill = score - monster.getMaxHp();  //多造成的傷害
        
        //若失敗
//        int enemyHp = monster.getHp(score);        //怪物剩餘血量
    }
    
    @Override
    public void sceneBegin() {
        if(monster != null && monster.getEnemyHp() > 0){
            Sound.fail();
        }
        else{
            Sound.win();
        }
        if (monster!= null){
            monster.setPlace(-800, -220);
        }
        again = SceneController.instance().irc().tryGetImage(new Path().img().objs().again());
        menu = SceneController.instance().irc().tryGetImage(new Path().img().objs().menu());
        home = SceneController.instance().irc().tryGetImage(new Path().img().objs().home());
        outRing = SceneController.instance().irc().tryGetImage(new Path().img().objs().outRing());
    }

    @Override
    public void sceneEnd() {
        
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().settle1()),
                0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
        g.setColor(Color.white);
        scoreBoard(g);
        
        
//        g.setColor(Color.red);
//        g.drawString("HP:"+monster.getEnemyHp(),missX, player2Y);
        //選項
        g.drawImage(again, goodX  - 350 , 800 , 100, 100, null);
        g.drawImage(menu, goodX + 10, 800 +10, 80, 80, null);
        g.drawImage(home, goodX + 350 +10 , 800 +10, 80, 80, null);
        g.drawImage(outRing, goodX - 350 -10 + 350 * option, 800 -10, 120, 120, null);
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", Font.BOLD,30));
        g.drawImage(again, goodX  - 350 , 800 , 100, 100, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().playagain()),490, 910 , 150, 80, null);
        g.drawImage(menu, goodX + 10, 800 +10, 80, 80, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().backMenu()),820, 910, 200, 80, null);
        g.drawImage(home, goodX + 350 +10 , 800 +10, 80, 80, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().backgrounds().backHome()),1170 , 910 , 200, 80, null);
        g.drawImage(outRing, goodX - 350 -10 + 350 * option, 800 -10, 120, 120, null);
        g.setColor(Color.white);
    }

    @Override
    public void update() {
        if (monster!= null){
            monster.update();
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
                if (commandCode == 1 && option > 0){
                    option --;
                }
                if (commandCode == 3 && option < 2){
                    option ++;
                }
                
                if (commandCode == 5){
                    switch(option){
                        case 0:
                            switch(Global.player){
                                case SINGLE:
                                    SceneController.instance().change(new GameScene(music));
                                    break;
                                case PLAYER2:
                                    SceneController.instance().change(new PvpScene(music));
                                    break;
                            }
                            break;
                        case 1:
                            SceneController.instance().change(new MenuScene());
                            break;
                        case 2:
                            SceneController.instance().change(new MainScene());
                            break;
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
    
    //結算框
    public void scoreBoard(Graphics g){
    
        //外框
        
//        g.drawRect(X_out, Y_out, widthOut, heightOut);
        
        //內框
        
//        g.drawRect(X_in, Y_in, widthIn, heightIn);
        
//        //橫線
//            //第一條線
//            
//            g.drawLine(X_in, Y_in + firstInterval , X_in + widthIn, Y_in + firstInterval);
//
//            //第二條線
//            g.drawLine(X_in         , Y_in + firstInterval + (heightIn - firstInterval) / 2
//                    , X_in + widthIn, Y_in + firstInterval + (heightIn - firstInterval) / 2);
//            
//        //直線
//            //第一條線
//            
//            g.drawLine(X_in + firstSegment, Y_in ,X_in + firstSegment,
//                    Y_in + firstInterval + (heightIn - firstInterval) / 2);
//            
//            //第二條線
//            g.drawLine(X_in + firstSegment + (widthIn - firstSegment) / 5,
//                    Y_in ,X_in + firstSegment + (widthIn - firstSegment) / 5,
//                    Y_in + firstInterval + (heightIn - firstInterval) / 2);
//        
//            //第三條線
//            g.drawLine(X_in + firstSegment + 2 * (widthIn - firstSegment) / 5,
//                    Y_in ,X_in + firstSegment + 2 *  (widthIn - firstSegment) / 5,
//                    Y_in + firstInterval + (heightIn - firstInterval) / 2);
//            
//            //第四條線
//            g.drawLine(X_in + firstSegment + 3 * (widthIn - firstSegment) / 5, 
//                    Y_in ,X_in + firstSegment + 3 *  (widthIn - firstSegment) / 5,
//                    Y_in + firstInterval + (heightIn - firstInterval) / 2);
//            
//            //第五條線
//            g.drawLine(X_in + firstSegment + 4 * (widthIn - firstSegment) / 5,
//                    Y_in ,X_in + firstSegment + 4 * (widthIn - firstSegment) / 5,
//                    Y_in + firstInterval + (heightIn - firstInterval) / 2);
            
        
        //字樣
        
        //畫框
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().showplay1()), playerX - 10, player1Y - 25, 70, 50, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box3()), maxComboX - 50, iconY - 60, 170, 120, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), maxComboX - 50, player1Y - 120, 170, 220, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().maxCombo()), maxComboX - 10, iconY - 25, 100, 50, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box3()), perfectX - 50, iconY - 60, 170, 120, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), perfectX - 50, player1Y - 120, 170, 220, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().perfect()), perfectX, iconY - 25, 70, 50, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box3()), goodX - 50, iconY - 60, 170, 120, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), goodX - 50, player1Y - 120, 170, 220, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().good()), goodX, iconY - 25, 70, 50, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box3()), missX - 50, iconY - 60, 170, 120, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), missX - 50, player1Y - 120, 170, 220, null);;
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().miss()), missX, iconY - 25, 70, 50, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box3()), scoreX - 50, iconY - 60, 170, 120, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), scoreX - 50, player1Y - 120, 170, 220, null);
        g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().showBlueScore()), scoreX, iconY - 25, 70, 50, null);
        
        //雙人畫面
        if(scoreB != null){
            g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().showplay2()), playerX - 10, player2Y - 25, 70, 50, null);
            g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), maxComboX - 50, player2Y - 120, 170, 220, null);
            g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), perfectX - 50, player2Y - 120, 170, 220, null);
            g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), goodX - 50, player2Y - 120, 170, 220, null);
            g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), missX - 50, player2Y - 120, 170, 220, null);
            g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().box4()), scoreX - 50, player2Y - 120, 170, 220, null);
        }else{
            g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().hp()), goodX, player2Y - 25, 70, 50, null);
            g.drawImage(SceneController.instance().irc().tryGetImage(new Path().img().objs().slash()), scoreX - 50, player2Y - 25, 20, 50, null);
        }
        //詳細資料
            

        maxComboA.paintComponent(g);
        perfectCountA.paintComponent(g);
        goodCountA.paintComponent(g);
        missCountA.paintComponent(g);
        scoreA.paintComponent(g);
        //雙人模式
        if(monster == null){
            maxComboB.paintComponent(g);
            perfectCountB.paintComponent(g);
            goodCountB.paintComponent(g);
            missCountB.paintComponent(g);
            scoreB.paintComponent(g);
        }
        
        
        //敵人
        if (monster!= null){
            monster.paintComponent(g);
            monsterHp.paintComponent(g);
            monsterMaxHp.paintComponent(g);
        }
    }
}
