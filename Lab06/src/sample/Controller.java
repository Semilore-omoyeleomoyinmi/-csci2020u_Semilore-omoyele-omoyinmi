// Correct package label
package sample;

// Needed imports
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {

    // Variables to set up application and to link to sample.fxml
    @FXML public GraphicsContext gc;
    @FXML private Canvas mCanvas;

    // All sample data code given
    private static double[] avgHousingPricesByYear = {247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7};
    private static double[] avgCommercialPricesByYear = {1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3};
    private static String[] ageGroups = {"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = {648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @FXML public void initialize(){
        gc = mCanvas.getGraphicsContext2D();
        PieChart(1000,purchasesByAgeGroup,pieColours);
        BarGraph(150,350, avgHousingPricesByYear,avgCommercialPricesByYear);
    }

    public void BarGraph(int w, int h, double[]data1, double []data2){

        // Variables
        double max_value = 0;
        double min_value = 0;
        double space = w / data1.length;
        double temp, temp2, temp3;
        double initial_point = 150;


        for (int i = 0; i < data1.length; i++) {

            if (i == 0) {
                max_value = data1[i];
                min_value = data1[i];
            }
            else if (data1[i] > max_value) {
                max_value = data1[i];
            }
            else if (data1[i] < min_value) {
                min_value = data1[i];
            }
        }

       for (int i = 0; i < data2.length; i++) {
            if (i == 0) {
                max_value = data2[i];
                min_value = data2[i];
            }
            else if (data2[i] > max_value) {
                max_value = data2[i];
            }
            else if (data2[i] < min_value) {
                min_value = data2[i];
            }
        }

        gc.setFill(Color.RED);
        for (int i= 0; i < data1.length; i++){
            temp = data1[i]/max_value;
            temp2 = temp * h;
            temp3 = h - temp2;
            gc.fillRect(initial_point - 100,temp3 + 20, space,temp2 + 20);
            initial_point += (space * 2.5);
        }

        initial_point = 150 + space;

        gc.setFill(Color.BLUE);

        for(int i = 0; i < data2.length; i++){
            temp = data2[i]/max_value;
            temp2 = temp * h;
            temp3 = h - temp2;
            gc.fillRect(initial_point - 100, temp3 + 20, space,temp2 + 20);
            initial_point += (space * 2.5);
        }
    }

    public void PieChart(int val, int[]array, Color[]colors){

        double result = 0;
        double initial_val = 0;
        double temp, temp2;

        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }

        for (int i = 0; i < array.length; i++) {
            temp = array[i] / result;
            temp2 = 360 * temp;
            gc.setFill(colors[i]);
            gc.fillArc(val / 2,0,300,300, initial_val,(temp2), ArcType.ROUND);
            initial_val += temp2;
        }
    }
}
