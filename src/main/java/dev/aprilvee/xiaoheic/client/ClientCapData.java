package dev.aprilvee.xiaoheic.client;

public class ClientCapData {
    private static int qi;
    private static int maxqi;

    private static float cultivation;
    private static float metalaspect;
    private static float wateraspect;
    private static float woodaspect;
    private static float fireaspect;
    private static float earthaspect;


    public static int getQi(){
        return qi;
    }
    public static int getMaxqi(){return maxqi;}
    public static float getCultivation(){return cultivation;}
    public static float getMetal(){return metalaspect;}
    public static float getWater(){return wateraspect;}
    public static float getWood(){return woodaspect;}
    public static float getFire(){return fireaspect;}
    public static float getEarth(){return earthaspect;}

    public static void setQi(int input){
        ClientCapData.qi = input;
    }
    public static void setMaxqi(int set){
        ClientCapData.maxqi = set;
    }
    public static void setCultivation(float set){
        ClientCapData.cultivation = set;
    }
    public static void setMetal(float set){
        ClientCapData.metalaspect = set;
    }
    public static void setWater(float set){
        ClientCapData.wateraspect = set;
    }
    public static void setWood(float set){
        ClientCapData.woodaspect = set;
    }
    public static void setFire(float set){
        ClientCapData.fireaspect = set;
    }
    public static void setEarth(float set){
        ClientCapData.earthaspect = set;
    }



}
