package org.paulushc.utils;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Component;

@Component
public class EntityManagerUtils {

    @Autowired
    @Qualifier("mainEntityManager")
    private EntityManager mainDatabase;

    @Autowired
    @Qualifier("secondEntityManager")
    private EntityManager secondDatabase;

    @Autowired
    @Qualifier("thirdEntityManager")
    private EntityManager thirdDatabase;

    public EntityManager getEm(String url){

        if(url.contains("main"))
            return mainDatabase;
        if(url.contains("second"))
            return secondDatabase;
        if(url.contains("third"))
            return thirdDatabase;
        return mainDatabase;
    }

    public JpaRepositoryFactory getJpaFactory(String url){
        return new JpaRepositoryFactory( getEm(url) );
    }

}
