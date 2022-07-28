package util;

import com.mvc.dao.Cart;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDemo {
	public static void main(String[] args) {
		Cart cart = new Cart();
		cart.setCart_number("99888");
		cart.setAmount(55);
		cart.setCreated_by("qwe");
		cart.setCreated_date(new java.sql.Date(new Date().getTime()));
		cart.setCustomer("test");
		cart.setLast_modified_by("aa");
		cart.setLast_modified_date(new java.sql.Date(new Date().getTime()));
		// 開啟Session，相當於開啟JDBC的Connection
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Transaction表示一組會話操作
		Transaction tx = session.beginTransaction();
		// 將物件映射至資料庫表格中儲存
		session.save(cart);
		tx.commit();
		session.close();

		System.out.println("新增資料OK!請先用MySQL觀看結果！");

		HibernateUtil.shutdown();
	}

}