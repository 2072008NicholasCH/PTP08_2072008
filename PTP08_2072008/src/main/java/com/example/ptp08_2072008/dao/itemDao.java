package com.example.ptp08_2072008.dao;

import com.example.ptp08_2072008.model.ItemEntity;
import com.example.ptp08_2072008.model.KategoriEntity;
import com.example.ptp08_2072008.util.utilConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class itemDao implements interfaceDao<ItemEntity> {
    Session session;
    @Override
    public List<ItemEntity> getData() {
        List<ItemEntity> itemList;
        session = utilConnection.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(ItemEntity.class);
        query.from((ItemEntity.class));
        itemList = session.createQuery(query).getResultList();
        session.close();

        return itemList;
    }

    @Override
    public void setData(ItemEntity data) {
        session = utilConnection.getSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(data);
            t.commit();
        } catch (Exception e){
            t.rollback();
        }

        session.close();
    }

    @Override
    public void delData(ItemEntity data) {
        session = utilConnection.getSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(data);
            t.commit();
        } catch (Exception e){
            t.rollback();
        }
        session.close();
    }

    @Override
    public void upData(ItemEntity data) {
        session = utilConnection.getSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(data);
            t.commit();
        } catch (Exception e){
            t.rollback();
        }
        session.close();
    }
}