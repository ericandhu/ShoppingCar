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
			// ���o request�����
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			response.setCharacterEncoding("UTF-8");
			// �b�o��O�Ψ� ����request�Ƕi�Ӫ��r��
			StringBuilder requestStrBuilder = new StringBuilder();
			String inputStr;
			// �w�]��1
			String inputKey = "1";

			while ((inputStr = streamReader.readLine()) != null) {
				// ���X��Ū�X���A��Ҧ��[�JrequestStrBuilder��
				requestStrBuilder.append(inputStr);

			}

			ShoppingCarServiceImpl shoppingCarServiceImpl = new ShoppingCarServiceImpl();
			// �Ω� request��json�ɮ��ഫ�� java����
			JSONObject parseObject = JSONObject.parseObject(requestStrBuilder.toString());
			if (parseObject != null) {
				// ���ojson�̭����Y�Ӫ���
				inputKey = parseObject.get("inputKey").toString();
			}

			// �������U�^�Ǹ�ƪ�array
			JSONArray array = new JSONArray();

			// ���p inputKey��1 �����d��
			if (inputKey.equals("1")) {
				List<Cart> cartList = shoppingCarServiceImpl.getCart();
				for (int i = 0; i < cartList.size(); i++) {
					JSONObject jsonObject = getJsonObject(cartList.get(i).getCart_number(),
							cartList.get(i).getCustomer(), cartList.get(i).getAmount(), cartList.get(i).getCreated_by(),
							cartList.get(i).getCreated_date(), cartList.get(i).getLast_modified_by(),
							cartList.get(i).getLast_modified_date());

					array.add(jsonObject);
				}
				// �d�ߵ������নjson�^�ǫe��
				response.getWriter().append(array.toString());
			}
			// ���p inputKey��2���w�渹�d��
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
			// ���p inputKey��3�h���d��
			else if (inputKey.equals("3")) {
				List<String> cart_list = new ArrayList();
				// JSON����i�J�ɬ��}�C���ݭn��Ķ���}�C��
				JSONArray jsonArr = parseObject.getJSONArray("cart_number_list");
				for (int i = 0; i < jsonArr.size(); i++) {
					// ��array�������i
					JSONObject jsonObject = jsonArr.getJSONObject(i);
					// ���X��椤�Akey�����নstring
					String cart_number = jsonObject.get("cart_number").toString();
					// �إߤ@��list�ө�o�쪺��
					cart_list.add(cart_number);
				}
				List<Cart> cartList = shoppingCarServiceImpl.getCart_By_Number_List(cart_list);
				// for�j��Ψӧ��ƭ̦A�নJSON��
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
		jsonObject.put("���B", amount);
		jsonObject.put("�ʪ����渹", cart_number);
		jsonObject.put("�Ыت�", created_by);
		jsonObject.put("�Ыؤ��", created_date);
		jsonObject.put("�̫�ק��", last_modified_by);
		jsonObject.put("�̫�ק���", last_modified_date);
		return jsonObject;
	}

}
