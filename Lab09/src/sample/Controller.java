package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


public class Controller {
    private String url = "https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";

    private BufferedReader reader;

    private GraphicsContext gc;

    @FXML private Canvas canvas;

    private List<Double> stockx = new ArrayList<>();

    private List<Double> stocky = new ArrayList<>();


    @FXML public void initialize(){
        downloadStockPrices();
        drawLinePlot();
    }

    public void downloadStockPrices(){
        try{
            InputStream file = new URL(url).openStream();
            reader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
            String line;
            double num =0;
            int i=0;
            line = reader.readLine();
            while(line != null ){
                if (num >1){
                    String []arrayWords = line.split(",");
                    System.out.println(arrayWords[4]);
                    stocky.add(Double.valueOf(arrayWords[4]));
                    stockx.add(num*10);
                    System.out.println(""+stockx.get(i)+ stocky.get(i));
                    i++;
                }

                num++;
                line = reader.readLine();


            }

        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    public void drawLinePlot(){//(List stockx, List stocky){
        gc = canvas.getGraphicsContext2D();

        // draw x and y axis
        gc.setStroke(Color.BLUE);
        gc.strokeLine(0,0, 0, 825);
        gc.strokeLine(0,825, 850, 825);

        gc.setStroke(Color.RED);
        gc.strokeLine(0,825-stocky.get(0), 20.0, 825-stocky.get(0));

        // loop through List for x and y
        for(int i = 0; i < stockx.size()-1; i++) {
            // plot line of a data point
            plotLine(stockx.get(i), stocky.get(i), stockx.get(i+1),stocky.get(i+1));
        }



    }

    public void plotLine(double x, double y, double xf, double yf){
        gc.setStroke(Color.RED);
        gc.strokeLine(x,825-y,xf,825-yf);


    }
}
