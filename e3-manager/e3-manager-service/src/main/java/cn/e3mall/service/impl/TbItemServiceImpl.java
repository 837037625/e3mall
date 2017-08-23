package cn.e3mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.TbItemService;


@Service
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Override
	public TbItem findTbItemById(Long Id) {
		//tbItemMapper.selectByPrimaryKey(id);
		TbItemExample example=new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(Id);
		List<TbItem> tbItems = tbItemMapper.selectByExample(example);
		if (tbItems.size()>0) {
			return tbItems.get(0);
		}
		return null;
	}

}
