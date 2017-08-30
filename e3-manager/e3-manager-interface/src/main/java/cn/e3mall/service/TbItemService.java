package cn.e3mall.service;

import cn.e3mall.CommonPojo.E3Result;
import cn.e3mall.CommonPojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;

public interface TbItemService {
	    /**
	     * 根据商品id查询商品
	     * @param Id
	     * @return
	     */
	    public TbItem  findTbItemById(Long Id);
	    /**
	     * 查询商品分页信息
	     * @param page
	     * @param rows
	     * @return
	     */
	    public EasyUIDataGridResult getItemList(int page,int rows);
	    /**
	     * 保存商品
	     * @param item
	     * @param desc
	     * @return
	     */
		public E3Result saveItem(TbItem item, String desc);
		/**
		 * 根据商品ID删除商品
		 * @param id
		 * @return
		 */
		public E3Result deleteItemById(Long id);
		/**
		 * 根据商品ID下架商品
		 * @param ids
		 * @return
		 */
		public E3Result instockItemById(Long ids);
		/**
		 * 根据商品ID上架商品
		 * @param ids
		 * @return
		 */
		public E3Result reshelfItemById(Long ids);
		/**
		 * 修改商品
		 * @param item
		 * @param desc
		 * @return
		 */
		public E3Result updateItem(TbItem item, String desc);
		/**
		 * 通过id获取desc
		 * @return
		 */
		public E3Result getItemDescById(Long id);
	   
}
