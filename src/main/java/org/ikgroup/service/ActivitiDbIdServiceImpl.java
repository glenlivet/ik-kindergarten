package org.ikgroup.service;

import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据库id的activiti实现
 * 
 * @author glenlivet
 *
 */
@Service
public class ActivitiDbIdServiceImpl implements DbIdService {

	@Autowired
	private ProcessEngineConfigurationImpl processEngineConfiguration;

	@Override
	public String nextId() {
		return processEngineConfiguration.getIdGenerator().getNextId();
	}

}
