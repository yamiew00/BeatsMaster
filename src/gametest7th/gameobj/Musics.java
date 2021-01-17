package gametest7th.gameobj;

public class Musics {
    private String path;
    private String name;
    private int bpm;
    private int playTime;
    private String category;
    
    public Musics(String name, int bpm, int playTime,String path, String category){
        this.name = name;
        this.bpm = bpm;
        this.playTime = playTime;
        this.path = path;
        this.category = category;
    }
    
    public String getName(){
        return name;
    }
    public int getBpm(){
        return bpm;
    }
    public int getPlayTime(){
        return playTime;
    }
    public String getPath(){
        return "/" + category + "/" + path;
    }
    public String getCategory(){
        return category;
    }
}
