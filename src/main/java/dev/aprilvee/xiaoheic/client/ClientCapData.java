package dev.aprilvee.xiaoheic.client;

public class ClientCapData {
    private static int qi;
    private static int maxqi;

    private static float cultivation;
    private static float metalattunement;
    private static float waterattunement;
    private static float woodattunement;
    private static float fireattunement;
    private static float earthattunement;


    public static int getQi(){
        return qi;
    }
    public static int getMaxqi(){return maxqi;}
    public static float getCultivation(){return cultivation;}
    public static float getMetal(){return metalattunement;}
    public static float getWater(){return waterattunement;}
    public static float getWood(){return woodattunement;}
    public static float getFire(){return fireattunement;}
    public static float getEarth(){return earthattunement;}

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
        ClientCapData.metalattunement = set;
    }
    public static void setWater(float set){
        ClientCapData.waterattunement = set;
    }
    public static void setWood(float set){
        ClientCapData.woodattunement = set;
    }
    public static void setFire(float set){
        ClientCapData.fireattunement = set;
    }
    public static void setEarth(float set){
        ClientCapData.earthattunement = set;
    }



}
