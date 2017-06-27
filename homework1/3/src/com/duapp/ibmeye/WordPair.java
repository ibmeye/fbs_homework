package com.duapp.ibmeye;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class WordPair implements WritableComparable<WordPair>{
	private Text first;
	private IntWritable second;
	
	public WordPair() {
		set( new Text(), new IntWritable());
	}
	
	public WordPair( Text first, IntWritable second) {
		set(first, second);
	}
	public void set( Text first, IntWritable second) {
		this.first = first;
		this.second = second;
	}

	public Text getFirst() {
		return first;
	}

	public IntWritable getSecond() {
		return second;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		first.readFields(in);
		second.readFields(in);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		first.write(out);
		second.write(out);
	}

	@Override
	public String toString() {
		return first + "\t" + second;
	}
	
	@Override
	public int compareTo(WordPair wp) {
		int cmp = first.compareTo(wp.first);
		if( cmp != 0 ) {
			return cmp;
		}
		return second.compareTo(wp.second);
	}
	
	@Override
	public boolean equals(Object o) {
		if( o instanceof WordPair ) {
			WordPair wp = (WordPair) o;
			return first.equals(wp.first) && second.equals(wp.second);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return first.hashCode() * 163 + second.hashCode();
	}
}
