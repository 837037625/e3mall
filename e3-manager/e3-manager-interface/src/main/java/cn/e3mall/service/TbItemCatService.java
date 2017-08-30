package cn.e3mall.service;

import java.util.List;

import cn.e3mall.CommonPojo.EasyUITreeNode;

public interface TbItemCatService {
	 public List<EasyUITreeNode> getCatList(Long parentId);
}
