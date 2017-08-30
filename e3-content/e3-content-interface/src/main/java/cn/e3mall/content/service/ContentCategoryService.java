package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.CommonPojo.E3Result;
import cn.e3mall.CommonPojo.EasyUITreeNode;
import cn.e3mall.pojo.TbContentCategory;

public interface ContentCategoryService {
	/**
	 * 查找treeNodes
	 * @param Id
	 * @return
	 */
	public List<EasyUITreeNode> getEasyUITreeNode(Long Id);
	/**
	 * 添加treeNode
	 * @param parentId
	 * @param name
	 * @return
	 */
	public E3Result addContentCategory(long parentId, String name);
	/**
	 * 根据id删除节点
	 * @param id
	 * @return
	 */
	public E3Result deleteContentCategory(Long id);
	/**
	 * 修改节点名字
	 * @param category
	 * @return
	 */
	public E3Result updateContentCategory(TbContentCategory category);

}
