package com.sduwh.foodcompany.bill;

/**
 * 此类用于商品在table上的显示
 * @author 李世鹏
 *
 */
public class GoodsTableData {
	/*String[] table_title = {"数量", "商品编号", "商品名称", "单价"};*/
	private int needNumber;
	private int nowNumber;
	private String goodsID;
	private String goodsName;
	private float value;
	
	public GoodsTableData(int needNumber, int nowNumber, String goodsID, String goodsName, float value) {
		this.needNumber = needNumber;
		this.nowNumber = nowNumber;
		this.goodsID = goodsID;
		this.goodsName = goodsName;
		this.value = value;
	}
	
	public int getNeedNumber() {
		return this.needNumber;
	}
	
	public int getNowNumber() {
		return this.nowNumber;
	}
	
	public String getGoodsID() {
		return this.goodsID;
	}
	
	public String getGoodsName() {
		return this.goodsName;
	}
	
	public float getValue() {
		return this.value;
	}
	
	public String[] toArray() {
		String[] ans = {
				String.format("%d", this.needNumber),
				String.format("%d", this.nowNumber),
				this.goodsID,
				this.goodsName,
				String.format("%.2f", this.value)
		};
		return ans;
	}

}
