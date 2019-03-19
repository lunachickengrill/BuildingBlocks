package com.components.xmlservlet.service;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MapEntryConverter implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return Map.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Map<String, String> map = (Map<String, String>) source;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			writer.startNode(entry.getKey().toString());
			writer.setValue(entry.getValue().toString());
			writer.endNode();
		}

	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Map<String, String> map = new HashMap<>();
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			map.put(reader.getNodeName(), new String(reader.getValue()));
			reader.moveUp();
		}

		return map;
	}

}
