//package com.journalapp.JournalApp.scheduler;
//
//import com.journalapp.JournalApp.entities.JournalEntry;
//import com.journalapp.JournalApp.entities.User;
////import com.journalapp.JournalApp.enums.Sentiment;
//import com.journalapp.JournalApp.enums.Sentiment;
//import com.journalapp.JournalApp.repositories.UserRepositoryImpl;
//import com.journalapp.JournalApp.services.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class UserScheduler {
//
//    @Autowired
//    EmailService emailService;
//
//
//    @Autowired
//    UserRepositoryImpl userRepository;
//
//
//@Scheduled(cron="0 0 9 * * SUN")
//void fetchUserAndSendSaMail()
//{
//    List<User> users = userRepository.getUserForSA();
//    for(User user : users)
//    {
//        List<JournalEntry> journalEntries = user.getJournalEntries();
////        List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate()
////                 .isAfter(LocalDateTime.now().minusDays(7)))
////                .map(JournalEntry::getSentiment)
////                .toList();
//
////        Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
////        for(Sentiment sentiment : sentiments)
////        {
////            if(sentiment != null)
////            {
////                sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0)+1);
////            }
////        }
//
////        Sentiment mostFrequentSentiment = null;
//        int maxCount = 0;
//
////        for(Map.Entry<Sentiment, Integer>  entry : sentimentCounts.entrySet())
//        {
////            if(entry.getValue() > maxCount)
////            {
////                maxCount = entry.getValue();
//////                mostFrequentSentiment = entry.getKey();
////            }
//        }
//
////        if(mostFrequentSentiment != null)
////        {
////            emailService.sendMail("vatsshubham87@gmail.com", "sentiment for last 7 days", mostFrequentSentiment.toString());
////        }
//    }
//
//}
//
//}
