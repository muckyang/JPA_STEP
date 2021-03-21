package com.jpa.step.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
<<<<<<< HEAD
    private final EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
=======
    private EntityManager em;
    Logger logger = LoggerFactory.getLogger(this.getClass());

>>>>>>> chapter05

    public SpringConfig(EntityManager em) {
        logger.debug("make EntityManager ");
        this.em = em;
    }


}
