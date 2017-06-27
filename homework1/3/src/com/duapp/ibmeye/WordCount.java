package com.duapp.ibmeye;

import java.io.IOException;
import java.util.StringTokenizer;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.examples.SecondarySort.IntPair;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;



public class WordCount extends Configured implements Tool {
	
	public static class WordCountMap extends Mapper<LongWritable, Text, WordPair, IntWritable> {
		private final IntWritable one = new IntWritable(1);
		
		private IntWritable count = new IntWritable();
		private Text word = new Text();
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text,  WordPair, IntWritable>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			StringTokenizer token = new StringTokenizer(line);
			
			
			String tmp = token.nextToken();
			if( tmp.equals("a") || tmp.equals("the") || tmp.equals("an") ) {
				return;
			}
			else {
				word.set( tmp );
				count.set( Integer.parseInt( token.nextToken() ) );
				
				context.write(new WordPair(word,count),count);
			}
		}
	}
	
	
	public static class KeyComparator extends WritableComparator {
		protected KeyComparator() {
			super( WordPair.class, true);
		}
		@Override
		public int compare(WritableComparable w1, WritableComparable w2) {
			WordPair wp1 = (WordPair) w1;
			WordPair wp2 = (WordPair) w2;
			int cmp = wp1.getSecond().compareTo(wp2.getSecond());
			if( cmp != 0 ) {
				return -cmp;
			}
			return wp1.getFirst().compareTo(wp2.getFirst());
		}
	}

	public static class WordCountReduce extends Reducer<WordPair,IntWritable,WordPair,IntWritable> {
		private static int sum = 0;
		@Override
		protected void reduce(WordPair wp, Iterable<IntWritable> counts,
				Reducer<WordPair, IntWritable, WordPair, IntWritable>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			
			for (IntWritable val : counts) {
				sum += val.get();
			}
			context.write(wp, new IntWritable(sum));
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
		Job job = new Job(getConf(), "Descending Order");
		
		job.setJarByClass(getClass());
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(WordCountMap.class);
		
		job.setSortComparatorClass(KeyComparator.class);
		
		job.setNumReduceTasks(1);
		
		job.setReducerClass(WordCountReduce.class);
		
		job.setOutputKeyClass(WordPair.class);
		job.setOutputValueClass(IntWritable.class);
		
		return job.waitForCompletion(true) ? 0:1;
	}
	
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new WordCount(), args);
		System.exit(exitCode);
	}



}
