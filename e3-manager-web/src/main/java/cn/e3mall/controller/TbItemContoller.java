package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.CommonPojo.E3Result;
import cn.e3mall.CommonPojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.TbItemService;

@Controller
@RequestMapping("/item")
public class TbItemContoller {
	@Autowired
    private  TbItemService tbItemService;
	//通过id查找item
	@RequestMapping("/{tbItemId}")
	@ResponseBody
	public TbItem findTbItemById(@PathVariable Long tbItemId){
		TbItem tbItem = tbItemService.findTbItemById(tbItemId);
		return tbItem;		
	}
	//分页展示item
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemsList(Integer page,Integer rows){
		EasyUIDataGridResult result = tbItemService.getItemList(page, rows);
		return result;
	}
	//保存item
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result saveItem(TbItem item,String desc){
		E3Result result =tbItemService.saveItem(item,desc);
		return result;
	}
	//删除 status 3
	@RequestMapping("/delete")
	@ResponseBody 
	public E3Result deleteItemById(Long ids){
		E3Result result=tbItemService.deleteItemById(ids);
		return result;
	}
	//下架 status2
	@RequestMapping("/instock")
	@ResponseBody 
	public E3Result instockItemById(Long ids){
		E3Result result=tbItemService.instockItemById(ids);
		return result;
	}
	//上架 status1
	@RequestMapping("/reshelf")
	@ResponseBody 
	public E3Result reshelfItemById(Long ids){
		E3Result result=tbItemService.reshelfItemById(ids);
		return result;
	}
	//去修改界面
	@RequestMapping("/editPage")
	public String toEditPage(){
		return "item-edit";
	}
	//获取desc描述
	@RequestMapping("/getItemDesc")
	@ResponseBody
	public E3Result getItemDesc(Long id){
		E3Result result= tbItemService.getItemDescById(id);
		return  result;
	}
	//修改更新
	@RequestMapping("/update")
	@ResponseBody
	public E3Result updateItem(TbItem item,String desc){
		E3Result result =tbItemService.updateItem(item,desc);
		return result;
	}
	
}
