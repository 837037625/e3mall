package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.CommonPojo.EasyUITreeNode;
import cn.e3mall.service.TbItemCatService;

@Controller
public class TbItemCatController {
	
	@Autowired
	private TbItemCatService itemCatService;
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		
		List<EasyUITreeNode> catList = itemCatService.getCatList(parentId);
		return catList;
	}



}
