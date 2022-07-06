package main.java.JoinReducePhaseGenericWritable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import java.io.IOException;

public class JoinDriver {
    public static class JoinGroupingComparator extends WritableComparator {
        public JoinGroupingComparator() {
            super(JobKey.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            JobKey first = (JobKey) a;
            JobKey second = (JobKey) b;

            return first.job.compareTo(second.job);
        }
    }

    public static class JoinSortingComparator extends WritableComparator {
        public JoinSortingComparator() {
            super(JobKey.class, true);
        }

        @Override
        public int compare(WritableComparable a, WritableComparable b) {
            JobKey first = (JobKey) a;
            JobKey second = (JobKey) b;
            return first.compareTo(second);
        }
    }

    public static class SalaryMapper extends Mapper<LongWritable,
            Text, JobKey, JoinGenericWritable> {
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String[] recordFields = value.toString().split(",");
//            if (!recordFields[0].equals("job")) {
            try {
                String job = recordFields[0];
                int salary = Integer.parseInt(recordFields[1]);

                JobKey recordKey = new JobKey(job, JobKey.SALARY_RECORD);
                SalaryRecord record = new SalaryRecord(salary);

                JoinGenericWritable genericRecord = new JoinGenericWritable(record);
                context.write(recordKey, genericRecord);
            } catch (Exception e) {
                System.out.println("skip header");
            }
        }
    }

    public static class PeopleMapper extends Mapper<LongWritable,
            Text, JobKey, JoinGenericWritable> {
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String[] recordFields = value.toString().split(",");
//            if (!recordFields[0].equals("id")) {
            try {
                int id = Integer.parseInt(recordFields[0]);
                String firstName = recordFields[1];
                String lastName = recordFields[2];
                int age = Integer.parseInt(recordFields[3]);
                String street = recordFields[4];
                String city = recordFields[5];
                String state = recordFields[6];
                int zip = Integer.parseInt(recordFields[7]);
                String job = recordFields[8];

                JobKey recordKey = new JobKey(job, JobKey.PEOPLE_RECORD);
                PeopleRecord record = new PeopleRecord(id, firstName, lastName, age, street, city, state, zip);
                JoinGenericWritable genericRecord = new JoinGenericWritable(record);
                context.write(recordKey, genericRecord);
            } catch(Exception e) {
                System.out.println("skip header");
            }
        }
    }

    public static class JoinReducer extends Reducer<JobKey,
            JoinGenericWritable, NullWritable, Text> {
        StringBuilder output = new StringBuilder();

        public void setup(Reducer.Context context) throws IOException{
            output.append("id,first_name,last_name,age,street,city,state,zip,job,salary\n");
        }

        public void reduce(JobKey key, Iterable<JoinGenericWritable> values,
                           Context context) throws IOException, InterruptedException {
            String salary = "";
            for (JoinGenericWritable v : values) {
                Writable record = v.get();
                if (key.recordType.equals(JobKey.SALARY_RECORD)) {
                    SalaryRecord sRecord = (SalaryRecord) record;
                    salary = sRecord.salary.toString();
                } else {
                    PeopleRecord pRecord = (PeopleRecord) record;
                    output.append(Integer.parseInt(pRecord.id.toString())).append(",");
                    output.append(pRecord.firstName.toString()).append(",");
                    output.append(pRecord.lastName.toString()).append(",");
                    output.append(Integer.parseInt(pRecord.age.toString())).append(",");
                    output.append(pRecord.street.toString()).append(",");
                    output.append(pRecord.city.toString()).append(",");
                    output.append(pRecord.state.toString()).append(",");
                    output.append(Integer.parseInt(pRecord.zip.toString())).append(",");
                    output.append(key.job.toString()).append(",");
                    output.append(salary);
                    output.append("\n");
                }
            }
        }
        public void cleanup(Reducer.Context context) throws IOException, InterruptedException {
            context.write(NullWritable.get(), new Text(output.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf,"JoinReducePhaseGenericWritable");
        job.setJarByClass(JoinDriver.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(JobKey.class);
        job.setMapOutputValueClass(JoinGenericWritable.class);
        FileSystem fs = FileSystem.get(conf);

        MultipleInputs.addInputPath(job, new Path(args[0]),
                TextInputFormat.class, SalaryMapper.class);

        MultipleInputs.addInputPath(job, new Path(args[1]),
                TextInputFormat.class, PeopleMapper.class);
        job.setReducerClass(JoinReducer.class);
        job.setSortComparatorClass(JoinSortingComparator.class);
        job.setGroupingComparatorClass(JoinGroupingComparator.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        if (fs.exists(new Path(args[2]))) {
            fs.delete(new Path(args[2]), true);
        }
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        System.exit(job.waitForCompletion(true) ? 0:1);
    }

}
