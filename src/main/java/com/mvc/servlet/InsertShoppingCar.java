package com.mvc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mvc.dao.Cart;
import com.mvc.dao.CommodityPoolMain;
import com.mvc.service.impl.JDBC.ShoppingCarServiceImpl;

@WebServlet("/InsertShoppingCar")
public class InsertShoppingCar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public InsertShoppingCar() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param = null;

		try {
			// 取得 request的資料
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			response.setCharacterEncoding("UTF-8");
			// 在這邊是用來 接收request傳進來的字串
			StringBuilder requestStrBuilder = new StringBuilder();
			String inputStr;
			// 預設為1
			String inputKey = "1";

			while ((inputStr = streamReader.readLine()) != null) {
				// 有幾行讀幾次，把所有加入requestStrBuilder裡
				requestStrBuilder.append(inputStr);
			}

			ShoppingCarServiceImpl shoppingCarServiceImpl = new ShoppingCarServiceImpl();
			// 用於 request的json檔案轉換成 java物件
			JSONObject parseObject = JSONObject.parseObject(requestStrBuilder.toString());
			if (parseObject != null) {
				// 取得json裡面的某個物件
				inputKey = parseObject.get("inputKey").toString();
			}

			// 接受底下回傳資料的array
			JSONArray array = new JSONArray();

			// 假如 inputKey為1單筆新增
			if (inputKey.equals("1")) {
				Cart cartInfo = new Cart();
				// Integer.parseInt 是用來把 字串 轉 成int型態
				cartInfo.setAmount(Integer.parseInt(parseObject.get("amount").toString()));
				cartInfo.setCart_number(parseObject.get("cart_number").toString());

				// 這邊直接帶入系統時間 然後因為 要符合 sql date的原因 所以要google 找方法 把 util.date轉換成 sql.date
				// sql date 跟 util date 都是一種時間格式
				cartInfo.setCreated_date(new java.sql.Date(new Date().getTime()));
				cartInfo.setLast_modified_date(new java.sql.Date(new Date().getTime()));
				cartInfo.setCreated_by(parseObject.get("created_by").toString());
				cartInfo.setLast_modified_by(parseObject.get("last_modified_by").toString());
				cartInfo.setCustomer(parseObject.get("customer").toString());
				// 執行新增資料
				shoppingCarServiceImpl.insertCartInfo(cartInfo);

				array.add(parseObject);
				// 查詢結束後轉成json回傳前端
				response.getWriter().append(array.toString());
			} else if (inputKey.equals("2")) {

			}
			// 新增單筆commodityPoolMain 資料
			else if (inputKey.equals("3")) {
				CommodityPoolMain cpm = new CommodityPoolMain();
				cpm.setCart_number(parseObject.get("cart_number").toString());
				cpm.setCommodity_pool_id(parseObject.get("commodity_pool_id").toString());
				cpm.setCommodity_pool_name(parseObject.get("commodity_pool_name").toString());
				cpm.setCommodity_pool_type(parseObject.get("commodity_pool_type").toString());
				cpm.setLog_id(parseObject.get("log_id").toString());
				cpm.setStop_check(parseObject.get("stop_check").toString());
				cpm.setStop_desc(parseObject.get("stop_desc").toString());
				shoppingCarServiceImpl.insertCommodityPoolInfo(cpm);
				array.add(parseObject);
				response.getWriter().append(array.toString());

			}
		} catch (Exception e) {
			response.getWriter().append(e.toString());
		}
	}

}