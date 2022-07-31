package com.mvc.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mvc.dao.Cart;
import com.mvc.dao.CommodityPoolMain;
import com.mvc.service.impl.Hibernate.ShoppingCarServiceImpl_Hibernate;
import com.mvc.service.impl.JDBC.ShoppingCarServiceImpl;

@WebServlet("/EricaShoppingCar")
public class EricaShoppingCar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Hibernate使用的interface
	 */
	ShoppingCarServiceImpl_Hibernate hibernateImpl = new ShoppingCarServiceImpl_Hibernate();

	/**
	 * JDBC使用的interface
	 */
	ShoppingCarServiceImpl shoppingCarServiceImpl = new ShoppingCarServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String inputKey = request.getParameter("inputKey");
			response.setCharacterEncoding("UTF-8");

			if (inputKey == null || inputKey.equals("")) {
				inputKey = "1";
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
				String cart_Number = request.getParameter("cart_number");
				Cart cart = shoppingCarServiceImpl.getCart_By_Number(cart_Number);
				JSONObject jsonObject = getJsonObject(cart.getCart_number(), cart.getCustomer(), cart.getAmount(),
						cart.getCreated_by(), cart.getCreated_date(), cart.getLast_modified_by(),
						cart.getLast_modified_date());
				array.add(jsonObject);
				response.getWriter().append(array.toString());
			}
			// 假如 inputKey為3全部查詢(table commoditypoolmain)
			else if (inputKey.equals("3")) {
				List<CommodityPoolMain> cPIList = shoppingCarServiceImpl.getCPI();
				for (int i = 0; i < cPIList.size(); i++) {
					JSONObject jsonObject1 = getJsonObject1(cPIList.get(i).getCart_number(),
							cPIList.get(i).getCommodity_pool_id(), cPIList.get(i).getCommodity_pool_name(),
							cPIList.get(i).getCommodity_pool_type(), cPIList.get(i).getLog_id(),
							cPIList.get(i).getStop_check(), cPIList.get(i).getStop_desc());
					array.add(jsonObject1);
				}
				// 查詢結束後轉成json回傳前端
				response.getWriter().append(array.toString());
			}
			// 假如 inputKey為4指定單號查詢(table commoditypoolmain)
			else if (inputKey.equals("4")) {
				// getParameter使用URL上參數
				String cart_Number = request.getParameter("cart_number");
				CommodityPoolMain cPM = shoppingCarServiceImpl.getCPM_By_Number(cart_Number);
				JSONObject jsonObject1 = getJsonObject1(cPM.getCart_number(), cPM.getCommodity_pool_id(),
						cPM.getCommodity_pool_name(), cPM.getCommodity_pool_type(), cPM.getLog_id(),
						cPM.getStop_check(), cPM.getStop_desc());
				array.add(jsonObject1);
				response.getWriter().append(array.toString());
			}
			// inputKey為5 Hibernate 全部查詢
			else if (inputKey.equals("5")) {
				List<Cart> cartList = hibernateImpl.getCart();
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
			// inputKey為6 Hibernate 指定單筆查詢
			else if (inputKey.equals("6")) {
				String cart_number = request.getParameter("cart_number");
				Cart cart = hibernateImpl.getCartByCartNumber(cart_number);
				JSONObject jsonObject = getJsonObject(cart.getCart_number(), cart.getCustomer(), cart.getAmount(),
						cart.getCreated_by(), cart.getCreated_date(), cart.getLast_modified_by(),
						cart.getLast_modified_date());
				array.add(jsonObject);
				response.getWriter().append(array.toString());
				}
							

		} catch (

		Exception e) {
			response.getWriter().append("Error!");
		}
	}

	/**
	 * 轉換table (Cart)資料 -> JSON 回前端
	 * 
	 * @param cart_number
	 * @param customer
	 * @param amount
	 * @param created_by
	 * @param created_date
	 * @param last_modified_by
	 * @param last_modified_date
	 * @return
	 */
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

	/**
	 * 轉換table (CommodityPoolMain)資料 -> JSON 回前端
	 * 
	 * @param cart_number
	 * @param commodity_pool_id
	 * @param commodity_pool_name
	 * @param commodity_pool_type
	 * @param log_id
	 * @param stop_check
	 * @param stop_desc
	 * @return
	 */
	public JSONObject getJsonObject1(String cart_number, String commodity_pool_id, String commodity_pool_name,
			String commodity_pool_type, String log_id, String stop_check, String stop_desc) {
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("ID", commodity_pool_id);
		jsonObject1.put("購物車編號", cart_number);
		jsonObject1.put("商品池名稱", commodity_pool_name);
		jsonObject1.put("商品池類別", commodity_pool_type);
		jsonObject1.put("log序號", log_id);
		jsonObject1.put("是否停用", stop_check);
		jsonObject1.put("停用原因", stop_desc);
		return jsonObject1;
	}

}