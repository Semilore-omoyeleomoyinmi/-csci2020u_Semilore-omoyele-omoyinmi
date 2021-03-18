package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

public class Controller {
private GetCSVData csvData = new GetCSVData("weatherwarnings-2015.csv");

private TreeMap<String, Integer> csvMap;


private Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};


@FXML
private GraphicsContext gc;

@FXML
private Canvas canvas = new Canvas(500, 400);

@FXML
    public void initialize() throws IOException {
        // get my graphics context
        gc = canvas.getGraphicsContext2D();
        csvMap = csvData.csvFileParse();
        // draw pieChart
        drawPiechart();
    }
    public void drawPiechart(){
    double initialAngle = 0;
    double sumInMap = 0;
    double pieAngle;

    // get the total sum of values in the map to be used in calculating the angle
    Set<String> keys = csvMap.keySet();
    for(String key: keys){
        sumInMap += csvMap.get(key);
    }


    /* y variable will increment the
     y position of the rectangles
     that represents the legend
     */
    int y = 0;
    int i = 0;
    for(String key: keys){
        pieAngle = (csvMap.get(key)/ sumInMap)*360;

        //fill shapes
        gc.setFill(pieColours[i]);
        gc.fillArc(480,0,300,300,initialAngle,pieAngle, ArcType.ROUND);
        gc.fillRect(90,y,18,18);

        // stroke lines
        gc.setFill(Color.BLACK);
        gc.strokeArc(480,0,300,300,initialAngle,pieAngle, ArcType.ROUND);
        gc.strokeRect(90,y,18,18);

        // text
        gc.fillText(key,120,y+15);

        initialAngle += pieAngle;
        i++;
        y+=37;
    }




    }
}
