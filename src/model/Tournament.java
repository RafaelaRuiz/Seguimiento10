package model;

import java.io.*;
import java.util.*;

import exceptions.*;

public class Tournament {
    private ArrayList<Country> participant;

    public Tournament() {
        participant= new ArrayList<>();
    }

    public void addCountry(String name, String medal, int amount){
        boolean search;
        if(amount<0){
            throw new InvalidNumberException();
        }else{
            if(!(medal.equalsIgnoreCase("oro")||medal.equalsIgnoreCase("plata")||medal.equalsIgnoreCase("bronce"))){
                throw new NotTypeMedalException();
            }else{
                search = searchCountry(name);
        
                if(search==false){
                    Country country = new Country(name);
                    this.participant.add(country);
                    country.medalAmount(medal, amount);
                    
                }else{
                    this.participant.get(getIndex(name)).medalAmount(medal, amount);
                }
            }
        }
    }

    private int getIndex(String name){
        int index=0;
        for(int i=0; i<participant.size(); i++){
            if(participant.get(i).getName().equalsIgnoreCase(name)){
                index=i;
            }
        }
        return index;
    }
    private boolean searchCountry(String name){
        boolean answer=false;
        for(int i=0; i<participant.size(); i++){
            if(participant.get(i).getName().equalsIgnoreCase(name)){
                answer=true;
            }
        }
        return answer;
    }

    public void showConventionalMedals(){
        System.out.println("======CONVENTIONAL CHART======");
        Collections.sort(participant);
        showCountries();
    }
    
    public void showCountries(){
        int maxNameLength = 0;
        for (Country country : participant) {
            int nameLength = country.getName().length();
            if (nameLength > maxNameLength) {
                maxNameLength = nameLength;
            }
        }

        System.out.printf("%-" + (maxNameLength + 1) + "s %s\t%s\t%s%n", "Name", "Gold", "Plate", "Bronze");

        for (Country country : participant) {
            System.out.printf("%-" + (maxNameLength + 1) + "s %d\t%d\t%d%n", country.getName(), country.getGold(), country.getPlate(), country.getBronze());
        }
    }
    public void showTotalMedals(){
        System.out.println("======TOTAL MEDALS CHART======");
        Collections.sort(participant, (a,b)-> {
            return b.totalMedals()-a.totalMedals();
        });
        showCountries();
    }

    public void showAlphabetical(){
        sort();
        System.out.println("======ALPHABET CHART======");
        showCountries();
    }

    public void sort(){
        for(int j=0; j<participant.size()-1; j++){
            for(int i=1; i<participant.size();i++){
                if((participant.get(i).getName().compareTo(participant.get(i-1).getName())<0)){
                    Country tempKey = participant.get(i);
                    participant.set(i, participant.get(i-1)); 
                    participant.set(i-1, tempKey); 
                }
            }
        }
    }

    static String folder = "data";
    static String path= "data/data.txt";
    public void save() throws IOException{
        File fold = new File(folder);
            if (!fold.exists()) {
                fold.mkdirs();
            }
        File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            String data = "";
            for(int i=0; i<participant.size(); i++){
                data+=participant.get(i).getName()+"::"+participant.get(i).getGold()+"::"+participant.get(i).getPlate()+"::"+participant.get(i).getBronze()+"\n";
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(data);
            writer.flush();
    
            fos.close();
    }
    public void load() throws IOException{
        File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
        File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line = "";
            while((line = reader.readLine()) != null ){
                String[] arr = line.split("::");
                Country enterCountry = new Country(arr[0]);
                participant.add(enterCountry);
                enterCountry.setGold(Integer.parseInt(arr[1]));
                enterCountry.setPlate(Integer.parseInt(arr[2]));
                enterCountry.setBronze(Integer.parseInt(arr[3]));
                
            }
        

    }




    public ArrayList<Country> getParticipant() {
        return participant;
    }

    public void setParticipant(ArrayList<Country> participant) {
        this.participant = participant;
    }
    
}
