package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.CommonPojo.E3Result;
import cn.e3mall.CommonPojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbContent;

public interface ContentService {
	/**
	 * 根据分类Id分页查询内容
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	 public EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows);

	 /**
	  * 添加content
	  * @param tbContent
	  * @return
	  */
	public E3Result saveContent(TbContent tbContent);
	
	/**
	 * 根据分类Id查询内容
	 */
	public List<TbContent> getContentList(Long categoryId);

}
