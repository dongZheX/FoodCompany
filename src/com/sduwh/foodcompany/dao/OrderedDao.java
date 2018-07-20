package com.sduwh.foodcompany.dao;

import java.util.Map;

public interface OrderedDao {
	public void insertOrder(Map map);
	public void getOrder(Map map);
	public void selectOrder(Map map);
}
