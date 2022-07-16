package com.mvc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String userName = request.getParameter("name");
		System.out.println("userName: " + userName);
		try {
			ShoppingCarServiceImpl shoppingCarServiceImpl = new ShoppingCarServiceImpl();
			List<Cart> cartList = shoppingCarServiceImpl.getCart();
			JSONArray array = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			for (int i = 0; i < cartList.size(); i++) {
				jsonObject.put("Amount", cartList.get(i).getAmount());
				jsonObject.put("Cart_number", cartList.get(i).getCart_number());
				array.add(jsonObject);
			}
			response.getWriter().append(array.toString());
		} catch (Exception e) {
			response.getWriter().append("Error!");
		}
		// response.getWriter().append("Served at:
		// doGet").append(request.getContextPath());
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

}
