
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in))){
            System.out.println("Enter number of elements:");
            int T= Integer.parseInt(bufferedReader.readLine());
            Hashtable<String, Integer> dict = dictionaryConstruction(T);
            List<String> answer = writeToFileAndRecord(dict);
            for(int i=0;i<answer.size()-1;i++){
                System.out.println("Record is equal to: " + answer.get(i) +" "+answer.get(answer.size()-1));
            }
        }
        catch(IOException exception){
            System.out.println("Mistake!");
        }
    }
    public static Hashtable<String, Integer> dictionaryConstruction (int parameter) {
        try(BufferedReader Reader = new BufferedReader (new InputStreamReader(System.in))){
            Hashtable<String, Integer> dict = new Hashtable<String, Integer>();
            boolean isKeyPresent;
            for(int j=1;j<=parameter;j++){
                String[] words = Reader.readLine().split(" ");
                isKeyPresent = dict.containsKey(words[0]);
                if (isKeyPresent){
                    dict.put(words[0], dict.getOrDefault(words[0], 0) + Integer.parseInt(words[1]));
                }
                else{
                    dict.put(words[0], Integer.parseInt(words[1]));
                }
            }
            return dict;
        }
        catch(IOException exception){
            System.out.println("Mistake!");
            Hashtable<String, Integer> dict = new Hashtable<String, Integer>();
            return dict;
        }
    }
    public static List<String> writeToFileAndRecord (Hashtable<String, Integer> dict) {
        Set<String> years = dict.keySet();
        try{
            FileWriter f0 = new FileWriter("output.txt");
            String newLine = System.getProperty("line.separator");
            int max_value = 0;
            List<String> years_max = new ArrayList<String>(); //здесь создаю список, чтобы если несколько дат с максимумом есть, то их можно вывести
            for(String key : years) {
                f0.write(key + " " + String.valueOf(dict.get(key)) + newLine);
                if (dict.get(key)>max_value){
                    max_value=dict.get(key);
                    years_max.clear();
                    years_max.add(key);
                }
                else if (dict.get(key)==max_value){
                    years_max.add(key);
                }
            }
            years_max.add(String.valueOf(max_value));
            f0.close();
            return years_max;
        }
        catch(IOException exception){
            System.out.println("Mistake!");
            List<String> years_max = new ArrayList<String>();
            return years_max;
        }
        
    }
}
         
