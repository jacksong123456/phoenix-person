package com.ginkgocap.ywxt.person;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class TestController {

	public static void main(String[] args) {

		Map<String, Long> map = new HashMap<String, Long>(1);

		map.put("id", 4564564564l);

		System.out.println(JSONObject.fromObject(map).toString());
	}

}
