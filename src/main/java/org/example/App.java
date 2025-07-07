package org.example;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.*;

import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Hello world!
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
        SpringApplication.run(App.class, args);

        // Создаем конфигурацию Hadoop

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "Word Count");

// Устанавливаем главный класс (Driver)

        job.setJarByClass(App.class);

// Устанавливаем Mapper и Reducer

        job.setMapperClass(TestMapper.class);

        job.setReducerClass(TestReducer.class);

// Устанавливаем типы выходных данных для Mapper и Reducer

        job.setOutputKeyClass(Text.class);

        job.setOutputValueClass(IntWritable.class);
        job.setOutputFormatClass(TextOutputFormat.class);

// Устанавливаем путь к входным и выходным данным

        FileInputFormat.addInputPath(job, new Path(
                SpringBootServletInitializer.class.getClassLoader().getResource("input.txt").toString()
        ));





        //conf.set("fs.defaultFS","hdfs://localhost:9000");
       // FSDataOutputStream fs = FileSystem.get(conf)
          //      .create(new Path("/test/output.txt"), true);
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/test/output"));

                //new Path(
                //SpringBootServletInitializer.class.getClassLoader().getResource("").toString() +"/output"
        //)



        int r = job.waitForCompletion(true) ? 0 : 1;

        if(job.isSuccessful()){
            System.out.println("Yes");
            //FileSystem hdfs = FileSystem.get(new URI("http://localhost:9000"), conf);

        } else{
            System.out.println("No");
        }
    }
}
