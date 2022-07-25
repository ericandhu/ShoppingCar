package com.mvc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mvc.service.impl.ShoppingCarServiceImpl;

@WebServlet("/DeleteCartInfo")
public class DeleteShoppingCar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeleteShoppingCar() {
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

			// 單筆刪除
			if (inputKey.equals("1")) {
				String cartNumber = parseObject.get("cart_number").toString();
				// 執行新增資料
				shoppingCarServiceImpl.deleteCartInfo(cartNumber);
				// 查詢結束後轉成json回傳前端
				response.getWriter().append("成功刪除! CartNumber: " + cartNumber);
			}
			// 刪除多筆
			else if (inputKey.equals("2")) {

			}
		} catch (Exception e) {
			response.getWriter().append(e.toString());
		}
	}

}