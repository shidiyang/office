package com.lu.office.service.repositoryimpl.charge;

import com.lu.office.model.charge.Charge;
import com.lu.office.model.people.Staff;
import com.lu.office.model.sys.Page;
import com.lu.office.service.dao.charge.ChargeMapper;
import com.lu.office.service.repository.charge.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scala.Int;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/20/17.
 */
@Repository("chargeService")
public class ChargeServiceImpl implements ChargeService{

    @Autowired
    private ChargeMapper chargeMapper;

    @Override
    public Page<Charge> getPageList(int page, int pageSize, String keyword) {
        keyword = keyword.trim();
        String cont ="";
        int offSet = (page-1)*pageSize;
        int count = 0;
        List<Charge> list = new ArrayList<>();
        if(!"".equals(keyword)){
            cont = " and charge_name like \'%"+keyword+"%\'";
        }
        count = chargeMapper.getCountByCont(cont);
        list = chargeMapper.getListByCont(offSet,pageSize,cont);
        Page<Charge> chargePage = new Page<>(list,page,pageSize,count);
        return chargePage;
    }

    @Override
    public Charge getOneByAll(Charge charge) {
        Integer id = charge.getId();
        StringBuffer sql = new StringBuffer(" and charge_name = \'"+charge.getChargeName()+"\' and prince = "+charge.getPrince()+" and unit =\'" +
                charge.getUnit()+"\' and charge_type = "+charge.getChargeType());
        if(id !=null){
            sql.append(" and id !="+id);
        }
        Charge charge1 = chargeMapper.getOneByCont(sql.toString());
        return charge1;
    }

    @Override
    public int saveOrUpdateOne(Charge charge) {
        Integer id = charge.getId();
        int num = 0;
        if(id == null){
            num = chargeMapper.insertSelective(charge);
        }else{
            num = chargeMapper.updateByPrimaryKeySelective(charge);
        }
        return num;
    }

    @Override
    public int deleteOneById(Integer id) {
        int num = chargeMapper.deleteByPrimaryKey(id);
        return num;
    }
}
