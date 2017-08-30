package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.asm.ByteVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.CommonPojo.E3Result;
import cn.e3mall.CommonPojo.EasyUIDataGridResult;
import cn.e3mall.CommonUtils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.TbItemService;

@Service(value = "tbItemServiceImpl")
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public TbItem findTbItemById(Long Id) {
		// tbItemMapper.selectByPrimaryKey(id);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(Id);
		List<TbItem> tbItems = tbItemMapper.selectByExample(example);
		if (tbItems.size() > 0) {
			return tbItems.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample example = new TbItemExample();
		// 没有添加添加就是查询全部
		List<TbItem> items = tbItemMapper.selectByExample(example);
		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(items);

		// 创建返回结果对象
		EasyUIDataGridResult result = new EasyUIDataGridResult(pageInfo.getTotal(), pageInfo.getList());

		return result;
	}

	@Override
	public E3Result saveItem(TbItem item, String desc) {
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		// 商品状态，1-正常，2-下架，3-删除
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		tbItemMapper.insert(item);
		// 4、创建一个TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		// 5、补全TbItemDesc的属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 6、向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		// 返回一个状态
		E3Result ok = E3Result.ok();
		return ok;
	}

	@Override
	public E3Result deleteItemById(Long id) {
		TbItem item = tbItemMapper.selectByPrimaryKey(id);
		item.setStatus((byte) 3);
		tbItemMapper.updateByPrimaryKey(item);
		E3Result result = E3Result.ok("200");
		return result;
	}

	@Override
	public E3Result instockItemById(Long id) {
		TbItem item = tbItemMapper.selectByPrimaryKey(id);
		item.setStatus((byte) 2);
		tbItemMapper.updateByPrimaryKey(item);
		E3Result result = E3Result.ok("200");
		return result;
	}

	@Override
	public E3Result reshelfItemById(Long id) {
		TbItem item = tbItemMapper.selectByPrimaryKey(id);
		item.setStatus((byte) 1);
		tbItemMapper.updateByPrimaryKey(item);
		E3Result result = E3Result.ok("200");
		return result;
	}

	@Override
	public E3Result updateItem(TbItem item, String desc) {
		//.TbItem 信息
		Date update = new Date();
		item.setUpdated(update);
		tbItemMapper.updateByPrimaryKey(item);
		// 2、TbItemDesc的属性
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(update);
		itemDescMapper.updateByPrimaryKey(itemDesc);

		E3Result result = E3Result.ok("200");
		return result;
	}

	@Override
	public E3Result getItemDescById(Long id) {
		E3Result result=new E3Result();
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
		result.setStatus(200);
		result.setData(itemDesc);
		return result;
	}

}
