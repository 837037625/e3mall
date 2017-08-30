package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.CommonPojo.E3Result;
import cn.e3mall.CommonPojo.EasyUIDataGridResult;
import cn.e3mall.CommonUtils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.jedis.impl.JedisClientPool;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {
	
	private static final String CONTENT_KEY = "CONTENT_KEY";

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private JedisClientPool jedisClientPool;
	
	@Override
	public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		//缓存没有查询数据库
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> contents = contentMapper.selectByExample(example);
		
		//取分页信息
		PageInfo<TbContent> pageInfo=new PageInfo<>(contents);
		//返回分页包装对象
		EasyUIDataGridResult result =new EasyUIDataGridResult(pageInfo.getTotal(),pageInfo.getList());
		
		return result;
	}

	@Override
	public E3Result saveContent(TbContent tbContent) {
		Date date =new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		
		contentMapper.insert(tbContent);
		
		//对内容信息做增删改查后只要删除该内容即可,缓存同步
		jedisClientPool.hdel(CONTENT_KEY, tbContent.getCategoryId().toString());
		
		return E3Result.ok();
	}

	@Override
	public List<TbContent> getContentList(Long categoryId) {
		//查询redis缓存
		try {
			String json = jedisClientPool.hget(CONTENT_KEY, categoryId+"");
			if (StringUtils.isNotBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果没有就查询数据库
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> tbContents = contentMapper.selectByExample(example);
		
		//返回之前添加数据库
		String json = JsonUtils.objectToJson(tbContents);
		jedisClientPool.hset(CONTENT_KEY, categoryId+"", json);
		
		return tbContents;
	}

}
