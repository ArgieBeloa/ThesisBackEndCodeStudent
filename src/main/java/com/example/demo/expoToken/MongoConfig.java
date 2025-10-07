package com.example.demo.expoToken;//package com.example.demo.expoToken;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
//
//import java.util.Arrays;
//
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//    @Override
//    protected String getDatabaseName() {
//        return "capstonedb"; // change to your db name
//    }
//
//    @Override
//    public MongoCustomConversions customConversions() {
//        return new MongoCustomConversions(Arrays.asList(
//                new OffsetDateTimeToDateConverter(),
//                new DateToOffsetDateTimeConverter()
//        ));
//    }
//
//    // Other config (MongoClient bean etc.) if needed
//}
