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
import com.mvc.service.impl.ShoppingCarServiceImpl;

@WebServlet("/UpdateShoppingCar")
public class UpdateShoppingCar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UpdateShoppingCar() {
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

			// 單筆修改
			if (inputKey.equals("1")) {
				Cart cartInfo = new Cart();
				cartInfo.setAmount(Integer.parseInt(parseObject.get("amount").toString()));
				cartInfo.setCart_number(parseObject.get("cart_number").toString());
				cartInfo.setLast_modified_date(new java.sql.Date(new Date().getTime()));
				cartInfo.setLast_modified_by(parseObject.get("last_modified_by").toString());
				cartInfo.setCustomer(parseObject.get("customer").toString());
				// 執行新增資料
				shoppingCarServiceImpl.updateCartInfo(cartInfo);
				// 查詢結束後轉成json回傳前端
				response.getWriter().append("更新成功!");
			}
			// 更新多筆
			else if (inputKey.equals("2")) {

			}
			// 單筆修改
			else if (inputKey.equals("3")) {
				CommodityPoolMain cPM = new CommodityPoolMain();
				cPM.setCart_number(parseObject.get("cart_number").toString());
				cPM.setCommodity_pool_id(parseObject.get("commodity_pool_id").toString());
				cPM.setCommodity_pool_name(parseObject.get("commodity_pool_name").toString());
				cPM.setCommodity_pool_type(parseObject.get("commodity_pool_type").toString());
				cPM.setLog_id(parseObject.get("log_id").toString());
				cPM.setStop_check(parseObject.get("stop_check").toString());
				cPM.setStop_desc(parseObject.get("stop_desc").toString());
				// 執行新增資料
				shoppingCarServiceImpl.updateCommodityPoolMain(cPM);
				// 查詢結束後轉成json回傳前端
				response.getWriter().append("更新成功!");
			}
		} catch (Exception e) {
			response.getWriter().append(e.toString());
		}
	}

	// String pattern = "yyyy-MM-dd";
	// // SimpleDateFormat 用來 把字串轉成 日期 建議用 2018-09-09這種格式傳入 雖然別的也可以但要寫對上面pattern
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	// Date date =
	// simpleDateFormat.parse(parseObject.get("last_modified_date").toString());

}