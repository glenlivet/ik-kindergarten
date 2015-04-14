package org.ikgroup.persistence.activiti;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.ikgroup.domain.activiti.HistoricActivityInstance;

public interface HistoricActivityInstanceMapper {

	public List<HistoricActivityInstance> getAll();
	
	public List<HistoricActivityInstance> getAll(RowBounds rowBounds);
	
}
