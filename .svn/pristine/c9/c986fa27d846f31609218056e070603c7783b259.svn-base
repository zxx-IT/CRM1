package com.atguigu.ssm.crm.chart;

import java.awt.Font;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.AbstractView;

@Controller
public class ChartView extends AbstractView{
	@Override
	protected void renderMergedOutputModel(Map<String, Object> modle,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PieDataset dataset = createDataSet(modle);
		String name = (String) modle.get("name");
		JFreeChart chart = creatChart(dataset, name);
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 720, 500);
	}

	private JFreeChart creatChart(PieDataset paramPieDataset, String name) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart3D(
				name, paramPieDataset, true, true, false);
		PiePlot3D localPiePlot3D = (PiePlot3D) localJFreeChart.getPlot();
		
		
		// 修饰图表
		// 1.修饰标题部分
		localJFreeChart.getTitle().setFont(new Font("华文彩云", Font.ITALIC, 30));

		// 2.修饰图例部分
		localJFreeChart.getLegend().setItemFont(new Font("华文新魏", Font.PLAIN, 20));

		// 3.修饰绘图区
		// ①获取绘图区对象

		// ②设置标签字体
		localPiePlot3D.setLabelFont(new Font("宋体", Font.PLAIN, 15));

		// ③设置前景透明
		localPiePlot3D.setForegroundAlpha(0.6f);

		// ④设置标签的详细信息
		localPiePlot3D.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0},{1}/{3},{2}"));

		localPiePlot3D.setDarkerSides(true);
		localPiePlot3D.setStartAngle(290.0D);
		localPiePlot3D.setDirection(Rotation.CLOCKWISE);
		localPiePlot3D.setForegroundAlpha(0.5F);
		localPiePlot3D.setNoDataMessage("No data to display");
		return localJFreeChart;
	}

	private PieDataset createDataSet(Map<String, Object> modle) {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		Set<Entry<String,Object>> entrySet = modle.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			if (!"name".equals(entry.getKey())) {
				localDefaultPieDataset.setValue(entry.getKey(), (int)entry.getValue());
			}
		}
		return localDefaultPieDataset;
	}
}
