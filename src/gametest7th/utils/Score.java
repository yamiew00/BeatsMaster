package gametest7th.utils;
/*
    計分方式
    miss            0分
    good            100分
    perfect n combo  100 * (n + 4) / 4 分
*/
public class Score {
    private int score;
    
    public Score(){
        score = 0;
    }
    
    public int getScore(){
        return score;
    }
    public void tranScore(Rate rate, int combo){
        switch(rate){
            case MISS:
                break;
            case GOOD:
                score += 100;
                break;
            case PERFECT:
                score += ( 100 * (combo + 4) ) / 4;
                break;
        }
    }
    
    public int getDeltaScore(Rate rate, int combo){
        switch(rate){
            case MISS:
                return 0 ;
            case GOOD:
                return 100;
            case PERFECT:
                return ( 100 * (combo + 4) ) / 4;
        }
        return 0;
    }
    
}
