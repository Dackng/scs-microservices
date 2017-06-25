package com.unmsm.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.unmsm.catalog.Catalog;
import com.unmsm.catalog.CatalogRepository;
import com.unmsm.catalog.FieldName;
import com.unmsm.catalog.FieldValue;
import com.unmsm.catalog.PrimaryGroup;

@Service
public class CatalogService {

	private CatalogRepository catalogRepository;
	
	@Autowired	
	public CatalogService(CatalogRepository catalogRepository){
		this.catalogRepository = catalogRepository;
	}
	
	public Catalog findElementByPrimaryIdAndSecondaryId(PrimaryGroup primaryId, Integer secondaryId){
		return catalogRepository.findElementByPrimaryIdAndSecondaryId(primaryId.getValue(), secondaryId);
	}
	
	public List<Catalog> getElementsListByPrimaryId(PrimaryGroup primaryId){
		return catalogRepository.findElementsListByPrimaryId(primaryId.getValue()
				,sortByIdAsc(FieldName.SECONDARY_ID.getValue()));
	}
	
	public Catalog findCurrentHealthPlan(PrimaryGroup primaryId){
		return catalogRepository.findElementByPrimaryIdAndState(primaryId.getValue(), FieldValue.ACTIVE.getValue());
	}
	
	public List<Character> getGenderElementsList(){
		List<Character> list = new ArrayList<Character>();
		list.add(FieldValue.MALE.getValue());
		list.add(FieldValue.FEMALE.getValue());
		return list;
	}
	
	public Catalog getElementOfPrimaryGroupByStateIndex(PrimaryGroup primaryId, Integer stateIndex){
		List<Catalog> list = catalogRepository.findElementsListByPrimaryId(primaryId.getValue(), 
				sortByIdAsc(FieldName.SECONDARY_ID.getValue()));
		return list != null ? list.get(stateIndex) : null; 
	}
	
	private Sort sortByIdAsc(String catalogNameField) {
        return new Sort(Sort.Direction.ASC, catalogNameField);
    }
}
