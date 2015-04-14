package org.ikgroup.service.activiti;

import java.util.List;

import org.ikgroup.core.mybatis.PagingBounds;
import org.ikgroup.domain.activiti.HistoricActivityInstance;
import org.ikgroup.persistence.activiti.HistoricActivityInstanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestPagingServiceImpl implements TestPagingService {
	
	@Autowired
	private HistoricActivityInstanceMapper actInstMapper;

	@Override
	public List<HistoricActivityInstance> getPagingData(int offset, int limit) {
		
		return actInstMapper.getAll(new PagingBounds(offset, limit));
	}

	@Override
	public List<HistoricActivityInstance> getAll() {
		return actInstMapper.getAll();
	}

}
