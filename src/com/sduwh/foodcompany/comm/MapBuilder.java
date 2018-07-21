package com.sduwh.foodcompany.comm;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.ResolutionSyntax;

public class MapBuilder {
	private static Map map = new HashMap<>();
	public static Map buildMap(Object...args){
		if(args.length%2!=0) return null;
		int temp = args.length / 2;
		map.clear();
		for(int i = 0;i<temp;i++){
			map.put(args[i*2],args[i*2+1]);
		}
		return map;
	}
}
