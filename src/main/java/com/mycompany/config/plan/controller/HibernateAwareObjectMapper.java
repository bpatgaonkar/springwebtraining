package com.mycompany.config.plan.controller;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {
	@Autowired
	SessionFactory sessionFactory;
	
    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module(sessionFactory);
        disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        registerModule(hm);
    }
}