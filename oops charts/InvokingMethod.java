package OOPS;

import java.io.IOException;

public class InvokingMethod {
    public static void main(String[] args) throws IOException {
//        DifferentCharts sd = new DifferentCharts();
//        sd.areaChart();
//        sd.barGraph();
//        sd.pieChart();

        InterfaceChart ID = new DifferentCharts();
        ID.pieChart();
        ID.areaChart();
        ID.barGraph();
    }
}