package com.cloudfish.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author FengJuan
 * @Date
 */
@RestController
public class IndexController {

	/**
	 * 列表页初始化
	 * @return
	 *
	 */
	@GetMapping("/index")
	public ModelAndView index(){
		return new ModelAndView("/index");
	}
	
	/**
	 * 列表页查询结果模拟
	 * @return
	 *
	 */
	@GetMapping("/searchByCriteria")
	public Object searchByCriteria(Pager pager){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> dataMap = null;
		//模拟分页查询的结果
		for(int i=((pager.getCurrentPage()-1) * pager.getPageSize()); i<pager.getCurrentPage() * pager.getPageSize();i++){
			dataMap = new HashMap<String,Object>();
			dataMap.put("userName", "aaaa" + i);
			dataMap.put("sex", i%2==0?"1":"0");
			dataMap.put("desc", "描述描述描述" + i);
			dataMap.put("money", "100.00");
			dataMap.put("userUid", "N"+i);
			list.add(dataMap);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", 100);
		map.put("code", 200);
		map.put("rows", list);
		return map;
	}
}
