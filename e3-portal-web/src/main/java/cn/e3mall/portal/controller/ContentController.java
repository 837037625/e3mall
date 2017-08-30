package cn.e3mall.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping
	public List<TbContent> getContentList(Long categoryId){
		List<TbContent> contentList = contentService.getContentList(categoryId);
		return contentList;
	}
	
	
}
