//package com.journalapp.JournalApp.services;
//
//import com.journalapp.JournalApp.entities.User;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//
//import java.util.stream.Stream;
//
//public class UserArgumentsProvider implements ArgumentsProvider {
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
//        return Stream.of(
//                Arguments.of(User.builder().username("shyam").password("shyam").build()),
//                Arguments.of(User.builder().username("suraj").password("").build()));
//    }
//}
