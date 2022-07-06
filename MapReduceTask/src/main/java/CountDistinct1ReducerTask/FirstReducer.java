package main.java.CountDistinct1ReducerTask;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FirstReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private Text word = new Text("1");
    private IntWritable result = new IntWritable();

    private int count = 0;

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        count += 1;
    }

    @Override
    protected void cleanup(Reducer.Context context) throws IOException, InterruptedException {
        context.write(new Text("0"), new IntWritable(count));
    }
}