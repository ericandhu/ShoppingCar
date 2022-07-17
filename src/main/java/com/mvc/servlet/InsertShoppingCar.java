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
import com.mvc.service.impl.ShoppingCarServiceImpl;

@WebServlet("/InsertShoppingCar")
public class InsertShoppingCar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public InsertShoppingCar() {
		super();
	}

	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param = null;

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
				Cart cartInfo = new Cart();
				// Integer.parseInt �O�Ψӧ� �r�� �� ��int���A
				cartInfo.setAmount(Integer.parseInt(parseObject.get("amount").toString()));
				cartInfo.setCart_number(parseObject.get("cart_number").toString());
				
				// �o�䪽���a�J�t�ήɶ� �M��]�� �n�ŦX sql date����] �ҥH�ngoogle ���k �� util.date�ഫ�� sql.date
				// sql date �� util date ���O�@�خɶ��榡
				cartInfo.setCreated_date(new java.sql.Date(new Date().getTime()));
				cartInfo.setLast_modified_date(new java.sql.Date(new Date().getTime()));
				cartInfo.setCreated_by(parseObject.get("created_by").toString());
				cartInfo.setLast_modified_by(parseObject.get("last_modified_by").toString());
				cartInfo.setCustomer(parseObject.get("customer").toString());
				// ����s�W���
				shoppingCarServiceImpl.insertCartInfo(cartInfo);
				
				array.add(parseObject);
				// �d�ߵ������নjson�^�ǫe��
				response.getWriter().append(array.toString());
			}
		} catch (Exception e) {
			response.getWriter().append(e.toString());
		}
	}

	// String pattern = "yyyy-MM-dd";
	// // SimpleDateFormat �Ψ� ��r���ন ��� ��ĳ�� 2018-09-09�o�خ榡�ǤJ ���M�O���]�i�H���n�g��W��pattern
	// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	// Date date =
	// simpleDateFormat.parse(parseObject.get("last_modified_date").toString());

}
