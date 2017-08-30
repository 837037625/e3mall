package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.CommonPojo.E3Result;
import cn.e3mall.CommonPojo.EasyUITreeNode;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getEasyUITreeNode(Long id) {
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		
		List<TbContentCategory> categories = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> treeNodes =new ArrayList<>();
		for (TbContentCategory category : categories) {
			EasyUITreeNode treeNode=new EasyUITreeNode();
			treeNode.setId(category.getId());
			treeNode.setText(category.getName());
			treeNode.setState(category.getIsParent()? "closed":"open");
			treeNodes.add(treeNode);
		}
		
		return treeNodes;
	}

	@Override
	public E3Result addContentCategory(long parentId, String name) {
		Date date =new Date();
		TbContentCategory category=new TbContentCategory();
		category.setParentId(parentId);
		category.setName(name);
		category.setCreated(date);
		category.setUpdated(date);
		category.setStatus(1);
		category.setSortOrder(1);
		category.setIsParent(false);
		contentCategoryMapper.insert(category);
		//判断父节点之前是父节点还是叶节点
		TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (parentCategory.getIsParent()==false) {
			parentCategory.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		
		return E3Result.ok(category);
	}

	@Override
	public E3Result deleteContentCategory(Long id) {
		//删除当前节点子节点(有则删,没有则不删)
		TbContentCategoryExample example =new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		contentCategoryMapper.deleteByExample(example);
		//删除当前节点
		contentCategoryMapper.deleteByPrimaryKey(id);
		E3Result result = E3Result.ok();
		return result;
	}

	@Override
	public E3Result updateContentCategory(TbContentCategory category) {
		TbContentCategory category2 = contentCategoryMapper.selectByPrimaryKey(category.getId());
		category2.setName(category.getName());
		contentCategoryMapper.updateByPrimaryKey(category2);
		return E3Result.ok();
	}

}
