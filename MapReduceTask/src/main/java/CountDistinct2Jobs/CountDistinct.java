package main.java.CountDistinct2Jobs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CountDistinct {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job1 = Job.getInstance(conf, "CountDistinct1");

        job1.setJarByClass(CountDistinct.class);
        job1.setMapperClass(FirstMapper.class);
        job1.setCombinerClass(FirstReducer.class);
        job1.setNumReduceTasks(3);
        job1.setReducerClass(FirstReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileSystem fs = FileSystem.get(conf); // delete file output when it exists
        if (fs.exists(new Path(args[1]))) {
            fs.delete(new Path(args[1]), true);
        }
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));

        Job job2 = Job.getInstance(conf, "CountDistinct2");
        job2.setJarByClass(CountDistinct.class);
        job2.setMapperClass(SecondMapper.class);
        job2.setCombinerClass(SecondReducer.class);
        job2.setReducerClass(SecondReducer.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(IntWritable.class);
        job2.getConfiguration().set("mapreduce.output.textoutputformat.separator",",");
        FileInputFormat.addInputPath(job2, new Path(args[1]));
        if (fs.exists(new Path(args[2]))) {
            fs.delete(new Path(args[2]), true);
        }
        FileOutputFormat.setOutputPath(job2, new Path((args[2])));
        if (job1.waitForCompletion(true)) System.exit(job2.waitForCompletion(true) ? 0:1);
    }
}
