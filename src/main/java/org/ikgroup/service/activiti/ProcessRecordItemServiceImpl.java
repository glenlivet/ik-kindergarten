package org.ikgroup.service.activiti;

import java.util.Date;

import org.ikgroup.domain.activiti.ProcessRecordItem;
import org.ikgroup.persistence.activiti.ProcessRecordItemMapper;
import org.ikgroup.service.DbIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProcessRecordItemServiceImpl implements ProcessRecordItemService {
	
	@Autowired
	private DbIdService dbIdService;

	@Autowired
	private ProcessRecordItemMapper processRecordItemMapper;

	@Override
	public void saveProcessStartRecord(String instanceId, String userId) {
		ProcessRecordItem item = new ProcessRecordItem();
		item.setId(dbIdService.nextId());
		item.setRelativeKey(instanceId);
		item.setType(ProcessRecordItem.TYPE_PROCESS_STARTED);
		item.setResponsible(userId);
		item.setTime(new Date());
		item.setInstanceId(instanceId);
		processRecordItemMapper.add(item);
	}

}
