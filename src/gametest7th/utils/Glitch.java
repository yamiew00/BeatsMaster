package gametest7th.utils;
// 按時間間隔回傳true,false
//預設從true開始
public class Glitch {
    private int period; //幀數
    private int time;   //次數
    private boolean signal;     //預設true 連閃開啟則固定間隔給false
    private boolean glitch;     // 連閃開始or結束
    private Delay delay;
    
    private boolean forever;
    
    public Glitch(int period,int time){
        forever = false;
        signal = true;
        glitch = false;             //連閃開關
        this.period = period;
        this.time = time;
        delay = new Delay(period);
        delay.loop();
    }
    
    public Glitch(int period){
        forever = true;
        signal = true;
        glitch = false;             //連閃開關
        this.period = period;
        this.time = 10;
        delay = new Delay(period);
        delay.loop();
    }
    
    public boolean getGlitch(){
        return glitch;
    }
    
    public boolean getSignal(){
        if(glitch){
            if(time == 0){
                glitch = false;
            }
            if (delay.count() && time > 0){
                if(signal){
                    signal = false;
                    if (!forever){
                        time --;
                    }
                }
                else{
                    signal = true;
                    if (!forever){
                        time --;
                    }
                }
            }
            return signal;
        }
        return signal;
    }
    
    public void glitch(){
        glitch = true;
    }
    public void stop(){
        glitch = false;
    }
}
