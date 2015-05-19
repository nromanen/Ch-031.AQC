package com.softserveinc.edu.oms.service.interfaces;

import java.util.List;

import com.softserveinc.edu.oms.domain.entities.Region;
import com.softserveinc.edu.oms.persistence.dao.params.user.UserSelectWayCondition;

public interface IRegionService extends Service<Region> {

	List<Region> findRegionByName(final String regionName, final UserSelectWayCondition condition);
}
