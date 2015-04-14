package org.ikgroup.service.activiti;

import java.util.List;

import org.ikgroup.domain.activiti.HistoricActivityInstance;

public interface TestPagingService {

	public List<HistoricActivityInstance> getPagingData(int offset, int limit);

	public List<HistoricActivityInstance> getAll();
}
