package gametest7th.utils;

import static gametest7th.utils.Global.*;
import java.util.ArrayList;

public class BeatTimeList extends ArrayList{
        private ArrayList<Float> beatTimeList;
        private int measure;
        private int bpm;
        private float[][] beats;
        private int count;
        
        
        //constructor
        public BeatTimeList(int measure, int bpm){
            count = 0;
            beatTimeList = new ArrayList<>();
            this.measure = measure;
            this.bpm = bpm;
            beats = new float[measure][];
            
            for (int i = 0; i < measure; i +=2){
                int random = Global.random(1,5);
                beats[i] = genBeats(i,random);
                if (i+1 <= measure - 1){
                    beats[i+1] = genBeats(i+1,random);
                }
            }
            
            for (int i =0; i < beats.length;i++){
                for(int j = 0; j < beats[i].length; j++){
                    count++;
                }
            }
            
        }
        
        
        public ArrayList getBeatTimes(){
            
            for (int i = 0; i < beats.length; i++){
                for (int j = 0; j < beats[i].length; j++){
                    if (beats[i][j] != -1){
                        beatTimeList.add(beats[i][j]);
                    }
                }
            }
            return beatTimeList;
        }
        
        //每個小節隨機生成節拍
        public float[] genBeats(int measure){
            int random = Global.random(1, 5);
            float[] measureArr = new float[1];
            switch (random){
                //4連拍
                case 1:
                    measureArr = new float[4];
                    measureArr[0] = decimal( 4 * (60f / bpm) * measure) ;
                    measureArr[1] = decimal( 4 * (60f / bpm) * measure + (60f / bpm)) ;
                    measureArr[2] = decimal( 4 * (60f / bpm) * measure + 2 * (60f / bpm));
                    measureArr[3] = decimal( 4 * (60f / bpm) * measure + 3 * (60f / bpm));
                    return measureArr;
            
                case 2:
                    measureArr = new float[2];
                    measureArr[0] = decimal(4 * (60f / bpm) * measure);
                    measureArr[1] = decimal(4 * (60f / bpm) * measure + 2 * (60f / bpm));
                    return measureArr;
                    
                case 3:
                    measureArr = new float[3];
                    measureArr[0] = decimal(4 * (60f / bpm) * measure);
                    measureArr[1] = decimal(4 * (60f / bpm) * measure + (60f / bpm));
                    measureArr[2] = decimal(4 * (60f / bpm) * measure + 3 * (60f / bpm));
                    return measureArr;
                
                case 4:
                    measureArr = new float[3];
                    measureArr[0] = decimal(4 * (60f / bpm) * measure);
                    measureArr[1] = decimal(4 * (60f / bpm) * measure + 2 * (60f / bpm));
                    measureArr[2] = decimal(4 * (60f / bpm) * measure + 3 * (60f / bpm));
                    return measureArr;
                case 5:
                    measureArr = new float[2];
                    measureArr[0] = decimal(4 * (60f / bpm) * measure);
                    measureArr[1] = decimal(4 * (60f / bpm) * measure + (60f / bpm));
                    return measureArr;
            }
            return measureArr;
        }
        
        //指定第measure小節為第random種拍子
        public float[] genBeats(int measure, int random){
            
            float[] measureArr = new float[1];
            switch (random){
                //4連拍
                case 1:
                    measureArr = new float[4];
                    measureArr[0] = decimal( 4 * (60f / bpm) * measure) ;
                    measureArr[1] = decimal( 4 * (60f / bpm) * measure + (60f / bpm)) ;
                    measureArr[2] = decimal( 4 * (60f / bpm) * measure + 2 * (60f / bpm));
                    measureArr[3] = decimal( 4 * (60f / bpm) * measure + 3 * (60f / bpm));
                    return measureArr;
            
                case 2:
                    measureArr = new float[2];
                    measureArr[0] = decimal(4 * (60f / bpm) * measure);
                    measureArr[1] = decimal(4 * (60f / bpm) * measure + 2 * (60f / bpm));
                    return measureArr;
                    
                case 3:
                    measureArr = new float[3];
                    measureArr[0] = decimal(4 * (60f / bpm) * measure);
                    measureArr[1] = decimal(4 * (60f / bpm) * measure + (60f / bpm));
                    measureArr[2] = decimal(4 * (60f / bpm) * measure + 3 * (60f / bpm));
                    return measureArr;
                
                case 4:
                    measureArr = new float[3];
                    measureArr[0] = decimal(4 * (60f / bpm) * measure);
                    measureArr[1] = decimal(4 * (60f / bpm) * measure + 2 * (60f / bpm));
                    measureArr[2] = decimal(4 * (60f / bpm) * measure + 3 * (60f / bpm));
                    return measureArr;
                case 5:
                    measureArr = new float[2];
                    measureArr[0] = decimal(4 * (60f / bpm) * measure);
                    measureArr[1] = decimal(4 * (60f / bpm) * measure + (60f / bpm));
                    return measureArr;
            }
            return measureArr;
        }
        
        
        public void setNull(int measure){
            count -= beats[measure].length;
            for (int i = 0; i < beats[measure].length;i++){
                beats[measure][i] = -1;
            }
            
        }
        
        
        //測試印出
        public void print(ArrayList al){
            for (int i = 0; i < al.size(); i++){
                System.out.println(al.get(i));
            }
        }
        
        //判斷是否為可以按方向鍵的時間點
        public boolean tellNotMiss(float now, int order){
            return now >= beatTimeList.get(order) - Global.MISS_INTERVAL / 2 && now <= beatTimeList.get(order) + Global.MISS_INTERVAL / 2;
        }
        public boolean tellPerfect(float now, int order){
            return now >= beatTimeList.get(order) - Global.PERFECT_INTERVAL / 2 && now <= beatTimeList.get(order) + Global.PERFECT_INTERVAL / 2;
        }
        
        @Override
        public int size(){
            return count;
        }
}
