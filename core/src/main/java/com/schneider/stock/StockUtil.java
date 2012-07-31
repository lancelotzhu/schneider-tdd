package com.schneider.stock;

public class StockUtil {

	public static String getLocationCode(String stockCode){
		
		if(stockCode.startsWith("0")){
			return "sz";
		}else if(stockCode.startsWith("6")){
			return "sh";
		}else{
			return "sh";
		} 	
	}
}
