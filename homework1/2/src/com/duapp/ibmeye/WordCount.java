package com.duapp.ibmeye;

import java.io.IOException;
import java.util.StringTokenizer;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class WordCount extends Configured implements Tool {
	
	public static class WordCountMap extends Mapper<LongWritable, Text, Text, IntWritable> {
		private final IntWritable one = new IntWritable(1);
		
		private Text word = new Text();
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			StringTokenizer token = new StringTokenizer(line);
			while( token.hasMoreTokens() ) {
				word.set(token.nextToken());
				context.write(word, one);
			}
		}
	}
	
	public static class WordCountReduce extends Reducer<Text,IntWritable,Text,IntWritable> {
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			
			int sum = 0;
			for ( IntWritable val : values ) {
				sum += val.get();
			}
			context.write(key,new IntWritable(sum));
		}
	}

	@Override
	public int run(String[] args) throws Exception {
		if( args.length != 2 ) {
			System.err.printf("Usage: %s [generic options] <input path> <output path>\n", getClass().getSimpleName() );
			ToolRunner.printGenericCommandUsage(System.err);
			return -1;
		}
		
		@SuppressWarnings("deprecation")
		Job job = new Job(getConf(), "Word Count");
		
		job.setJarByClass(getClass());
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(WordCountMap.class);
		
		job.setNumReduceTasks(1);
		job.setReducerClass(WordCountReduce.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		return job.waitForCompletion(true) ? 0:1;
	}
	
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new WordCount(), args);
		System.exit(exitCode);
	}



}
