package com.example.ptp08_2072008.dao;

import com.example.ptp08_2072008.model.ItemEntity;
import com.example.ptp08_2072008.model.KategoriEntity;
import com.example.ptp08_2072008.util.utilConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class categoryDao implements interfaceDao <KategoriEntity>{
    Session session;
    @Override
    public List<KategoriEntity> getData() {
        List<KategoriEntity> katList;
        session = utilConnection.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(KategoriEntity.class);
        query.from((KategoriEntity.class));
        katList = session.createQuery(query).getResultList();
        session.close();

        return katList;
    }

    @Override
    public void setData(KategoriEntity data) {
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
    public void delData(KategoriEntity data) {
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
    public void upData(KategoriEntity data) {
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
