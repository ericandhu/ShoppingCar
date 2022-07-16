package com.mvc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mvc.dao.Cart;
import com.mvc.service.impl.ShoppingCarServiceImpl;

@WebServlet("/EricaShoppingCar")
public class EricaShoppingCar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EricaShoppingCar() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

			// 假如 inputKey為1 全部查詢
			if (inputKey.equals("1")) {
				List<Cart> cartList = shoppingCarServiceImpl.getCart();
				for (int i = 0; i < cartList.size(); i++) {
					JSONObject jsonObject = getJsonObject(cartList.get(i).getCart_number(),
							cartList.get(i).getCustomer(), cartList.get(i).getAmount(), cartList.get(i).getCreated_by(),
							cartList.get(i).getCreated_date(), cartList.get(i).getLast_modified_by(),
							cartList.get(i).getLast_modified_date());

					array.add(jsonObject);
				}
				// 查詢結束後轉成json回傳前端
				response.getWriter().append(array.toString());
			}
			// 假如 inputKey為2指定單號查詢
			else if (inputKey.equals("2")) {
				String cart_Number = parseObject.get("cart_number").toString();
				Cart cart = shoppingCarServiceImpl.getCart_By_Number(cart_Number);
				JSONObject jsonObject = getJsonObject(cart.getCart_number(),
						cart.getCustomer(), cart.getAmount(), cart.getCreated_by(),
						cart.getCreated_date(), cart.getLast_modified_by(),
						cart.getLast_modified_date());
				array.add(jsonObject);
				response.getWriter().append(array.toString());

			}
			// 假如 inputKey為3多筆查詢
			else if (inputKey.equals("3")) {
				List<String> cart_list = new ArrayList();
				// JSON物件進入時為陣列狀需要轉譯成陣列狀
				JSONArray jsonArr = parseObject.getJSONArray("cart_number_list");
				for (int i = 0; i < jsonArr.size(); i++) {
					// 取array狀中行數i
					JSONObject jsonObject = jsonArr.getJSONObject(i);
					// 取出當行中，key的值轉成string
					String cart_number = jsonObject.get("cart_number").toString();
					// 建立一個list來放得到的值
					cart_list.add(cart_number);
				}
				List<Cart> cartList = shoppingCarServiceImpl.getCart_By_Number_List(cart_list);
				// for迴圈用來把資料們再轉成JSON檔
				for (int i = 0; i < cartList.size(); i++) {
					JSONObject jsonObject = getJsonObject(cartList.get(i).getCart_number(),
							cartList.get(i).getCustomer(), cartList.get(i).getAmount(), cartList.get(i).getCreated_by(),
							cartList.get(i).getCreated_date(), cartList.get(i).getLast_modified_by(),
							cartList.get(i).getLast_modified_date());

					array.add(jsonObject);
				}
				response.getWriter().append(array.toString());

			}

		} catch (Exception e) {
			response.getWriter().append("Error!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param = null;

		try {
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null) {
				responseStrBuilder.append(inputStr);
			}
			JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
			param = jsonObject.toJSONString();
			System.out.println(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("{\"id\":\"1\", \"name\": \"erica\"}");
		// response.getWriter().append("Served
		// at:doGet").append(request.getContextPath());
		// doGet(request, response);
	}

	public JSONObject getJsonObject(String cart_number, String customer, int amount, String created_by,
			Date created_date, String last_modified_by, Date last_modified_date) {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("ID", customer);
		jsonObject.put("金額", amount);
		jsonObject.put("購物車單號", cart_number);
		jsonObject.put("創建者", created_by);
		jsonObject.put("創建日期", created_date);
		jsonObject.put("最後修改者", last_modified_by);
		jsonObject.put("最後修改日期", last_modified_date);
		return jsonObject;
	}

}
