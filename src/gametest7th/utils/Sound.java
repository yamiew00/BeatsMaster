package gametest7th.utils;

import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {
    private static final String startPath = ".\\src\\resources\\sounds\\";
    
    private static AudioStream testSound;
    
    public static void load(String sound){
        try {
            FileInputStream fis=new FileInputStream(startPath + sound + ".wav");
            AudioStream as=new AudioStream(fis);
            AudioPlayer.player.start(as);
        }catch (IOException e){
                e.printStackTrace();
        }
        
    }
    
    public static void button(){
        load("按鈕");
    }
    public static void good(){
        load("good");
    }
    public static void miss(){
        load("miss");
    }
    public static void perfect1(){
        load("perfect1");
    }
    public static void perfect2(){
        load("perfect2");
    }
    public static void perfect3(){
        load("perfect3");
    }
    public static void perfect4(){
        load("perfect4");
    }
    public static void perfect5(){
        load("perfect5");
    }
    public static void fail(){
        load("失敗場景");
    }
    public static void win(){
        load("勝利");
    }
    public static void cheer(){
        load("喝采");
    }
    
    public static void test(){
        if (testSound == null){
            try {
                FileInputStream fis=new FileInputStream(startPath + "perfect1" + ".wav");
                AudioStream testSound=new AudioStream(fis);
                AudioPlayer.player.start(testSound);
            }catch (IOException e){
                    e.printStackTrace();
            }
        }
        else{
           AudioPlayer.player.start(testSound);
           try {
                FileInputStream fis=new FileInputStream(startPath + "perfect1" + ".wav");
                AudioStream testSound=new AudioStream(fis);
            }catch (IOException e){
                    e.printStackTrace();
            }
        }
    }
}
