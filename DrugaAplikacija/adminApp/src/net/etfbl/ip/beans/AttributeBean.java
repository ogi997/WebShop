package net.etfbl.ip.beans;

import java.io.Serializable;
import java.util.List;

import net.etfbl.ip.dao.AttributeDAO;
import net.etfbl.ip.dto.Attribute;

public class AttributeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AttributeBean() {}
	
	public List<Attribute> getAllAttributes(Integer categoryId) {
		return AttributeDAO.selectAll(categoryId);
	}
	public int addNewAttribute(Attribute attribute) {
		return AttributeDAO.insert(attribute);
	}
	public Attribute getAttributeId(Integer id) {
		return AttributeDAO.getAttributeById(id);
	}
	public int updateAttribute(Attribute attribute) {
		return AttributeDAO.update(attribute);
	}
	public int deleteAttribute(Attribute attribute) {
		return AttributeDAO.delete(attribute);
	}
}
