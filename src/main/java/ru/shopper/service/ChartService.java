package ru.shopper.service;

import be.ceau.chart.BarChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.BarData;
import be.ceau.chart.dataset.BarDataset;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;

@Component
public class ChartService {
    public String getChartUsersCount(String labelName, Collection<BigDecimal> data, String... labels) {
        BarDataset barDataset = new BarDataset()
                .setLabel(labelName)
                .setData(data)
                .addBackgroundColor(Color.DARK_SALMON)
                .setBorderWidth(2);

        BarData barData = new BarData()
                .addLabels(labels)
                .addDataset(barDataset);

        return new BarChart(barData).toJson();
    }
}
