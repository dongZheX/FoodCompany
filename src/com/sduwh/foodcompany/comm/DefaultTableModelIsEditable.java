package com.sduwh.foodcompany.comm;

import javax.swing.table.DefaultTableModel;

public class DefaultTableModelIsEditable extends DefaultTableModel {
	public DefaultTableModelIsEditable(){
		super();
	}
	
	public DefaultTableModelIsEditable(Object[][] data, Object[] columnNames){
		super();
	}
	
    public boolean isCellEditable(int row,int column){
        if(column == 1&&row == 1){
           return true;
        }else{
           return false;
        }
    }
}
