package com.bangmaple.daos;

import com.bangmaple.entities.Rules;

import javax.annotation.ManagedBean;
import javax.persistence.*;

import java.util.List;

@ManagedBean
public class ModSecRulesDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ModSecRulesPU");

    public void insertRule(Rules entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(entity);
            et.commit();
        } catch (PersistenceException e) {
            et.rollback();
        }
    }
    
    public Rules get(int id) {
        return emf.createEntityManager().find(Rules.class, id);
    }

    public List<Rules> getAll() {
        return emf.createEntityManager().createNativeQuery("SELECT * FROM rules").getResultList();
    }

    public List<Rules> getBriefRules() {
        return emf.createEntityManager().createNativeQuery("SELECT id, rule_name, rule_description FROM rules").getResultList();
    }

    public Rules getRule(int id) {
        return emf.createEntityManager().find(Rules.class, id);
    }
}
