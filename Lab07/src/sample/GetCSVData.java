package sample;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class GetCSVData {
    private String inputCSV;
    private TreeMap<String, Integer> csvMap;

    public GetCSVData(String file){
        inputCSV = file;
        csvMap = new TreeMap<>();
        // put all the strings to be counted in a map
        setcsvMap();
    }

    private void setcsvMap() {
        // set each string data and initial count
        csvMap.put("FLASH FLOOD", 0);
        csvMap.put("SEVERE THUNDERSTORM", 0);
        csvMap.put("SPECIAL MARINE", 0);
        csvMap.put("TORNADO", 0);
    }

    // load in the csv file
    public TreeMap csvFileParse() throws IOException {
        FileReader fileReader = new FileReader(inputCSV);

        BufferedReader input = new BufferedReader(fileReader);

        // read the headers of the file
        String line = input.readLine();

        while(line != null){
            String []arrayWords = line.split(",");
            //increment the map for each string in the column
            int count = csvMap.get(arrayWords[5]); // line at column 6
            count +=1;
            csvMap.put(arrayWords[5], count);
            line = input.readLine();
        }
        return csvMap;
    }
}
