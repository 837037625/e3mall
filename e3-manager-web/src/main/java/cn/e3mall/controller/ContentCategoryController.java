package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.CommonPojo.E3Result;
import cn.e3mall.CommonPojo.EasyUITreeNode;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.pojo.TbContentCategory;

@Controller
@RequestMapping("/content")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService categoryService;
	
	@RequestMapping("/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getEasyUITreeNodes(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EasyUITreeNode> treeNodes = categoryService.getEasyUITreeNode(parentId);
		return treeNodes;
	}
	
     @RequestMapping("/category/create")
     @ResponseBody
     public E3Result createCategory(Long parentId,String name){
    	 E3Result result = categoryService.addContentCategory(parentId, name);
    	 return result;
     }
     @RequestMapping("/category/delete")
     @ResponseBody
     public E3Result deleteCategory(Long id){
    	 E3Result result = categoryService.deleteContentCategory(id);
    	 return result;
     }
     @RequestMapping("/category/update")
     @ResponseBody
     public E3Result updateCategory(TbContentCategory category){
    	 E3Result result = categoryService.updateContentCategory(category);
    	 return result;
     }
}
