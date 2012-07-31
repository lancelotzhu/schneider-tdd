package com.schneider.stock;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.velocity.util.StringUtils;

import com.schneider.tdd.TddException;

public class StockQuery {

	public StockBean query(String stockCode) {

		String locationCode = StockUtil.getLocationCode(stockCode);

		String realStockCode = new StringBuilder(locationCode)
				.append(stockCode).toString();

		StockBean stock = null;

		String url = new StringBuilder("http://hq.sinajs.cn/list=").append(
				realStockCode).toString();
		HttpClient client = new HttpClient();

		GetMethod get = new GetMethod(url);
		try {
			client.executeMethod(get);
		} catch (HttpException e1) {
			throw new TddException(e1);
		} catch (IOException e1) {
			throw new TddException(e1);
		}
		// execute method and handle any error responses.

		try {
			String results = get.getResponseBodyAsString();

			int beginPosition = results.indexOf("\"") + 1;
			int endPosition = results.lastIndexOf("\"") - 1;

			String cleanResults = results.substring(beginPosition, endPosition);
			String[] resultArray = StringUtils.split(cleanResults, ",");
			stock = new StockBean();
			stock.setStockName(resultArray[0]);
			stock.setIntialPrice(new BigDecimal(resultArray[1]));
			stock.setLastEndPrice(new BigDecimal(resultArray[2]));
			stock.setCurrentPrice(new BigDecimal(resultArray[3]));
			stock.setTodayMaxPrice(new BigDecimal(resultArray[4]));
			stock.setTodayMinPrice(new BigDecimal(resultArray[5]));

		} catch (IOException e) {
			throw new TddException(e);
		} finally {
			// Process the data from the input stream.
			get.releaseConnection();
		}

		return stock;
	}
}
