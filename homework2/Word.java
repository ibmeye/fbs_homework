

import java.io.Serializable;
import java.util.List;

class Word implements Serializable{
	private String word_name;
	private List<Symbol> symbols;
	public String getWord_name() {
		return word_name;
	}
	public void setWord_name(String word_name) {
		this.word_name = word_name;
	}
	public List<Symbol> getSymbols() {
		return symbols;
	}
	public void setSymbols(List<Symbol> symbols) {
		this.symbols = symbols;
	}
	
	
}

class Symbol implements Serializable{
	List<Part> parts;

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
	
}

class Part implements Serializable{
	String part;
	List<String> means;
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getMeans() {
		StringBuilder sb = new StringBuilder();
		for (String str : means) {
			sb.append(str);
			sb.append(";");
		}
		return sb.toString();
	}
	public void setMeans(List<String> means) {
		this.means = means;
	}
	
}
