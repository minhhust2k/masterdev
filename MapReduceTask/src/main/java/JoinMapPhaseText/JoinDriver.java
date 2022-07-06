package main.java.JoinMapPhaseText;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

public class JoinDriver {
    public static class PeopleMapper extends Mapper<LongWritable,
            Text, IntWritable, Text> {
        private HashMap<String, Integer> salaryMaps = new HashMap<String, Integer>();
        private void readSalaryFile(BufferedReader bufferedReader) throws IOException{
//            List<String> lines = FileUtils.readLines(new File(uri));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] recordFields = line.split(",");
                if (!recordFields[0].equals("job")) {
                    String job = recordFields[0];
                    int salary = Integer.parseInt(recordFields[1]);
                    salaryMaps.put(job, salary);
                }
                bufferedReader.readLine();
            }
        }

        public void setup(Context context) throws IOException{
            URI[] uris = context.getCacheFiles();
            FSDataInputStream dataIn = FileSystem.get(context.getConfiguration()).open(new Path(uris[0]));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataIn));
            readSalaryFile(bufferedReader);
        }

        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String[] recordFields = value.toString().split(",");
            if (!recordFields[0].equals("id")) {
                int id = Integer.parseInt(recordFields[0]);
                String firstName = recordFields[1];
                String lastName = recordFields[2];
                int age = Integer.parseInt(recordFields[3]);
                String street = recordFields[4];
                String city = recordFields[5];
                String state = recordFields[6];
                int zip = Integer.parseInt(recordFields[7]);
                String job = recordFields[8];

                PeopleRecord record = new PeopleRecord(id, firstName, lastName, age, street, city, state, zip, job, salaryMaps.get(job));
                context.write(new IntWritable(id), new Text(record.toString()));
            }
        }
    }
    public static class JoinReducer extends Reducer
            <IntWritable, Text, NullWritable, Text> {
        StringBuilder output = new StringBuilder();

        public void setup(Reducer.Context context) throws IOException{
            output.append("id,first_name,last_name,age,street,city,state,zip,job,salary\n");
        }

        public void reduce(IntWritable key, Iterable<Text> values,
                           Context context) throws IOException, InterruptedException{
            for (Text v : values) {
                output.append(v.toString()).append("\n");
            }
        }
        public void cleanup(Reducer.Context context) throws IOException, InterruptedException {
            context.write(NullWritable.get(), new Text(output.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "JoinMapPhaseText");
        job.setJarByClass(JoinDriver.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setMapperClass(PeopleMapper.class);
        FileSystem fs = FileSystem.get(conf);

        FileInputFormat.addInputPath(job, new Path(args[1]));
        job.addCacheFile(new URI(args[0]));
        job.setReducerClass(JoinReducer.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        if (fs.exists(new Path(args[2]))) {
            fs.delete(new Path(args[2]), true);
        }
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
