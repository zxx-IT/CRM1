package com.atguigu.ssm.crm.test;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestHandler {
	
	@RequestMapping("/freechart1")
	public void testFreeChart(HttpServletResponse response) throws IOException{
		
		PieDataset dataset = createDataSet();
		JFreeChart chart = createChart(dataset);
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 370, 360);
	}
	
	@RequestMapping("/freechart2")
	public String freechrtView(Map<String, Object> map){
		map.put("Java", 100);
		map.put("C", 90);
		map.put("C++", 80);
		map.put("C#", 70);
		map.put("PHP", 60);
		
		return "chartView";
	}
	private JFreeChart createChart(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart3D(
				"Pie Chart 3D Demo 1", paramPieDataset, true, true, false);
		PiePlot3D localPiePlot3D = (PiePlot3D) localJFreeChart.getPlot();
		localPiePlot3D.setDarkerSides(true);
		localPiePlot3D.setStartAngle(290.0D);
		localPiePlot3D.setDirection(Rotation.CLOCKWISE);
		localPiePlot3D.setForegroundAlpha(0.5F);
		localPiePlot3D.setNoDataMessage("No data to display");
		return localJFreeChart;
	}

	private PieDataset createDataSet() {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		localDefaultPieDataset
				.setValue("Java", new Double(43.200000000000003D));
		localDefaultPieDataset.setValue("Visual Basic", new Double(10.0D));
		localDefaultPieDataset.setValue("C/C++", new Double(17.5D));
		localDefaultPieDataset.setValue("PHP", new Double(32.5D));
		localDefaultPieDataset.setValue("Perl", null);
		return localDefaultPieDataset;
	}
}
