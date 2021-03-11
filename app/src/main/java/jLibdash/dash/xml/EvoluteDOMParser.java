package jLibdash.dash.xml;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Optional;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import jLibdash.dash.helpers.PathUtils;


public class EvoluteDOMParser {
	
	private DASHNode dashNode;
	private String url;
	private Optional<InputStream> inputStream;
	
	public EvoluteDOMParser(String url) {
		this.url = url;
		inputStream = Optional.empty();
	}
	
	public EvoluteDOMParser(String url, InputStream inputStream) {
		this.url = url;
		this.inputStream = Optional.of(inputStream);
	}
	
	public void parse() throws DocumentException {
		
		SAXReader reader = new SAXReader();
		Document document = null;
		
		if(inputStream.isPresent())
			document = reader.read(this.inputStream.get());
		else
			document = reader.read(this.url);
		
		Element root = document.getRootElement();
		
		this.dashNode = loadNodes(root);
	}
	
	private DASHNode loadNodes(Element root) {
		
		DASHNode mpdNode = new DASHNode(
				root.getName(), root.getTextTrim(), root.getNodeType(), PathUtils.getDirectoryPath(url));
		
		Attribute attribute;
		Iterator<Attribute> attributeIterator = root.attributeIterator();
		while(attributeIterator.hasNext()) {
			attribute = attributeIterator.next();
			mpdNode.addAttribute(attribute.getName(), attribute.getValue());
		}
		
		Iterator<Element> iterator = root.elementIterator();
		while(iterator.hasNext())
			mpdNode.addSubNode(loadNodes(iterator.next()));
		
		return mpdNode;
		
	}
	
	public DASHNode getRootNode() {
		return this.dashNode;
	}

}
