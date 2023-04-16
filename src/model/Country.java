package model;

public class Country implements Comparable<Country>{
    private String name;
    private int gold;
    private int plate;
    private int bronze;

    public Country(String name) {
        this.name = name;
        gold=0;
        plate=0;
        bronze=0;
    }
    
    public void medalAmount(String medal, int amount){
        if(medal.equalsIgnoreCase("oro")){
            this.gold = amount;
        }
        if(medal.equalsIgnoreCase("plata")){
            this.plate = amount;
        }
        if(medal.equalsIgnoreCase("bronce")){
            this.bronze = amount;
        }
    }
    public String getMedal(String medal){
        return medal;
    }
    public int totalMedals(){
        return this.gold+this.plate+this.bronze;
    }
    @Override
    public int compareTo(Country o) {
        int criteria;
        if((criteria=o.gold-this.gold)!=0){
            return criteria;
        }
        if((criteria=o.plate-this.plate)!=0){
            return criteria;
        }
        if((criteria=o.bronze-this.bronze)!=0){
            return criteria;
        }else{
            criteria=this.name.compareTo(o.name);
        }
        return criteria;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPlate() {
        return plate;
    }

    public void setPlate(int plate) {
        this.plate = plate;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }
}