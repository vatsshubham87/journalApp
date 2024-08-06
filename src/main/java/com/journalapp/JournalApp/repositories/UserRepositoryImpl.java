package com.journalapp.JournalApp.repositories;

import com.journalapp.JournalApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;


   public List<User>  getUserForSA()
    {
        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is("ram"));
        query.addCriteria(Criteria.where("email").ne(null).ne(""));
//        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        query.addCriteria(Criteria.where("userName").nin("shyam"));
        return mongoTemplate.find(query, User.class);
    }

}
