package com.lu.office.service.repository.charge;

import com.lu.office.model.charge.Charge;
import com.lu.office.model.sys.Page;

/**
 * Created by user on 5/20/17.
 */
public interface ChargeService {
    Page<Charge> getPageList(int page, int pageSize, String keyword);

    Charge getOneByAll(Charge charge);

    int saveOrUpdateOne(Charge charge);

    int deleteOneById(Integer id);
}
