package com.schneider.tdd.service.impl;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import com.schneider.tdd.service.StockInfo;
import com.schneider.tdd.service.StockQueryService;

@Service(value="stockQueryService")
public class StockQueryServiceImpl implements StockQueryService {

	public StockInfo queryStockInfoByCode(String code) {
		String url = "http://hq.sinajs.cn/list=";
		if (code.startsWith("0")) {
			url += "sz" + code;
		} else if (code.startsWith("6")) {
			url += "sh" + code;
		}

		StockInfo stockInfo = new StockInfo();
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);		
		try {
			client.executeMethod(get);
			String response = get.getResponseBodyAsString();
			String value = response.split("\"")[1];
			String[] items = value.split(",");
			stockInfo.setStockName(items[0]);
			stockInfo.setOpenPrice(new BigDecimal(items[1]));
			stockInfo.setClosePrice(new BigDecimal(items[2]));
			stockInfo.setHighestPrice(new BigDecimal(items[4]));
			stockInfo.setLowestPrice(new BigDecimal(items[5]));
			stockInfo.setBuyOnePrice(new BigDecimal(items[6]));
			stockInfo.setSellOnePrice(new BigDecimal(items[7]));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			get.releaseConnection();
		}

		return stockInfo;
	}
}
