/**
 * @Title: CoordinateService.java
 * Copyright: Copyright (c) 2016 dc 
 */
package com.gatz.dc.api.rest.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gatz.dc.api.rest.common.CustomizedPropertyConfigurer;

@Service
public class CoordinateService {

	private static final String FIELD_ADDRESS = "address";
	private static final String FIELD_COORDINATE_LNG = "lng";
	private static final String FIELD_COORDINATE_LAT = "lat";

	@Autowired
	CustomizedPropertyConfigurer customizedPropertyConfigurer;

	public List<Map<String, String>> getBaiduCoordinate(List<String> listAddress) throws Exception {
		List<Map<String, String>> resultList = null;

		/** HtmlUnit请求web页面 */
		WebClient wc = new WebClient(BrowserVersion.CHROME);
		wc.getOptions().setUseInsecureSSL(true);
		wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		wc.getOptions().setCssEnabled(false); // 禁用css支持
		wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
		wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
		wc.getOptions().setDoNotTrackEnabled(false);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("addresses", listAddress);
		
		URL url = new URL("http://172.16.145.129:3000/coordinate/baidu");
		WebRequest request = new WebRequest(url, HttpMethod.POST);
		request.setAdditionalHeader("Content-type", "application/json");
		request.setCharset("utf-8");
		
//		request.setRequestBody("{\"addresses\":[\"理想国际大厦\",\"锦秋国际\",\"龙德广场\",\"海龙电子城\",\"xxx\"]}");
		request.setRequestBody(jsonObject.toString());
		HtmlPage page = wc.getPage(request);
		boolean finished = false;
		while(finished == false) {
			int jobs = wc.waitForBackgroundJavaScript(400);
			finished = (jobs == 0);
		}
		Document doc = Jsoup.parse(page.asXml());
		
		resultList = new ArrayList<Map<String, String>>();
		Elements trEls = doc.select("tr");
		for(Element trEl : trEls) {
			Map<String, String> point = new HashMap<String, String>();
			Elements tdEls = trEl.select("td");
			point.put(FIELD_ADDRESS, tdEls.get(0).text());
			point.put(FIELD_COORDINATE_LNG, tdEls.get(1).text());
			point.put(FIELD_COORDINATE_LAT, tdEls.get(2).text());
			resultList.add(point);
		}
		
		wc.close();
		return resultList;
	}

}
